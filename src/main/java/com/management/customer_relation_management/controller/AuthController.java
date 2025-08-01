package com.management.customer_relation_management.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.customer_relation_management.JwtSecurity.AdminCustomDetail;
import com.management.customer_relation_management.JwtSecurity.JwtProvider;
import com.management.customer_relation_management.JwtSecurity.ManagerCustomDetail;
import com.management.customer_relation_management.entities.Admin;
import com.management.customer_relation_management.entities.Manager;
import com.management.customer_relation_management.helper.DateTimeFormatter;
import com.management.customer_relation_management.request.LoginRequest;
import com.management.customer_relation_management.request.RegisterRequest;
import com.management.customer_relation_management.response.LoginResponse;
import com.management.customer_relation_management.response.SuccessResponse;
import com.management.customer_relation_management.role.Roles;
import com.management.customer_relation_management.service.serviceImpl.AdminServiceImpl;
import com.management.customer_relation_management.service.serviceImpl.ManagerServiceImpl;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = { "http://localhost:5173/", "http://localhost:5174/" })
public class AuthController {

    @Autowired
    private AdminServiceImpl adminServiceImpl;

    @Autowired
    private ManagerServiceImpl managerServiceImpl;

    @Autowired
    private AdminCustomDetail adminCustomDetail;

    @Autowired
    private ManagerCustomDetail managerCustomDetail;

    @PostMapping("/admin/register")
    public ResponseEntity<SuccessResponse> registerAdmin(@RequestBody RegisterRequest adminRegisterRequest) {

        SuccessResponse res = new SuccessResponse();

        Admin a = this.adminServiceImpl.getAdminByEmail(adminRegisterRequest.getEmail());

        if (a != null) {
            res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            res.setStatusCode(500);
            res.setMessage("Admin Already Exits");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }

        Admin admin = new Admin();
        admin.setEmail(adminRegisterRequest.getEmail());
        admin.setContact(adminRegisterRequest.getContact());
        admin.setName(adminRegisterRequest.getName());
        admin.setPassword(new BCryptPasswordEncoder().encode(adminRegisterRequest.getPassword()));
        admin.setRegistationDate(DateTimeFormatter.format(LocalDateTime.now()));
        admin.setRole(admin.getRole());

        try {
            this.adminServiceImpl.saveAdmin(admin);

            res.setStatus(HttpStatus.OK);
            res.setStatusCode(200);
            res.setMessage("Admin Registered Successfully");
            return ResponseEntity.of(Optional.of(res));

        } catch (Exception e) {
            e.printStackTrace();

            res.setMessage(e.toString());
            res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            res.setStatusCode(500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @PostMapping("/manager/register")
    public ResponseEntity<SuccessResponse> registerManager(@RequestBody RegisterRequest registerRequest) {

        SuccessResponse res = new SuccessResponse();

        Manager m = this.managerServiceImpl.getManagerByEmail(registerRequest.getEmail());

        if (m != null) {
            res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            res.setStatusCode(500);
            res.setMessage("manager Already Exits");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }

        Manager manager = new Manager();
        manager.setEmail(registerRequest.getEmail());
        manager.setContact(registerRequest.getContact());
        manager.setName(registerRequest.getName());
        manager.setPassword(new BCryptPasswordEncoder().encode(registerRequest.getPassword()));
        manager.setRegistationDate(DateTimeFormatter.format(LocalDateTime.now()));

        try {
            this.managerServiceImpl.saveManager(manager);

            res.setStatus(HttpStatus.OK);
            res.setStatusCode(200);
            res.setMessage("Registered Successfull. Approval request sent to Admin !");
            return ResponseEntity.of(Optional.of(res));

        } catch (Exception e) {
            e.printStackTrace();

            res.setMessage(e.toString());
            res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            res.setStatusCode(500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

//    @PostMapping("/admin/login")
//    public ResponseEntity<LoginResponse> adminLogin(@RequestBody LoginRequest loginRequest) {
//        LoginResponse res = new LoginResponse();
//
//        Admin admin = this.adminServiceImpl.getAdminByEmail(loginRequest.getEmail());
//
//        if (admin == null) {
//            res.setStatus(HttpStatus.UNAUTHORIZED);
//            res.setMessage("Invalid email or password");
//            res.setStatusCode(401);
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
//        }
//
//        UserDetails adminDetails = adminCustomDetail.loadUserByUsername(loginRequest.getEmail());
//        // Validate the password
//        boolean isPasswordValid = new BCryptPasswordEncoder().matches(
//                loginRequest.getPassword(),
//                adminDetails.getPassword() // Encoded password
//        );
//
//        if (!isPasswordValid) {
//            res.setStatus(HttpStatus.FORBIDDEN);
//            res.setMessage("Invalid email or password");
//            res.setStatusCode(403);
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
//        }
//
//        // Authenticate the admin
//        Authentication authentication = adminAuthenticate(adminDetails.getUsername(), loginRequest.getPassword());
//
//        // Generate JWT token
//        String token = JwtProvider.generateJwtToken(authentication);
//
//        admin.setLoginDate(DateTimeFormatter.format(LocalDateTime.now()));
//        this.adminServiceImpl.saveAdmin(admin);
//
//        // Prepare response
//        res.setToken(token);
//        res.setStatusCode(200);
//        res.setStatus(HttpStatus.OK);
//        res.setMessage("Admin login successful");
//
//        return ResponseEntity.of(Optional.of(res));
//    }

    @PostMapping("/admin/login")
    public ResponseEntity<LoginResponse> adminLogin(@RequestBody LoginRequest loginRequest) {
        LoginResponse res = new LoginResponse();

        // Try finding in Admin table
        Admin admin = adminServiceImpl.getAdminByEmail(loginRequest.getEmail());

        if (admin != null && admin.getRole() == Roles.ADMIN) {
            return handleAdminLogin(admin, loginRequest, res);
        }
        
        // If not found in either
        res.setStatus(HttpStatus.UNAUTHORIZED);
        res.setMessage("Invalid email or password");
        res.setStatusCode(401);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }
    
    private ResponseEntity<LoginResponse> handleAdminLogin(Admin admin, LoginRequest request, LoginResponse res) {
        boolean isPasswordValid = new BCryptPasswordEncoder().matches(
                request.getPassword(), admin.getPassword());

        if (!isPasswordValid) {
            return forbidden(res, "Invalid password");
        }

        UserDetails userDetails = adminCustomDetail.loadUserByUsername(admin.getEmail());
        Authentication auth = adminAuthenticate(userDetails.getUsername(), request.getPassword());
        String token = JwtProvider.generateJwtToken(auth);

        admin.setLoginDate(DateTimeFormatter.format(LocalDateTime.now()));
        adminServiceImpl.saveAdmin(admin);

        res.setToken(token);
        res.setStatus(HttpStatus.OK);
        res.setStatusCode(200);
        res.setMessage("Admin login successful");
        return ResponseEntity.ok(res);
    }


    private ResponseEntity<LoginResponse> forbidden(LoginResponse res, String message) {
        res.setStatus(HttpStatus.FORBIDDEN);
        res.setMessage(message);
        res.setStatusCode(403);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
    }

    
    private Authentication adminAuthenticate(String email, String password) {
        UserDetails details = adminCustomDetail.loadUserByUsername(email);
        if (details == null) {
            throw new BadCredentialsException("invalid admin");
        }

        return new UsernamePasswordAuthenticationToken(details, password, details.getAuthorities());

    }

    @PostMapping("/manager/login")
    public ResponseEntity<LoginResponse> signin(@RequestBody LoginRequest loginRequest) {
        LoginResponse res = new LoginResponse();

        Manager manager = this.managerServiceImpl.getManagerByEmail(loginRequest.getEmail());
        
        if (manager != null && manager.getRole() == Roles.MANAGER) {
            return handleManagerLogin(manager, loginRequest, res);
        }

        if (manager == null) {
            res.setStatus(HttpStatus.UNAUTHORIZED);
            res.setMessage("Invalid email or password");
            res.setStatusCode(401);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }

    // authentication for user
    private Authentication userAuthenticate(String email, String password) {
        UserDetails details = managerCustomDetail.loadUserByUsername(email);
        if (details == null) {
            throw new BadCredentialsException("invalid manager");
        }
        return new UsernamePasswordAuthenticationToken(details, password, details.getAuthorities());

    }
    
    private ResponseEntity<LoginResponse> handleManagerLogin(Manager manager, LoginRequest request, LoginResponse res) {
        // Check if manager is approved and active
        if (!manager.isApproved()) {
            return forbidden(res, "Manager not approved yet!");
        }

        if (!manager.isActive()) {
            return forbidden(res, "Manager is not active yet!");
        }

        // Validate password
        boolean isPasswordValid = new BCryptPasswordEncoder().matches(
            request.getPassword(), manager.getPassword()
        );

        if (!isPasswordValid) {
            return forbidden(res, "Invalid password");
        }

        // Authenticate and generate token
        UserDetails userDetails = managerCustomDetail.loadUserByUsername(manager.getEmail());
        Authentication auth = userAuthenticate(userDetails.getUsername(), request.getPassword());
        String token = JwtProvider.generateJwtToken(auth);

        // Update login time
        manager.setLoginDate(DateTimeFormatter.format(LocalDateTime.now()));
        managerServiceImpl.saveManager(manager);

        // Success response
        res.setToken(token);
        res.setStatus(HttpStatus.OK);
        res.setStatusCode(200);
        res.setMessage("Manager login successful");
        return ResponseEntity.ok(res);
    }


}
