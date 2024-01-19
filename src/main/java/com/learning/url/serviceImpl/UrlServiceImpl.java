package com.learning.url.serviceImpl;

import com.learning.url.entity.Url;
import com.learning.url.repository.UrlRepository;
import com.learning.url.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService {
    @Autowired
    private UrlRepository urlRepository;


    @Override
    public Url create(Url url) {
        return urlRepository.save(url);
    }

    @Override
    public Url update(Url url) {
        return urlRepository.save(url);
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
    public String shortenUrl(String originUrl) {
        // Générer une clé unique pour l'URL
        String key = generateKey(originUrl);

        // Construire l'URL raccourcie
        String shortUrl = "http://yourdomain.com/" + key;

        // Enregistrer l'URL dans la base de données
        Url url = new Url();
        url.setLongUrl(originUrl);
        url.setShortUrl(shortUrl);
        urlRepository.save(url);

        return shortUrl;
    }

    @Override
    public String getOriginalUrl(String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl);
        return (url != null) ? url.getLongUrl() : null;
    }

    private String generateKey(String originalUrl) {
        // Générer une clé unique à partir de l'URL (par exemple, en utilisant Base64)
        byte[] urlBytes = originalUrl.getBytes();
        return Base64.getUrlEncoder().encodeToString(urlBytes);
    }
}
