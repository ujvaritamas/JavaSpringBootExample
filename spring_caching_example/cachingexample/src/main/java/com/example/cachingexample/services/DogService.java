package com.example.cachingexample.services;

import com.example.cachingexample.model.Dog;
import com.example.cachingexample.repositories.DogRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log
public class DogService {

    @Autowired
    private DogRepository dogRepository;

    public List<Dog> getAllDog(){
        return dogRepository.findAll();
    }

    public void saveDog(Dog dog){
        Optional<Dog> persistedDog = dogRepository.findByName(dog.getName());
        if(persistedDog.isPresent()){
            log.info("Dog is still in the db.");
            return;
        }

        dogRepository.save(dog);
    }

    public Optional<Dog> getDogById(Long id){
        return dogRepository.findById(id);
    }

    public Optional<Dog> getDogByName(String name){
        return dogRepository.findByName(name);
    }

}
