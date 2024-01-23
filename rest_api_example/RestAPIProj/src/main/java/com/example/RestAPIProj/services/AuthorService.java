package com.example.RestAPIProj.services;

import com.example.RestAPIProj.domain.entities.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    AuthorEntity createAuthor(AuthorEntity authorEntity);

    List<AuthorEntity> findAll();

    Optional<AuthorEntity> findById(Long id);

    Boolean isExists(Long id);

    AuthorEntity saveAuthor(AuthorEntity authorEntity);

    AuthorEntity partialUpdate(Long id, AuthorEntity author);

    AuthorEntity deleteAuthor(Long id);
}
