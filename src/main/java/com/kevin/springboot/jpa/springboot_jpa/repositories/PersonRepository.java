package com.kevin.springboot.jpa.springboot_jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kevin.springboot.jpa.springboot_jpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByProgrammingLanguage(String programmingLanguage);

    @Query("SELECT p FROM Person p WHERE p.programmingLanguage = ?1 and p.name = ?2")
    List<Person> buscarByProgrammingLanguage(String programmingLanguage, String name);

    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    @Query("SELECT p.name, p.programmingLanguage FROM Person p")
    List<Object[]> obtenerPersonData();

    @Query("SELECT p.name, p.programmingLanguage FROM Person p WHERE p.name = ?1")
    List<Object[]> obtenerPersonData(String name);

    @Query("SELECT p.name, p.programmingLanguage FROM Person p WHERE p.programmingLanguage = ?1")
    List<Object[]> obtenerPersonDataByProgrammingLanguage(String programmingLanguage);

    @Query("SELECT p.name, p.programmingLanguage FROM Person p WHERE p.name = ?1 AND p.programmingLanguage = ?2")
    List<Object[]> obtenerPersonData(String name, String programmingLanguage);
}
