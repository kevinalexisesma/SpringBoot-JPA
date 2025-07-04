package com.kevin.springboot.jpa.springboot_jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kevin.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.kevin.springboot.jpa.springboot_jpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query("SELECT new com.kevin.springboot.jpa.springboot_jpa.dto.PersonDto(p.name, p.lastName) from Person p")
    List<PersonDto> findAllPersonDto();

    @Query("SELECT new Person(p.name, p.lastName) from Person p")
    List<Person> findAllObjectPersonPersonalized();

    @Query("SELECT p.name FROM Person p where p.id = ?1")
    String getNameById(Long id);

    @Query("SELECT p.id FROM Person p where p.id = ?1")
    Long getIdById(Long id);

    @Query("SELECT CONCAT(p.name, ' ',  p.lastName) FROM Person p where p.id = ?1")
    String getFullNameById(Long id);

    @Query("SELECT p FROM Person p WHERE p.id = ?1")
    Optional<Person> findOne(Long id);

    @Query("SELECT p FROM Person p WHERE p.name = ?1")
    Optional<Person> findOneName(String name);

    @Query("SELECT p FROM Person p WHERE p.name LIKE %?1%")
    Optional<Person> findOneLikeName(String name);

    Optional<Person> findByNameContaining(String name);

    List<Person> findByProgrammingLanguage(String programmingLanguage);

    @Query("SELECT p FROM Person p WHERE p.programmingLanguage = ?1 and p.name = ?2")
    List<Person> buscarByProgrammingLanguage(String programmingLanguage, String name);

    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    @Query("SELECT p, p.programmingLanguage FROM Person p")
    List<Object[]> findAllMixPersonDataList();

    @Query("SELECT p.id, p.name, p.lastName, p.programmingLanguage FROM Person p")
    List<Object[]> obtenerPersonDataList();

    @Query("SELECT p.id, p.name, p.lastName, p.programmingLanguage FROM Person p WHERE p.id = ?1")
    Optional<Object> obtenerPersonDataById(Long id);

    @Query("SELECT p.name, p.programmingLanguage FROM Person p")
    List<Object[]> obtenerPersonData();

    @Query("SELECT p.name, p.programmingLanguage FROM Person p WHERE p.name = ?1")
    List<Object[]> obtenerPersonData(String name);

    @Query("SELECT p.name, p.programmingLanguage FROM Person p WHERE p.programmingLanguage = ?1")
    List<Object[]> obtenerPersonDataByProgrammingLanguage(String programmingLanguage);

    @Query("SELECT p.name, p.programmingLanguage FROM Person p WHERE p.name = ?1 AND p.programmingLanguage = ?2")
    List<Object[]> obtenerPersonData(String name, String programmingLanguage);
}
