package com.udacity.graphql.service;

import com.udacity.graphql.entity.Dog;
import com.udacity.graphql.repository.DogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogsService {

    @Autowired
    private DogsRepository dogsRepository;

    public List<Dog> getAll() {
        return (List<Dog>) dogsRepository.findAll();

    }

        public List<Dog> retrieveDogs() {
            return (List<Dog>) dogsRepository.findAll();
        }

        public List<String> retrieveDogBreed() {
            return (List<String>) dogsRepository.findAllBreed();
        }

        public String retrieveDogBreedById(Long id) {
            Optional<String> optionalBreed = Optional.ofNullable(dogsRepository.findBreedById(id));
            String breed = optionalBreed.orElseThrow(DogNotFoundException::new);
            return breed;
        }

        public List<String> retrieveDogNames() {
            return (List<String>) dogsRepository.findAllName();
        }

}
