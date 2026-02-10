package io.github.joaoprbrasil.libraryapi.repository;

import io.github.joaoprbrasil.libraryapi.model.Autor;
import io.github.joaoprbrasil.libraryapi.model.GeneroLivro;
import io.github.joaoprbrasil.libraryapi.model.Livro;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest(){
        Livro novoLivro = new Livro();
        novoLivro.setTitulo("Crime e Castigo");
        novoLivro.setIsbn("31255-51252");
        novoLivro.setPreco(BigDecimal.valueOf(192));
        novoLivro.setGenero(GeneroLivro.MISTERIO);
        novoLivro.setDataPublicacao(LocalDate.of(1980,12,12));

        Autor autor = autorRepository
                .findById(UUID.fromString("236a3f6a-b20f-4c9d-95c2-f4d42804c201"))
                .orElse(null);

        novoLivro.setAutor(autor);

        repository.save(novoLivro);
    }

    @Test
    void salvarAutorELivroTest(){
        Livro novoLivro = new Livro();
        novoLivro.setTitulo("Karmazov Brothers");
        novoLivro.setIsbn("31255-51252");
        novoLivro.setPreco(BigDecimal.valueOf(192));
        novoLivro.setGenero(GeneroLivro.MISTERIO);
        novoLivro.setDataPublicacao(LocalDate.of(1980,12,12));

        Autor autor = new Autor();
        autor.setNome("Smerdiakov");
        autor.setNacionalidade("Russia");
        autor.setDataNascimento(LocalDate.of(1821, 7, 11));

        autorRepository.save(autor);

        novoLivro.setAutor(autor);

        repository.save(novoLivro);
    }

    @Test
    void salvarCascadeTest(){
        Livro novoLivro = new Livro();
        novoLivro.setTitulo("Crime e Castigo");
        novoLivro.setIsbn("31255-51252");
        novoLivro.setPreco(BigDecimal.valueOf(192));
        novoLivro.setGenero(GeneroLivro.MISTERIO);
        novoLivro.setDataPublicacao(LocalDate.of(1980,12,12));

        Autor autor = new Autor();
        autor.setNome("Smerdiakov");
        autor.setNacionalidade("Russia");
        autor.setDataNascimento(LocalDate.of(1821, 7, 11));

        novoLivro.setAutor(autor);

        repository.save(novoLivro);
    }

    @Test
    void atualizarAutorDoLivroTest(){
        UUID id = UUID.fromString("1253771d-3674-4d49-aff7-7471697fce54");
        var livroParaAtualizar = repository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("f019d8e2-ada6-4433-9d59-166b5f5286fe");
        Autor autor = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(autor);

        repository.save(livroParaAtualizar);
    }

    @Test
    void deletarTest(){
        UUID id = UUID.fromString("1253771d-3674-4d49-aff7-7471697fce54");
        repository.deleteById(id);
    }

    @Test
    void deletarPorCascadeTest(){
        UUID id = UUID.fromString("1253771d-3674-4d49-aff7-7471697fce54");
        repository.deleteById(id);
    }

    @Test
    @Transactional
    void buscarLivroTest(){
        UUID id = UUID.fromString("1253771d-3674-4d49-aff7-7471697fce54");
        Livro livro = repository.findById(id).orElse(null);
        System.out.println("Livro:");
        System.out.println(livro.getTitulo());

        System.out.println("Autor:");
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void buscaPorTitulo(){
        List<Livro> lista = repository.findByTitulo("Crime e Castigo");
        lista.forEach(System.out::println);
    }

    @Test
    void buscaPorIsbn(){
        List<Livro> lista = repository.findByIsbn("31255-51252");
        lista.forEach(System.out::println);
    }

    @Test
    void buscaPorTituloEPreco(){
        String tituloPesquisa = "Crime e Castigo";
        BigDecimal precoPesquisa = BigDecimal.valueOf(192);
        List<Livro> lista = repository.findByTituloAndPreco(tituloPesquisa, precoPesquisa);
        lista.forEach(System.out::println);
    }

}