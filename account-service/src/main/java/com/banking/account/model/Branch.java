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

    public Branch() {
    }

    public Branch(String branchCode) {
        this.branchCode = branchCode;
    }

    public Branch(Integer id, String branchCode, String branchName, String adress, String contactNumber) {
        this.id = id;
        this.branchCode = branchCode;
        this.branchName = branchName;
        this.adress = adress;
        this.contactNumber = contactNumber;
    }
}
