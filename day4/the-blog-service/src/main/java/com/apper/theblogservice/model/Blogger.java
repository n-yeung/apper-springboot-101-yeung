package com.apper.theblogservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "BLOGGER")
@Data
public class Blogger {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "Complete_Name")
    private String name;

    @Column(name = "Password")
    private String password;

    @Column(name = "Email")
    private String email;

    @Column(name = "Created_At")
    private LocalDateTime createdAt;

    @Column(name = "Last_Updated")
    private LocalDateTime lastUpdated;

    @PrePersist
    public void setCreatedAt(){
        createdAt = LocalDateTime.now();
        //to make a timestamp at the end
        lastUpdated = LocalDateTime.now();
    }

    @PreUpdate
    public void setLastUpdated(){
        lastUpdated =   LocalDateTime.now();
    }

}
