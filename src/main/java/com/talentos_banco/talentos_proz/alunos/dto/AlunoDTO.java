package com.talentos_banco.talentos_proz.alunos.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoDTO {

    private Long id;
    private String nome;
    private String sobrenome;
    private int idade;
    private String descricao;
    private int telefone;
    private String email;
    private String github;
    private String dataFormatura;
    private String areaInteresse;
    private String softSkills;
    private Long cursoId;
}
