package com.banking.account.service;

import com.banking.account.filters.UserContextHolder;
import com.banking.account.model.Account;
import com.banking.account.repository.AccountRepository;
import com.banking.account.service.client.CustomerFeignClient;
import com.banking.account.service.client.CustomerRestTemplateClient;
import com.banking.account.utils.EntityUtils;
import com.banking.core.dto.account.AccountDTO;
import com.banking.core.dto.customer.CustomerDTO;
import com.banking.core.dto.customer.PersonalDetails;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static io.github.resilience4j.bulkhead.annotation.Bulkhead.Type.THREADPOOL;

@Service
@RequiredArgsConstructor
public class AccountService {
    public static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    @Autowired
    MessageSource messages;
    private final AccountRepository accountRepository;

    @Autowired
    CustomerFeignClient feignClient;
    @Autowired
    CustomerRestTemplateClient customerRestClient;
    @Autowired
    ModelMapper mapper;

    public AccountDTO findById(Integer id) {
        return convertToDTO(accountRepository.findById(id).orElse(null));
    }

    public AccountDTO getAccountByNumber(String accountNumber) {
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

    @CircuitBreaker(name = "detailService", fallbackMethod = "buildFallbackDetails")
    @RateLimiter(name = "detailService", fallbackMethod = "buildFallbackDetails")
    @Retry(name = "retryDetailService", fallbackMethod = "buildFallbackDetails")
    @Bulkhead(name = "bulkheadDetailService", type = THREADPOOL, fallbackMethod = "buildFallbackDetails")
    public CustomerDTO getPersonalDetails(String accountNumber) throws TimeoutException {
        logger.debug("getLicensesByOrganization Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
        return getCustomer(accountRepository.findByAccountNumber(accountNumber).get().getCustomerId());
    }

    @CircuitBreaker(name = "customerService")
    private CustomerDTO getCustomer(String cuctomerId) {
        return customerRestClient.getCustomer(cuctomerId);
    }

    private PersonalDetails buildFallbackDetails(String customerId, Throwable t) {

        PersonalDetails details = new PersonalDetails();

        return details;
    }

    private AccountDTO convertToDTO(Account account) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return mapper.map(account, AccountDTO.class);
    }

    private Account convertToEntity(AccountDTO accountDTO) {
        Account account = mapper.map(accountDTO, Account.class);
        return account;
    }


}
