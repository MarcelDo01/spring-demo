package de.berlin.htw.webtech.demo.m2.Service;

import de.berlin.htw.webtech.demo.m2.Controller.api.Pet;
import de.berlin.htw.webtech.demo.m2.Controller.api.PetManipulationRequest;
import de.berlin.htw.webtech.demo.m2.persistence.Gender;
import de.berlin.htw.webtech.demo.m2.persistence.PersonRepository;
import de.berlin.htw.webtech.demo.m2.persistence.PetEntity;
import de.berlin.htw.webtech.demo.m2.persistence.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final PersonRepository personRepository;
    private final PersonTransformer personTransformer;

    public PetService(PetRepository petRepository, PersonRepository personRepository, PersonTransformer personTransformer) {
        this.petRepository = petRepository;
        this.personRepository = personRepository;
        this.personTransformer = personTransformer;
    }

    public List<Pet> findAll() {
        List<PetEntity> pets = petRepository.findAll();
        return pets.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Pet create(PetManipulationRequest request) {
        var gender = Gender.valueOf(request.getGender());
        var owner = personRepository.findById(request.getOwnerId()).orElseThrow();
        var petEntity = new PetEntity(request.getName(), gender, owner);
        petEntity = petRepository.save(petEntity);
        return transformEntity(petEntity);
    }

    private Pet transformEntity(PetEntity petEntity) {
        var gender = petEntity.getGender() != null ? petEntity.getGender().name() : Gender.UNKNOWN.name();
        return new Pet(
                petEntity.getId(),
                petEntity.getName(),
                gender,
                personTransformer.transformEntity(petEntity.getOwner()));
    }
}
