package com.udacity.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.udacity.graphql.entity.Dog;
import com.udacity.graphql.entity.Location;
import com.udacity.graphql.repository.DogsRepository;
import com.udacity.graphql.repository.LocationRepository;
import com.udacity.graphql.service.DogNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {

    private LocationRepository repository;
    private DogsRepository dogsRepository;

    public Query(LocationRepository repository, DogsRepository dogsRepository) {
        this.repository = repository;
        this.dogsRepository = dogsRepository;
    }

    public Iterable<Location> findAllLocations(){
        return repository.findAll();
    }

    public Iterable<Dog> findDogBreeds(){
        return dogsRepository.findAll();
    }

    public Iterable<Dog> findAllDogs(){
        return dogsRepository.findAll();
    }

    public String findDogBreedById(Long id){
        return dogsRepository.findBreedById(id);
    }

    public Iterable<String> findAllDogNames(){
        return dogsRepository.findAllName();
    }

    public Dog findDogById(Long id) {
        Optional<Dog> optionalDog = dogsRepository.findById(id);
        if (optionalDog.isPresent()) {
            return optionalDog.get();
        } else {
            throw new DogNotFoundException("Dog Not Found", id);
        }
    }


}
