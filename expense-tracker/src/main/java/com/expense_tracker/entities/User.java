package com.expense_tracker.entities;

import com.expense_tracker.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    @Size(min = 5 , max = 30 , message = "fullName should be between 5 and 30 characters")
    private String fullName;

    @Column(unique = true)
    @Email(message = "Enter a valid Email")
    private String email;

    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @NotNull
    private boolean isActive;

    @NotNull
    private boolean isAccountBlocked;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
