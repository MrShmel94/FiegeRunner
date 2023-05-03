package com.example.fiegerunner.entity;

import com.example.fiegerunner.entity.enums.Role;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class EmployeeRegistered {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name" , nullable = false)
    private String username;

    @Column(name = "expertis", nullable = false)
    private Integer expertis;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;
}
