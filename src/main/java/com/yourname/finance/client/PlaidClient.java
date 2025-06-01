package com.yourname.finance.client;

import com.plaid.client.request.PlaidApi;
import com.plaid.client.model.TransactionsGetRequest;
import com.plaid.client.model.TransactionsGetResponse;
import com.plaid.client.model.Transaction;
import com.yourname.finance.dto.PlaidTransactionDTO;
import org.springframework.stereotype.Component;
import retrofit2.Response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlaidClient {

    private final PlaidApi plaidApi;

    public PlaidClient(PlaidApi plaidApi) {
        this.plaidApi = plaidApi;
    }

    public List<PlaidTransactionDTO> getRealTransactions(String accessToken) {
        try {
            TransactionsGetRequest request = new TransactionsGetRequest()
                    .accessToken(accessToken)
                    .startDate(LocalDate.now().minusMonths(1))
                    .endDate(LocalDate.now());

            Response<TransactionsGetResponse> response = plaidApi
                    .transactionsGet(request)
                    .execute();

            if (!response.isSuccessful()) {
                System.err.println("Plaid error: " + response.errorBody().string());
                return List.of();
            }

            return response.body().getTransactions().stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    private PlaidTransactionDTO toDto(Transaction t) {
        return new PlaidTransactionDTO(
                t.getName(),
                BigDecimal.valueOf(t.getAmount()),
                t.getDate().toString(),
                t.getCategory(),
                String.valueOf(t.getTransactionType())
        );
    }
}
