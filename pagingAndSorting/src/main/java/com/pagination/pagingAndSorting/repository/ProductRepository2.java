package com.pagination.pagingAndSorting.repository;

import com.pagination.pagingAndSorting.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository2  {

    @PersistenceContext
    private EntityManager entityManager;

    public Page<Product> searchByDynamicColumn(String columnName, String keyword, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);
        System.out.println("Product Root"+root.getModel());
        System.out.println(root.get("name"));

        Predicate predicate = cb.like(cb.lower(root.get(columnName)), "%" + keyword.toLowerCase() + "%");
        cq.where(predicate);

        TypedQuery<Product> query = entityManager.createQuery(cq);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Product> result = query.getResultList();
        long total = result.size();

        return new PageImpl<>(result, pageable, total);
    }
}
