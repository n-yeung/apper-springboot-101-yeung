package com.example.account.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service //makes it a bean so no need to make many more instances
public class AccountService {

    private List<Account> accounts = new ArrayList<>();

    private final IdGeneratorService generateIdService;

    public AccountService(IdGeneratorService generateIdService) {
        this.generateIdService = generateIdService;
    }

    public Account create(String firstName, String lastName, String username, String clearPassword) {
        Account account = new Account();

        String id = generateIdService.getNextId();
        System.out.println("Generated id: " + id);
        account.setId(id);
        account.setBalance(1_000.0);
        LocalDateTime now = LocalDateTime.now();
        account.setCreationDate(now);
        account.setLastUpdated(now);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setUsername(username);
        account.setClearPassword(clearPassword);
        account.setVerificationCode(generateIdService.generateRandomCharacters(6));

        accounts.add(account);

        return account;
    }

    public Account get(String accountId) {
        for (Account account : accounts) {
            if (account.getId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

    public List<Account> getAll() {
        return accounts;
    }

    public void update(String accountId, String firstName, String lastName, String username, String clearPassword) {
        Account account = get(accountId);

        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setUsername(username);
        account.setClearPassword(clearPassword);
        account.setLastUpdated(LocalDateTime.now());
        accounts.set(accounts.indexOf(account), account);
    }

    public void delete(String accountId) {
        Account account = get(accountId);
        accounts.remove(account);
    }
}