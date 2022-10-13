package com.example.fiegerunner.dto;

import com.example.fiegerunner.entity.Evaluation;
import com.example.fiegerunner.entity.enums.Employment;
import com.example.fiegerunner.entity.enums.Gender;
import com.example.fiegerunner.entity.enums.Position;
import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
@Builder
public class EmployeeReadTLDto {

    Integer id;

    Integer expertis;

    String firstName;

    String lastName;

    boolean isWorked;

    String position;

    String comment;

    String gender;

    String employment;

}
