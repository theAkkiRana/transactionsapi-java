package com.rabo.innovation.transactions.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data 
@Document("transaction")
public class Transaction {
    @Id
    private String id; 
    private String debitedfrom;
    private String creditedto;
    private String timestamp;
    private String reference;
    private String message;
    private double amount;
}
