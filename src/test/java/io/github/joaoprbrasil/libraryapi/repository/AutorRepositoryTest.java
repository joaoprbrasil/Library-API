package io.github.joaoprbrasil.libraryapi.repository;

import io.github.joaoprbrasil.libraryapi.model.Autor;
import io.github.joaoprbrasil.libraryapi.model.GeneroLivro;
import io.github.joaoprbrasil.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;
    @Autowired
    LivroRepository livroRepository;

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

    @Test
    void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Raskolnikov");
        autor.setNacionalidade("Russia");
        autor.setDataNascimento(LocalDate.of(1731, 7, 11));

        Livro novoLivro = new Livro();
        novoLivro.setTitulo("Tradução página de biografia");
        novoLivro.setIsbn("31255-51252");
        novoLivro.setPreco(BigDecimal.valueOf(354));
        novoLivro.setGenero(GeneroLivro.BIOGRAFIA);
        novoLivro.setDataPublicacao(LocalDate.of(1690,12,12));
        novoLivro.setAutor(autor);

        Livro novoLivro2 = new Livro();
        novoLivro2.setTitulo("Ensaio sobre dilemas morais");
        novoLivro2.setIsbn("31255-51252");
        novoLivro2.setPreco(BigDecimal.valueOf(354));
        novoLivro2.setGenero(GeneroLivro.CIENCIA);
        novoLivro2.setDataPublicacao(LocalDate.of(1780,12,12));
        novoLivro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(novoLivro);
        autor.getLivros().add(novoLivro2);

        repository.save(autor);

        livroRepository.saveAll(autor.getLivros());
    }


    @Test
    // @Transactional // deixa a transição aberta
    void listarLivrosAutor(){
        var id = UUID.fromString("9645c316-c495-4cc4-b635-be7fdad26124");
        var autor = repository.findById(id).get();

        List<Livro> livros = livroRepository.findByAutor(autor);
        autor.setLivros(livros);

        autor.getLivros().forEach(System.out::println);
    }
}
