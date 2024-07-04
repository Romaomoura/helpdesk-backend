package com.devromaomoura.helpdesk.services;

import com.devromaomoura.helpdesk.domain.Chamado;
import com.devromaomoura.helpdesk.domain.Cliente;
import com.devromaomoura.helpdesk.domain.Tecnico;
import com.devromaomoura.helpdesk.domain.dto.ChamadoDTO;
import com.devromaomoura.helpdesk.domain.enums.Prioridade;
import com.devromaomoura.helpdesk.domain.enums.Status;
import com.devromaomoura.helpdesk.repositories.ChamadoRepository;
import com.devromaomoura.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    @Autowired TecnicoService tecnicoService;
    @Autowired ClienteService clienteService;

    public ChamadoDTO findById(Integer id){
        Optional<Chamado> chamado = repository.findById(id);
        if (chamado.isEmpty()) throw new ObjectNotFoundException("Chamado não encontrado");
        return new ChamadoDTO(chamado.get());
    }

    public List<ChamadoDTO> findAll() {
        List<Chamado> chamados = repository.findAll();
        return chamados.stream().map(ChamadoDTO::new).toList();
    }

    public ChamadoDTO create(ChamadoDTO objChamado) {
        var chamadoSalvo = repository.save(this.newChamado(objChamado));
        objChamado.setId(chamadoSalvo.getId());
        objChamado.setNomeTecnico(chamadoSalvo.getTecnico().getNome());
        objChamado.setNomeCliente(chamadoSalvo.getCliente().getNome());
        return objChamado;
    }
    public ChamadoDTO update(Integer id, ChamadoDTO objChamado) {
        this.findById(id);
        objChamado.setId(id);
        var chamado = repository.saveAndFlush(newChamado(objChamado));
        objChamado.setNomeTecnico(chamado.getTecnico().getNome());
        objChamado.setNomeCliente(chamado.getCliente().getNome());
        return objChamado;
    }

    private Chamado newChamado(ChamadoDTO objChamado){
        Tecnico tecnico = tecnicoService.retornaTecnicoCompleto(objChamado.getTecnico());
        Cliente cliente = clienteService.retornaClienteCompleto(objChamado.getCliente());

        Chamado chamado = new Chamado();

        if (objChamado.getId() != null) chamado.setId(objChamado.getId());

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(objChamado.getPrioridade()));
        chamado.setStatus(Status.toEnum(objChamado.getStatus()));
        chamado.setTitulo(objChamado.getTitulo());
        chamado.setObsevacoes(objChamado.getObsevacoes());
        return chamado;

    }


    private Chamado retornaChamadoCompleto(Integer id){
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado"));
    }
}
