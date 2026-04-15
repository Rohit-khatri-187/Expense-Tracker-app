package com.expense_tracker.services.impl;

import com.expense_tracker.entities.User;
import com.expense_tracker.enums.UserRole;
import com.expense_tracker.mapper.Mapper;
import com.expense_tracker.repositories.UserRepository;
import com.expense_tracker.dtos.RegistrationRequest;
import com.expense_tracker.dtos.RegistrationResponse;
import com.expense_tracker.services.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Mapper mapper;

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
}
