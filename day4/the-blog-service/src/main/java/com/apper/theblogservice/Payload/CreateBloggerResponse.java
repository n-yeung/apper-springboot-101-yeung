package com.apper.theblogservice.Payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CreateBloggerResponse {
    private String id;

    @JsonProperty("date_registration")
    private LocalDateTime dateRegistration;
}