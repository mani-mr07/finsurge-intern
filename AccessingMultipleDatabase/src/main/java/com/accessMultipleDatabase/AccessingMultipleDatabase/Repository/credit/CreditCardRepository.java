package com.accessMultipleDatabase.AccessingMultipleDatabase.Repository.credit;

import com.accessMultipleDatabase.AccessingMultipleDatabase.Entity.creditCard.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard,Long> {
}
