package com.ritik.scm.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.task.TaskExecutionProperties.Simple;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    private String userId;
    @Column(name = "user_name", nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(length = 128)
    private String about;
    private String profilePic;
    private String phoneNumber;

    // information
    private boolean enabled = false;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    // SELF, GOOGLE, FACEBOOK, GITHUB, ...
    @Enumerated(EnumType.STRING) // enum('SELF', 'GOOGLE', 'GITHUB') in sql schema
    private Providers provider = Providers.SELF;
    private String providerUserId;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList = new ArrayList<>();

    // Cascade -> if user updates, its contact will also get updated
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contactList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // list of roles[USER, ADMIN]
        // COLLECTION OF sIMPLEgRANTEDaUTHERITY[roles{ADMIN, USER}]
        Collection<SimpleGrantedAuthority> roles = roleList.stream().map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());

        return roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

}
