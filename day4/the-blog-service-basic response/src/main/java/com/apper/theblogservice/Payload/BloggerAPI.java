package com.apper.theblogservice.Payload;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("blogger")
public class BloggerAPI {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBloggerResponse createBlogger(@RequestBody @Valid CreateBloggerRequest request){
        System.out.println(request);

        CreateBloggerResponse response = new CreateBloggerResponse();
        response.setId("dummy_id");
        response.setDateRegistration(LocalDateTime.now());

        return response;
    }
}
