package com.talentos_banco.talentos_proz.cursos.service;

import com.talentos_banco.talentos_proz.cursos.dto.CursoDTO;
import com.talentos_banco.talentos_proz.cursos.mapper.CursoMapper;
import com.talentos_banco.talentos_proz.cursos.model.CursoModel;
import com.talentos_banco.talentos_proz.cursos.repository.CursoRepository;
import com.talentos_banco.talentos_proz.util.Verificacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

    public List<CursoDTO> verCursos(){
        List<CursoModel> cursoModel = cursoRepository.findAll();
        
        return cursoModel.stream().map(CursoMapper::toDTO).collect(Collectors.toList());
    }

    public CursoDTO verCursoId(Long id) {
        CursoModel cursoModel = Verificacao.verificaID(cursoRepository, id, 1);
        return CursoMapper.toDTO(cursoModel);
    }

    public void adicionarCurso(CursoDTO cursoDTO) {
        CursoModel cursoModel = CursoMapper.toEntity(cursoDTO);
        cursoRepository.save(cursoModel);
    }

    public void atualizarCurso(Long id, CursoDTO cursoDTO) {
        CursoModel cursoModel = Verificacao.verificaID(cursoRepository, id, 1);
        CursoMapper.updateEntity(cursoModel, cursoDTO);
        cursoRepository.save(cursoModel);
    }

    public void deletarAluno(Long id) {
        CursoModel cursoModel = Verificacao.verificaID(cursoRepository, id, 1);
        cursoRepository.delete(cursoModel);
    }
}
