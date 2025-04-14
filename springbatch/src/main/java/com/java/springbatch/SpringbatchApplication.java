package com.java.springbatch;

import com.java.springbatch.Entity.Company;
import com.java.springbatch.Entity.CompanyRating;
import com.java.springbatch.Repository.CompanyRatingRepository;
import com.java.springbatch.Repository.CompanyRepository;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Random;

@SpringBootApplication
@EntityScan(basePackages="com.java.springbatch.Entity")
@EnableScheduling
public class SpringbatchApplication  {
	private final Random random = new Random();

	private final CompanyRepository companyRepository;
	private final CompanyRatingRepository companyRatingRepository;

	public SpringbatchApplication(CompanyRepository companyRepository, CompanyRatingRepository companyRatingRepository) {
		this.companyRepository = companyRepository;
		this.companyRatingRepository = companyRatingRepository;
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringbatchApplication.class, args);
	}

//	@Override
//	public void run(String... args) {
//		for (int i = 1001; i <= 5000; i++) {
//			Company company = new Company();
//			company.setCompanyName("Company " + i);
//			company.setIndustry(randomIndustry());
//			company.setCeo("CEO " + i);
//			company.setHeadquarters("HQ " + i);
//			company.setFoundedYear(1950 + random.nextInt(73));
//			company.setEmployees(100 + random.nextInt(5000));
//			company.setRevenue(random.nextDouble() * 10000);
//			company.setWebsite("https://company" + i + ".com");
//			company.setEmail("contact@company" + i + ".com");
//			company.setPhone("+91 98765" + (1000 + random.nextInt(9000)));
//
//			// Additional fields
//			company.setCountry("Country " + i);
//			company.setCity("City " + i);
//			company.setState("State " + i);
//			company.setZipCode("ZIP-" + (10000 + random.nextInt(90000)));
//			company.setBusinessType(randomBusinessType());
//			company.setMarketCap("$" + (random.nextInt(500) + "M"));
//			company.setStockSymbol("SYM" + i);
//			company.setOwnershipType(randomOwnershipType());
//			company.setTaxId("TAX" + i);
//			company.setPubliclyTraded(random.nextBoolean());
//			company.setAnnualProfit("$" + random.nextInt(1000) + "M");
//			company.setCustomerBase(random.nextInt(10000) + " customers");
//			company.setSupplierCount(random.nextInt(500) + " suppliers");
//			company.setSubsidiaryCompanies(random.nextInt(5) + " subsidiaries");
//			company.setMainProducts("Product " + i);
//			company.setMainServices("Service " + i);
//			company.setEmployeeSatisfactionScore(random.nextInt(100) + "%");
//			company.setTurnoverRate(random.nextInt(30) + "%");
//			company.setrAndDBudget("$" + random.nextInt(500) + "M");
//			company.setMarketingBudget("$" + random.nextInt(300) + "M");
//			company.setSocialMediaPresence(random.nextBoolean() ? "High" : "Low");
//			company.setEnvironmentalImpact(random.nextBoolean() ? "Low" : "Moderate");
//			company.setSustainabilityScore(random.nextInt(100) + "%");
//			company.setBrandReputation(random.nextBoolean() ? "Excellent" : "Good");
//			company.setCybersecurityMeasures(random.nextBoolean() ? "Advanced" : "Basic");
//			company.setLegalIssues(random.nextBoolean() ? "None" : "Few");
//			company.setLaborLawsCompliance(random.nextBoolean() ? "Yes" : "No");
//			company.setTaxCompliance(random.nextBoolean() ? "Compliant" : "Issues");
//			company.setInternationalOffices(random.nextInt(10) + " offices");
//			company.setMajorClients(random.nextInt(50) + " clients");
//			company.setPartnerships(random.nextInt(10) + " partnerships");
//			company.setLogisticsEfficiency(random.nextBoolean() ? "High" : "Medium");
//			company.setSupplyChainDiversity(random.nextBoolean() ? "Diverse" : "Limited");
//			company.setCrisisManagement(random.nextBoolean() ? "Excellent" : "Needs Improvement");
//			company.setExpansionPlans(random.nextBoolean() ? "Yes" : "No");
//			company.setProductQualityScore(random.nextInt(100) + "%");
//			company.setServiceQualityScore(random.nextInt(100) + "%");
//			company.setAverageCustomerRating(random.nextInt(5) + " stars");
//			company.setTrainingPrograms(random.nextBoolean() ? "Available" : "Not Available");
//			company.setWorkforceDiversity(random.nextInt(100) + "%");
//
//			// Save company
//			Company savedCompany = companyRepository.save(company);
//
//			// Create CompanyRating
//			CompanyRating rating = new CompanyRating();
//			rating.setRating(1 + random.nextDouble() * 4);
//			rating.setReviews(10 + random.nextInt(500));
//			rating.setCompany(savedCompany);
//
//			// Save rating
//			companyRatingRepository.save(rating);
//		}
//		System.out.println(" 1000 Companies & Ratings Generated Successfully!");
//	}
//
//	private String randomIndustry() {
//		String[] industries = {"Tech", "Finance", "Healthcare", "Retail", "Manufacturing"};
//		return industries[random.nextInt(industries.length)];
//	}
//
//	private String randomBusinessType() {
//		String[] types = {"B2B", "B2C", "E-commerce", "Manufacturing", "Consulting"};
//		return types[random.nextInt(types.length)];
//	}
//
//	private String randomOwnershipType() {
//		String[] types = {"Private", "Public", "Government-Owned", "Non-Profit"};
//		return types[random.nextInt(types.length)];
//	}
}

