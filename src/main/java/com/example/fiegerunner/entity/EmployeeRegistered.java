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
    Integer id;

    @Column(name = "user_name")
    String username;

    Integer expertis;

    String password;

    @Enumerated(EnumType.STRING)
    Role role;
}
