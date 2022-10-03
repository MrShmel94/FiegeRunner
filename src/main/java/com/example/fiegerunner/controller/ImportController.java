package com.example.fiegerunner.controller;

import com.example.fiegerunner.service.ImportService2;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class ImportController {

    private final ImportService2 service2;

    @SneakyThrows
    @PostMapping("/import")
    public String mapReadExcelDatatoDB(@RequestParam("files")MultipartFile[] files){
        System.out.println();
        for (MultipartFile file :files){
            service2.get(file.getInputStream());
            System.out.println(file.getOriginalFilename());
        }
        return "user/login";
    }
}
