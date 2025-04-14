package com.pagination.pagingAndSorting.repository;

import com.pagination.pagingAndSorting.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%',:name, '%'))")
    Page<Product> findByNameContaining( Pageable pageable,String name,String field);

    @Query("SELECT p from Product p WHERE "+
    "(:#{#product.id} =0 OR p.id=:#{#product.id}) AND"+
    "(:#{#product.name} is  NULL OR p.name=:#{#product.name}) AND"+
            "(:#{#product.price} is NULL OR p.price=:#{#product.price}) AND"+
            "(:#{#product.quantity}=0 OR p.quantity=:#{#product.quantity}) AND"+
            "(:#{#product.descrip} is  NULL OR p.descrip=:#{#product.descrip})")
    Page<Product> findByColumnFilteration(Pageable pageable,Product product);
}
