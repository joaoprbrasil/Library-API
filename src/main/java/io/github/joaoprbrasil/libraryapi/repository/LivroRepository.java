package io.github.joaoprbrasil.libraryapi.repository;

import io.github.joaoprbrasil.libraryapi.model.Autor;
import io.github.joaoprbrasil.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    //Query Method
    // select * from livro where id_autor = id;
    List<Livro> findByAutor(Autor autor);

    // select * from livro where titulo = titulo;
    List<Livro> findByTitulo(String titulo);

    // select * from livro where isbn = isbn;
    List<Livro> findByIsbn(String isbn);

    // select * from livro where titulo = titulo and preco = preco;
    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    // select * from livro where titulo = titulo or preco = preco;
    List<Livro> findByTituloOrIsbn(String titulo, String isbn);
}
