package com.devromaomoura.helpdesk.domain;

import com.devromaomoura.helpdesk.domain.dto.PessoaDTO;
import com.devromaomoura.helpdesk.domain.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Tecnico extends Pessoa {

    @OneToMany(mappedBy = "tecnico", fetch = FetchType.LAZY)
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }

    public Tecnico(PessoaDTO tecnico) {
        this.id = tecnico.id;
        this.nome = tecnico.nome;
        this.cpf = tecnico.cpf;
        this.email = tecnico.email;
        this.senha = tecnico.senha;
        this.perfis = tecnico.perfis.stream().map(Perfil::getCodigo).collect(Collectors.toSet());
        this.dataCriacao = tecnico.dataCriacao;
    }
}
