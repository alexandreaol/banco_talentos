package com.talentos_banco.talentos_proz.util;

import com.talentos_banco.talentos_proz.errors.NaoEncontrado;
import org.springframework.data.jpa.repository.JpaRepository;

public class Verificacao {

    public static final String[] message = {"Aluno não encontrado", "curso não encontrado"};

    public static <T, ID> T verificaID(JpaRepository<T, ID> repository, ID id, int indice) {
        return repository.findById(id).orElseThrow(() -> new NaoEncontrado(message[indice]));
    }
}
