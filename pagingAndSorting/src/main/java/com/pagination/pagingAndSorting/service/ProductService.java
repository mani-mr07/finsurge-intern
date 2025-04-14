package com.pagination.pagingAndSorting.service;

import com.pagination.pagingAndSorting.entity.Product;
import com.pagination.pagingAndSorting.repository.ProductRepository;
import com.pagination.pagingAndSorting.repository.ProductRepository2;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductRepository2 productRepository2;

    public List<Product> listAllProducts(){
        List<Product>products= productRepository.findAll();
        System.out.println(products.get(0).getName());
        return products;
    }

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public Product getById(int id){

        Product product= productRepository.findById(id).get();
        if(product!=null){
            return product;
        }
        return null;

    }

    public List<Product>productsWithSorting(String field){
        return productRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }


    public Page<Product> findProductsWithPagination(int offset, int pageSize){
        Page<Product> products = productRepository.findAll(PageRequest.of(offset, pageSize));
        return  products;
    }


    public Page<Product> getProductWithPaginationAndSorting(int offset, int pageSize, String field) {
        return productRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(field)));
    }

    public Page<Product>getProductWithSompleFilteration(int offset,int pageSize,String field,String fieldValue){
    Pageable productPage=PageRequest.of(offset,pageSize).withSort(Sort.by(field).ascending());
//    Page<Product>productsSearch=productRepository.findByNameContaining((Pageable) productPage,fieldValue,field);
//    return productsSearch;
        return productRepository2.searchByDynamicColumn(field, fieldValue, productPage);

    }

    public HashMap<String, Object> findProductBasedOnAppliedFilteration(Product product,int offset,int pageSize) {
        Pageable productPage=PageRequest.of(offset,pageSize).withSort(Sort.by("id").ascending());
       Page<Product>products= productRepository.findByColumnFilteration(productPage,product);
       HashMap<String,Object>map=new HashMap<>();

       map.put("Content",products.getContent());
       map.put("Total Elements",products.getTotalElements());
       map.put("Total Number of Elements ",products.getNumberOfElements());
       map.put("Number",products.getNumber());
       map.put("pageable",products.getPageable());
        map.put("Size",products.getSize());
        map.put("Sort",products.getSort());
       map.put("Total Pages",products.getTotalPages());
       return map;
    }
}
