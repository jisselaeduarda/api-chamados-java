package br.com.eduarda.chamados.dto;

import br.com.eduarda.chamados.model.PrioridadeChamado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CriarChamadoRequest(
        @NotBlank(message = "O título é obrigatório.")
        @Size(max = 100, message = "O título deve ter no máximo 100 caracteres.")
        String titulo,

        @NotBlank(message = "A descrição é obrigatória.")
        @Size(max = 1000, message = "A descrição deve ter no máximo 1000 caracteres.")
        String descricao,

        @NotBlank(message = "O nome do solicitante é obrigatório.")
        @Size(max = 120, message = "O nome deve ter no máximo 120 caracteres.")
        String solicitante,

        @NotNull(message = "A prioridade é obrigatória.")
        PrioridadeChamado prioridade
) {}
