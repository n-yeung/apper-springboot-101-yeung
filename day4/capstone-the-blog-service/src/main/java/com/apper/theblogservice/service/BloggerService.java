package com.apper.theblogservice.service;

import com.apper.theblogservice.exceptions.blogNotFoundException;
import com.apper.theblogservice.exceptions.invalidBloggerIdException;
import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.repository.BlogRepository;
import com.apper.theblogservice.repository.BloggerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BloggerService {

    private final BloggerRepository bloggerRepository;

    private final BlogRepository blogRepository;

    public BloggerService(BloggerRepository bloggerRepository, BlogRepository blogRepository) {
        this.bloggerRepository = bloggerRepository;
        this.blogRepository = blogRepository;
    }

    public Blogger createBlogger(String email, String name, String password) {
        Blogger blogger = new Blogger();
        blogger.setEmail(email);
        blogger.setName(name);
        blogger.setPassword(password);
        return bloggerRepository.save(blogger);
    }

    public boolean isIdExisting(String id) {
        return bloggerRepository.existsById(id);
    }

    public Blogger getBlogger(String id) {
        Optional<Blogger> bloggerResult = bloggerRepository.findById(id);//find blogger by its id
        return bloggerResult.get();
    }

    public boolean isEmailAlreadyUsed(String email) {
        return bloggerRepository.existsByEmail(email);
    }

    public List<Blogger> getAllBlogger() {
        return (List<Blogger>) bloggerRepository.findAll();
    }

    public Blog createBlog(String title, String body, String bloggerId) {
        Blogger blogger = getBlogger(bloggerId);

        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setBody(body);
        blog.setDateCreated(LocalDateTime.now());
        blog.setLastUpdated(LocalDateTime.now());
        blog.setBlogger(blogger);
        return blogRepository.save(blog);
    }

    public Blog getBlog(String blogId) throws blogNotFoundException {
        Optional<Blog> blogResult = blogRepository.findById(blogId);//find blogger by its id

        if(blogResult.isEmpty()){
            throw new blogNotFoundException("Blog not found with Id: " + blogId);
        }

        return blogResult.get();
    }
    public Blog updateBlog(String blogId, String title, String body) throws blogNotFoundException{
        Blog blog = getBlog(blogId);
        blog.setTitle(title);
        blog.setBody(body);
        return blogRepository.save(blog);
    }

    public List<Blog> getAllBlog() {
        return (List<Blog>) blogRepository.findAll();
    }

    public List<Blog> getBlogsByBlogger(String bloggerId) throws invalidBloggerIdException {
        if (!bloggerRepository.existsById(bloggerId)) {
            throw new invalidBloggerIdException("No registered blogger found with blogger id");
        }
        Optional<Blogger> bloggerResult = bloggerRepository.findById(bloggerId);
        if (bloggerResult.isPresent()) {
            Blogger blogger = bloggerResult.get();
            return blogger.getBlogs();
        }
        return new ArrayList<>();
    }
}
