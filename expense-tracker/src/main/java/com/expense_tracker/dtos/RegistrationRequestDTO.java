package com.expense_tracker.dtos;

import com.expense_tracker.enums.UserRole;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationRequestDTO {

    private String fullName;
    private String email;

    @Size(min = 8 , max = 30 , message = "Password should be between 8 and 30 characters ")
    private String password;
    private UserRole role;

}
