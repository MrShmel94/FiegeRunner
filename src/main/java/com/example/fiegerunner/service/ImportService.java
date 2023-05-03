//package com.example.fiegerunner.service;
//
//import com.example.fiegerunner.entity.perfomancePack.*;
//import com.example.fiegerunner.repository.PerformancePackRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.stereotype.Service;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import java.io.InputStream;
//import java.time.LocalDate;
//import java.util.*;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class ImportService {
//
//    private final PerformancePackRepository repository;
//    private final static String NAME_VALUE_OPT = "LINESORTPACK";
//    private final static String NAME_VALUE_SINGLE = "PACK_SINGLE";
//    private final static String NAME_VALUE_MULTI = "PACK_MULTI";
//    private final static String NAME_VALUE_SORT = "SORT";
//    private final static String NAME_FILE_UPH_I = "UPH I";
//    private final static String NAME_FILE_UPH_WMO = "UPH I - WMO";
//    private final static String NAME_FILE_SUPPORT = "Activities per Employee";
//    private final static String NAME_OPTIMUS_ACTIVITY = "Linesorter Pack";
//    private final static String NAME_NTT_ACTIVITY = "NTT";
//    private final static String NAME_DIRECT_ACTIVITY = "Direct";
//    private final static String NAME_SUPPORT_ACTIVITY = "Support";
//    private final static Set<Integer> valueExpertis = new HashSet<>();
//    private HashMap<Integer, PerformanceWmo> performanceWmo = new HashMap<>();
//    private HashMap<Integer, PerformanceSupportNttPack> performanceSupportNttPack = new HashMap<>();
//
//
//    @SneakyThrows
//    public synchronized void get(InputStream stream) {
//        valueExpertis.clear();
//        performanceWmo.clear();
//        performanceSupportNttPack.clear();
//        var workbook = new XSSFWorkbook(stream);
//        var sheetUPH = workbook.getSheet(NAME_FILE_UPH_I);
//        var sheetWmo = workbook.getSheet(NAME_FILE_UPH_WMO);
//        performanceWmo = processingValueWmo(sheetWmo);
//        var sheetActivities = workbook.getSheet(NAME_FILE_SUPPORT);
//        performanceSupportNttPack = processingSupportValue(sheetActivities);
//
//        Map<Integer, PerformanceOPT> performanceOPT = new HashMap<>();
//        Map<Integer, PerformanceSingle> performanceSingle = new HashMap<>();
//        Map<Integer, PerformanceMulti> performanceMulti = new HashMap<>();
//        Map<Integer, PerformanceSort> performanceSort = new HashMap<>();
//        List<PerformancePack> valuePerformancePack = new ArrayList<>();
//        LocalDate date = null;
//        var row1 = sheetUPH.getRow(0);
//        int cellWorkDate = getCountRow(row1, "work date date");
//        int cellEmployeeNumber = getCountRow(row1, "employee personnel number");
//        int cellActivityName = getCountRow(row1, "activity activity name");
//        int cellTotalTime = getCountRow(row1, "activity duration (h)");
//        int cellTotalQl = getCountRow(row1, "# items total");
//        var maybeDate = sheetUPH.getRow(1).getCell(cellWorkDate);
//        if (maybeDate.getCellType() == CellType.NUMERIC) {
//            date = maybeDate.getLocalDateTimeCellValue().toLocalDate();
//        }
//        if (date != null) {
//            for (int i = 1; i <= sheetUPH.getLastRowNum(); i++) {
//                var row = sheetUPH.getRow(i);
//                var maybeExpertis = row.getCell(cellEmployeeNumber);
//                int expertis = 0;
//                if (maybeExpertis.getCellType() == CellType.NUMERIC) {
//                    expertis = (int) maybeExpertis.getNumericCellValue();
//                    if (expertis == 0) {
//                        continue;
//                    }
//                    valueExpertis.add(expertis);
//                }
//                var maybeTotalQl = row.getCell(cellTotalQl);
//                int totalQl = 0;
//                if (maybeTotalQl.getCellType() == CellType.NUMERIC) {
//                    totalQl = (int) maybeTotalQl.getNumericCellValue();
//                }
//                var maybeTotalTime = row.getCell(cellTotalTime);
//                int totalTime = 0;
//                if (maybeTotalTime.getCellType() == CellType.NUMERIC) {
//                    totalTime = (int) Math.round(maybeTotalTime.getNumericCellValue() * 60);
//                }
//                var maybeProc = row.getCell(cellActivityName);
//                if (maybeProc.getCellType() == CellType.STRING) {
//                    switch (maybeProc.getStringCellValue()) {
//                        case NAME_VALUE_OPT -> performanceOPT.put(expertis, PerformanceOPT.builder()
//                                .qlOpt(totalQl)
//                                .timeOpt(totalTime)
//                                .build());
//
//                        case NAME_VALUE_MULTI -> performanceMulti.put(expertis, PerformanceMulti.builder()
//                                .qlMulti(totalQl)
//                                .timeMulti(totalTime)
//                                .build());
//                        case NAME_VALUE_SINGLE -> performanceSingle.put(expertis, PerformanceSingle.builder()
//                                .qlSingle(totalQl)
//                                .timeSingle(totalTime)
//                                .build());
//                        case NAME_VALUE_SORT -> performanceSort.put(expertis, PerformanceSort.builder()
//                                .qlSort(totalQl)
//                                .timeSort(totalTime)
//                                .build());
//                    }
//                }
//            }
//
//
//            for (var expert : valueExpertis) {
//                valuePerformancePack.add(PerformancePack.builder()
//                        .expertis(expert)
//                        .date(date)
//                        .performanceMulti(performanceMulti.getOrDefault(expert, new PerformanceMulti(0, 0)))
//                        .performanceOPT(performanceOPT.getOrDefault(expert, new PerformanceOPT(0, 0)))
//                        .performanceSingle(performanceSingle.getOrDefault(expert, new PerformanceSingle(0, 0)))
//                        .performanceSort(performanceSort.getOrDefault(expert, new PerformanceSort(0, 0)))
//                        .performanceWmo(performanceWmo.getOrDefault(expert, new PerformanceWmo(0, 0)))
//                        .performanceSupportNttPack(performanceSupportNttPack.getOrDefault(expert, new PerformanceSupportNttPack(0, 0, 0)))
//                        .build());
//            }
//
//            repository.saveAll(valuePerformancePack);
//        } else {
//            log.info("Date is null, please retry.");
//        }
//    }
//
//    private HashMap<Integer, PerformanceWmo> processingValueWmo(XSSFSheet sheet) {
//        var row1Wmo = sheet.getRow(0);
//        int cellEmployeeNumberWmo = getCountRow(row1Wmo, "employee personnel number");
//        int cellTotalTimeWmo = getCountRow(row1Wmo, "activity duration (h)");
//        int cellTotalQlWmo = getCountRow(row1Wmo, "# Loaded WMOs to Load Carrier");
//        for (int x = 1; x <= sheet.getLastRowNum(); x++) {
//            var rowWmo = sheet.getRow(x);
//            var maybeExpertis = rowWmo.getCell(cellEmployeeNumberWmo);
//            int expertis = 0;
//            if (maybeExpertis.getCellType() == CellType.NUMERIC) {
//                expertis = (int) maybeExpertis.getNumericCellValue();
//                if (expertis == 0) {
//                    continue;
//                }
//                valueExpertis.add(expertis);
//            }
//            var maybeTotalQl = rowWmo.getCell(cellTotalQlWmo);
//            int totalQl = 0;
//            if (maybeTotalQl.getCellType() == CellType.NUMERIC) {
//                totalQl = (int) maybeTotalQl.getNumericCellValue();
//            }
//            var maybeTotalTime = rowWmo.getCell(cellTotalTimeWmo);
//            int totalTime = 0;
//            if (maybeTotalTime.getCellType() == CellType.NUMERIC) {
//                totalTime = (int) Math.round(maybeTotalTime.getNumericCellValue() * 60);
//            }
//            performanceWmo.put(expertis, PerformanceWmo.builder()
//                    .timeWmo(totalTime)
//                    .qlWmo(totalQl)
//                    .build());
//        }
//        return performanceWmo;
//    }
//
//    private HashMap<Integer, PerformanceSupportNttPack> processingSupportValue(@org.jetbrains.annotations.NotNull XSSFSheet sheet) {
//        HashMap<Integer, Integer> valueSupportOptimus = new HashMap<>();
//        HashMap<Integer, Integer> valueSupportManual = new HashMap<>();
//        HashMap<Integer, Integer> valueNtt = new HashMap<>();
//        Set<Integer> supportExpertis = new HashSet<>();
//        var row1Sup = sheet.getRow(0);
//        int cellEmployeeNumberSupport = getCountRow(row1Sup, "employee personnel number");
//        int cellTotalTimeSupport = getCountRow(row1Sup, "Activity Duration (h)");
//        int cellActivityName = getCountRow(row1Sup, "Final Cluster Final Cluster Name");
//        int cellCategorySupport = getCountRow(row1Sup, "Activity Cluster Category Activity Cluster Category Name");
//        for (int x = 1; x <= sheet.getLastRowNum(); x++) {
//            var rowSup = sheet.getRow(x);
//            var maybeExpertis = rowSup.getCell(cellEmployeeNumberSupport);
//            int expertis = 0;
//            if (maybeExpertis.getCellType() == CellType.NUMERIC) {
//                expertis = (int) maybeExpertis.getNumericCellValue();
//                if (expertis == 0) {
//                    continue;
//                }
//                valueExpertis.add(expertis);
//                supportExpertis.add(expertis);
//            }
//            var maybeTotalTime = rowSup.getCell(cellTotalTimeSupport);
//            int totalTime = 0;
//            if (maybeTotalTime.getCellType() == CellType.NUMERIC) {
//                totalTime = (int) Math.round(maybeTotalTime.getNumericCellValue() * 60);
//            }
//            var maybeActivity = rowSup.getCell(cellCategorySupport);
//            String typeActivity = "";
//            if (maybeActivity.getCellType() == CellType.STRING) {
//                typeActivity = maybeActivity.getStringCellValue();
//            }
//            if (typeActivity.equalsIgnoreCase(NAME_DIRECT_ACTIVITY)) {
//                continue;
//            }
//            if (typeActivity.equalsIgnoreCase(NAME_NTT_ACTIVITY)) {
//                valueNtt.merge(expertis, totalTime, Integer::sum);
//                continue;
//            }
//            var maybeProc = rowSup.getCell(cellActivityName);
//            if (maybeProc.getCellType() == CellType.STRING) {
//                if (maybeProc.getStringCellValue().equalsIgnoreCase(NAME_OPTIMUS_ACTIVITY)) {
//                    if (typeActivity.equalsIgnoreCase(NAME_SUPPORT_ACTIVITY)) {
//                        valueSupportOptimus.merge(expertis, totalTime, Integer::sum);
//                    }
//                } else {
//                    if (typeActivity.equalsIgnoreCase(NAME_SUPPORT_ACTIVITY)) {
//                        valueSupportManual.merge(expertis, totalTime, Integer::sum);
//                    }
//                }
//            }
//        }
//
//        for (Integer exp : supportExpertis) {
//            performanceSupportNttPack.put(exp, PerformanceSupportNttPack.builder()
//                    .timeSupportOptimus(valueSupportOptimus.getOrDefault(exp, 0))
//                    .timeNttPack(valueNtt.getOrDefault(exp, 0))
//                    .timeSupportManual(valueSupportManual.getOrDefault(exp, 0))
//                    .build());
//        }
//        return performanceSupportNttPack;
//    }
//
//    private int getCountRow(XSSFRow row, String cell) {
//        int count = 0;
//        for (Cell next : row) {
//            if (next.getCellType() == CellType.STRING) {
//                var stringCellValue = next.getStringCellValue();
//                if (cell.toLowerCase(Locale.ROOT).equals(stringCellValue.toLowerCase(Locale.ROOT))) {
//                    return count;
//                } else {
//                    count++;
//                }
//            }
//        }
//        return count;
//    }
//}
