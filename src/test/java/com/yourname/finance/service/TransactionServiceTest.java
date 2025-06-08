package com.yourname.finance.service;

import static java.lang.Double.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.plaid.client.model.Transaction;
import com.yourname.finance.dto.CategorySummary;
import com.yourname.finance.model.Category;
import com.yourname.finance.service.impl.CategoryNormalizer;
import com.yourname.finance.service.impl.TransactionService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionServiceTest {
    private TransactionService transactionService;
    private CategoryNormalizer normalizer;
    
    @BeforeEach 
    void setup() {
        normalizer = mock(CategoryNormalizer.class);
        transactionService = new TransactionService(normalizer);
    }
    
    @Test
    void testGenerateInsights() {
        Transaction tranx1 = new Transaction().name("KFC").amount(valueOf("-20.00")).date(LocalDate.now()).category(List.of("Restaurants"));
        Transaction tranx2 = new Transaction().name("Chipotle").amount(valueOf("-20.00")).date(LocalDate.now()).category(List.of("Restaurants"));

        List<Transaction> transactions = List.of(tranx1, tranx2);
        
        when(normalizer.normalize("Restaurants")).thenReturn(Category.FOOD);

        Map<String, List<CategorySummary>> insights = transactionService.generateInsights(transactions);

        List<CategorySummary> expectedExpenses = List.of(new CategorySummary("FOOD", new BigDecimal("40.0")));

        assertEquals(expectedExpenses, insights.get("expenses"));
        assertEquals(1, insights.get("expenses").size());
    }
    
}
