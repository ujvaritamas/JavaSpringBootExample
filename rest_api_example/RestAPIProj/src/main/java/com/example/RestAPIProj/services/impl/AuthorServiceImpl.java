package com.example.RestAPIProj.services.impl;

import com.example.RestAPIProj.domain.entities.AuthorEntity;
import com.example.RestAPIProj.repositories.AuthorRepository;
import com.example.RestAPIProj.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity createAuthor(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }

    @Override
    public List<AuthorEntity> findAll() {
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<AuthorEntity> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Boolean isExists(Long id) {
        return authorRepository.existsById(id);
    }

    @Override
    public AuthorEntity saveAuthor(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }

    @Override
    public AuthorEntity partialUpdate(Long id, AuthorEntity author) {
        author.setId(id);

        return authorRepository.findById(id).map(existingAuthor -> {
            Optional.ofNullable(author.getName()).ifPresent(existingAuthor::setName);
            Optional.ofNullable(author.getAge()).ifPresent(existingAuthor::setAge);
            return authorRepository.save(existingAuthor);
        }).orElseThrow(()-> new RuntimeException("Author does not exists.") );
    }

    @Override
    public AuthorEntity deleteAuthor(Long id) {

        Optional<AuthorEntity> author = authorRepository.findById(id);

        if(author.isPresent()){
            authorRepository.delete(author.get());
            return author.get();
        }
        else{
            throw new RuntimeException("Author does not found");
        }
    }
}
