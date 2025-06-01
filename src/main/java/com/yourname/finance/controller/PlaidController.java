package com.yourname.finance.controller;

import com.plaid.client.request.PlaidApi;
import com.plaid.client.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plaid")
public class PlaidController {

    private final PlaidApi plaidApi;

    @Autowired
    public PlaidController(PlaidApi plaidApi) {
        this.plaidApi = plaidApi;
    }

    @PostMapping("/link-token/create")
    public ResponseEntity<Map<String, String>> createLinkToken() {
        LinkTokenCreateRequestUser user = new LinkTokenCreateRequestUser()
                .clientUserId("test-user-id");

        LinkTokenCreateRequest request = new LinkTokenCreateRequest()
                .user(user)
                .clientName("My Finance Tracker")
                .products(List.of(Products.TRANSACTIONS))
                .countryCodes(List.of(CountryCode.US))
                .language("en");

        try {
            LinkTokenCreateResponse response = plaidApi.linkTokenCreate(request).execute().body();
            return ResponseEntity.ok(Map.of("link_token", response.getLinkToken()));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/token/exchange")
    public ResponseEntity<Map<String, String>> exchangePublicToken(@RequestBody Map<String, String> payload) {
        String publicToken = payload.get("public_token");

        try {
            ItemPublicTokenExchangeRequest request = new ItemPublicTokenExchangeRequest()
                    .publicToken(publicToken);
            ItemPublicTokenExchangeResponse response = plaidApi.itemPublicTokenExchange(request).execute().body();

            String accessToken = response.getAccessToken();
            return ResponseEntity.ok(Map.of("access_token", accessToken));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<com.plaid.client.model.Transaction>> getTransactions(@RequestParam String accessToken) {
        try {
            TransactionsGetRequest request = new TransactionsGetRequest()
                    .accessToken(accessToken)
                    .startDate(LocalDate.now().minusMonths(1))
                    .endDate(LocalDate.now());

            TransactionsGetResponse response = plaidApi.transactionsGet(request).execute().body();
            return ResponseEntity.ok(response.getTransactions());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
