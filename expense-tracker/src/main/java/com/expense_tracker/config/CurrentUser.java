package com.expense_tracker.config;

import com.expense_tracker.entities.User;
import com.expense_tracker.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {

    public User getCurrentUserData(){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return customUserDetails.getUser();
    }

}
