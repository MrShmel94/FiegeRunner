package com.example.fiegerunner.controller;

import com.example.fiegerunner.service.ImportService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class ImportController {

    private final ImportService service;

    @SneakyThrows
    @PostMapping("/import")
    public String mapReadExcelDatatoDB(@RequestParam("files")MultipartFile[] files){
//        Arrays.stream(files)
//                .filter(file -> file.getOriginalFilename().contains("Dep Performance - Daily"))
//                .forEach(file -> {
//                    try {
//                        service.get(file.getInputStream());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                });
        for (MultipartFile file :files){
            service.get(file.getInputStream());
            System.out.println(file.getOriginalFilename());
        }
        return "user/login";
    }
}
