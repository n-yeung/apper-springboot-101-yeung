package com.apper.theblogservice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;

import java.time.LocalDateTime;

@Entity
@Table(name="BLOG")
@Data
public class Blog {

    @Id
    @Column(name = "BLOG_ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
   // @JoinColumn(name = "BLOGGER_ID")
    private Blogger blogger;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "BODY")
    private String body;

    @Column(name = "CREATED_AT")
    private LocalDateTime dateCreated;

    @Column(name = "LAST_UPDATE")
    private LocalDateTime lastUpdated;

    @PrePersist
    public void setCreatedAT() {
        LocalDateTime now = LocalDateTime.now();
        dateCreated = now;
        lastUpdated = now;
    }

    @PreUpdate
    public void setUpdatedAt() {
        LocalDateTime now = LocalDateTime.now();
        lastUpdated = now;
    }

}