package com.kevin.springboot.jpa.springboot_jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kevin.springboot.jpa.springboot_jpa.entities.Person;
import com.kevin.springboot.jpa.springboot_jpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		findOne();
		// list();
	}

	public void findOne() {
		/*
		 * Person person = null;
		 * Optional<Person> optional = personRepository.findOneName("Andres");
		 * if (optional.isPresent()) {
		 * person = optional.get();
		 * }
		 * System.out.println(person);
		 */
		personRepository.findByNameContaining("pe").ifPresent(System.out::println);
	}

	public void list() {
		// List<Person> persons = (List<Person>) personRepository.findAll();
		// List<Person> persons = (List<Person>)
		// personRepository.buscarByProgrammingLanguageAndName("Java", "Andres");
		List<Person> persons = (List<Person>) personRepository.findByProgrammingLanguageAndName("Java", "Andres");

		persons.stream().forEach(p -> System.out.println(p));

		List<Object[]> personsValues = personRepository.obtenerPersonData();
		personsValues.stream().forEach(p -> System.out.println(p[0] + " es experto en " + p[1]));
	}
}
