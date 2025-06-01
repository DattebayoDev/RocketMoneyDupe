package com.yourname.finance.service;

import com.plaid.client.model.Transaction;
import com.yourname.finance.dto.CategorySummary;

import java.util.List;
import java.util.Map;

public interface ITransactionService {
//    Map<TransactionType, Map<Category, Double>> getInsights(LocalDate start, LocalDate end, List<Transaction> transactions);
    Map<String, List<CategorySummary>> generateInsights(List<Transaction> transactions);
}
