package com.example.account.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

@RestController
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAccountResponse createAccount(@RequestBody CreateAccountRequest request) {
        Account account= accountService.create(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword());

        CreateAccountResponse response = new CreateAccountResponse();
        response.setVerificationCode(account.getVerificationCode());

        return response;
    }

    @GetMapping("{accountId}")
    public GetAccountResponse getAccount(@PathVariable String accountId) {
        Account account = accountService.get(accountId);

        return createGetAccountResponse(account);
    }

    @GetMapping
    public List<GetAccountResponse> getAllAccounts() {
        List<GetAccountResponse> responseList = new ArrayList<>();

        for (Account account : accountService.getAll()) {
            GetAccountResponse response = createGetAccountResponse(account);
            responseList.add(response);
        }

        return responseList;
    }

    private GetAccountResponse createGetAccountResponse(Account account) {
        GetAccountResponse response = new GetAccountResponse();
        response.setBalance(account.getBalance());
        response.setFirstName(account.getFirstName());
        response.setLastName(account.getLastName());
        response.setUsername(account.getUsername());
        response.setRegistrationDate(account.getCreationDate());
        response.setAccountId(account.getId());
        return response;
    }

    @PutMapping("{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateAccountResponseLastUpdated updateAccount (@PathVariable String accountId, @RequestBody CreateAccountRequest request) {
        accountService.update(accountId, request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword());
        UpdateAccountResponseLastUpdated updateResponse = new UpdateAccountResponseLastUpdated();
        updateResponse.setLastUpdated(LocalDateTime.now());
        return updateResponse;
    }

    @DeleteMapping("{accountId}") //the one in curly braces is a dynamic value, if no curly braces it will not be unique
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable String accountId) { //path variable since you are pasting the id at the path
        accountService.delete(accountId);
    }

}