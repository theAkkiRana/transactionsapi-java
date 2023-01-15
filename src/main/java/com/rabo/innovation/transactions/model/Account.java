package com.rabo.innovation.transactions.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.util.List;

import com.rabo.innovation.transactions.model.TransactionRef;

@AllArgsConstructor
@NoArgsConstructor
@Data 
@Document("account")
public class Account {
    @Id
    private String number;
    private String salutation;
    private String firstName;
    private String middleName;
    private String lastName;
    private String emailId;
    private String accountType;
    private double openingBalance;
    private double minBalance = 50;
    private double currentBalance;
    private String openingTimestamp;
    private List<TransactionRef> transactions;

    // private synchronized boolean withdraw(double amount, String transactionId){
    //        if(this.currentBalance - amount < this.minBalance){
    //             return false;
    //        }else {
    //         TransactionRef transactionRef = new TransactionRef();
    //         transactionRef.setId(transactionId);
    //         transactionRef.setType("debit");
    //         transactions.add(transactionRef);
    //         this.currentBalance = this.currentBalance - amount;
    //         return true;
    //        }
    // }

    // private boolean deposit(double amount, String transactionId){
    //     TransactionRef transactionRef = new TransactionRef();
    //     transactionRef.setId(transactionId);
    //     transactionRef.setType("credit");
    //     transactions.add(transactionRef);
    //     this.currentBalance = this.currentBalance + amount;
    // }
}
