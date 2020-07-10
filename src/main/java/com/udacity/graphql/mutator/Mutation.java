package com.udacity.graphql.mutator;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.udacity.graphql.entity.Dog;
import com.udacity.graphql.entity.Location;
import com.udacity.graphql.repository.DogsRepository;
import com.udacity.graphql.repository.LocationRepository;
import com.udacity.graphql.service.DogNotFoundException;
import com.udacity.graphql.service.LocationNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {

    private LocationRepository repository;
    private DogsRepository dogsRepository;

    public Mutation(LocationRepository repository, DogsRepository dogsRepository) {
        this.repository = repository;
        this.dogsRepository = dogsRepository;
    }


    public Location newLocation(String name, String address){
        Location location = new Location(name, address);
        repository.save(location);
        return location;
    }



    public Location updateLocation(String newName, Long id){
        Optional<Location> locationOptional = repository.findById(id);

        if (locationOptional.isPresent()) {
            Location location = locationOptional.get();
            location.setName(newName);
            repository.save(location);
            return location;
        }

        throw new LocationNotFoundException("Location not found!", id);
    }

    public Boolean deleteLocation(Long id){
        repository.deleteById(id);
        return true;
    }


    public Boolean deleteDogBreed(Long id){
        dogsRepository.deleteById(id);
        return true;
    }

    public Dog updateDogName(String newName,Long id)
    {
        Optional<Dog> dogOptional = dogsRepository.findById(id);

        if (dogOptional.isPresent()) {
            Dog dog = dogOptional.get();
            dog.setName(newName);
            dogsRepository.save(dog);
            return dog;
        }

        throw new DogNotFoundException("Dog not found!", id);
    }

}
