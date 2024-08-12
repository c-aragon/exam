package com.exam.account.service.impl;

import com.exam.account.exception.EntityNotFoundException;
import com.exam.account.exception.ServiceErrorException;
import com.exam.account.external.ClientClient;
import com.exam.account.repository.AccountRepository;
import com.exam.account.repository.TransactionRepository;
import com.exam.account.service.ReportService;
import com.exam.account.service.dto.AccountReportDto;
import com.exam.account.service.dto.ClientDto;
import com.exam.account.service.dto.DataDto;
import com.exam.account.service.dto.TransactionDto;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    private final ClientClient client;

    public ReportServiceImpl(ClientClient client,
                             AccountRepository accountRepository,
                             TransactionRepository transactionRepository) {
        this.client = client;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public DataDto createReport(LocalDateTime startDate, LocalDateTime endDate, Long idClient) {
        ClientDto clientDto;

        try {
            clientDto = client.getClient(idClient);
        } catch (FeignException ex) {
            if (ex.status() == 404) {
                throw new EntityNotFoundException(String.format("Client %d doesn't exists!", idClient));
            } else {
                throw new ServiceErrorException("Error on get data from client service");
            }
        }

        List<AccountReportDto> accountReportDtos = accountRepository.findAllByIdClient(idClient)
                .stream().map(account -> AccountReportDto.builder()
                        .accountId(account.getId())
                        .accountType(account.getAccountType())
                        .balance(account.getBalance())
                        .transactions(new ArrayList<>())
                        .build())
                .toList();

        for (AccountReportDto account : accountReportDtos) {
            account.setTransactions(
                    transactionRepository.findAllByAccount_IdAndDateBetween(account.getAccountId(),
                                    startDate,
                                    endDate).stream().map(
                                    transaction -> TransactionDto.builder()
                                            .date(transaction.getDate())
                                            .transactionType(transaction.getTransactionType())
                                            .amount(transaction.getAmount())
                                            .balance(transaction.getBalance())
                                            .oldBalance(transaction.getOldBalance())
                                            .statusTransaction(transaction.getStatusTransaction())
                                            .build())
                            .toList());
        }

        return DataDto.builder()
                .idClient(idClient)
                .name(clientDto.getName())
                .address(clientDto.getAddress())
                .accounts(accountReportDtos)
                .build();
    }
}
