package com.ManyToMany.Mapping.Service;

import com.ManyToMany.Mapping.entity.Course;
import com.ManyToMany.Mapping.entity.Person;
import com.ManyToMany.Mapping.repository.CourseRepository;
import com.ManyToMany.Mapping.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CourseRepository courseRepository;
    public ResponseEntity createNewPerson(Person person){
        Person existingPerson = personRepository.findByName(person.getName());
        List<Course> updatedCourses;
        if (existingPerson == null) {
            System.out.println("new Person");
            existingPerson = new Person();
            existingPerson.setName(person.getName());
            updatedCourses= new ArrayList<>();
            existingPerson.setCourses(updatedCourses);
        }
        else{
            updatedCourses=existingPerson.getCourses();
        }
        for (Course course : person.getCourses()) {

        Course existingCourse = courseRepository.findByCourseName(course.getCourseName());

        if (existingCourse != null) {
            if(!updatedCourses.contains(existingCourse)){
            updatedCourses.add(existingCourse);}
        } else {
            existingCourse=courseRepository.save(course);
            updatedCourses.add(existingCourse);
        }
        existingCourse.getRegisteredPerson().add(existingPerson);

    }

        existingPerson.setCourses(updatedCourses);
        personRepository.save(existingPerson);
        return new ResponseEntity(existingPerson,HttpStatus.OK);
    }
}

