package m2.Service;

import m2.Repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public List<Person> findAll(){
        List<PersonEntity> persons = personRepository.findAll();
        return persons.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }
    public Person create(PersonManipulateRequest request){
        var personEntity = new PersonEntity(request.getFirstName(), request.getLastName(), request.isVaccinated());
        personEntity=personRepository.save(personEntity);
        return transformEntity(personEntity);
    }
    private Person transformEntity(PersonEntity personEntity){
        return new Person(
                personEntity.getId(),
                personEntity.getFirstName(),
                personEntity.getLastName(),
                personEntity.getVaccinated()
        );
    }
    public Person findById(Long id){
        var personEntity = personRepository.findById(id);
        return personEntity.map(this::transformEntity).orElse(null);
    }
    public Person update(Long id, PersonManipulateRequest request){
        var personEntityOptinal = personRepository.findById(id);
        if(personEntityOptinal.isEmpty()){
            return null;
        }
        var personEntity = personEntityOptinal.get();
        personEntity.setFirstName(request.getFirstName());
        personEntity.setLastName(request.getLastName());
        personEntity.setVaccinated(request.isVaccinated());
        personEntity= personRepository.save(personEntity);
        return transformEntity(personEntity);
    }

    public boolean deleteById(Long id){
        if(!personRepository.existsById(id)){
            return false;
        }
        personRepository.deleteById(id);
        return true;
    }
}
