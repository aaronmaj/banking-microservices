package com.banking.core.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BranchDtO {
    @JsonProperty("branch_code")
    private String branchCode;
    @JsonProperty("branch_name")
    private String branchName;
    private String address;
    @JsonProperty("contact_number")
    private String contactNumber;
}
