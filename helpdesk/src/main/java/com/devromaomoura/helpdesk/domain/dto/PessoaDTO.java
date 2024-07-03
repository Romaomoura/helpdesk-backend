package com.devromaomoura.helpdesk.domain.dto;

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

    public PessoaDTO(Integer id, String nome, String cpf, String email, String senha, Set<Perfil> perfis, LocalDate dataCriacao) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.perfis = perfis;
        this.dataCriacao = dataCriacao;
    }
}
