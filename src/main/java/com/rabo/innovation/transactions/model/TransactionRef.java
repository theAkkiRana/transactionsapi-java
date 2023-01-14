package com.rabo.innovation.transactions.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data 
public class TransactionRef {
    private String id;
    private String type;
}
