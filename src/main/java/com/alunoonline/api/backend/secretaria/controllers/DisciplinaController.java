package com.alunoonline.api.backend.secretaria.controllers;

import com.alunoonline.api.backend.secretaria.models.Disciplina;
import com.alunoonline.api.backend.secretaria.services.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {

    @Autowired
    DisciplinaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Disciplina> create(@RequestBody Disciplina disciplina) {
        Disciplina disciplinaCreate = service.create(disciplina);

        return ResponseEntity.status(201).body(disciplinaCreate);
    }
}