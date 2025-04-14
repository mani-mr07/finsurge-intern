package com.accessMultipleDatabase.AccessingMultipleDatabase.Repository.customer;

import com.accessMultipleDatabase.AccessingMultipleDatabase.Entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
