package com.slippery.greenroots.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
    private LocalDateTime joinedOn;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Organization> organizationCreated;
    @ManyToMany
    private List<Organization> organizationsJoined;

}
