package com.talentos_banco.talentos_proz.cursos.service;

import java.util.List;
import java.util.stream.Collectors;

import com.talentos_banco.talentos_proz.cursos.dto.CursoDTO;
import org.springframework.stereotype.Service;

import com.talentos_banco.talentos_proz.cursos.repository.CursoRepository;
import com.talentos_banco.talentos_proz.cursos.mapper.CursoMapper;
import com.talentos_banco.talentos_proz.cursos.model.CursoModel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoService {
    private final CursoMapper cursoMapper;
    private final CursoRepository cursoRepository;
    
    public List<CursoDTO> verCursos(){
        List<CursoModel> cursoModel = cursoRepository.findAll();
        
        return cursoModel.stream().map(cursoMapper::toDTO).collect(Collectors.toList());
    }
}
