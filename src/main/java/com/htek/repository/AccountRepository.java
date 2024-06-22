package com.htek.repository;

import com.htek.configuration.AeromapperConfiguration;
import com.htek.model.Account;
import com.htek.model.Transaction;
import com.htek.model.TransferDetails;
import jakarta.inject.Inject;

import java.util.List;

public class AccountRepository{

    @Inject
    private AeromapperConfiguration aeromapperConfiguration;


    public Object createAccount(Account account) {
         aeromapperConfiguration.getMapper().save(account);
         return "Account added successfully!";
    }

    public Object getAccountByAccNumber(long accountNumber) {
        return aeromapperConfiguration.getMapper().read(Account.class,accountNumber);
    }

    public List<Account> getAllAccount() {
        return aeromapperConfiguration.getMapper().scan(Account.class);
    }

    public void save(Account account) {
        aeromapperConfiguration.getMapper().save(account);
    }

    public Object deleteAccountByAccNumber(long accountNumber) {
        return aeromapperConfiguration.getMapper().delete(Account.class,accountNumber);
    }

    public void saveTransaction(Transaction transaction) {
        aeromapperConfiguration.getMapper().save(transaction);
    }

    public List<Transaction> listTransaction() {
        return aeromapperConfiguration.getMapper().scan(Transaction.class);
    }

    public Object transfer(TransferDetails transferDetails) {
        return aeromapperConfiguration.getMapper().read(TransferDetails.class,transferDetails);
    }

}
