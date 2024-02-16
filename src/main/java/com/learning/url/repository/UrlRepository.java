package com.learning.url.repository;

import com.learning.url.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url,Long> {
    Url findByShortUrl(String shortUrl);
    @Query(
            value = "SELECT * \n" +
                    "FROM url \n" +
                    "WHERE longUrl =:longUrl",
            nativeQuery = true)
    Optional<Url> findByLongUrl(String longUrl);
}
