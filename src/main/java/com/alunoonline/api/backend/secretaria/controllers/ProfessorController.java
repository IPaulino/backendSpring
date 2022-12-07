package com.alunoonline.api.backend.secretaria.controllers;

import com.alunoonline.api.backend.secretaria.models.Professor;
import com.alunoonline.api.backend.secretaria.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    ProfessorService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Professor> create(@RequestBody Professor professor){
        Professor professorCreate = service.create(professor);

        return ResponseEntity.status(201).body(professorCreate);
    }
}