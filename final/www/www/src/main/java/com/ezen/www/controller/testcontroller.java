package com.ezen.www.controller;

import com.ezen.www.service.testService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test/*")
@RequiredArgsConstructor
@Slf4j
public class testcontroller {

   private final testService tsv;

   @GetMapping("/teacher")
   public String information(){
      return "teacher";
   }

}
