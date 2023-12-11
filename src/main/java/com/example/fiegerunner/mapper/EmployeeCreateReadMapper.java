package com.example.fiegerunner.mapper;

import com.example.fiegerunner.dto.EmployeeCreateReadDto;
import com.example.fiegerunner.dto.EmployeeReadTLDto;
import com.example.fiegerunner.entity.*;
import com.example.fiegerunner.repository.DepartmentRepository;
import com.example.fiegerunner.repository.PositionRepository;
import com.example.fiegerunner.repository.ShiftRepository;
import com.example.fiegerunner.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeCreateReadMapper implements Mapper <EmployeeCreateReadDto, Employee>{

    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final ShiftRepository shiftRepository;
    private final TeamRepository teamRepository;

    @Override
    public Employee map(EmployeeCreateReadDto object) {

        Department department = departmentRepository.findByName(object.department()).orElse(null);
        Position position = positionRepository.findByName(object.position()).orElse(null);
        Shift shift = shiftRepository.findByName(object.shift()).orElse(null);
        Team team = teamRepository.findByName(object.team()).orElse(null);

        return Employee.builder()
                .id(object.id())
                .expertis(object.expertis())
                .firstName(object.firstName())
                .lastName(object.lastName())
                .isWorked(Optional.ofNullable(object.isWorked()).isPresent() ? object.isWorked() : true)
                .comment(object.comment())
                .dateOfHiring(object.dateOfHiring())
                .dateOfDismissal(object.dateOfDismissal())
                .team(team)
                .shift(shift)
                .department(department)
                .position(position)
                .gender(object.gender())
                .supervisorExpertise(object.supervisorExpertis())
                .build();
    }

    @Override
    public EmployeeCreateReadDto reverseMap(Employee employee) {
        return new EmployeeCreateReadDto(
                employee.getId(),
                employee.getExpertis(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getTeam().getName(),
                employee.getShift().getName(),
                employee.getDepartment().getName(),
                employee.getPosition().getName(),
                employee.getGender(),
                employee.getDateOfHiring(),
                employee.getDateOfDismissal(),
                employee.getIsWorked(),
                employee.getComment(),
                employee.getSupervisorExpertise()
        );
    }

    public EmployeeReadTLDto mapToReadTLDto(Employee employee) {
        return new EmployeeReadTLDto(
                employee.getId(),
                employee.getExpertis(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getPosition().getName()
        );
    }
}
