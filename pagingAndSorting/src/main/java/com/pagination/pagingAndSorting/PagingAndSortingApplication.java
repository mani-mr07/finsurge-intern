package com.pagination.pagingAndSorting;

import com.pagination.pagingAndSorting.entity.Product;
import com.pagination.pagingAndSorting.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PagingAndSortingApplication  {

	@Autowired
	private ProductRepository productRepository;
	public static void main(String[] args) {
		SpringApplication.run(PagingAndSortingApplication.class, args);
	}
//	@Transactional
//	@Override
//	public void run(String... args) {
//		for (int i = 0; i < 1000; i++) {
//			productRepository.save(new Product("Product"+i,100,10,"HI THERE ,HOW ARE YOU"+i));
//		}
//	}
}
