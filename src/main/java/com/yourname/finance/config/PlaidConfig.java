package com.yourname.finance.config;

import com.plaid.client.ApiClient;
import com.plaid.client.request.PlaidApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class PlaidConfig {

    @Bean
    public PlaidApi plaidApi() {
        // Create with API keys
        ApiClient apiClient = new ApiClient(Map.of(
                "clientId", "68339c8545aadd0023b5d099",
                "secret", "f81cec5084bd7a73b83dbb2575df13"
        ));

        // Set base URL manually
        apiClient.setPlaidAdapter(ApiClient.Sandbox); // Use .Production or .Development as needed

        return apiClient.createService(PlaidApi.class);
    }
}
