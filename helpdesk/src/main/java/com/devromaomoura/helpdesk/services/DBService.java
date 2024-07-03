package com.devromaomoura.helpdesk.services;

import com.devromaomoura.helpdesk.domain.Chamado;
import com.devromaomoura.helpdesk.domain.Cliente;
import com.devromaomoura.helpdesk.domain.Tecnico;
import com.devromaomoura.helpdesk.domain.enums.Perfil;
import com.devromaomoura.helpdesk.domain.enums.Prioridade;
import com.devromaomoura.helpdesk.domain.enums.Status;
import com.devromaomoura.helpdesk.repositories.ChamadoRepository;
import com.devromaomoura.helpdesk.repositories.ClienteRepository;
import com.devromaomoura.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBService {
    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    public boolean instanciaDB(){
        Tecnico tec1 = new Tecnico(null, "Romao", "12345678910", "romao@mail.com", "123");
        tec1.addPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Amanda", "12345678922", "amanda@mail.com", "123");

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.EM_ANDAMENTO, "Chamado 1", "Primeiro Chamado", tec1, cli1);

        Tecnico tec2 = new Tecnico(null, "Moura", "12345676610", "moura@mail.com", "123");
        tec2.addPerfil(Perfil.ADMIN);

        Cliente cli2 = new Cliente(null, "Luna", "12348878922", "luna@mail.com", "123");

        Chamado c2 = new Chamado(null, Prioridade.MEDIA, Status.EM_ANDAMENTO, "Chamado 2", "Segundo Chamado", tec1, cli1);

        tecnicoRepository.saveAll(List.of(tec1, tec2));
        clienteRepository.saveAll(List.of(cli1, cli2));
        chamadoRepository.saveAll(List.of(c1, c2));

        return true;
    }

}
