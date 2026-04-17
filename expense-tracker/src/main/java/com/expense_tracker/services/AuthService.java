package com.expense_tracker.services;

import com.expense_tracker.dtos.LoginRequestDTO;
import com.expense_tracker.dtos.LoginResponseDTO;
import com.expense_tracker.dtos.RegistrationRequestDTO;
import com.expense_tracker.dtos.RegistrationResponseDTO;

public interface AuthService {

    public RegistrationResponseDTO register(RegistrationRequestDTO request);

    public LoginResponseDTO login(LoginRequestDTO request);

}
