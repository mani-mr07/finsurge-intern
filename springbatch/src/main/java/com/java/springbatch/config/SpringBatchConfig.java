package com.java.springbatch.config;

import com.java.springbatch.Entity.Student;
import com.java.springbatch.Repository.StudentRepository;
import org.springframework.batch.core.*;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.sql.DataSource;
import java.util.Collections;

@Configuration
@EnableTransactionManagement

public class SpringBatchConfig {

    private final StudentRepository studentRepository;
    private final DataSource dataSource;

    public SpringBatchConfig(StudentRepository studentRepository, DataSource dataSource) {
        this.studentRepository = studentRepository;
        this.dataSource = dataSource;

    }

    @Bean
    public JobRepository jobRepository(PlatformTransactionManager transactionManager) throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTransactionManager(transactionManager);
        factory.afterPropertiesSet();
        return factory.getObject();
    }


    @Bean
    public PlatformTransactionManager transactionManager() {
        return new org.springframework.orm.jpa.JpaTransactionManager();
    }


    @Bean(name="reader")
    public ItemReader<Student> reader() {
        FlatFileItemReader<Student> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/sample_entities.csv"));
        itemReader.setName("excelReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        System.out.println("Reader works properly");
        return itemReader;
//        return new ExcelItemReader("src/main/resources/students.xlsx");

    }

    public LineMapper<Student> lineMapper() {
        DefaultLineMapper<Student> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(",");
        tokenizer.setStrict(false);
        tokenizer.setNames("id","name","email","age","address");

        BeanWrapperFieldSetMapper<Student> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Student.class);

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    @Bean
    public StudentProcessor processor() {
        return new StudentProcessor();
    }
    @Bean(name="readingProcessor")
    public StudentWriterProcessor writerProcessor(){
        return new StudentWriterProcessor();
    }


    @Bean(name="writer")
    public RepositoryItemWriter<Student> writer() {
        RepositoryItemWriter<Student> writer = new RepositoryItemWriter<>();
        writer.setRepository(studentRepository);
        writer.setMethodName("save");
        System.out.println("writer performs successfully");
        return writer;
    }


        @Bean(name = "step1")
        public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws Exception {
        return new StepBuilder("step1",jobRepository)
            .<Student, Student>chunk(10,transactionManager)
            .reader(reader())
            .processor(processor())
            .writer(writer())
                .taskExecutor(taskExecutor())
            .build();
        }

        @Bean(name="databaseReader")
        public RepositoryItemReader<Student> databaseReader(StudentRepository studentRepository) {
            RepositoryItemReader<Student> reader = new RepositoryItemReader<>();
            reader.setRepository(studentRepository);
            reader.setMethodName("findAll");
            reader.setSort(Collections.singletonMap("id", Sort.Direction.ASC));
            return reader;
        }
        @Bean(name="step2")
        public Step step2(JobRepository jobRepository,PlatformTransactionManager transactionManager,ExcelItemReader excelItemReader,RepositoryItemReader<Student> databaseReader)throws Exception{
            System.out.println("STEP2 IS WORKING CORRECTLY");
            return new StepBuilder("step2", jobRepository)
                    .<Student,Student>chunk(20,transactionManager)
                    .reader(databaseReader)
                    .processor(writerProcessor())
                    .writer(excelItemReader)
                    .taskExecutor(taskExecutor())
                    .build();
        }


        @Bean(name="importStudent")
        public Job runJob(@Qualifier("step1")Step step1, JobRepository jobRepository) {
            return new JobBuilder("importStudent",jobRepository)
                     .incrementer(new RunIdIncrementer())
                    .start(step1)
                .build();
        }
    @Bean(name="writeToExcelJob")
    public Job writeToExcelJob(JobRepository jobRepository, @Qualifier("step2")Step step2) {
        System.out.println("JOBWRITER IS WORKING");
        return new JobBuilder("writeToExcelJob", jobRepository)
                .incrementer(new RunIdIncrementer()) // Ensure a new job instance
                .start(step2)
                .build();
    }

        @Bean
        public TaskExecutor taskExecutor(){
            SimpleAsyncTaskExecutor asyncTaskExecutor=new SimpleAsyncTaskExecutor();
            asyncTaskExecutor.setConcurrencyLimit(10);
            return asyncTaskExecutor;
        }



}
