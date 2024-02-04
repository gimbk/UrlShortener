package com.learning.url.serviceImpl;

import com.learning.url.dto.request.HttpResponse;
import com.learning.url.entity.Url;
import com.learning.url.repository.UrlRepository;
import com.learning.url.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class UrlServiceImpl implements UrlService {
    @Autowired
    private UrlRepository urlRepository;


    @Override
    public HttpResponse<Url> create(Url url) {
        return HttpResponse.<Url>builder()
                .data(Collections.singleton(urlRepository.save(url)))
                .message("New url shortener create successfully")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build();
    }

    @Override
    public HttpResponse<Url> update(Url url) {
        Optional<Url> optionalUrl = Optional.ofNullable(Optional.of(urlRepository.findByShortUrl(url.getShortUrl()))
                .orElseThrow(() -> new RuntimeException("url not found")));
        log.info("Updating url in database");
        Url updateUrl = optionalUrl.get();
        updateUrl.setId(updateUrl.getId());
        updateUrl.setLongUrl(url.getLongUrl());
        updateUrl.setShortUrl(url.getShortUrl());
        urlRepository.save(updateUrl);
        return HttpResponse.<Url>builder()
                .data(Collections.singleton(updateUrl))
                .message("updated url successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @Override
    public void delete(Long id) {
        urlRepository.deleteById(id);
    }

    @Override
    public List<Url> getAll() {
        return urlRepository.findAll();
    }

    @Override
    public Optional<Url> getOne(Long id) {
        return urlRepository.findById(id);
    }

    @Override
    public Optional<Url> getOneLongUrl(String originUrl) {
        return urlRepository.findByLongUrl(originUrl);
    }

    @Override
    public HttpResponse<Url> shortenUrl(String originUrl) {
        // Générer une clé unique pour l'URL
        String shortUrl = generateKey(originUrl);
        // Enregistrer l'URL dans la base de données
        Url url = new Url();
        url.setLongUrl(originUrl);
        url.setShortUrl(shortUrl);

        return create(url);
    }

    @Override
    public String getOriginalUrl(String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl);
        return (url != null) ? url.getLongUrl() : null;
    }

    private String generateKey(String originalUrl) {
        // Générer une clé unique à partir de l'URL (par exemple, en utilisant Base64)
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(originalUrl.getBytes(StandardCharsets.UTF_8));
            String encodedHash = Base64.getUrlEncoder().encodeToString(hash);
            return encodedHash.substring(0, 8); // Utilisez les premiers 8 caractères pour l'URL raccourcie
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erreur lors de la génération de la clé");
        }
    }
}
