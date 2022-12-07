package com.alunoonline.api.backend.matricula_aluno.controllers;

import com.alunoonline.api.backend.matricula_aluno.dtos.HistoricoAlunoDto;
import com.alunoonline.api.backend.matricula_aluno.dtos.MatriculaAlunoNotasOnlyDto;
import com.alunoonline.api.backend.matricula_aluno.models.MatriculaAluno;
import com.alunoonline.api.backend.matricula_aluno.services.MatriculaAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/matricula-aluno")
public class MatriculaAlunoController {

    @Autowired
    MatriculaAlunoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MatriculaAluno> create(@RequestBody MatriculaAluno matriculaAluno){
        MatriculaAluno matriculaAlunoCreated = service.create(matriculaAluno);

        return ResponseEntity.status(201).body(matriculaAlunoCreated);
    }

    @PatchMapping("/updateGrades/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void patch(@RequestBody MatriculaAlunoNotasOnlyDto matriculaAlunoNotasOnlyDto,
                      @PathVariable Long id){
        service.updateGrades(matriculaAlunoNotasOnlyDto, id);
    }

    @PatchMapping("/updateStatusToBreak/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void patchStatus(@PathVariable Long id){
        service.updateStatusToBreak(id);
    }

    @GetMapping("/studentsGrades/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HistoricoAlunoDto studentsGrades(@PathVariable Long id){
        return service.getHistoricoFromAluno(id);
    }
}
