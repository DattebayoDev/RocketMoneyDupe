package com.yourname.finance.dto;

import java.math.BigDecimal;

public class CategorySummary {
    private String category;
    private BigDecimal amount;

    public CategorySummary(String category, BigDecimal amount) {
        this.category = category;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
