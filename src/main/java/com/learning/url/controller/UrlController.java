package com.learning.url.controller;

import com.learning.url.dto.request.UrlDTO;
import com.learning.url.entity.Url;
import com.learning.url.repository.UrlRepository;
import com.learning.url.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(value ="https://shortngo.onrender.com")
@Controller
@RequestMapping(value = "/")
public class UrlController {
    @Autowired
    private UrlService urlService;

    private UrlRepository urlRepository;

    @PostMapping("api/url/shorten")
    public ResponseEntity<?> shortenUrl(@RequestBody UrlDTO urlDTO) {
        return ResponseEntity.ok().body(urlService.shortenUrl(urlDTO.getLongUrl()));
    }

    @PostMapping("api/url/check")
    public ResponseEntity<?> checkLongUrl(@RequestBody UrlDTO urlDTO) {
        return ResponseEntity.ok().body(urlService.getOneLongUrl(urlDTO.getLongUrl()));
    }

    @GetMapping("{shortUrl}")
    public String getOriginalUrl(@PathVariable String shortUrl) {
        String originalUrl = urlService.getOriginalUrl(shortUrl);
        if (originalUrl != null) {
            return "redirect:" + originalUrl;
        } else {
            return "URL raccourcie non valide";
        }
    }
}
