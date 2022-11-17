package com.example.fiegerunner.mapper;

public abstract class UtilsMapper {

    public static String convertTime(Integer time){
        int hour = time / 60;
        int minutes = time % 60;
        return hour + ":" + minutes;
    }

    public static Integer getUPH(Integer time, Integer ql){
        return time < 60 ? ql : Math.round(ql/(time / 60));
    }
}
