package com.example.fiegerunner.mapper;

import com.example.fiegerunner.dto.DailyLogDTO;
import com.example.fiegerunner.entity.DailyLog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DailyLogMapper {

    DailyLogMapper INSTANCE = Mappers.getMapper(DailyLogMapper.class);

    DailyLog toEntity(DailyLogDTO dto);
    DailyLogDTO toDTO(DailyLog entity);

}
