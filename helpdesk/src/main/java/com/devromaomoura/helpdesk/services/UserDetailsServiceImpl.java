package com.devromaomoura.helpdesk.services;

import com.devromaomoura.helpdesk.domain.Pessoa;
import com.devromaomoura.helpdesk.repositories.PessoaRepository;
import com.devromaomoura.helpdesk.security.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Pessoa> user = pessoaRepository.findByEmail(email);
        return user.map(pessoa ->
                new UserSecurityService(pessoa.getId(), pessoa.getEmail(), pessoa.getSenha(), pessoa.getPerfis()))
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
