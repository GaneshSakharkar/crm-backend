package com.management.customer_relation_management.service.serviceImpl;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.management.customer_relation_management.entities.Admin;
import com.management.customer_relation_management.helper.DateTimeFormatter;
import com.management.customer_relation_management.repository.AdminRepo;
import com.management.customer_relation_management.role.Roles;

@Component
public class DataInitilizer implements CommandLineRunner{
	
	@Autowired
	AdminRepo oAdminRepo;

	@Override
	public void run(String... args) throws Exception {
		
		String superAdminEmail = "superadmin@gmailcom";
		String name = "SUPER ADMIN";
		
		boolean isExist = oAdminRepo.findByEmailAndName(superAdminEmail,name).isPresent();
		//contact,email,login_date,name,password,registation_date,role,id) values (?,?,?,?,?,?,?,?)]
		
		if(!isExist) {
            Admin superAdmin = new Admin();
            
            superAdmin.setEmail(superAdminEmail);
            superAdmin.setContact("8793414423");
            superAdmin.setName("SUPER ADMIN");
            superAdmin.setPassword(new BCryptPasswordEncoder().encode("admin@123"));
            superAdmin.setRegistationDate(DateTimeFormatter.format(LocalDateTime.now()));
            superAdmin.setRole(superAdmin.getRole());

            oAdminRepo.save(superAdmin);
            System.out.println("Super Admin created successfully.");
        } else {
            System.out.println("Super Admin already exists. No action taken.");
        }
		
	}

}
