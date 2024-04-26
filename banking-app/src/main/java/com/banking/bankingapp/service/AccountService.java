package com.banking.bankingapp.service;

import com.banking.bankingapp.dto.AccountDto;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    Optional<AccountDto> getAccountById(Long id) throws AccountNotFoundException;

    AccountDto deposit(Long id, double amount) throws AccountNotFoundException;

    AccountDto withdraw(Long id, double amount) throws AccountNotFoundException;

    List<AccountDto> getAllAccounts();


    void deleteAccount(Long id) throws AccountNotFoundException;
}
