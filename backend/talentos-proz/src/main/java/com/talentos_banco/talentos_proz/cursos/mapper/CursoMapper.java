package com.talentos_banco.talentos_proz.cursos.mapper;

import com.talentos_banco.talentos_proz.alunos.dto.AlunoDTO;
import com.talentos_banco.talentos_proz.alunos.mapper.AlunoMapper;
import com.talentos_banco.talentos_proz.alunos.model.AlunoModel;
import com.talentos_banco.talentos_proz.cursos.dto.CursoDTO;
import com.talentos_banco.talentos_proz.cursos.model.CursoModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CursoMapper {

    public static CursoDTO toDTO(CursoModel cursoModel){
        CursoDTO cursoDTO = new CursoDTO();

        cursoDTO.setIdCurso(cursoModel.getIdCurso());
        cursoDTO.setNome(cursoModel.getNome());
        cursoDTO.setCodigo(cursoModel.getCodigo());

        List<AlunoModel> alunoModels = cursoModel.getAlunos();
        if(alunoModels != null) {
            List<AlunoDTO> alunoDTOS = alunoModels.stream().map(AlunoMapper::toDTO).collect(Collectors.toList());
            cursoDTO.setAlunos(alunoDTOS);
        }

        return cursoDTO;
    }

    public static CursoModel toEntity(CursoDTO cursoDTO){
        CursoModel cursoModel = new CursoModel();

        cursoModel.setNome(cursoDTO.getNome());
        cursoModel.setCodigo(cursoDTO.getCodigo());

        return cursoModel;
    }

    public static void updateEntity(CursoModel cursoModel, CursoDTO cursoDTO) {
        cursoModel.setNome(cursoDTO.getNome());
        cursoModel.setCodigo(cursoDTO.getCodigo());
    }
}
