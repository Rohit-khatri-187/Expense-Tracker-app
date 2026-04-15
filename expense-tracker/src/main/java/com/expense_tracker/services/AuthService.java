package com.expense_tracker.services;

import com.expense_tracker.dtos.RegistrationRequest;
import com.expense_tracker.dtos.RegistrationResponse;

public interface AuthService {

    public RegistrationResponse register(RegistrationRequest request);

}
