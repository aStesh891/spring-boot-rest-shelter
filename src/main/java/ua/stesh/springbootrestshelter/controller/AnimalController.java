package ua.stesh.springbootrestshelter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.stesh.springbootrestshelter.dao.AnimalRepository;
import ua.stesh.springbootrestshelter.exception.InvalidAnimalTypeExc;
import ua.stesh.springbootrestshelter.model.Animal;
import ua.stesh.springbootrestshelter.model.AnimalTypes;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shelter")
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping
    public ResponseEntity<List<Animal>> listAllPersons() {
        List<Animal> persons = animalRepository.findAll();
        return ResponseEntity.ok().body(persons);
    }

    @GetMapping(value = "/{animalId}")
    public ResponseEntity<Animal> getPerson(@PathVariable("animalId") Long animalId) throws EntityNotFoundException {
        Optional<Animal> person = animalRepository.findById(animalId);
        if (!person.isPresent())
            throw new EntityNotFoundException("id-" + animalId);
        return ResponseEntity.ok().body(person.get());
    }

    @PostMapping
    public ResponseEntity<Animal> createPerson(@RequestBody @Valid Animal animal) throws InvalidAnimalTypeExc{
        checkAnimalType(animal.getType());
        Animal a = animalRepository.save(animal);
        return ResponseEntity.status(201).body(a);
    }

    @PutMapping(value = "/{animalId}")
    public ResponseEntity<Animal> updatePerson(@RequestBody @Valid Animal animal, @PathVariable("animalId") Long animalId) throws EntityNotFoundException, InvalidAnimalTypeExc {
        checkAnimalType(animal.getType());
        Optional<Animal> a = animalRepository.findById(animalId);
        
        if (!a.isPresent())
            throw new EntityNotFoundException("id-" + animalId);
        Animal oldAnimal = a.get();
        oldAnimal.setName(animal.getName());
        oldAnimal.setType(animal.getType());
        
        return ResponseEntity.ok().body(animalRepository.save(oldAnimal));
    }

    @DeleteMapping(value = "/{animalId}")
    public ResponseEntity<Animal> deletePerson(@PathVariable("animalId") Long animalId) throws EntityNotFoundException {
        Optional<Animal> p = animalRepository.findById(animalId);
        if (!p.isPresent())
            throw new EntityNotFoundException("id-" + animalId);
        animalRepository.deleteById(animalId);
        
        return ResponseEntity.ok().body(p.get());
    }

    private static void checkAnimalType(String type) throws InvalidAnimalTypeExc{
        int anchor = 0;
        for (AnimalTypes animalType : AnimalTypes.values()) {
            if (type.equals(animalType.getType())) anchor+=1;
        }
        if (anchor == 0) throw new InvalidAnimalTypeExc();
    }

}
