package com.devromaomoura.helpdesk.services;

import com.devromaomoura.helpdesk.domain.Tecnico;
import com.devromaomoura.helpdesk.domain.dto.PessoaDTO;
import com.devromaomoura.helpdesk.repositories.TecnicoRepository;
import com.devromaomoura.helpdesk.services.exceptions.DataValidationException;
import com.devromaomoura.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private PessoaService pessoaService;

    @Autowired private BCryptPasswordEncoder encoder;

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
        objTecnico.setSenha(encoder.encode(objTecnico.getSenha()));
        Tecnico newTecnico = new Tecnico(objTecnico);
        repository.save(newTecnico);
        return new PessoaDTO(newTecnico);
    }

    public PessoaDTO update(Integer id, PessoaDTO objTecnico) {
        this.findById(id);
        objTecnico.setId(id);
        repository.saveAndFlush(PessoaDTO.toTecnico(objTecnico));
        return objTecnico;
    }

    public void delete(Integer id) {
        Tecnico tecnico = this.retornaTecnicoCompleto(id);
        if (tecnico.getChamados().size() > 0) {
            throw new DataValidationException("O tecnico possui chamados associados.");
        }
        repository.deleteById(id);
    }

    public Tecnico retornaTecnicoCompleto(Integer id) {
        Optional<Tecnico> tecnico = repository.findById(id);
        return tecnico.orElseThrow(() -> new ObjectNotFoundException("Técnico não encontrado."));
    }
}
