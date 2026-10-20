package br.com.eduarda.chamados.repository;

import br.com.eduarda.chamados.model.Chamado;
import br.com.eduarda.chamados.model.StatusChamado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
    List<Chamado> findByStatusOrderByCriadoEmDesc(StatusChamado status);
}
