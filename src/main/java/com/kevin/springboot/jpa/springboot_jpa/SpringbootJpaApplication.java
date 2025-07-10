package com.kevin.springboot.jpa.springboot_jpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.kevin.springboot.jpa.springboot_jpa.dto.PersonDto;
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
		queriesFunctionAggregation();
	}

	@Transactional(readOnly = true)
	public void queriesFunctionAggregation() {
		System.out.println(
				"=================== Consulta con el total de registros de la tabla persona ===================");
		Long count = personRepository.getTotalPerson();
		System.out.println("Total de personas: " + count);

		System.out.println("=================== Consulta con el id mínimo de la tabla persona ===================");
		Long minId = personRepository.getMinId();
		System.out.println("Id mínimo de personas: " + minId);

		System.out.println("=================== Consulta con el id máximo de la tabla persona ===================");
		Long maxId = personRepository.getMaxId();
		System.out.println("Id máximo de personas: " + maxId);

		System.out.println("=================== Consulta el nombre y su largo ===================");
		List<Object[]> personNameLength = personRepository.getPersonNameLength();
		personNameLength.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("Nombre: " + name + ", Largo: " + length);
		});

		System.out.println("=================== Consulta con el nombre más corto ===================");
		Integer minLengthName = personRepository.getMinLengthName();
		System.out.println("Nombre más corto tiene " + minLengthName + " caracteres");

		System.out.println("=================== Consulta con el nombre más largo ===================");
		Integer maxLengthName = personRepository.getMaxLengthName();
		System.out.println("Nombre más largo tiene " + maxLengthName + " caracteres");

		System.out.println(
				"=================== Consulta con las funciones de agregación: MIN, MAX, SUM, AVG y COUNT ===================");
		Object[] resume = (Object[]) personRepository.getResumeAggregationFunction();
		System.out.println("min=" + resume[0] +
				", max=" + resume[1] +
				", sum=" + resume[2] +
				", avg=" + resume[3] +
				", count=" + resume[4]);
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesBetween() {
		System.out
				.println("=================== Consulta por rangos =================== ");
		List<Person> persons = personRepository.findByIdBetweenOrderByNameAsc(2L, 5L);
		persons.forEach(System.out::println);

		System.out
				.println("=================== Consulta por rangos de nombres =================== ");
		persons = personRepository.findByNameBetweenOrderByNameDescLastNameDesc("J", "Q");
		persons.forEach(System.out::println);

		persons = personRepository.findAllByOrderByNameAscLastNameDesc();
		persons.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesConcatUpperAndLowerCase() {
		System.out
				.println("=================== Consulta nombres y apellidos de personas =================== ");
		List<String> fullNames = personRepository.findAllFullNameConcat();
		fullNames.forEach(System.out::println);

		System.out
				.println("=================== Consulta nombres y apellidos mayúscula =================== ");
		List<String> fullNamesUpper = personRepository.findAllFullNameConcatUpper();
		fullNamesUpper.forEach(System.out::println);

		System.out
				.println("=================== Consulta nombres y apellidos minúscula =================== ");
		List<String> fullNamesLower = personRepository.findAllFullNameConcatLower();
		fullNamesLower.forEach(System.out::println);
		System.out
				.println("=================== Consulta personalizada persona upper lower case =================== ");
		List<Object[]> personsRegs = personRepository.findAllPersonDataListCase();
		personsRegs.forEach(reg -> {
			System.out.println("id=" + reg[0] +
					", name=" + reg[1] +
					", apellido=" + reg[2] +
					", programmingLanguage=" + reg[3]);
		});
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesDistinct() {
		System.out
				.println("=================== Consultas con nombres de personas =================== ");
		List<String> names = personRepository.findAllNames();
		names.forEach(System.out::println);

		System.out
				.println("=================== Consultas con nombres unicos de personas ===================");
		names = personRepository.findAllNamesDistinct();
		names.forEach(System.out::println);

		System.out
				.println("=================== Consultas con lenguajes de programación únicos =================== ");
		List<String> programmingLanguages = personRepository.findAllProgrammingLanguagesDistinct();
		programmingLanguages.forEach(System.out::println);

		System.out
				.println("=================== Conteo con lenguajes de programación únicos =================== ");
		Long programmingLanguagesCount = personRepository.findAllProgrammingLanguagesDistinctCount();
		System.out.println("Total de lenguajes de programación únicos: " + programmingLanguagesCount);

	}

	@Transactional(readOnly = true)
	public void personalizedQueries2() {
		System.out.println(
				"=================== Consulta por objeto persona y lenguaje de programación ===================");
		List<Object[]> personsRegs = personRepository.findAllMixPersonDataList();
		personsRegs.forEach(reg -> {
			System.out.println("programmingLanguage=" + reg[1] +
					", person=" + reg[0]);
		});

		System.out.println("consulta que puebla y devuelve objeto entity de una instancia personalizada");
		List<Person> persons = personRepository.findAllObjectPersonPersonalized();
		persons.forEach(System.out::println);

		System.out.println("consulta que puebla y devuelve objeto DTO de una instancia personalizada");
		List<PersonDto> personsDto = personRepository.findAllPersonDto();
		personsDto.forEach(System.out::println);

	}

	@Transactional(readOnly = true)
	public void personalizedQueries() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("=================== Consulta solo el nombre por el id ===================");
		System.out.println("Ingrese el id de la persona a buscar:");
		Long id = scanner.nextLong();
		scanner.close();

		System.out.println("=================== Mostrar solo el nombre ===================");
		String name = personRepository.getNameById(id);
		System.out.println(name);

		System.out.println("=================== Mostrar solo el id ===================");
		Long idDb = personRepository.getIdById(id);
		System.out.println(idDb);

		System.out.println("=================== Mostrar el nombre completo ===================");
		String fullName = personRepository.getFullNameById(id);
		System.out.println(fullName);

		System.out.println("=================== Consulta por campos personalizados por el id ===================");
		Optional<Object> optionaPersonReg = personRepository.obtenerPersonDataById(id);
		optionaPersonReg.ifPresent(reg -> {
			Object[] person = (Object[]) reg;
			System.out.println("id=" + person[0] +
					", name=" + person[1] +
					", apellido=" + person[2] +
					", programmingLanguage=" + person[3]);
		});

		System.out.println("=================== Consulta por campos personalizados ===================");
		List<Object[]> regs = personRepository.obtenerPersonDataList();
		regs.forEach(person -> {
			System.out.println("id=" + person[0] +
					", name=" + person[1] +
					", apellido=" + person[2] +
					", programmingLanguage=" + person[3]);
		});
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
