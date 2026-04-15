package com.expense_tracker.mapper;

import com.expense_tracker.dtos.RegistrationResponse;
import com.expense_tracker.entities.User;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public RegistrationResponse ToRegistrationResponse( User user){

        return RegistrationResponse.builder()
                .id(user.getUserId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();

    }

}
