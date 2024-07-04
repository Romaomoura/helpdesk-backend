package com.devromaomoura.helpdesk.resources;

import com.devromaomoura.helpdesk.domain.dto.ChamadoDTO;
import com.devromaomoura.helpdesk.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

    @Autowired
    private ChamadoService service;

    @GetMapping("/{id}")
    private ResponseEntity<ChamadoDTO> findById(@PathVariable("id") Integer id){
        return ResponseEntity.status(200).body(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll(){
        return ResponseEntity.status(200).body(service.findAll());
    }
}
