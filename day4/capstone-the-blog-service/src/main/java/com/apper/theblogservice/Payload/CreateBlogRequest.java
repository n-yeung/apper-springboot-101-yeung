package com.apper.theblogservice.Payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateBlogRequest {

    @JsonProperty("title")
    @NotBlank(message = "Title is required.")
    private String title;

    @JsonProperty("body")
    @NotBlank(message = "Body is required.")
    private String body;

    @JsonProperty("blogger_id")
    @NotBlank(message = "Blogger ID is required.")
    private String bloggerId;

}