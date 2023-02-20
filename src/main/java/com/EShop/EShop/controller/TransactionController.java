package com.EShop.EShop.controller;

import com.EShop.EShop.dto.TransactionDto;
import com.EShop.EShop.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;

    TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping("/transaction")
    private ResponseEntity<List<TransactionDto>> getAllTransactions(){
        return ResponseEntity.ok(transactionService.getAllTransaction());
    }

    @GetMapping("/transaction/{id}")
    private ResponseEntity<TransactionDto> getTransactionById(@PathVariable Long id){
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    @PostMapping("/transaction")
    private ResponseEntity<TransactionDto> saveTransaction(@Valid @RequestBody TransactionDto transactionDto){
        return ResponseEntity.ok(transactionService.saveTransaction(transactionDto));
    }

}
