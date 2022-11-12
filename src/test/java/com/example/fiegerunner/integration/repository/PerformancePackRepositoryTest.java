package com.example.fiegerunner.integration.repository;

import com.example.fiegerunner.integration.annotation.IT;
import com.example.fiegerunner.repository.EmployeeRepository;
import com.example.fiegerunner.repository.EmployeeRepositoryAdded;
import com.example.fiegerunner.repository.PerformancePackRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.*;

@IT
@RequiredArgsConstructor
public class PerformancePackRepositoryTest {

    private final PerformancePackRepository repository;
    private final EmployeeRepository repositoryEmp;
    private final EmployeeRepositoryAdded repositoryAdded;

    @Test
    void check(){
        var byId = repository.findById(1);
        System.out.println(byId);
    }

    @Test
    void checkFind(){
        List<Integer> array = List.of(304192,302158);
        var result = repository.findByAllPerformanceForYourTeamFull(
                LocalDate.of(2022,8, 1), LocalDate.of(2022, 9,30), array.toArray(new Integer[0])
        );

        System.out.println();
        result.forEach(System.out::println);
    }

    @Test
    void checkEmplRep(){
        var mayBeUsername = repositoryEmp.findByUsername("test@gmail.ru");
        System.out.println();
    }

    @Test
    void checkExpertis(){
        var byExpertis = repositoryAdded.findByExpertis(304573);
        System.out.println();
    }

    @Test
    void checkFindAll(){
        var allBySupervisorExpertis = repositoryAdded.findAllBySupervisorExpertis(1);
        System.out.println();
    }

    @Test
    void checkPer(){
        var allByExpertisAndDateBetween = repository.findAllByExpertisAndDateBetween(304917, LocalDate.of(2020, 5, 10)
                , LocalDate.of(2022, 10, 15));
        System.out.println();
    }

    @Test
    void mathematic(){
        int count = 175;
        int hour = count / 60;
        int munites = count % 60;
        System.out.println(hour + ":" + munites );
    }

    @SneakyThrows
    @Test
    void testik(){
        var workbook = new XSSFWorkbook(new FileInputStream(new File("/Users/mrshmel/Desktop/FiegeRunner/src/test/Pack Dep Performance - Daily 2022-11-05.xlsx")));
        var sheetUPH = workbook.getSheet("UPH I");
        int count = 0;
        int cellWorkDate = 0;
        int cellEmployeeNumber = 0;
        int cellActivityName = 0;
        int cellTotalTime = 0;
        int cellTotalQl = 0;
        for (Cell next : sheetUPH.getRow(0)) {
            if (next.getCellType() == CellType.STRING) {
                var stringCellValue = next.getStringCellValue();
                switch (stringCellValue.toLowerCase(Locale.ROOT)) {
                    case "work date date" -> {
                        cellWorkDate = count;
                        count++;
                    }
                    case "employee personnel number" -> {
                        cellEmployeeNumber = count;
                        count++;
                    }
                    case "activity activity name" -> {
                        cellActivityName = count;
                        count++;
                    }
                    case "activity duration (h)" -> {
                        cellTotalTime = count;
                        count++;
                    }
                    case "# items total" -> {
                        cellTotalQl = count;
                        count++;
                    }
                    default -> count++;
                }
            }
        }
        System.out.println(cellWorkDate + " " + cellEmployeeNumber + " " + cellActivityName + " " + cellTotalTime+ " " + cellTotalQl);
    }
}
