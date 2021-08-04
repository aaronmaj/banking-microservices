package com.banking.account.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name= "branches")
@Entity
public class Branch {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name="branch_code", unique = true,nullable = false)
    private String branchCode;
    @Column(name="branch_name")
    private String branchName;
    private String adress;
    private String contactNumber;
}
