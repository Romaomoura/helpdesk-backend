package com.devromaomoura.helpdesk.services;

import com.devromaomoura.helpdesk.domain.Tecnico;
import com.devromaomoura.helpdesk.domain.dto.PessoaDTO;
import com.devromaomoura.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public PessoaDTO findById(Integer id) {
        Optional<Tecnico> tecnicoOptional = Optional.of(repository.getReferenceById(id));
        return Tecnico.toDTO(tecnicoOptional.get());
    }
}
