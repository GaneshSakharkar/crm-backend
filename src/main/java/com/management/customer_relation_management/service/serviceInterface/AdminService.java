package com.management.customer_relation_management.service.serviceInterface;

import java.util.UUID;

import com.management.customer_relation_management.entities.Admin;

public interface AdminService {
    Admin getAdminByEmail(String email);

    Admin saveAdmin(Admin admin);

    Admin getAdminById(Long id);

    Admin getAdminByJwt(String jwt);
}
