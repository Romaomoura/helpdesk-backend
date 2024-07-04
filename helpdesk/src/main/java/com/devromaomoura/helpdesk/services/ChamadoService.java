package com.devromaomoura.helpdesk.services;

import com.devromaomoura.helpdesk.domain.Chamado;
import com.devromaomoura.helpdesk.domain.dto.ChamadoDTO;
import com.devromaomoura.helpdesk.repositories.ChamadoRepository;
import com.devromaomoura.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    public ChamadoDTO findById(Integer id){
        Optional<Chamado> chamado = repository.findById(id);
        if (chamado.isEmpty()) throw new ObjectNotFoundException("Chamado não encontrado");
        return new ChamadoDTO(chamado.get());
    }
}
