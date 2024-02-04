package com.learning.url.service;

import com.learning.url.dto.request.HttpResponse;
import com.learning.url.entity.Url;

import java.util.List;
import java.util.Optional;

public interface UrlService {
    HttpResponse<Url> create(Url url);
    HttpResponse<Url> update(Url url);
    void delete(Long id);
    List<Url> getAll();
    Optional<Url> getOne(Long id);
    Optional<Url> getOneLongUrl(String originUrl);
    HttpResponse<Url> shortenUrl(String originUrl);
    String getOriginalUrl(String shortUrl);
}
