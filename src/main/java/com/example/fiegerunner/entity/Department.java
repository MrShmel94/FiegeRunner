package com.example.fiegerunner.entity;

import com.example.fiegerunner.entity.enums.DepartmentEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "department")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

//    @OneToMany(mappedBy = "department")
//    private Set<Employee> employees;

    @ManyToMany
    @JoinTable(
            name = "department_process",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name = "process_id")
    )
    private Set<Process> processes;

}
