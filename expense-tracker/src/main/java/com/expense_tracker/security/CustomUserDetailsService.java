package com.expense_tracker.security;

import com.expense_tracker.repositories.UserRepository;
import com.expense_tracker.entities.User;
import com.expense_tracker.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new NotFoundException("User not found with this email") );
        return CustomUserDetails.builder()
                .user(user)
                .build();

    }
}
