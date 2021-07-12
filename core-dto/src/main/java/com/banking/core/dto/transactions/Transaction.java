package com.banking.core.dto.transactions;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="Transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private  String  txnId;
    private  String description;
    private  double amount;
    private  LocalDate date;
    @ManyToOne
    @JoinColumn(name = "compte_id")
    private Compte source;
    private  TransactionType type;
    @ManyToOne
    private  Compte destination;

    public Transaction() {
    }

    public Transaction(String txnId, Compte source, String description, double amount, LocalDate date, TransactionType type) {
        this.txnId = txnId;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.source = source;
        this.type = type;
        destination = source;
    }

    public Transaction(String txnId, String description, double amount, LocalDate date, Compte source, TransactionType type, Compte destination) {
        this.txnId = txnId;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.source = source;
        this.type = type;
        this.destination = destination;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Compte getSource() {
        return source;
    }

    public void setSource(Compte source) {
        this.source = source;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Compte getDestination() {
        return destination;
    }

    public void setDestination(Compte destination) {
        this.destination = destination;
    }
}
