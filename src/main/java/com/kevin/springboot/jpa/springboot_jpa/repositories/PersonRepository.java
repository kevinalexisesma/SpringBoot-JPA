package com.kevin.springboot.jpa.springboot_jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kevin.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.kevin.springboot.jpa.springboot_jpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query("SELECT MIN(LENGTH(p.name)) FROM Person p")
    Integer getMinLengthName();

    @Query("SELECT MAX(LENGTH(p.name)) FROM Person p")
    Integer getMaxLengthName();

    @Query("SELECT p.name, LENGTH(p.name) FROM Person p")
    List<Object[]> getPersonNameLength();

    @Query("SELECT COUNT(p) from Person p")
    Long getTotalPerson();

    @Query("select MIN(p.id) from Person p")
    Long getMinId();

    @Query("select MAX(p.id) from Person p")
    Long getMaxId();

    List<Person> findAllByOrderByNameAscLastNameDesc();

    @Query("SELECT p FROM Person p ORDER BY p.name, p.lastName desc")
    List<Person> getAllOrdered();

    List<Person> findByIdBetweenOrderByNameAsc(Long id1, Long id2);

    List<Person> findByNameBetweenOrderByNameDescLastNameDesc(String name1, String name2);

    @Query("SELECT p FROM Person p WHERE p.id BETWEEN ?1 AND ?2 ORDER BY p.name desc")
    List<Person> findAllBetweenId(Long id1, Long id2);

    @Query("SELECT p FROM Person p WHERE p.name BETWEEN ?1 AND ?2 ORDER BY p.name, p.lastName desc")
    List<Person> findAllBetweenName(String c1, String c2);

    @Query("SELECT p.id, UPPER(p.name), LOWER(p.lastName), UPPER(p.programmingLanguage) FROM Person p")
    List<Object[]> findAllPersonDataListCase();

    @Query("SELECT UPPER(p.name || ' ' || p.lastName) from Person p")
    List<String> findAllFullNameConcatUpper();

    @Query("SELECT LOWER(p.name || ' ' || p.lastName) from Person p")
    List<String> findAllFullNameConcatLower();

    // @Query("SELECT CONCAT(p.name, ' ', p.lastName) from Person p")
    @Query("SELECT p.name || ' ' || p.lastName from Person p")
    List<String> findAllFullNameConcat();

    @Query("SELECT COUNT(DISTINCT(p.programmingLanguage)) FROM Person p")
    Long findAllProgrammingLanguagesDistinctCount();

    @Query("SELECT DISTINCT(p.programmingLanguage) FROM Person p")
    List<String> findAllProgrammingLanguagesDistinct();

    @Query("SELECT p.name FROM Person p")
    List<String> findAllNames();

    @Query("SELECT DISTINCT(p.name) FROM Person p")
    List<String> findAllNamesDistinct();

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
