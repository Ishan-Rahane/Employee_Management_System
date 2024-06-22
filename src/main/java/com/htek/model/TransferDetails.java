package com.htek.model;

import com.aerospike.mapper.annotations.AerospikeBin;
import com.aerospike.mapper.annotations.AerospikeKey;
import com.aerospike.mapper.annotations.AerospikeRecord;
import io.micronaut.core.annotation.Introspected;
import jakarta.inject.Inject;

@Introspected
@AerospikeRecord(namespace = "test",set = "transfer")
public class TransferDetails {

    @AerospikeBin
    private long fromAccount;

    @AerospikeBin
    private long toAccount;

    @AerospikeBin
    private long amount;


    public TransferDetails(long fromAccount, long toAccount, long amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    public TransferDetails() {
    }

    public long getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(long fromAccount) {
        this.fromAccount = fromAccount;
    }

    public long getToAccount() {
        return toAccount;
    }

    public void setToAccount(long toAccount) {
        this.toAccount = toAccount;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        return "TransferDetails{" +
                "fromAccount=" + fromAccount +
                ", toAccount=" + toAccount +
                ", amount=" + amount +
                '}';
    }

}
