package com.example.account.service;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateAccountResponseLastUpdated {
    private LocalDateTime lastUpdated;
}