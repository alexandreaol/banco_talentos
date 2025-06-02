package com.talentos_banco.talentos_proz.alunos.repository;

import com.talentos_banco.talentos_proz.alunos.dto.AlunoDTO;
import com.talentos_banco.talentos_proz.alunos.model.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<AlunoModel, Long> {
    Optional<AlunoModel> findBynome(String nome);
}
