package com.example.account.service;

import lombok.Data;

import java.time.LocalDateTime;

@Data //sets getter and setters already
public class Account {
    private String id;
    private Double balance;

    private String firstName;
    private String lastName;
    private String username;
    private String clearPassword;
    private String verificationCode;

    private LocalDateTime creationDate;
    private LocalDateTime lastUpdated;
}