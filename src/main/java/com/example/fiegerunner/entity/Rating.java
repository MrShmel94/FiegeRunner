package com.example.fiegerunner.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "ratings")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private Employee supervisor;

    @Column(name = "rating_param1", nullable = false)
    private Double ratingParam1;

    @Column(name = "rating_param2", nullable = false)
    private Double ratingParam2;

    @Column(name = "rating_param3", nullable = false)
    private Double ratingParam3;

    @Column(name = "comment_param1")
    private String commentParam1;

    @Column(name = "comment_param2")
    private String commentParam2;

    @Column(name = "comment_param3")
    private String commentParam3;

    @Column(name = "rating_date", nullable = false)
    private Date ratingDate;
}
