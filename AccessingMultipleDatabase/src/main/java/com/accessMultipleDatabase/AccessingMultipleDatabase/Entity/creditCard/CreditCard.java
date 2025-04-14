package com.accessMultipleDatabase.AccessingMultipleDatabase.Entity.creditCard;

import jakarta.persistence.*;

@Entity
@Table(name="Customer")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long creditCardId;
    private String bankName;

    public Long getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(Long creditCardId) {
        this.creditCardId = creditCardId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
