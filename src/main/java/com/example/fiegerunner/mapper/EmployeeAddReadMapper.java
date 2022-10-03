package com.example.fiegerunner.mapper;

import com.example.fiegerunner.dto.EmployeeAddReadDto;
import com.example.fiegerunner.entity.EmployeeAddRead;
import com.example.fiegerunner.entity.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class EmployeeAddReadMapper implements Mapper <EmployeeAddReadDto, EmployeeAddRead>{

    @Override
    public EmployeeAddRead map(EmployeeAddReadDto object) {
        return EmployeeAddRead.builder()
                .expertis(object.getExpertis())
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .isWorked(true)
                .team(object.getTeam())
                .shift(object.getShift())
                .department(object.getDepartment())
                .position(object.getPosition())
                .gender(object.getGender())
                .employment(object.getEmployment())
                .supervisorExpertis(object.getSupervisorExpertis())
                .build();
    }

}
