package com.accessMultipleDatabase.AccessingMultipleDatabase.Service;

import com.accessMultipleDatabase.AccessingMultipleDatabase.Entity.creditCard.CreditCard;
import com.accessMultipleDatabase.AccessingMultipleDatabase.Repository.credit.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;

    @Autowired
    public CreditCardService(@Qualifier("creditCardRepository") CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Transactional(transactionManager = "creditCardTransactionManager")
    public CreditCard addCreditCard(CreditCard creditCard) {

        return creditCardRepository.save(creditCard);
    }
}
