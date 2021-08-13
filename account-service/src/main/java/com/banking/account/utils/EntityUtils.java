package com.banking.account.utils;

import com.banking.account.model.Account;
import com.banking.core.dto.account.AccountDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityUtils<T,U> {
    private final ModelMapper mapper;

    public static T convertToDTO(U u) {
        return mapper.map(u, T.class);
    }

    public static U convertToEntity(T t) {
        U u = mapper.map(accountDTO, Account.class);
        return account;
    }
}
