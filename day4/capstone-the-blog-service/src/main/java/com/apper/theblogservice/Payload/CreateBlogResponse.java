package com.apper.theblogservice.Payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateBlogResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("blogger_id")
    private String bloggerId;

    @JsonProperty("created_at")
    private LocalDateTime dateCreated;

    @JsonProperty("last_updated")
    private LocalDateTime lastUpdated;
}