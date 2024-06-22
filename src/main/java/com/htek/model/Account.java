package com.htek.model;

import com.aerospike.mapper.annotations.AerospikeBin;
import com.aerospike.mapper.annotations.AerospikeKey;
import com.aerospike.mapper.annotations.AerospikeRecord;

import java.sql.Timestamp;

@AerospikeRecord(namespace = "test",set = "account")
public class Account {

    @AerospikeKey
    @AerospikeBin
    private long accountNumber;

    @AerospikeBin
    private String panNumber;

    @AerospikeBin
    private String address;

    @AerospikeBin
    private long balance;

    public Account(){}

    public Account(long accountNumber, String panNumber, String address, long depositAmount, long withdraw, long balance) {
        this.accountNumber = accountNumber;
        this.panNumber = panNumber;
        this.address = address;
        this.balance = balance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
              ", accountNumber=" + accountNumber +
                ", panNumber='" + panNumber + '\'' +
                ", address='" + address + '\'' +
                ", balance=" + balance +
              '}';
    }
}
