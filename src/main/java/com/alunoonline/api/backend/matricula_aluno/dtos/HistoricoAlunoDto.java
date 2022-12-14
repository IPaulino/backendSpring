package com.alunoonline.api.backend.matricula_aluno.dtos;

import lombok.Data;

import java.util.List;

@Data
public class HistoricoAlunoDto {
    private String nomeAluno;
    private String cursoAluno;
    private List<DisciplinasAlunoDto> disciplinasAlunoList;
}
