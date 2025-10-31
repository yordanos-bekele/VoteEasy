package com.yordanos_bekele.Polling_Voting.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/home")
public class HomeController {
    @GetMapping
    public ResponseEntity<String> home() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("static/home.html");
        String htmlContent = Files.readString((htmlFile.getFile().toPath()));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE)
                .body(htmlContent);
    }
}
