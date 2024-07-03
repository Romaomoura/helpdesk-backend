package com.devromaomoura.helpdesk.services;

import com.devromaomoura.helpdesk.domain.dto.PessoaDTO;
import com.devromaomoura.helpdesk.repositories.PessoaRepository;
import com.devromaomoura.helpdesk.services.exceptions.DataValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public void validaCpfEmail(PessoaDTO obj) {
        if (pessoaRepository.existsByCpf(obj.getCpf())) throw new
                DataValidationException("Já existe um registro com esse cpf");
        if (pessoaRepository.existsByEmail(obj.getEmail())) throw new
                DataValidationException("Já existe um registro com esse email");
    }
}
