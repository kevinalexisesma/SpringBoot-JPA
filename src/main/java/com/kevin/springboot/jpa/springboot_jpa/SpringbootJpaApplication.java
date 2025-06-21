package com.kevin.springboot.jpa.springboot_jpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

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
		delete2();
		// list();
	}

	@Transactional
	public void delete() {
		personRepository.findAll().forEach(System.out::println);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id de la persona a eliminar:");
		Long id = scanner.nextLong();
		personRepository.deleteById(id);

		personRepository.findAll().forEach(System.out::println);
		scanner.close();
	}

	@Transactional
	public void delete2() {
		personRepository.findAll().forEach(System.out::println);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id de la persona a eliminar:");
		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = personRepository.findById(id);
		optionalPerson.ifPresentOrElse(
				personRepository::delete,
				() -> System.out.println("Lo sentimos, no existe la persona con el id: " + id));

		personRepository.findAll().forEach(System.out::println);
		scanner.close();
	}

	@Transactional
	public void update() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id de la persona a actualizar:");
		Long id = scanner.nextLong();
		Optional<Person> optionalPerson = personRepository.findById(id);

		// optionalPerson.ifPresent(person -> {
		if (optionalPerson.isPresent()) {
			Person person = optionalPerson.orElseThrow();
			System.out.println("Persona encontrada: " + person);
			System.out.println("Ingrese el lenguje de programación de la persona:");
			String programmingLanguage = scanner.next();
			person.setProgrammingLanguage(programmingLanguage);

			Person personDb = personRepository.save(person);
			System.out.println("Persona actualizada: " + personDb);
		} else {
			System.out.println("No se encontró una persona con el id: " + id);
		}
		// });
		scanner.close();
	}

	@Transactional
	public void create() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el nombre de la persona:");
		String name = scanner.next();
		System.out.println("Ingrese el apellido de la persona:");
		String lastName = scanner.next();
		System.out.println("Ingrese el lenguaje de programacion de la persona:");
		String programmingLanguage = scanner.next();
		scanner.close();

		Person person = new Person(null, name, lastName, programmingLanguage);
		Person personNew = personRepository.save(person);
		System.out.println("Persona creada: " + personNew);

		personRepository.findById(personNew.getId()).ifPresent(System.out::println);
	}

	@Transactional(readOnly = true)
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

	@Transactional(readOnly = true)
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
