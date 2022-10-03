package com.example.fiegerunner.controller;

import com.example.fiegerunner.dto.EmployeeAddReadDto;
import com.example.fiegerunner.dto.EmployeeCreateDto;
import com.example.fiegerunner.entity.PerformanceProjection;
import com.example.fiegerunner.entity.enums.*;
import com.example.fiegerunner.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class StartController {

    private final EmployeeService service;

    @GetMapping("/adduser")
    public String createEmployee(Model model, @ModelAttribute("user") EmployeeAddReadDto employee){
        model.addAttribute("user", employee);
        model.addAttribute("shift", Shift.values());
        model.addAttribute("team", Team.values());
        model.addAttribute("department", Department.values());
        model.addAttribute("position", Position.values());
        model.addAttribute("gender", Gender.values());
        model.addAttribute("employment", Employment.values());
        return "user/createNewUser";
    }

    @PostMapping("/create")
    public String addedEmployee(@ModelAttribute EmployeeAddReadDto employee,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes
                                ){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("user", employee);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/adduser";
        }
        service.create(employee);
        return "redirect:/users";
    }

    @GetMapping("/usersPerformance")
    public String findAllUsers(){
        return "user/allUsersPerformance";
    }

    @PostMapping("/usersPerformance")
    public String findAllUsers(Model model,
                               @RequestParam String dateBefore,
                               @RequestParam String dateAfter,
                               @CurrentSecurityContext SecurityContext context,
                               @AuthenticationPrincipal UserDetails userDetails
    ){
        var allPerformanceForEmployee = service.getAllPerformanceForEmployee(userDetails.getUsername(),
                LocalDate.parse(dateBefore), LocalDate.parse(dateAfter));
        model.addAttribute("performance", allPerformanceForEmployee);
        System.out.println();
        return "user/allUsersPerformance";
    }

    @PostMapping("/registration")
    public String create(@ModelAttribute EmployeeCreateDto employee,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", employee);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/login";
        }
        service.createNewUser(employee);
        return "redirect:/login";
    }

}
