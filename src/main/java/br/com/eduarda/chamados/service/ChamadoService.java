package br.com.eduarda.chamados.service;

import br.com.eduarda.chamados.dto.AtualizarChamadoRequest;
import br.com.eduarda.chamados.dto.CriarChamadoRequest;
import br.com.eduarda.chamados.model.Chamado;
import br.com.eduarda.chamados.model.StatusChamado;
import br.com.eduarda.chamados.repository.ChamadoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ChamadoService {
    private final ChamadoRepository repository;

    public ChamadoService(ChamadoRepository repository) {
        this.repository = repository;
    }

    public Chamado criar(CriarChamadoRequest request) {
        Chamado chamado = new Chamado(
                request.titulo().trim(),
                request.descricao().trim(),
                request.solicitante().trim(),
                request.prioridade()
        );
        return repository.save(chamado);
    }

    public List<Chamado> listar(StatusChamado status) {
        if (status != null) {
            return repository.findByStatusOrderByCriadoEmDesc(status);
        }
        return repository.findAll();
    }

    public Chamado buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Chamado não encontrado: " + id));
    }

    public Chamado atualizar(Long id, AtualizarChamadoRequest request) {
        Chamado chamado = buscarPorId(id);
        chamado.atualizarDados(
                request.titulo().trim(),
                request.descricao().trim(),
                request.solicitante().trim(),
                request.prioridade()
        );
        return repository.save(chamado);
    }

    public Chamado atualizarStatus(Long id, StatusChamado status) {
        Chamado chamado = buscarPorId(id);
        chamado.atualizarStatus(status);
        return repository.save(chamado);
    }

    public void excluir(Long id) {
        Chamado chamado = buscarPorId(id);
        repository.delete(chamado);
    }
}
