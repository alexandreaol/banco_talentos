package com.talentos_banco.talentos_proz.alunos.controller;

import com.talentos_banco.talentos_proz.alunos.dto.AlunoDTO;
import com.talentos_banco.talentos_proz.alunos.model.AlunoModel;
import com.talentos_banco.talentos_proz.alunos.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> verAluno() {
        List<AlunoDTO> alunos = alunoService.verAlunos();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> verPorId(@PathVariable Long id) {
        AlunoDTO alunoDTO = alunoService.verAlunoId(id);
        return ResponseEntity.ok(alunoDTO);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> adicionarAluno(@RequestBody AlunoDTO alunoDTO) {
        AlunoModel alunoModel = alunoService.adicionarAluno(alunoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Aluno adicionado!"));
    }


}
