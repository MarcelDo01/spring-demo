package m2.Repository;

import m2.Service.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity,Long> {

    List<PersonEntity> findAllByFirstName(String firstName);

}
