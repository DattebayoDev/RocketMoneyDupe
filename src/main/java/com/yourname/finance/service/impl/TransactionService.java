package com.yourname.finance.service.impl;

import com.yourname.finance.dto.CategorySummary;
import com.yourname.finance.model.Category;
import com.yourname.finance.service.ICategoryNormalizer;
import com.yourname.finance.service.ITransactionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionService implements ITransactionService {

    private final ICategoryNormalizer ICategoryNormalizer;

    public TransactionService(ICategoryNormalizer ICategoryNormalizer) {
        this.ICategoryNormalizer = ICategoryNormalizer;
    }

    public Map<String, List<CategorySummary>> generateInsights(List<com.plaid.client.model.Transaction> transactions) {
        Map<Category, BigDecimal> incomeTotals = new HashMap<>();
        Map<Category, BigDecimal> expenseTotals = new HashMap<>();

        for (com.plaid.client.model.Transaction txn : transactions) {
            String raw = txn.getCategory() != null && !txn.getCategory().isEmpty()
                    ? txn.getCategory().get(0)
                    : txn.getName();

            Category category = ICategoryNormalizer.normalize(raw);
            BigDecimal amount = BigDecimal.valueOf(txn.getAmount());
            boolean isIncome = amount.compareTo(BigDecimal.ZERO) > 0;

            Map<Category, BigDecimal> target = isIncome ? incomeTotals : expenseTotals;
            target.put(category, target.getOrDefault(category, BigDecimal.ZERO).add(amount.abs()));
        }

        return Map.of(
                "income", toSummaryList(incomeTotals),
                "expenses", toSummaryList(expenseTotals)
        );
    }

    private List<CategorySummary> toSummaryList(Map<Category, BigDecimal> data) {
        return data.entrySet().stream()
                .map(entry -> new CategorySummary(entry.getKey().name(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
