package com.example.fiegerunner.entity;

import com.example.fiegerunner.entity.enums.TeamEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "team")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

//    @ManyToMany(mappedBy = "teams")
//    private Set<Employee> employees;

}
