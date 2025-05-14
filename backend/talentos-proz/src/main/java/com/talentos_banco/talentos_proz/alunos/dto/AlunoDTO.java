package com.talentos_banco.talentos_proz.alunos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AlunoDTO {

    private Long id;

    @NotBlank(message = "Nome não pode esta em branco!")
    private String nome;

    private String sobrenome;

    private int idade;

    private String descricao;

    private int telefone;

    private String email;

    private String github;

    private String dataFormatura;

    private Long cursoId;
}
