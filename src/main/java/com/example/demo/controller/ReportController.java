package com.example.demo.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/report")
public class ReportController {
    @GetMapping
    public HttpEntity<?> getReports(){
        return ResponseEntity.ok(" Get Reports ");
    }

}
