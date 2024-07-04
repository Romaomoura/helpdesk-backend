package com.devromaomoura.helpdesk.services;

import com.devromaomoura.helpdesk.domain.Cliente;
import com.devromaomoura.helpdesk.domain.dto.PessoaDTO;
import com.devromaomoura.helpdesk.repositories.ClienteRepository;
import com.devromaomoura.helpdesk.services.exceptions.DataValidationException;
import com.devromaomoura.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PessoaService pessoaService;

    @Autowired private BCryptPasswordEncoder encoder;

    public PessoaDTO findById(Integer id) {
        Optional<Cliente> clienteOptional = repository.findById(id);
        if (clienteOptional.isEmpty()) throw new ObjectNotFoundException("Cliente não encontrado.");
        return new PessoaDTO(clienteOptional.get());
    }

    public List<PessoaDTO> findAll() {
        List<Cliente> clientes = repository.findAll();
        return clientes.stream().map(PessoaDTO::new).toList();
    }

    public PessoaDTO create(PessoaDTO objCliente) {
        pessoaService.validaCpfEmail(objCliente);
        objCliente.setSenha(encoder.encode(objCliente.getSenha()));
        Cliente newCliente = new Cliente(objCliente);
        repository.save(newCliente);
        objCliente.setId(newCliente.getId());
        return objCliente;
    }

    public PessoaDTO update(Integer id, PessoaDTO objCliente) {
        this.findById(id);
        objCliente.setId(id);
        repository.saveAndFlush(PessoaDTO.toClient(objCliente));
        return objCliente;
    }

    public void delete(Integer id) {
        Cliente cliente = this.retornaClienteCompleto(id);
        if (cliente.getChamados().size() > 0) {
            throw new DataValidationException("O cliente possui chamados associados.");
        }
        repository.deleteById(id);
    }

    public Cliente retornaClienteCompleto(Integer id) {
        Optional<Cliente> clienteOptional = repository.findById(id);
        return clienteOptional.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado."));
    }
}
