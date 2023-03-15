package com.mySystem.patientekidney.controllers;


import jakarta.persistence.GeneratedValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {
   @GetMapping(value = {"/", "/index"})
   public String getHome(){
      System.out.println("Entre a HomeCotroller");
      return "home";
   }
}
