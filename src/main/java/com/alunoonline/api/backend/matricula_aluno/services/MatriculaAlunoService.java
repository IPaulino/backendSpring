package com.alunoonline.api.backend.matricula_aluno.services;

import com.alunoonline.api.backend.matricula_aluno.dtos.DisciplinasAlunoDto;
import com.alunoonline.api.backend.matricula_aluno.dtos.HistoricoAlunoDto;
import com.alunoonline.api.backend.matricula_aluno.dtos.MatriculaAlunoNotasOnlyDto;
import com.alunoonline.api.backend.matricula_aluno.models.MatriculaAluno;
import com.alunoonline.api.backend.matricula_aluno.repositories.MatriculaAlunoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MatriculaAlunoService {

    @Autowired
    MatriculaAlunoRepository repository;

    public MatriculaAluno create(MatriculaAluno matriculaAluno) {
        matriculaAluno.setStatus("Matriculado");
        return repository.save(matriculaAluno);
    }

    public void updateGrades(MatriculaAlunoNotasOnlyDto matriculaAlunoNotasOnlyDto, Long matriculaAlunoId) {
        Optional<MatriculaAluno> matriculaAlunoToUpdate = repository.findById(matriculaAlunoId);

        //verdadeiro ou falso, preciso atualizar
        boolean needUpdate = false;

        if (StringUtils.hasLength(matriculaAlunoNotasOnlyDto.getNota1().toString())) {
            matriculaAlunoToUpdate.ifPresent(matriculaAluno -> matriculaAluno.setNota1(matriculaAlunoNotasOnlyDto.getNota1()));
            needUpdate = true;
        }
        if (StringUtils.hasLength(matriculaAlunoNotasOnlyDto.getNota1().toString())) {
            matriculaAlunoToUpdate.ifPresent(matriculaAluno -> matriculaAluno.setNota2(matriculaAlunoNotasOnlyDto.getNota2()));
            needUpdate = true;
        }

        if(needUpdate){
            if(matriculaAlunoToUpdate.get().getNota1() != null && matriculaAlunoToUpdate.get().getNota2() != null){
                if((matriculaAlunoToUpdate.get().getNota1() + matriculaAlunoToUpdate.get().getNota2()) /2 >= 7) {
                    matriculaAlunoToUpdate.ifPresent(matriculaAluno -> matriculaAluno.setStatus("Aprovado"));
                }else{
                    matriculaAlunoToUpdate.ifPresent(matriculaAluno -> matriculaAluno.setStatus("Reprovado"));
                }
            }

            repository.save(matriculaAlunoToUpdate.get());
        }
    }

    public void updateStatusToBreak(Long matriculaAlunoId){
        Optional<MatriculaAluno> matriculaAlunoToUpdate = repository.findById(matriculaAlunoId);

        if(matriculaAlunoToUpdate.isPresent()) {
            if (Objects.equals(matriculaAlunoToUpdate.get().getStatus(), "Matriculado")) {
                matriculaAlunoToUpdate.ifPresent(matriculaAluno -> matriculaAluno.setStatus("Trancada"));
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Só é possível contrar com o status de Matriculado.");
            }
        } else {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Matricula não encontrada.");
        }
        repository.save(matriculaAlunoToUpdate.get());
    }

    public HistoricoAlunoDto getHistoricoFromAluno(Long alunoId){
        List<MatriculaAluno> matriculasDoAluno = repository.findByAlunoId(alunoId);

        if(!matriculasDoAluno.isEmpty()) {
            HistoricoAlunoDto historico = new HistoricoAlunoDto();

            historico.setNomeAluno(matriculasDoAluno.get(0).getAluno().getNome());
            historico.setCursoAluno(matriculasDoAluno.get(0).getAluno().getNome());
            List<DisciplinasAlunoDto> disciplinasList = new ArrayList<>();

            for (MatriculaAluno matricula : matriculasDoAluno){
                DisciplinasAlunoDto disciplinasAlunoDto = new DisciplinasAlunoDto();

                disciplinasAlunoDto.setNomeDisciplina(matricula.getDisciplina().getNome());
                disciplinasAlunoDto.setProfessorDisciplina(matricula.getDisciplina().getProfessor().getNome());
                disciplinasAlunoDto.setNota1(matricula.getNota1());
                disciplinasAlunoDto.setNota2(matricula.getNota2());

                if(matricula.getNota1() != null && matricula.getNota2() != null){
                    disciplinasAlunoDto.setMedia((matricula.getNota1() + matricula.getNota2()) /2);
                } else {
                    disciplinasAlunoDto.setMedia(null);
                }
                disciplinasAlunoDto.setStatus(matricula.getStatus());

                disciplinasList.add(disciplinasAlunoDto);
            }

            historico.setDisciplinasAlunoList(disciplinasList);
            return historico;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse aluno não possui matricula.");
        }
    }
}
