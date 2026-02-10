package io.github.joaoprbrasil.libraryapi;

import io.github.joaoprbrasil.libraryapi.model.Autor;
import io.github.joaoprbrasil.libraryapi.repository.AutorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		var context = SpringApplication.run(Application.class, args);
		AutorRepository repository = context.getBean(AutorRepository.class);

		exemploSalvarRegistro(repository);
	}

	public static void exemploSalvarRegistro(AutorRepository autorRepository){
		Autor autor = new Autor();
		autor.setNome("Dostoevsky");
		autor.setNacionalidade("Russia");
		autor.setDataNascimento(LocalDate.of(1821, 7, 11));

		var autorSalvo = autorRepository.save(autor);
		System.out.println("Autor salvor: " + autorSalvo);
	}


}
