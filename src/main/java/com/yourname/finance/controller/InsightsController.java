package com.yourname.finance.controller;

import com.plaid.client.model.Transaction;
import com.plaid.client.model.TransactionsGetRequest;
import com.plaid.client.model.TransactionsGetResponse;
import com.plaid.client.request.PlaidApi;
import com.yourname.finance.dto.CategorySummary;
import com.yourname.finance.service.ITransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plaid")
public class InsightsController {

    private final PlaidApi plaidApi;
    private final ITransactionService ITransactionService;

    public InsightsController(PlaidApi plaidApi, ITransactionService ITransactionService) {
        this.plaidApi = plaidApi;
        this.ITransactionService = ITransactionService;
    }

    @GetMapping("/insights")
    public ResponseEntity<Map<String, List<CategorySummary>>> getInsights(@RequestParam String accessToken) {
        try {
            TransactionsGetRequest request = new TransactionsGetRequest()
                    .accessToken(accessToken)
                    .startDate(LocalDate.now().minusMonths(1))
                    .endDate(LocalDate.now());

            TransactionsGetResponse response = plaidApi.transactionsGet(request).execute().body();

            List<Transaction> transactions = response.getTransactions();
            Map<String, List<CategorySummary>> insights = ITransactionService.generateInsights(transactions);

            return ResponseEntity.ok(insights);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
