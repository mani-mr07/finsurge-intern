package com.java.springbatch.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "company")
@Getter
@Setter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String industry;
    private String ceo;
    private String headquarters;
    private int foundedYear;
    private int employees;
    private double revenue;
    private String website;
    private String email;
    private String phone;

    private String country;
    private String city;
    private String state;
    private String zipCode;
    private String businessType;
    private String marketCap;
    private String stockSymbol;
    private String ownershipType;
    private String taxId;
    private boolean publiclyTraded;

    private String annualProfit;
    private String customerBase;
    private String supplierCount;
    private String subsidiaryCompanies;
    private String mainProducts;
    private String mainServices;
    private String employeeSatisfactionScore;
    private String turnoverRate;
    private String rAndDBudget;
    private String marketingBudget;

    private String socialMediaPresence;
    private String environmentalImpact;
    private String sustainabilityScore;
    private String brandReputation;
    private String cybersecurityMeasures;
    private String legalIssues;
    private String laborLawsCompliance;
    private String taxCompliance;
    private String internationalOffices;
    private String majorClients;

    private String partnerships;
    private String logisticsEfficiency;
    private String supplyChainDiversity;
    private String crisisManagement;
    private String expansionPlans;
    private String productQualityScore;
    private String serviceQualityScore;
    private String averageCustomerRating;
    private String trainingPrograms;
    private String workforceDiversity;

    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
    private CompanyRating companyRating;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public int getFoundedYear() {
        return foundedYear;
    }

    public void setFoundedYear(int foundedYear) {
        this.foundedYear = foundedYear;
    }

    public int getEmployees() {
        return employees;
    }

    public String getMajorClients() {
        return majorClients;
    }

    public void setMajorClients(String majorClients) {
        this.majorClients = majorClients;
    }

    public String getPartnerships() {
        return partnerships;
    }

    public void setPartnerships(String partnerships) {
        this.partnerships = partnerships;
    }

    public String getLogisticsEfficiency() {
        return logisticsEfficiency;
    }

    public void setLogisticsEfficiency(String logisticsEfficiency) {
        this.logisticsEfficiency = logisticsEfficiency;
    }

    public String getSupplyChainDiversity() {
        return supplyChainDiversity;
    }

    public void setSupplyChainDiversity(String supplyChainDiversity) {
        this.supplyChainDiversity = supplyChainDiversity;
    }

    public String getCrisisManagement() {
        return crisisManagement;
    }

    public void setCrisisManagement(String crisisManagement) {
        this.crisisManagement = crisisManagement;
    }

    public String getProductQualityScore() {
        return productQualityScore;
    }

    public void setProductQualityScore(String productQualityScore) {
        this.productQualityScore = productQualityScore;
    }

    public String getExpansionPlans() {
        return expansionPlans;
    }

    public void setExpansionPlans(String expansionPlans) {
        this.expansionPlans = expansionPlans;
    }

    public String getServiceQualityScore() {
        return serviceQualityScore;
    }

    public void setServiceQualityScore(String serviceQualityScore) {
        this.serviceQualityScore = serviceQualityScore;
    }

    public String getAverageCustomerRating() {
        return averageCustomerRating;
    }

    public void setAverageCustomerRating(String averageCustomerRating) {
        this.averageCustomerRating = averageCustomerRating;
    }

    public String getTrainingPrograms() {
        return trainingPrograms;
    }

    public void setTrainingPrograms(String trainingPrograms) {
        this.trainingPrograms = trainingPrograms;
    }

    public String getWorkforceDiversity() {
        return workforceDiversity;
    }

    public void setWorkforceDiversity(String workforceDiversity) {
        this.workforceDiversity = workforceDiversity;
    }

    public CompanyRating getCompanyRating() {
        return companyRating;
    }

    public void setCompanyRating(CompanyRating companyRating) {
        this.companyRating = companyRating;
    }

    public void setEmployees(int employees) {
        this.employees = employees;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getOwnershipType() {
        return ownershipType;
    }

    public void setOwnershipType(String ownershipType) {
        this.ownershipType = ownershipType;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public boolean isPubliclyTraded() {
        return publiclyTraded;
    }

    public void setPubliclyTraded(boolean publiclyTraded) {
        this.publiclyTraded = publiclyTraded;
    }

    public String getAnnualProfit() {
        return annualProfit;
    }

    public void setAnnualProfit(String annualProfit) {
        this.annualProfit = annualProfit;
    }

    public String getCustomerBase() {
        return customerBase;
    }

    public void setCustomerBase(String customerBase) {
        this.customerBase = customerBase;
    }

    public String getSupplierCount() {
        return supplierCount;
    }

    public void setSupplierCount(String supplierCount) {
        this.supplierCount = supplierCount;
    }

    public String getSubsidiaryCompanies() {
        return subsidiaryCompanies;
    }

    public void setSubsidiaryCompanies(String subsidiaryCompanies) {
        this.subsidiaryCompanies = subsidiaryCompanies;
    }

    public String getMainProducts() {
        return mainProducts;
    }

    public void setMainProducts(String mainProducts) {
        this.mainProducts = mainProducts;
    }

    public String getMainServices() {
        return mainServices;
    }

    public void setMainServices(String mainServices) {
        this.mainServices = mainServices;
    }

    public String getEmployeeSatisfactionScore() {
        return employeeSatisfactionScore;
    }

    public void setEmployeeSatisfactionScore(String employeeSatisfactionScore) {
        this.employeeSatisfactionScore = employeeSatisfactionScore;
    }

    public String getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(String turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public String getrAndDBudget() {
        return rAndDBudget;
    }

    public void setrAndDBudget(String rAndDBudget) {
        this.rAndDBudget = rAndDBudget;
    }

    public String getMarketingBudget() {
        return marketingBudget;
    }

    public void setMarketingBudget(String marketingBudget) {
        this.marketingBudget = marketingBudget;
    }

    public String getSocialMediaPresence() {
        return socialMediaPresence;
    }

    public void setSocialMediaPresence(String socialMediaPresence) {
        this.socialMediaPresence = socialMediaPresence;
    }

    public String getSustainabilityScore() {
        return sustainabilityScore;
    }

    public void setSustainabilityScore(String sustainabilityScore) {
        this.sustainabilityScore = sustainabilityScore;
    }

    public String getEnvironmentalImpact() {
        return environmentalImpact;
    }

    public void setEnvironmentalImpact(String environmentalImpact) {
        this.environmentalImpact = environmentalImpact;
    }

    public String getBrandReputation() {
        return brandReputation;
    }

    public void setBrandReputation(String brandReputation) {
        this.brandReputation = brandReputation;
    }

    public String getCybersecurityMeasures() {
        return cybersecurityMeasures;
    }

    public void setCybersecurityMeasures(String cybersecurityMeasures) {
        this.cybersecurityMeasures = cybersecurityMeasures;
    }

    public String getLegalIssues() {
        return legalIssues;
    }

    public void setLegalIssues(String legalIssues) {
        this.legalIssues = legalIssues;
    }

    public String getLaborLawsCompliance() {
        return laborLawsCompliance;
    }

    public void setLaborLawsCompliance(String laborLawsCompliance) {
        this.laborLawsCompliance = laborLawsCompliance;
    }

    public String getTaxCompliance() {
        return taxCompliance;
    }

    public void setTaxCompliance(String taxCompliance) {
        this.taxCompliance = taxCompliance;
    }

    public String getInternationalOffices() {
        return internationalOffices;
    }

    public void setInternationalOffices(String internationalOffices) {
        this.internationalOffices = internationalOffices;
    }
}

