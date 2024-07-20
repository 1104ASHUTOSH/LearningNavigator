package com.example.lms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class EasterEggController {

    @GetMapping("/hidden-feature/{number}")
    public ResponseEntity<String> getNumberFact(@PathVariable int number) {
        String url = "http://
