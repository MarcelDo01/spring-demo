package de.berlin.htw.webtech.demo.m2.Service;


import de.berlin.htw.webtech.demo.m2.Controller.api.Person;
import de.berlin.htw.webtech.demo.m2.persistence.Gender;
import de.berlin.htw.webtech.demo.m2.persistence.PersonEntity;
import de.berlin.htw.webtech.demo.m2.persistence.PersonRepository;
import org.springframework.stereotype.Service;
import de.berlin.htw.webtech.demo.m2.Controller.api.PersonManipulationRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonTransformer personTransformer;

    public PersonService(PersonRepository personRepository, PersonTransformer personTransformer) {
        this.personRepository = personRepository;
        this.personTransformer = personTransformer;
    }

    public List<Person> findAll() {
        List<PersonEntity> persons = personRepository.findAll();
        return persons.stream()
                .map(personTransformer::transformEntity)
                .collect(Collectors.toList());
    }

    public Person findById(Long id) {
        var personEntity = personRepository.findById(id);
        return personEntity.map(personTransformer::transformEntity).orElse(null);
    }

    public Person create(PersonManipulationRequest request) {
        var gender = Gender.valueOf(request.getGender());
        var personEntity = new PersonEntity(request.getFirstName(), request.getLastName(), request.isVaccinated(), gender);
        personEntity = personRepository.save(personEntity);
        return personTransformer.transformEntity(personEntity);
    }

    public Person update(Long id, PersonManipulationRequest request) {
        var personEntityOptional = personRepository.findById(id);
        if (personEntityOptional.isEmpty()) {
            return null;
        }

        var personEntity = personEntityOptional.get();
        personEntity.setFirstName(request.getFirstName());
        personEntity.setLastName(request.getLastName());
        personEntity.setVaccinated(request.isVaccinated());
        personEntity.setGender(Gender.valueOf(request.getGender()));
        personEntity = personRepository.save(personEntity);

        return personTransformer.transformEntity(personEntity);
    }

    public boolean deleteById(Long id) {
        if (!personRepository.existsById(id)) {
            return false;
        }

        personRepository.deleteById(id);
        return true;
    }
}