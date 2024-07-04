package com.devromaomoura.helpdesk.resources;

import com.devromaomoura.helpdesk.domain.Tecnico;
import com.devromaomoura.helpdesk.domain.dto.PessoaDTO;
import com.devromaomoura.helpdesk.services.TecnicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService service;

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable("id") Integer id) {
        PessoaDTO tecnico = service.findById(id);
        return ResponseEntity.ok(tecnico);
    }

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> findAll() {
        List<PessoaDTO> tecnicos = service.findAll();
        return ResponseEntity.status(201).body(tecnicos);
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> create(@RequestBody @Valid PessoaDTO objTecnico) {
        PessoaDTO tecnico = service.create(objTecnico);
        return ResponseEntity.status(201).body(tecnico);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> update(@PathVariable("id") Integer id, @RequestBody @Valid PessoaDTO objTecnico) {
        PessoaDTO tecnico = service.update(id, objTecnico);
        return ResponseEntity.status(201).body(tecnico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.status(201).body("Deletado com Sucesso");
    }
}
