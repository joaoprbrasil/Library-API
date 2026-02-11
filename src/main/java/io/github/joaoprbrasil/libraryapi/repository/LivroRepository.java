package io.github.joaoprbrasil.libraryapi.repository;

import io.github.joaoprbrasil.libraryapi.model.Autor;
import io.github.joaoprbrasil.libraryapi.model.GeneroLivro;
import io.github.joaoprbrasil.libraryapi.model.Livro;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    //Query Method
    // select * from livro where id_autor = id;
    List<Livro> findByAutor(Autor autor);

    // select * from livro where titulo = ?;
    List<Livro> findByTitulo(String titulo);

    // select * from livro where isbn = ?;
    List<Livro> findByIsbn(String isbn);

    // select * from livro where titulo = ? and preco = ?;
    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    // select * from livro where titulo = ? or preco = ?;
    List<Livro> findByTituloOrIsbn(String titulo, String isbn);

    // select * from livro data_publicacao between ? = ?
    List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);

    // JPQL -> referencia as entidades e as propriedades
    // select l.* from livro as l order by l.titulo
    @Query(" select l from Livro as l order by l.titulo ")
    List<Livro> listarTodos();

    // select l.* from livro as l order by l.titulo, l.preco
    @Query(" select l from Livro as l order by l.titulo, l.preco ")
    List<Livro> listarTodosOrdenadoPorTituloAndPreco();

    // select a.* from livro l join autor a on a.id = l.id_autor
    @Query(" select a from Livro as l join l.autor as a ")
    List<Autor> listarAutoresDosLivros();

    // select distinct l.* from livro l
    @Query("select distinct l.titulo from Livro l")
    List<String> listarNomesDiferentesLivros();


    @Query("""
        select l.genero
        from Livro l
        join l.autor a
        where a.nacionalidade = 'Russia'
        order by l.genero
    """)
    List<String> listarGenerosAutoresRussos();

    // named parameters -> parametros nomeados
    @Query("select l from Livro l where l.genero = :generoLivro order by :paramOrdenacao")
    List<Livro> findByGenero(@Param("generoLivro") GeneroLivro generoLivro,
                             @Param("paramOrdenacao") String paramOrdenacao);

    // positional parameters
    @Query("select l from Livro l where l.genero = ?1 order by ?2")
    List<Livro> findByGeneroPositionalParameters(GeneroLivro generoLivro,
                                                 String paramOrdenacao);

    @Modifying
    @Transactional
    @Query("delete from Livro where genero = ?1")
    void deleteByGenero(GeneroLivro genero);

    @Modifying
    @Transactional
    @Query("update Livro set dataPublicacao = ?1")
    void updateDataPublicacao(LocalDate novaData);

    boolean existsByAutor(Autor autor);
}
