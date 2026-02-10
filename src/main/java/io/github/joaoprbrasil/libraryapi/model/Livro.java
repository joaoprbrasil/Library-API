package io.github.joaoprbrasil.libraryapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "livro")
@Data // Cria os getters, setters, toString, equals e hashCode, argsConstructor

//@Getter@Setter
//@ToString
//@EqualsAndHashCode
//@RequiredArgsConstructor
//
// //@NoArgsConstructor
//@AllArgsConstructor

public class Livro {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "isbn", length = 20, nullable = false)
    private String isbn;

    @Column(name = "titulo", length = 150, nullable = false)
    private String titulo;

    @Column(name = "data_publicacao", nullable = false)
    private LocalDate dataPublicacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero", length = 30, nullable = false)
    private GeneroLivro genero;

    @Column(name = "preco", precision = 18, scale = 2, nullable = false)
    private BigDecimal preco; // ou private Double preco;

    @ManyToOne(
            //cascade = CascadeType.ALL // Deleta o autor ao deletar o produto
            fetch = FetchType.LAZY // FetchType.EAGER carrega tudo da entidade e entidades estrangeiras
    )
    @JoinColumn(name = "id_autor", nullable = false)
    private Autor autor;

}
