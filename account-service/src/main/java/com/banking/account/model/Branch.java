package com.banking.account.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name= "branches")
@Entity
public class Branch {
    @Id
    @Column(name="branch_id")
    private String branchId;
    @Column(name="branch_name", unique = true,nullable = false)
    private String branchName;
    private String adress;
    private String contactNumber;
}
