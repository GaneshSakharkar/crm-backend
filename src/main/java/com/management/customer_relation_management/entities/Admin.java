package com.management.customer_relation_management.entities;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.management.customer_relation_management.role.Roles;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String name;
    private String contact;
    private String password;
    @Enumerated(EnumType.STRING)
    private Roles role = Roles.ADMIN;
    private String registationDate;
    private String loginDate;

    // === Getters and Setters ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getRegistationDate() {
        return registationDate;
    }

    public void setRegistationDate(String registationDate) {
        this.registationDate = registationDate;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    // === UserDetails Implementation ===

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;  // Use email as the username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Can implement logic if needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Can implement logic if needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Can implement logic if needed
    }

    @Override
    public boolean isEnabled() {
        return true; // You can add an "active" flag in the entity if needed
    }
}
