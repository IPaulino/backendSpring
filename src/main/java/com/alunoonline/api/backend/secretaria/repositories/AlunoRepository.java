package com.alunoonline.api.backend.secretaria.repositories;

import com.alunoonline.api.backend.secretaria.models.Aluno; // Import da Classe Aluno.
import org.springframework.data.jpa.repository.JpaRepository; //
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
