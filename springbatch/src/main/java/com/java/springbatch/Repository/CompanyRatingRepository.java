package com.java.springbatch.Repository;

import com.java.springbatch.Entity.Company;
import com.java.springbatch.Entity.CompanyRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRatingRepository extends JpaRepository<CompanyRating,Long> {
    @Query(value = "SELECT reviews from company_rating where company_id=:value",nativeQuery = true)
    Long findByCompany(Long value);
}
