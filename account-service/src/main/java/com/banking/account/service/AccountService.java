package com.banking.account.service;

import com.banking.account.model.Account;
import com.banking.account.repository.AccountRepository;
import com.banking.core.dto.accounts.AccountDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AccountService {
    @Autowired
    MessageSource messages;
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public AccountDTO findById(Integer id) {
        return convertToDTO(accountRepository.findById(id).orElse(null));
    }
    public AccountDTO findByAccountNumber(String accountNumber) {
        return convertToDTO(accountRepository.findByAccountNumber(accountNumber).get());
    }
    public List<AccountDTO> findAll() {
        return StreamSupport
                .stream(accountRepository.findAll().spliterator(), false)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AccountDTO createAccount(Account account) {
        return convertToDTO(accountRepository.save(account));
    }

    public AccountDTO updateAccount(Account account) {
        return convertToDTO(accountRepository.save(account));
    }

    public String deleteAccount(String accountNumber) {
        String responseMessage = null;
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if (account.isPresent())
            accountRepository.delete(account.get());
        responseMessage = String.format(messages.getMessage("account.delete.message", null, null), accountNumber);
        return responseMessage;
    }

    private AccountDTO convertToDTO(Account account) {
        return modelMapper.map(account, AccountDTO.class);
    }

    public Account convertToEntity(AccountDTO accountDTO) {
        Account account = modelMapper.map(accountDTO, Account.class);
        return account;
    }
}
