package com.banking.account.mappers;

import com.banking.account.model.Account;
import com.banking.account.model.Branch;
import com.banking.core.dto.account.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mappings({
            @Mapping(source = "owner", target = "withAccountHolder"),
            @Mapping(source = "branch.branchCode", target ="withBranch" )
    })
    AccountDto convertToDto(Account account);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "availableBalance", ignore = true),
            @Mapping(target = "currentBalance", ignore = true),
            @Mapping(target = "numberOfSignatories", ignore = true),
            @Mapping(source = "accountHolder", target = "owner")
    })
    Account convertToEntity(AccountDto accountDto);

    default Branch toBranch(String branch) {
        return branch != null ? new Branch( branch ) : null;
    }
}
