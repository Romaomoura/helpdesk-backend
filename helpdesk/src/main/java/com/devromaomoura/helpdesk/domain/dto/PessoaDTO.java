package com.devromaomoura.helpdesk.domain.dto;

import com.devromaomoura.helpdesk.domain.Tecnico;
import com.devromaomoura.helpdesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PessoaDTO {
    public Integer id;
    public String nome;
    public String cpf;
    public String email;
    public String senha;
    public Set<Perfil> perfis = new HashSet<>();
    @JsonFormat(pattern = "dd/MM/yyyy")
    public LocalDate dataCriacao = LocalDate.now();

    public PessoaDTO() {
    }

    public PessoaDTO(Tecnico tecnico) {
        this.id = tecnico.getId();
        this.nome = tecnico.getNome();
        this.cpf = tecnico.getCpf();
        this.email = tecnico.getEmail();
        this.senha = tecnico.getSenha();
        this.perfis = tecnico.getPerfis();
        this.dataCriacao = tecnico.getDataCriacao();
    }
}
