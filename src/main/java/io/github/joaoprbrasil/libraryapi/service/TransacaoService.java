package io.github.joaoprbrasil.libraryapi.service;

import io.github.joaoprbrasil.libraryapi.model.Autor;
import io.github.joaoprbrasil.libraryapi.model.GeneroLivro;
import io.github.joaoprbrasil.libraryapi.model.Livro;
import io.github.joaoprbrasil.libraryapi.repository.AutorRepository;
import io.github.joaoprbrasil.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private LivroRepository livroRepository;

    ///  livro (titulo, ..., nome_arquivo) -> id.png
    @Transactional
    public void salvarLivroComFoto(){
        // salva o livro
        // repository.save(livro)

        // pega o id do livro = livro.getId();
        // var id = livro.getId();

        // salvar foto do livro -> bucket na nuvem
        // bucketService.salvar(livro.getFoto(), id + ".png");

        // atualizar o nome do arquivo que foi salvo
        // livro.setNomeArquivFoto(id + ".png");
    }

    @Transactional
    public void atualizacaoSemAtualizar(){
        var livro = livroRepository
                .findById(UUID.fromString("52c14ce3-46f1-4927-a31f-fd7398949430"))
                .orElse(null);

        livro.setDataPublicacao(LocalDate.of(2024,1,1));
    }


    @Transactional
    public void executar(){
        // Salva o autor
        Autor autor = new Autor();
        autor.setNome("Smerdiakov");
        autor.setNacionalidade("Russia");
        autor.setDataNascimento(LocalDate.of(1821, 7, 11));

        autorRepository.save(autor);

        if(autor.getNome().equals("Smerdiakov")){
            throw new RuntimeException("Rollback");
        }

        // Salva o livro
        Livro novoLivro = new Livro();
        novoLivro.setTitulo("Karmazov Brothers");
        novoLivro.setIsbn("31255-51252");
        novoLivro.setPreco(BigDecimal.valueOf(192));
        novoLivro.setGenero(GeneroLivro.MISTERIO);
        novoLivro.setDataPublicacao(LocalDate.of(1980,12,12));

        novoLivro.setAutor(autor);

    }



}
