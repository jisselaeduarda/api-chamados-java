package br.com.eduarda.chamados.dto;

import br.com.eduarda.chamados.model.StatusChamado;
import jakarta.validation.constraints.NotNull;

public record AtualizarStatusRequest(
        @NotNull(message = "O status é obrigatório.")
        StatusChamado status
) {}
