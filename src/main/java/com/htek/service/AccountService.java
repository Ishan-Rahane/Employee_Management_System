package com.htek.service;

import com.htek.dto.ResponseDTO;
import com.htek.exception.InsufficientBalance;
import com.htek.model.Account;
import com.htek.model.Transaction;
import com.htek.model.Transaction_Type;
import com.htek.model.TransferDetails;
import com.htek.repository.AccountRepository;
import com.htek.util.CommonUtils;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Singleton
public class AccountService extends Thread{

    Account account = new Account();

    @Inject
    private AccountRepository accountRepository;

    public Object createAccount(Account account) {
        return accountRepository.createAccount(account);
    }

    public Object getAccountByAccNumber(long accountNumber) {
        return accountRepository.getAccountByAccNumber(accountNumber);
    }

    public List<Account> getAllAccount() {
        return accountRepository.getAllAccount();
    }

    public Object deleteAccountByAccNumber(long accountNumber) {
        return accountRepository.deleteAccountByAccNumber(accountNumber);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public void saveTransaction(Transaction transaction) {
        accountRepository.saveTransaction(transaction);
    }

    public List<Transaction> account() {
        return accountRepository.listTransaction();
    }

    public Transaction sendMoney1(TransferDetails transferDetails,long amount) throws InterruptedException {
        long toAccountNumber = transferDetails.getToAccount();
        Account toAccount = (Account) accountRepository.getAccountByAccNumber(toAccountNumber);

        toAccount.setBalance(toAccount.getBalance() + amount);
        accountRepository.save(toAccount);

        Transaction transaction2 = new Transaction(toAccountNumber, amount, Transaction_Type.CREDIT, CommonUtils.formatDate(new Date(), CommonUtils.DATE_TIME_FORMATE_11));
        accountRepository.saveTransaction(transaction2);
        return transaction2;
    }

    public List<Transaction> transfer(TransferDetails transferDetails) throws InterruptedException {

        long fromAccountNumber = transferDetails.getFromAccount();
        long amount = transferDetails.getAmount();

        Account fromAccount = (Account) accountRepository.getAccountByAccNumber(fromAccountNumber);

        if(fromAccount.getBalance() <= 0 || amount > fromAccount.getBalance()) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setResponse("Insufficient Balance!");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        accountRepository.save(fromAccount);

        Transaction transaction1 = new Transaction(fromAccountNumber, amount, Transaction_Type.DEBIT, CommonUtils.formatDate(new Date(), CommonUtils.DATE_TIME_FORMATE_11));
        accountRepository.saveTransaction(transaction1);
        TimeUnit.SECONDS.sleep(1);
        Transaction transaction=sendMoney1(transferDetails,amount);

        return Arrays.asList(transaction1,transaction);
    }

}
