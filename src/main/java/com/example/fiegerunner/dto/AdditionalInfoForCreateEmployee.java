package com.example.fiegerunner.dto;

import com.example.fiegerunner.entity.enums.DepartmentEnum;
import com.example.fiegerunner.entity.enums.PositionEnum;
import com.example.fiegerunner.entity.enums.ShiftEnum;
import com.example.fiegerunner.entity.enums.TeamEnum;

import java.util.List;
import java.util.Map;

public record AdditionalInfoForCreateEmployee (
        List<Map<String, Object>> depatments,
        List<Map<String, Object>> positions,
        List<Map<String, Object>> teams,
        List<Map<String, Object>> shifts
){}
