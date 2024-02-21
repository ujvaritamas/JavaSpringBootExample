package com.example.cachingexample.controllers;

import com.example.cachingexample.model.Dog;
import com.example.cachingexample.services.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Controller
public class DogController {

    @Autowired
    DogService dogService;

    @GetMapping("/")
    public ResponseEntity<String> root(){
        return ResponseEntity.status(HttpStatus.OK).body("hello");
    }

    @Cacheable("dogs")
    @GetMapping("/getDogs")
    public ResponseEntity<List<Dog>> getDogs() throws InterruptedException {
        Thread.sleep(3000);
        return ResponseEntity.status(HttpStatus.OK).body(dogService.getAllDog());
    }

    @CacheEvict(value="dogs", allEntries=true)
    @PostMapping("/addDog")
    public  ResponseEntity<String> addDog(@RequestBody Dog dog) {
        dogService.saveDog(dog);
        return ResponseEntity.status(HttpStatus.OK).body("saved");
    }

    @GetMapping("/getDogById/{id}")
    public ResponseEntity<Dog> getDogById(@PathVariable Long id) {
        Optional<Dog> persistedDogOptional = dogService.getDogById(id);

        if (persistedDogOptional.isPresent()) {
            return ResponseEntity.ok(persistedDogOptional.get()); // Return 200 OK with the dog if found
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if dog with given ID is not found
        }
    }

    @GetMapping("/getDogByName/{name}")
    public ResponseEntity<Dog> getDogByname(@PathVariable String name) {
        Optional<Dog> persistedDogOptional = dogService.getDogByName(name);

        // Return 200 OK with the dog if found
        // Return 404 Not Found if dog with given ID is not found
        return persistedDogOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
