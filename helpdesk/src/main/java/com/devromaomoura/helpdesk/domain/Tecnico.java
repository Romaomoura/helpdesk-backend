package com.devromaomoura.helpdesk.domain;

import com.devromaomoura.helpdesk.domain.dto.PessoaDTO;
import com.devromaomoura.helpdesk.domain.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

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

    public static PessoaDTO toDTO(Tecnico tecnico) {
        if (tecnico == null) return null;

        PessoaDTO obj = new PessoaDTO();
        obj.id = tecnico.getId();
        obj.nome = tecnico.getNome();
        obj.cpf = tecnico.getCpf();
        obj.email = tecnico.getEmail();
        obj.senha = tecnico.getSenha();
        obj.perfis = tecnico.getPerfis();
        obj.dataCriacao = tecnico.getDataCriacao();

        return obj;

    }
}
