package de.berlin.htw.webtech.demo.m2.persistence;

import de.berlin.htw.webtech.demo.m2.persistence.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity,Long> {

    List<PersonEntity> findAllByFirstName(String firstName);

}
