package com.management.customer_relation_management.JwtSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.management.customer_relation_management.entities.Admin;
import com.management.customer_relation_management.service.serviceImpl.AdminServiceImpl;

@Service
public class AdminCustomDetail implements UserDetailsService {

    @Autowired
    private AdminServiceImpl adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminService.getAdminByEmail(username);

        if (admin == null) {
            throw new UsernameNotFoundException("Invalid credentials");
        }
        
        if (admin.getPassword() == null) {
            throw new UsernameNotFoundException("Invalid credentials: password not set");
        }

        // The password here is already encoded with BCrypt when saved
        return User.builder()
                .username(admin.getEmail())
                .password(admin.getPassword()) // Already BCrypt-hashed
                //.roles(admin.getRole().toString()) // Spring auto-prefixes with "ROLE_"
                .build();
    }
}
