package com.alunoonline.api.backend.secretaria.services;

import com.alunoonline.api.backend.secretaria.models.Disciplina;
import com.alunoonline.api.backend.secretaria.repositories.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository repository;

    public Disciplina create(Disciplina disciplina){
        return repository.save(disciplina);
    }

}
