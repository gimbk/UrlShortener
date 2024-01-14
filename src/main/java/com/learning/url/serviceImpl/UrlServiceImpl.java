package com.learning.url.serviceImpl;

import com.learning.url.entity.Url;
import com.learning.url.repository.UrlRepository;
import com.learning.url.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
