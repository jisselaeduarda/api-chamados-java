package br.com.eduarda.chamados.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chamados")
public class Chamado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false, length = 1000)
    private String descricao;

    @Column(nullable = false, length = 120)
    private String solicitante;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PrioridadeChamado prioridade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusChamado status;

    @Column(nullable = false)
    private LocalDateTime criadoEm;

    private LocalDateTime atualizadoEm;

    protected Chamado() {
    }

    public Chamado(String titulo, String descricao, String solicitante, PrioridadeChamado prioridade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.solicitante = solicitante;
        this.prioridade = prioridade;
        this.status = StatusChamado.ABERTO;
        this.criadoEm = LocalDateTime.now();
    }

    @PreUpdate
    void registrarAtualizacao() {
        this.atualizadoEm = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public String getSolicitante() { return solicitante; }
    public PrioridadeChamado getPrioridade() { return prioridade; }
    public StatusChamado getStatus() { return status; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public LocalDateTime getAtualizadoEm() { return atualizadoEm; }

    public void atualizarDados(String titulo, String descricao, String solicitante,
                               PrioridadeChamado prioridade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.solicitante = solicitante;
        this.prioridade = prioridade;
    }

    public void atualizarStatus(StatusChamado status) {
        this.status = status;
    }
}
