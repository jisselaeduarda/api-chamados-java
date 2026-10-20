package br.com.eduarda.chamados.controller;

import br.com.eduarda.chamados.dto.AtualizarChamadoRequest;
import br.com.eduarda.chamados.dto.AtualizarStatusRequest;
import br.com.eduarda.chamados.dto.CriarChamadoRequest;
import br.com.eduarda.chamados.model.Chamado;
import br.com.eduarda.chamados.model.StatusChamado;
import br.com.eduarda.chamados.service.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chamados")
public class ChamadoController {
    private final ChamadoService service;

    public ChamadoController(ChamadoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Chamado criar(@Valid @RequestBody CriarChamadoRequest request) {
        return service.criar(request);
    }

    @GetMapping
    public List<Chamado> listar(@RequestParam(required = false) StatusChamado status) {
        return service.listar(status);
    }

    @GetMapping("/{id}")
    public Chamado buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Chamado atualizar(@PathVariable Long id,
                             @Valid @RequestBody AtualizarChamadoRequest request) {
        return service.atualizar(id, request);
    }

    @PatchMapping("/{id}/status")
    public Chamado atualizarStatus(@PathVariable Long id,
                                   @Valid @RequestBody AtualizarStatusRequest request) {
        return service.atualizarStatus(id, request.status());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
