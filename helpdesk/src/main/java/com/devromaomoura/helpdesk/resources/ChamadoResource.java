package com.devromaomoura.helpdesk.resources;

import com.devromaomoura.helpdesk.domain.dto.ChamadoDTO;
import com.devromaomoura.helpdesk.services.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

    @Autowired
    private ChamadoService service;

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable("id") Integer id){
        return ResponseEntity.status(200).body(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll(){
        return ResponseEntity.status(200).body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<ChamadoDTO> create(@RequestBody @Valid ChamadoDTO objChamado){
        ChamadoDTO chamado = service.create(objChamado);
        return ResponseEntity.status(201).body(chamado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChamadoDTO> update(@PathVariable("id") Integer id,@RequestBody @Valid ChamadoDTO objChamado){
        ChamadoDTO chamado = service.update(id, objChamado);
        return ResponseEntity.status(201).body(chamado);
    }
}
