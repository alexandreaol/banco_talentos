package com.talentos_banco.talentos_proz.cursos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talentos_banco.talentos_proz.cursos.dto.CursoDTO;
import com.talentos_banco.talentos_proz.cursos.service.CursoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/Curso")
@RequiredArgsConstructor
public class CursoController {
    private final CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<CursoDTO>> verCursos(){
        List<CursoDTO> curso = cursoService.verCursos();
        return ResponseEntity.ok(curso);
    }
}