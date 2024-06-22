package com.htek.model;

import com.aerospike.mapper.annotations.AerospikeBin;
import com.aerospike.mapper.annotations.AerospikeEnum;
import com.aerospike.mapper.annotations.AerospikeKey;
import com.aerospike.mapper.annotations.AerospikeRecord;

@AerospikeRecord(namespace = "test",set = "transactions")
public class Transaction {

    @AerospikeBin
    private long accountNumber;

    @AerospikeBin
    private long amount;

    @AerospikeEnum
    private Transaction_Type transactionType;

    @AerospikeBin
    @AerospikeKey
    private String timeStamp;

    public Transaction(long accountNumber, long amount, Transaction_Type transactionType, String timeStamp) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.transactionType = transactionType;
        this.timeStamp = timeStamp;
    }

    public Transaction() {
    }

    public Transaction(long fromAccount, long toAccount, long amount, Transaction_Type credit, String formatDate) {
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Transaction_Type getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Transaction_Type transactionType) {
        this.transactionType = transactionType;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "accountNumber=" + accountNumber +
                ", amount=" + amount +
                ", transactionType=" + transactionType +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }
}
