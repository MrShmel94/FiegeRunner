package com.example.fiegerunner.entity;

import com.example.fiegerunner.entity.enums.DepartmentEnum;
import com.example.fiegerunner.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_hiring")
    private Date dateOfHiring;

    @Column(name = "date_of_dismissal")
    private Date dateOfDismissal;

    @Column(name = "expertise", nullable = false, unique = true)
    private Integer expertis;

    @Column(name = "supervisor_expertise")
    private Integer supervisorExpertise;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @Column(name = "is_worked", nullable = false)
    private Boolean isWorked;

    @Column(name = "comment")
    private String comment;

    @Column(name = "gender", nullable = false)
    private String gender;

    @ManyToOne
    @JoinColumn(name = "shift_id")
    private Shift shift;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
