package com.yourname.finance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaidTransactionDTO {
    private String name;
    private BigDecimal amount;
    private String date;
    private List<String> category;
    private String transaction_type;
}
