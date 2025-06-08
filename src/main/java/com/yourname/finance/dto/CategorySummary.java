package com.yourname.finance.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CategorySummary {
    private String category;
    private BigDecimal amount;

    public CategorySummary(String category, BigDecimal amount) {
        this.category = category;
        this.amount = amount;
    }

}
