package com.talentos_banco.talentos_proz.cursos.dto;

import com.talentos_banco.talentos_proz.alunos.dto.AlunoDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CursoDTO {
    private Long id_curso;

    private String nome;

    private String codigo;

    private List<AlunoDTO> alunos;
}
