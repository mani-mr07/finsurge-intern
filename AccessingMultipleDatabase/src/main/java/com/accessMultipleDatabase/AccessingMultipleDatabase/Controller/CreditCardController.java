package com.accessMultipleDatabase.AccessingMultipleDatabase.Controller;

import com.accessMultipleDatabase.AccessingMultipleDatabase.Entity.creditCard.CreditCard;
import com.accessMultipleDatabase.AccessingMultipleDatabase.Service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/creditCard")
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @PostMapping
    public ResponseEntity createCreditCard(@RequestBody CreditCard creditCard){
        System.out.println(creditCard.getBankName());
        if(creditCard.getBankName()!=null) {
            return new ResponseEntity(creditCardService.addCreditCard(creditCard), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity("Empty student",HttpStatus.BAD_REQUEST);
        }
    }

}
