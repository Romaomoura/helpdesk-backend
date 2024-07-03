package com.devromaomoura.helpdesk.services;

import com.devromaomoura.helpdesk.domain.Tecnico;
import com.devromaomoura.helpdesk.repositories.TecnicoRepository;
import com.devromaomoura.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> tecnicoOptional = repository.findById(id);
        return tecnicoOptional.orElseThrow(() -> new ObjectNotFoundException("Tecnico não encontrado."));
    }
}
