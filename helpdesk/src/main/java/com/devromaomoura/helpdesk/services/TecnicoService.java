package com.devromaomoura.helpdesk.services;

import com.devromaomoura.helpdesk.domain.Tecnico;
import com.devromaomoura.helpdesk.domain.dto.PessoaDTO;
import com.devromaomoura.helpdesk.repositories.TecnicoRepository;
import com.devromaomoura.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public PessoaDTO findById(Integer id) {
        Optional<Tecnico> tecnicoOptional = repository.findById(id);
        if (tecnicoOptional.isEmpty()) throw new ObjectNotFoundException("Técnico não encontrado.");
        return new PessoaDTO(tecnicoOptional.get());
    }

    public List<PessoaDTO> findAll() {
        List<Tecnico> tecnicos = repository.findAll();
        return tecnicos.stream().map(PessoaDTO::new).toList();
    }
}
