package com.yourname.finance.mapper;

import com.yourname.finance.dto.PlaidTransactionDTO;
import com.yourname.finance.model.Transaction;
import com.yourname.finance.model.TransactionType;

import java.time.LocalDate;

public class TransactionMapper {

    public static Transaction fromDto(PlaidTransactionDTO dto) {
        LocalDate parsedDate = null;
        try {
            if (dto.getDate() != null && !dto.getDate().isBlank()) {
                parsedDate = LocalDate.parse(dto.getDate());
            }
        } catch (Exception e) {
            System.err.println("Could not parse date: " + dto.getDate());
        }

        return new Transaction(
                dto.getAmount(),
                parsedDate,
                (dto.getCategory() != null && !dto.getCategory().isEmpty()) ? dto.getCategory().get(0) : "Uncategorized",
                dto.getName(),
                inferType(dto)
        );
    }


    private static TransactionType inferType(PlaidTransactionDTO dto) {
        // 🔄 Customize later: for now, hardcode everything as EXPENSE
        return TransactionType.EXPENSE;
    }
}
