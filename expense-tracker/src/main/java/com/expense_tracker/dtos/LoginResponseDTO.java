package com.expense_tracker.dtos;

import com.expense_tracker.enums.UserRole;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class LoginResponseDTO {

    private String token;
    private UserRole role;

}
