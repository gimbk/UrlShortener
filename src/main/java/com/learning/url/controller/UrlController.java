package com.learning.url.controller;

import com.learning.url.dto.request.UrlDTO;
import com.learning.url.entity.Url;
import com.learning.url.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value ="*")
@Controller
@RequestMapping(value = "/api/url")
public class UrlController {
    @Autowired
    private UrlService urlService;

    @PostMapping(value = "/add")
    private ResponseEntity<?> create(@RequestBody UrlDTO urlDTO){
        Url url =new Url();
        return null;
    }

    @PostMapping("/shorten")
    public ResponseEntity<?> shortenUrl(@RequestBody String originalUrl) {
        // Logique pour raccourcir l'URL et renvoyer l'URL raccourcie
    }

    @GetMapping("/original/{shortUrl}")
    public ResponseEntity<?> getOriginalUrl(@PathVariable String shortUrl) {
        // Logique pour récupérer l'URL d'origine à partir de l'URL raccourcie
    }
}
