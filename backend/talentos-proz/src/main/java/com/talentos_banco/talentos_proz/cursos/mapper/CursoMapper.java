package com.talentos_banco.talentos_proz.cursos.mapper;

import com.talentos_banco.talentos_proz.cursos.dto.CursoDTO;
import com.talentos_banco.talentos_proz.cursos.model.CursoModel;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {
    public CursoDTO toDTO(CursoModel cursoModel){
        CursoDTO cursoDTO = new CursoDTO();

        cursoDTO.setId_curso(cursoModel.getId_curso());
        cursoDTO.setNome(cursoModel.getNome());
        cursoDTO.setCodigo(cursoModel.getCodigo());

        return cursoDTO;
    }

    public CursoModel toEntity(CursoDTO cursoDTO){
        CursoModel cursoModel = new CursoModel();

        cursoModel.setNome(cursoDTO.getNome());
        cursoModel.setCodigo(cursoDTO.getCodigo());

        return cursoModel;
    }
}
