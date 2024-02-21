package com.example.cachingexample.repositories;

import com.example.cachingexample.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {

    public Optional<Dog> findByName(String name);
}
