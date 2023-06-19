package de.berlin.htw.webtech.demo.m2.Service;

import de.berlin.htw.webtech.demo.m2.persistence.Gender;
import de.berlin.htw.webtech.demo.m2.persistence.PersonEntity;
import de.berlin.htw.webtech.demo.m2.persistence.PetEntity;
import org.springframework.stereotype.Service;
import de.berlin.htw.webtech.demo.m2.Controller.api.Person;

import java.util.stream.Collectors;

@Service
public class PersonTransformer {

    public Person transformEntity(PersonEntity personEntity) {
        var gender = personEntity.getGender() != null ? personEntity.getGender().name() : Gender.UNKNOWN.name();
        var petIds = personEntity.getPets().stream().map(PetEntity::getId).collect(Collectors.toList());
        return new Person(
                personEntity.getId(),
                personEntity.getFirstName(),
                personEntity.getLastName(),
                gender,
                personEntity.getVaccinated(),
                petIds);
    }
}