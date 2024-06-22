package com.htek.controller;

import com.htek.dto.CustomResponse;
import com.htek.dto.ResponseDTO;
import com.htek.model.*;
import com.htek.service.AccountService;
import com.htek.util.CommonUtils;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import jakarta.inject.Inject;

import javax.annotation.security.PermitAll;
import java.util.*;

@Controller("/emp/account")
public class AccountController {

    @Inject
    private AccountService accountService;

    private ResponseDTO responseDTO = new ResponseDTO();

    @Post("/open")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<CustomResponse> createAccount(@Body Account account) {
        return HttpResponse.ok(new CustomResponse(accountService.createAccount(account)));
    }

    @Get("/show")
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER"})
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<List<Account>> getAccount() {
        List<Account> acc = accountService.getAllAccount();

        if (acc.size() >= 0) {
            return HttpResponse.ok().body(acc);
        }
        return HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Get("/{accountNumber}")
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER"})
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<CustomResponse> getAccountByAccNumber(@PathVariable long accountNumber) {
        return HttpResponse.ok(new CustomResponse(accountService.getAccountByAccNumber(accountNumber)));
    }

    @Delete("/{accountNumber}")
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER"})
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<CustomResponse> deleteAccountByAccNumber(@PathVariable long accountNumber) {
        return HttpResponse.ok(new CustomResponse(accountService.deleteAccountByAccNumber(accountNumber)));
    }


    @Post("/withdraw")
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER"})
    public HttpResponse<?> withdraw(@QueryValue long accountNumber, @QueryValue long withdrawAmount) {

        Account account = (Account) accountService.getAccountByAccNumber(accountNumber);

        account.setBalance(account.getBalance() - withdrawAmount);
        Transaction transaction = new Transaction(account.getAccountNumber(), withdrawAmount, Transaction_Type.DEBIT, CommonUtils.formatDate(new Date(), CommonUtils.DATE_TIME_FORMATE_11));
        accountService.save(account);
        accountService.saveTransaction(transaction);
        return HttpResponse.ok(accountService.getAccountByAccNumber(accountNumber));
    }

    @Post("/deposit")
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER"})
    public HttpResponse<?> deposit(@QueryValue long accountNumber, @QueryValue long depositAmount) {

        Account account = (Account) accountService.getAccountByAccNumber(accountNumber);

        account.setBalance(account.getBalance() + depositAmount);
        Transaction transaction = new Transaction(account.getAccountNumber(), depositAmount, Transaction_Type.CREDIT, CommonUtils.formatDate(new Date(), CommonUtils.DATE_TIME_FORMATE_11));
        accountService.save(account);
        accountService.saveTransaction(transaction);
        return HttpResponse.ok(accountService.getAccountByAccNumber(accountNumber));
    }

    @PermitAll
    @Get("/trans/stmt/")
    public HttpResponse<?> accountState(@QueryValue long accountNumber, @QueryValue String startDate,
                                        @QueryValue String endDate) {
        HashMap<String, List> hmap = new HashMap<>();
        List<Transaction> updList = new ArrayList<>();

        try {
            List<Transaction> txn = accountService.account();
            Account account = (Account) accountService.getAccountByAccNumber(accountNumber);
            for (int i = 0; i < txn.size(); i++) {
                if (txn.get(i).getAccountNumber() == (accountNumber)) {
                    Date date = CommonUtils.dateFormat(txn.get(i).getTimeStamp());
                    if (CommonUtils.isWithinRange(date, CommonUtils.dateFormat(startDate),
                            CommonUtils.dateFormat(endDate))) {
                        updList.add(txn.get(i));
                    }
                }
            }
            hmap.put("Account balance", Arrays.asList(account.getBalance()));
            hmap.put("Transaction History", updList);
            responseDTO.setHistory(hmap);
            responseDTO.setResponse("SUCCESS");
            return HttpResponse.ok(responseDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Post("/transfer")
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER"})
    public HttpResponse<CustomResponse> transfer(@Body TransferDetails transferDetails) throws InterruptedException {
        return HttpResponse.ok(new CustomResponse(accountService.transfer(transferDetails)));
    }
}