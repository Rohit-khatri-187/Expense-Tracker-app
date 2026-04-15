package com.expense_tracker.dtos;

import com.expense_tracker.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationResponse {

    private Long id;
    private String fullName;
    private String email;
    private UserRole role;
    private LocalDateTime createdAt;

}
