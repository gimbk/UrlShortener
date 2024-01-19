package com.learning.url.service;

import com.learning.url.entity.Url;

import java.util.List;
import java.util.Optional;

public interface UrlService {
    Url create(Url url);
    Url update(Url url);
    void delete(Long id);
    List<Url> getAll();
    Optional<Url> getOne(Long id);
    String shortenUrl(String originUrl);
    String getOriginalUrl(String shortUrl);
}
