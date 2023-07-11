package com.apper.theblogservice;

import com.apper.theblogservice.Payload.BloggerDetails;
import com.apper.theblogservice.Payload.CreateBloggerRequest;
import com.apper.theblogservice.Payload.CreateBloggerResponse;
import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.service.BloggerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("blogger")
public class BloggerAPI {

    private final BloggerService bloggerService;

    public BloggerAPI(BloggerService bloggerService) {
        this.bloggerService = bloggerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBloggerResponse createBlogger(@RequestBody @Valid CreateBloggerRequest request){
        System.out.println(request);

        Blogger createdBlogger = bloggerService.createBlogger(request.getEmail(), request.getName(), request.getPassword());

        CreateBloggerResponse response = new CreateBloggerResponse();
        response.setId(createdBlogger.getId());
        response.setDateRegistration(createdBlogger.getCreatedAt());

        return response;
    }

    @GetMapping("{id}")
    public BloggerDetails getBlogger(@PathVariable String id) {
        Blogger blogger = bloggerService.getBlogger(id);

        BloggerDetails bloggerDetails = new BloggerDetails();
        bloggerDetails.setId(blogger.getId());
        bloggerDetails.setName(blogger.getName());
        bloggerDetails.setEmail(blogger.getEmail());
        bloggerDetails.setDateRegistration(blogger.getCreatedAt());

        return bloggerDetails;
    }
}
