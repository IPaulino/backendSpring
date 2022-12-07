package com.alunoonline.api.backend.secretaria.repositories;

import com.alunoonline.api.backend.secretaria.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
