package com.yourname.finance.service.impl;

import com.yourname.finance.model.Category;
import com.yourname.finance.service.ICategoryNormalizer;
import org.springframework.stereotype.Service;

@Service
public class CategoryNormalizer implements ICategoryNormalizer {
    @Override
    public Category normalize(String raw) {
        if (raw == null) return Category.UNCATEGORIZED;
        String lower = raw.toLowerCase();

        if (lower.contains("grocery") || lower.contains("supermarket")) return Category.FOOD;
        if (lower.contains("restaurant") || lower.contains("dining")) return Category.FOOD;
        if (lower.contains("uber") || lower.contains("lyft") || lower.contains("transport")) return Category.TRANSPORTATION;
        if (lower.contains("rent") || lower.contains("housing")) return Category.HOUSING;
        if (lower.contains("salary") || lower.contains("payroll")) return Category.INCOME;

        return Category.UNCATEGORIZED;
    }
}

