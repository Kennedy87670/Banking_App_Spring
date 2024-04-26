package com.banking.bankingapp.controller;

import com.banking.bankingapp.dto.AccountDto;
import com.banking.bankingapp.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountController {

    private AccountService accountService;


    //Add Accounts REST API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody  AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }


//get account REST API
    @GetMapping("/{id}")
    public ResponseEntity<Optional<AccountDto>> getAccountbyId(@PathVariable  Long id) throws AccountNotFoundException {
        Optional<AccountDto> accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

// Deposit REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<String> deposit(@PathVariable Long id,
                                          @RequestBody Map<String,  Double> request) throws AccountNotFoundException {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id, request.get("amount"));
        return ResponseEntity.ok("You have been credited with " + amount + " in your account with ID " + accountDto.getId());


    }

//    Withdraw REST API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<String> withdraw (@PathVariable Long id,
                                            @RequestBody Map<String, Double >request) throws AccountNotFoundException {
        double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok("You have been debited with " + amount + " in your account with ID " + accountDto.getId());
    }

//    Get All Accounts REST API
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts( ){
        List<AccountDto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);

    }

//    Delete account REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) throws AccountNotFoundException {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account with ID " + id + " is deleted successfully");
    }

}
