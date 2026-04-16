package com.expense_tracker.services.impl;

import com.expense_tracker.dtos.LoginRequest;
import com.expense_tracker.dtos.LoginResponse;
import com.expense_tracker.entities.User;
import com.expense_tracker.enums.UserRole;
import com.expense_tracker.exceptions.AccountLockedException;
import com.expense_tracker.exceptions.InvalidCredentialException;
import com.expense_tracker.mapper.Mapper;
import com.expense_tracker.repositories.UserRepository;
import com.expense_tracker.dtos.RegistrationRequest;
import com.expense_tracker.dtos.RegistrationResponse;
import com.expense_tracker.security.CustomUserDetails;
import com.expense_tracker.security.JwtUtils;
import com.expense_tracker.services.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Mapper mapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public RegistrationResponse register(RegistrationRequest request) {

        log.info("Inside the register service () ");

        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("User Already exist with this email");
        }

        User newUser = new User();
        newUser.setFullName(request.getFullName());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        if(request.getRole() == null ){
            newUser.setRole(UserRole.USER);
        }else {
            newUser.setRole(request.getRole());
        }

        newUser.setActive(true);
        newUser.setAccountBlocked(false);

        User savedUser = userRepository.save(newUser);

        return mapper.ToRegistrationResponse(savedUser);
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        log.info("Inside the login service () : ");

        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = customUserDetails.getUser();

            String token = jwtUtils.generateToken(user.getEmail());

            return LoginResponse.builder()
                    .token(token)
                    .role(user.getRole())
                    .build();

        } catch (LockedException e) {
            throw new AccountLockedException("User account is blocked");
        }catch (BadCredentialsException e){
            throw new InvalidCredentialException("Invalid credentials");
        }

    }
}
