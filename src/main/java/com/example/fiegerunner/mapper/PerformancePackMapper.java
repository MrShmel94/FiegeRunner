//package com.example.fiegerunner.mapper;
//
//import com.example.fiegerunner.dto.PerformanceDto;
//import com.example.fiegerunner.entity.PerformanceProjection;
//import org.springframework.stereotype.Component;
//
//@Component
//public class PerformancePackMapper implements Mapper<PerformanceProjection, PerformanceDto>{
//
//    @Override
//    public PerformanceDto map(PerformanceProjection object) {
//
//        return PerformanceDto.builder()
//                .expertis(object.getExpertis())
//                .firstName(object.getFirstName())
//                .lastName(object.getLastName())
//                .uphOptimus(UtilsMapper.getUPH(object.getTimeOpt(), object.getQlOpt()))
//                .totalQlOptimus(object.getQlOpt())
//                .totalTimeOptimus(UtilsMapper.convertTime(object.getTimeOpt()))
//                .uphMulti(UtilsMapper.getUPH(object.getTimeMulti(), object.getQlMulti()))
//                .totalQlMulti(object.getQlMulti())
//                .totalTimeMulti(UtilsMapper.convertTime(object.getTimeMulti()))
//                .uphSingle(UtilsMapper.getUPH(object.getTimeSingle(), object.getQlSingle()))
//                .totalQlSingle(object.getQlSingle())
//                .totalTimeSingle(UtilsMapper.convertTime(object.getTimeSingle()))
//                .uphSort(UtilsMapper.getUPH(object.getTimeSort(), object.getQlSort()))
//                .totalQlSort(object.getQlSort())
//                .totalTimeSort(UtilsMapper.convertTime(object.getTimeSort()))
//                .uphWMO(UtilsMapper.getUPH(object.getQlWmo(), object.getQlWmo()))
//                .totalQlWMO(object.getQlWmo())
//                .totalTimeWMO(UtilsMapper.convertTime(object.getTimeWmo()))
//                .build();
//    }
//}
