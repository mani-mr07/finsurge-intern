package com.pagination.pagingAndSorting.controller;

import com.pagination.pagingAndSorting.entity.Product;
import com.pagination.pagingAndSorting.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    private ResponseEntity<List<Product>> listAllProducts(){
        List<Product>products=productService.listAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Product> createProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.createProduct(product),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Product> getOneProduct(@RequestParam int id){
        return new ResponseEntity<>(productService.getById(id),HttpStatus.OK);
    }
    @GetMapping("sorted/{field}")
    private ResponseEntity<List<Product>>sortByField(@PathVariable String field){
        return new ResponseEntity<>(productService.productsWithSorting(field),HttpStatus.OK);
    }
    @GetMapping("pagingExample/{offset}/{pageNo}")
    private ResponseEntity<Page<Product>>getAllPAge(@PathVariable int offset,@PathVariable int pageNo){
        return new ResponseEntity<>(productService.findProductsWithPagination(offset,pageNo),HttpStatus.OK);
    }
    @GetMapping("pagingExampleWithSorting/{offset}/{pageNo}/{field}")
    private ResponseEntity<Page<Product>>getAllPageWithSorting(@PathVariable int offset,@PathVariable int pageNo,@PathVariable String field){
        return new ResponseEntity<>(productService.getProductWithPaginationAndSorting(offset, pageNo, String.valueOf(field)),HttpStatus.OK);
    }
    @GetMapping("pagingWithSearch/{offset}/{pageSize}/{field}/{fieldValue}")
    private ResponseEntity<Page<Product>>getProduct(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String field,@PathVariable String fieldValue) {
        return new ResponseEntity<>(productService.getProductWithSompleFilteration(offset,pageSize, field, fieldValue), HttpStatus.OK);
    }
    @GetMapping("filterBasedOnColumn")
    private ResponseEntity<HashMap<String,Object>>getProductByFilteration(@RequestBody Product product,
                                                                          @RequestParam int offset,@RequestParam int pageNumber){
        System.out.println(product.toString());
        return new ResponseEntity<>(productService.findProductBasedOnAppliedFilteration(product,offset,pageNumber),HttpStatus.OK);
    }
}
