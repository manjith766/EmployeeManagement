package com.manjith.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name required")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name required")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Email required")
    @Email(message = "Invalid email")
    @Column(unique = true, nullable = false)
    private String email;
}