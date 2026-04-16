package com.expense_tracker.services;

import com.expense_tracker.dtos.LoginRequest;
import com.expense_tracker.dtos.LoginResponse;
import com.expense_tracker.dtos.RegistrationRequest;
import com.expense_tracker.dtos.RegistrationResponse;

public interface AuthService {

    public RegistrationResponse register(RegistrationRequest request);

    public LoginResponse login(LoginRequest request);

}
