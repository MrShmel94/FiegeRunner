package com.example.fiegerunner.mapper;

import com.example.fiegerunner.dto.EmployeeAddDto;
import com.example.fiegerunner.entity.EmployeeAdded;
import com.example.fiegerunner.entity.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class EmployeeAddedMapper implements Mapper <EmployeeAddDto, EmployeeAdded>{

    @Override
    public EmployeeAdded map(EmployeeAddDto object) {
        return EmployeeAdded.builder()
                .expertis(object.getExpertis())
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .isWorked(true)
                .team(object.getTeam())
                .shift(object.getShift())
                .department(object.getDepartment())
                .role(Role.Storekeeper)
                .position(object.getPosition())
                .gender(object.getGender())
                .employment(object.getEmployment())
                .supervisorExpertis(object.getSupervisorExpertis())
                .build();
    }

}
