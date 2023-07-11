package com.apper.theblogservice.Payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateBloggerRequest {

    @NotBlank(message = "`email` is required") //combination of not null and not empty
    @Email(message = "Invalid email format") //built in made easy email verification
    private String email;

    @Size(min = 5, message = "`password` must be at least 5 characters") //min or max
    @NotBlank(message = "`password` is required") //no hierarchy, follow logic in checking
    private String password;

    //@JsonProperty("first_name") //ensures postman would know the parameter despite diff in field name
    @NotBlank(message = "`Name` is required")
    private String name;
}
