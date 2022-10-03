package com.example.fiegerunner.service;

import com.example.fiegerunner.entity.perfomance.*;
import com.example.fiegerunner.repository.PerformancePackRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ImportService2 {

    private final PerformancePackRepository repository;
    private final static String NAME_VALUE_OPT = "LINESORTPACK";
    private final static String NAME_VALUE_SINGLE = "PACK_SINGLE";
    private final static String NAME_VALUE_MULTI = "PACK_MULTI";
    private final static String NAME_VALUE_SORT = "SORT";


    @SneakyThrows
    public void get(InputStream stream){
        var workbook = new XSSFWorkbook(stream);
        var sheetUPH = workbook.getSheet("UPH I");
        var sheetWmo = workbook.getSheet("UPH I - WMO");
        Map<Integer, PerformanceOPT> performanceOPT = new HashMap<>();
        Map<Integer, PerformanceSingle2> performanceSingle = new HashMap<>();
        Map<Integer, PerformanceMulti2> performanceMulti = new HashMap<>();
        Map<Integer, PerformanceSort2> performanceSort = new HashMap<>();
        Map<Integer, PerformanceWmo2> performanceWmo = new HashMap<>();
        List<PerformancePack> valuePerformancePack = new ArrayList<>();
        Set<Integer> valueExpertis = new HashSet<>();
        LocalDate date = null;
        var maybeDate = sheetUPH.getRow(1).getCell(0);
        if (maybeDate.getCellType() == CellType.NUMERIC){
            date = maybeDate.getLocalDateTimeCellValue().toLocalDate();
        }
        for (int i = 1; i <= sheetUPH.getLastRowNum(); i++) {
            var row = sheetUPH.getRow(i);
            var maybeExpertis = row.getCell(1);
            int expertis = 0;
            if(maybeExpertis.getCellType() == CellType.NUMERIC){
                expertis = (int) maybeExpertis.getNumericCellValue();
                valueExpertis.add(expertis);
            }
            var maybeTotalQl = row.getCell(8);
            int totalQl = 0;
            if (maybeTotalQl.getCellType() == CellType.NUMERIC){
                totalQl = (int) maybeTotalQl.getNumericCellValue();
            }
            var maybeTotalTime = row.getCell(7);
            int totalTime = 0;
            if (maybeTotalTime.getCellType() == CellType.NUMERIC){
                totalTime = (int)Math.round(maybeTotalTime.getNumericCellValue() * 60);
            }
            var maybeProc = row.getCell(4);
            if (maybeProc.getCellType() == CellType.STRING) {
                switch (maybeProc.getStringCellValue()) {
                    case NAME_VALUE_OPT -> performanceOPT.put(expertis, PerformanceOPT.builder()
                            .qlOpt(totalQl)
                            .timeOpt(totalTime)
                            .build());

                    case NAME_VALUE_MULTI -> performanceMulti.put(expertis, PerformanceMulti2.builder()
                            .qlMulti(totalQl)
                            .timeMulti(totalTime)
                            .build());
                    case NAME_VALUE_SINGLE -> performanceSingle.put(expertis, PerformanceSingle2.builder()
                            .qlSingle(totalQl)
                            .timeSingle(totalTime)
                            .build());
                    case NAME_VALUE_SORT -> performanceSort.put(expertis, PerformanceSort2.builder()
                            .qlSort(totalQl)
                            .timeSort(totalTime)
                            .build());
                }
            }
        }

        for (int x = 1; x <= sheetWmo.getLastRowNum(); x++){
            var rowWmo = sheetWmo.getRow(x);
            var maybeExpertis = rowWmo.getCell(1);
            int expertis = 0;
            if(maybeExpertis.getCellType() == CellType.NUMERIC){
                expertis = (int) maybeExpertis.getNumericCellValue();
                valueExpertis.add(expertis);
            }
            var maybeTotalQl = rowWmo.getCell(9);
            int totalQl = 0;
            if (maybeTotalQl.getCellType() == CellType.NUMERIC){
                totalQl = (int) maybeTotalQl.getNumericCellValue();
            }
            var maybeTotalTime = rowWmo.getCell(8);
            int totalTime = 0;
            if (maybeTotalTime.getCellType() == CellType.NUMERIC){
                totalTime = (int)Math.round(maybeTotalTime.getNumericCellValue() * 60);
            }
            performanceWmo.put(expertis, PerformanceWmo2.builder()
                    .timeWmo(totalTime)
                    .qlWmo(totalQl)
                    .build());
        }

        for (var expert : valueExpertis){
            valuePerformancePack.add(PerformancePack.builder()
                    .expertis(expert)
                    .date(date)
                    .performanceMulti(performanceMulti.getOrDefault(expert, new PerformanceMulti2(0,0)))
                    .performanceOPT(performanceOPT.getOrDefault(expert, new PerformanceOPT(0,0)))
                    .performanceSingle(performanceSingle.getOrDefault(expert, new PerformanceSingle2(0,0)))
                    .performanceSort(performanceSort.getOrDefault(expert, new PerformanceSort2(0,0)))
                    .performanceWmo(performanceWmo.getOrDefault(expert, new PerformanceWmo2(0,0)))
                    .build());
        }

        repository.saveAll(valuePerformancePack);
    }
}
