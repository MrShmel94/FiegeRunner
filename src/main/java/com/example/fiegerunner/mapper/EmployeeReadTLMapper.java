package com.example.fiegerunner.mapper;

import com.example.fiegerunner.dto.EmployeeAddReadDto;
import com.example.fiegerunner.dto.EmployeeReadTLDto;
import com.example.fiegerunner.entity.EmployeeAddRead;
import org.springframework.stereotype.Component;

@Component
public class EmployeeReadTLMapper implements Mapper<EmployeeAddRead, EmployeeReadTLDto> {

    @Override
    public EmployeeReadTLDto map(EmployeeAddRead object) {
        return EmployeeReadTLDto.builder()
                .id(object.getId())
                .expertis(object.getExpertis())
                .employment(object.getEmployment().name())
                .comment(object.getComment())
                .firstName(object.getFirstName())
                .gender(object.getGender().name())
                .position(object.getPosition().name())
                .isWorked(object.isWorked())
                .lastName(object.getLastName())
                .build();
    }

}
