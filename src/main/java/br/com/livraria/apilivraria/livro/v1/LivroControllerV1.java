package br.com.livraria.apilivraria.livro.v1;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.livraria.apilivraria.livro.Livro;
import br.com.livraria.apilivraria.livro.LivroDTO;
import br.com.livraria.apilivraria.livro.services.DeleteLivroService;
import br.com.livraria.apilivraria.livro.services.GetLivroService;
import br.com.livraria.apilivraria.livro.services.ListLivroService;
import br.com.livraria.apilivraria.livro.services.SaveLivroService;
import br.com.livraria.apilivraria.livro.services.UpdateLivroService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/livro")
public class LivroControllerV1 {

    private final GetLivroService getLivroService;
    private final ListLivroService listLivroService;
    private final SaveLivroService saveLivroService;
    private final UpdateLivroService updateLivroService;
    private final DeleteLivroService deleteLivroService;

    @GetMapping(value = "/{id}") 
    public LivroDTO find(@PathVariable("id") Long id) {
        return LivroDTO.from(getLivroService.find(id));
    }

    @GetMapping 
    public List<LivroDTO> findAll() {
        return LivroDTO.fromAll(listLivroService.findAll());
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping //adiciona um novo Livro
    public void insert(@Valid @RequestBody LivroDTO livroDTO) {
        saveLivroService.insert(Livro.to(livroDTO));
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}") //atualizar um Livro
    public void update(@Valid @RequestBody LivroDTO livroDTO, @PathVariable Long id) {
		updateLivroService.update(Livro.to(livroDTO), id);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}") //Deleta Livro
    public void delete(@PathVariable Long id) {
        deleteLivroService.delete(id);
    }

}
