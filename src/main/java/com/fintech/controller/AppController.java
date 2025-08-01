package com.fintech.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AppController {

    @GetMapping("/calculator")
    public ResponseEntity<String> calculator() {
        return ResponseEntity.ok("<h1>Calculator Page - Open to Everyone</h1>");
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("<h1>Test Page - Protected Route</h1>");
    }
}
