package com.devromaomoura.helpdesk.services;

import com.devromaomoura.helpdesk.domain.Tecnico;
import com.devromaomoura.helpdesk.domain.dto.PessoaDTO;
import com.devromaomoura.helpdesk.domain.enums.Perfil;
import com.devromaomoura.helpdesk.repositories.PessoaRepository;
import com.devromaomoura.helpdesk.repositories.TecnicoRepository;
import com.devromaomoura.helpdesk.services.exceptions.DataValidationException;
import com.devromaomoura.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired private PessoaService pessoaService;

    public PessoaDTO findById(Integer id) {
        Optional<Tecnico> tecnicoOptional = repository.findById(id);
        if (tecnicoOptional.isEmpty()) throw new ObjectNotFoundException("Técnico não encontrado.");
        return new PessoaDTO(tecnicoOptional.get());
    }

    public List<PessoaDTO> findAll() {
        List<Tecnico> tecnicos = repository.findAll();
        return tecnicos.stream().map(PessoaDTO::new).toList();
    }

    public PessoaDTO create(PessoaDTO objTecnico) {
        pessoaService.validaCpfEmail(objTecnico);
        Tecnico newTecnico = new Tecnico(objTecnico);
        repository.save(newTecnico);
        objTecnico.setId(newTecnico.getId());
        objTecnico.addPerfil(Perfil.CLIENTE);
        return objTecnico;
    }

}
