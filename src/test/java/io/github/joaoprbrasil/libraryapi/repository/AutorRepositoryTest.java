package io.github.joaoprbrasil.libraryapi.repository;

import io.github.joaoprbrasil.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Smerdiakov");
        autor.setNacionalidade("Russia");
        autor.setDataNascimento(LocalDate.of(1821, 7, 11));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor salvor: " + autorSalvo);
    }

    @Test
    public void atualizarTest(){
        var id = UUID.fromString("236a3f6a-b20f-4c9d-95c2-f4d42804c201");

        Optional<Autor> possivelAutor = repository.findById(id);

        if(possivelAutor.isPresent()){
            Autor autorEncontrado = possivelAutor.get();

            System.out.println("Dados do Autor: " + autorEncontrado);
            autorEncontrado.setDataNascimento(LocalDate.of(1961,1,10));

            repository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("236a3f6a-b20f-4c9d-95c2-f4d42804c201");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("27b9f811-e323-42c9-a929-26aae5d238d8");
        var autor = repository.findById(id).get();
        repository.delete(autor); // delete por objeto
    }
}
