package com.alunoonline.api.backend.secretaria.services;

import com.alunoonline.api.backend.secretaria.models.Aluno;
import com.alunoonline.api.backend.secretaria.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired; // Injeção de dependências automatica.
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository repository;

    // metodo para salvar aluno no repositorio(BAnco de Dados?).
    public Aluno create(Aluno aluno) {
        return repository.save(aluno);
    }

    public List<Aluno> findAll() {
        return repository.findAll();
    }

    public Optional<Aluno> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}