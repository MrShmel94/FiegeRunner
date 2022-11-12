package com.example.fiegerunner.controller;

import com.example.fiegerunner.service.ImportService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ImportController {

    private final ImportService service2;
    private final String TYPE_FORMAT = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    @SneakyThrows
    @PostMapping("/import")
    public String mapReadExcelDatatoDB(@RequestParam("files")MultipartFile[] files,
                                       @AuthenticationPrincipal UserDetails userDetails){
        log.info("User {} trying to load {} files.", userDetails.getUsername(), files.length);
        if(files.length > 0) {
            for (MultipartFile file : files) {
                if (Objects.requireNonNull(file.getContentType()).equalsIgnoreCase(TYPE_FORMAT)) {

                    System.out.println(file.getOriginalFilename());
                    service2.get(file.getInputStream());
                }
            }
        }
        return "user/login";
    }
}
