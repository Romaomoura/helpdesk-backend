package com.devromaomoura.helpdesk.domain.dto;

import com.devromaomoura.helpdesk.domain.Chamado;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ChamadoDTO {
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;
    @NotNull(message = "O campo PRIORIDADE é obrigatorio.")
    private Integer prioridade;
    @NotNull(message = "O campo STATUS é obrigatorio.")
    private Integer status;
    @NotBlank(message = "O campo TITULO é obrigatorio.")
    private String titulo;
    @NotBlank(message = "O campo OBSERVACOES é obrigatorio.")
    private String obsevacoes;
    @NotNull(message = "O campo TECNICO é obrigatorio.")
    private Integer tecnico;
    @NotNull(message = "O campo CLIENTE é obrigatorio.")
    private Integer cliente;
    private String nomeTecnico;
    private String nomeCliente;

    public ChamadoDTO() {
        super();
    }

    public ChamadoDTO(Chamado chamado) {
        super();
        this.id = chamado.getId();
        this.dataAbertura = chamado.getDataAbertura();
        this.dataFechamento = chamado.getDataFechamento();
        this.prioridade = chamado.getPrioridade().getCodigo();
        this.status = chamado.getStatus().getCodigo();
        this.titulo = chamado.getTitulo();
        this.obsevacoes = chamado.getObsevacoes();
        this.tecnico = chamado.getTecnico().getId();
        this.cliente = chamado.getCliente().getId();
        this.nomeTecnico = chamado.getTecnico().getNome();
        this.nomeCliente = chamado.getCliente().getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObsevacoes() {
        return obsevacoes;
    }

    public void setObsevacoes(String obsevacoes) {
        this.obsevacoes = obsevacoes;
    }

    public Integer getTecnico() {
        return tecnico;
    }

    public void setTecnico(Integer tecnico) {
        this.tecnico = tecnico;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public String getNomeTecnico() {
        return nomeTecnico;
    }

    public void setNomeTecnico(String nomeTecnico) {
        this.nomeTecnico = nomeTecnico;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}
