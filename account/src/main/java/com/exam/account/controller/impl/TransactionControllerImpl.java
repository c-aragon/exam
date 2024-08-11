package com.exam.account.controller.impl;

import com.exam.account.controller.TransactionController;
import com.exam.account.controller.dto.TransactionDto;
import com.exam.account.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class TransactionControllerImpl implements TransactionController {

    private final TransactionService transactionService;

    public TransactionControllerImpl(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDto createTransaction(@Valid @RequestBody TransactionDto transactionDto) {
        return transactionService.createTransaction(transactionDto);
    }

    @PatchMapping("/{id}")
    @Override
    public TransactionDto editTransaction(@PathVariable Long id, @Valid @RequestBody TransactionDto transactionDto) {
        return transactionService.editTransaction(id, transactionDto);
    }

    @DeleteMapping({"/{id}"})
    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }

    @GetMapping({"/{id}"})
    @Override
    public TransactionDto getTransaction(@PathVariable Long id) {
        return transactionService.getTransaction(id);
    }

    @GetMapping
    @Override
    public List<TransactionDto> getTransactions() {
        return transactionService.getTransactions();
    }
}
