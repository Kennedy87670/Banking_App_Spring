package com.banking.bankingapp.service.impl;

import com.banking.bankingapp.dto.AccountDto;
import com.banking.bankingapp.dto.mapper.AccountMapper;
import com.banking.bankingapp.entity.Account;
import com.banking.bankingapp.exceptionHandlers.InsufficientAccountBalance;
import com.banking.bankingapp.repository.AccountRepository;
import com.banking.bankingapp.service.AccountService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {




    private final AccountRepository accountRepository;
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }


@Override
public Optional<AccountDto> getAccountById(Long id) throws AccountNotFoundException {
    return Optional.ofNullable(accountRepository.findById(id)
            .map(AccountMapper::mapToAccountDto)
            .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + id)));
}


    @Override
    public AccountDto deposit(Long id, double amount) throws AccountNotFoundException {
        Account account=  accountRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("Account not found with id: " + id));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount =accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) throws AccountNotFoundException {

            Account account = accountRepository.findById(id)
                    .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + id));

            if (account.getBalance() < amount) {
                throw new InsufficientAccountBalance("Insufficient amount");
            }

            double total = account.getBalance() - amount;
            account.setBalance(total);
            Account savedAccount = accountRepository.save(account);
            return AccountMapper.mapToAccountDto(savedAccount);

    }



    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(AccountMapper::mapToAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) throws AccountNotFoundException {
        Account account=  accountRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("Account not found with id: " + id));

        accountRepository.deleteById(id);
    }
}
