package com.alunoonline.api.backend.secretaria.repositories;

import com.alunoonline.api.backend.secretaria.models.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
}
