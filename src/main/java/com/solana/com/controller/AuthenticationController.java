package com.solana.com.controller;


import com.solana.com.dao.AccountRepository;
import com.solana.com.dto.AccountDTO;
import com.solana.com.dto.AuthenticationRequest;
import com.solana.com.dto.UsersDTO;
import com.solana.com.respone.ApiResponse;
import com.solana.com.service.AccountService;
import com.solana.com.service.JwtUtil;
import com.solana.com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AccountService accountService;
    @Autowired
    UsersService usersService;
    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        if (userDetails != null && passwordEncoder.matches(authenticationRequest.getPassword(), userDetails.getPassword())) {
            String accessToken = jwtUtil.generateToken(userDetails);
            String refreshToken = jwtUtil.generateRefreshToken(userDetails);

            Map<String, String> tokens = new HashMap<>();
            tokens.put("accessToken", accessToken);
            tokens.put("refreshToken", refreshToken);

            List<String> roles = jwtUtil.extractRoles(accessToken);

            for (String role : roles) {
                System.out.println("Role: " + role);
            }
            return ResponseEntity.ok(tokens);
        } else {
            throw new BadCredentialsException("Incorrect username or password");
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> tokenRequest) {
        String refreshToken = tokenRequest.get("refreshToken");

        try {
            String username = jwtUtil.extractUsername(refreshToken);

            if (username != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                // Kiểm tra tính hợp lệ của refresh token
                if (jwtUtil.validateToken(refreshToken, userDetails)) {
                    String newAccessToken = jwtUtil.generateToken(userDetails);
                    Map<String, String> response = new HashMap<>();
                    response.put("accessToken", newAccessToken);
                    return ResponseEntity.ok(response);
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<UsersDTO>> getUserbyUsername(@RequestParam("username") String username) throws Exception {
            ApiResponse<UsersDTO> response = new ApiResponse<>();
            AccountDTO accountDTO = accountService.getAccountById(username);
            System.out.println(accountDTO.getUser().getId());
            UsersDTO usersDTO = usersService.findUser(accountDTO.getUser().getId());
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Success");
            response.setResult(usersDTO);
            return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam(value = "firstname") String firstname,
                                      @RequestParam(value = "lastname") String lastname,
                                      @RequestParam(value = "email") String email,
                                      @RequestParam(value = "username") String username,
                                      @RequestParam(value = "password") String password,
                                      @RequestParam(value = "birthday", defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthday,
                                      @RequestParam(value = "phonenumber") String phonenumber) {
        // Kiểm tra các trường không được trống hoặc null
        if (firstname == null || firstname.isEmpty() ||
                lastname == null || lastname.isEmpty() ||
                email == null || email.isEmpty() ||
                username == null || username.isEmpty() ||
                password == null || password.isEmpty() ||
                phonenumber == null || phonenumber.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("All fields are required and must not be null or empty.");
        }

        // Kiểm tra tính duy nhất của username
        if (accountService.getAccountById(username)!=null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already taken.");
        }

        // Kiểm tra tính duy nhất của email
        if (usersService.existsByEmail(email)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already in use.");
        }

        try {
            UsersDTO usersDTO = usersService.save(new UsersDTO(null, firstname, lastname, "defaultAvatar", birthday, email, "", phonenumber, "", ""));
            accountService.save(new AccountDTO(username, passwordEncoder.encode(password), usersDTO.getId(), null));
            return ResponseEntity.status(HttpStatus.OK).body("Successful register");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }
    }
}
