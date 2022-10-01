package com.example.fiegerunner.service;

import com.example.fiegerunner.entity.perfomance.PerformanceMulti;
import com.example.fiegerunner.entity.perfomance.PerformanceSort;
import com.example.fiegerunner.entity.perfomance.PerformanceSorter;
import com.example.fiegerunner.entity.perfomance.PerformanceSingle;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImportService {

    private final PerformanceService service;

    @SneakyThrows
    public void get(InputStream stream){
        var workbook = new XSSFWorkbook(stream);
        var sheetUPH = workbook.getSheet("UPH I");
        List<PerformanceSorter> valuePerformanceOptSorter = new ArrayList<>();
        List<PerformanceSingle> valuePerformanceSingle = new ArrayList<>();
        List<PerformanceMulti> valuePerformanceOptMulti = new ArrayList<>();
        List<PerformanceSort> valuePerformanceSort = new ArrayList<>();
        for (int i = 1; i <= sheetUPH.getLastRowNum() ; i++) {
            var row = sheetUPH.getRow(i);
            if (row.getCell(4).getStringCellValue().equalsIgnoreCase("LINESORTPACK")){
                extracted(valuePerformanceOptSorter, row);
            }
            if (row.getCell(4).getStringCellValue().equalsIgnoreCase("PACK_SINGLE")){
                extractedSingle(valuePerformanceSingle, row);
            }
            if (row.getCell(4).getStringCellValue().equalsIgnoreCase("SORT")){
                extractedSort(valuePerformanceSort, row);
            }
            if (row.getCell(4).getStringCellValue().equalsIgnoreCase("PACK_MULTI")){
                extractedMulti(valuePerformanceOptMulti, row);
            }
        }
        service.savePerformance(valuePerformanceOptSorter);
        service.savePerformanceSingle(valuePerformanceSingle);
        service.savePerformanceMulti(valuePerformanceOptMulti);
        service.savePerformanceSort(valuePerformanceSort);
    }

    private void extracted(List<PerformanceSorter> valuePerformanceOptSorter, XSSFRow row) {
        valuePerformanceOptSorter.add(
                PerformanceSorter.builder()
                        .date(row.getCell(0).getLocalDateTimeCellValue().toLocalDate())
                        .expertis((int) row.getCell(1).getNumericCellValue())
                        .totalQl((int) row.getCell(8).getNumericCellValue())
                        .totalTime((int) Math.round(row.getCell(7).getNumericCellValue() * 60))
                        .build()
        );
    }

    private void extractedSingle(List<PerformanceSingle> valuePerformanceOpt, XSSFRow row) {
        valuePerformanceOpt.add(
                PerformanceSingle.builder()
                        .date(row.getCell(0).getLocalDateTimeCellValue().toLocalDate())
                        .expertis((int) row.getCell(1).getNumericCellValue())
                        .totalQl((int) row.getCell(8).getNumericCellValue())
                        .totalTime((int) Math.round(row.getCell(7).getNumericCellValue() * 60))
                        .build()
        );
    }

    private void extractedMulti(List<PerformanceMulti> valuePerformanceOpt, XSSFRow row) {
        valuePerformanceOpt.add(
                PerformanceMulti.builder()
                        .date(row.getCell(0).getLocalDateTimeCellValue().toLocalDate())
                        .expertis((int) row.getCell(1).getNumericCellValue())
                        .totalQl((int) row.getCell(8).getNumericCellValue())
                        .totalTime((int) Math.round(row.getCell(7).getNumericCellValue() * 60))
                        .build()
        );
    }

    private void extractedSort(List<PerformanceSort> valuePerformanceOpt, XSSFRow row) {
        valuePerformanceOpt.add(
                PerformanceSort.builder()
                        .date(row.getCell(0).getLocalDateTimeCellValue().toLocalDate())
                        .expertis((int) row.getCell(1).getNumericCellValue())
                        .totalQl((int) row.getCell(8).getNumericCellValue())
                        .totalTime((int) Math.round(row.getCell(7).getNumericCellValue() * 60))
                        .build()
        );
    }
}
