//package com.yourname.finance.test;
//
//import com.yourname.finance.client.PlaidClient;
//import com.yourname.finance.dto.PlaidTransactionDTO;
//import com.yourname.finance.mapper.TransactionMapper;
//import com.yourname.finance.model.Transaction;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class PlaidClientTest {
//    public static void main(String[] args) {
//        PlaidClient client = new PlaidClient();
//
//        // Step 1: Load and parse mock JSON
//        List<PlaidTransactionDTO> dtoList = client.getMockTransactions();
//
//        System.out.println("Parsed DTOs:");
//        for (PlaidTransactionDTO dto : dtoList) {
//            System.out.println("Name: " + dto.getName());
//            System.out.println("Amount: " + dto.getAmount());
//            System.out.println("Date: " + dto.getDate());
//            System.out.println("Category: " + dto.getCategory());
//            System.out.println("Type: " + dto.getTransaction_type());
//            System.out.println("----------------------");
//        }
//
//        // Step 2: Map DTOs to internal Transaction model
//        List<Transaction> transactions = dtoList.stream()
//                .map(TransactionMapper::fromDto)
//                .toList();
//
//        System.out.println("Mapped Domain Transactions:");
//        for (Transaction tx : transactions) {
//            System.out.println("Name: " + tx.getName());
//            System.out.println("Amount: " + tx.getAmount());
//            System.out.println("Date: " + tx.getDate());
//            System.out.println("Category: " + tx.getRawCategory());
//            System.out.println("Type: " + tx.getType());
//            System.out.println("----------------------");
//        }
//    }
//}
