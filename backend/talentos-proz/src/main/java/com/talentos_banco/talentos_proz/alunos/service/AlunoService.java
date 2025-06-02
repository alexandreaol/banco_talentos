package com.talentos_banco.talentos_proz.alunos.service;

import com.talentos_banco.talentos_proz.alunos.dto.AlunoDTO;
import com.talentos_banco.talentos_proz.alunos.mapper.AlunoMapper;
import com.talentos_banco.talentos_proz.alunos.model.AlunoModel;
import com.talentos_banco.talentos_proz.alunos.repository.AlunoRepository;
import com.talentos_banco.talentos_proz.cursos.model.CursoModel;
import com.talentos_banco.talentos_proz.cursos.repository.CursoRepository;
import com.talentos_banco.talentos_proz.errors.NaoEncontrado;
import com.talentos_banco.talentos_proz.util.Verificacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    public List<AlunoDTO> verAlunos() {
        List<AlunoModel> alunoModel = alunoRepository.findAll();
        return alunoModel.stream().map(AlunoMapper::toDTO).collect(Collectors.toList());
    }

    public void adicionarAluno(AlunoDTO alunoDTO) {
        AlunoModel alunoModel = AlunoMapper.toEntity(alunoDTO);
        CursoModel cursoModel = Verificacao.verificaID(cursoRepository, alunoDTO.getCursoId(), 1);
        alunoModel.setCurso(cursoModel);
        alunoRepository.save(alunoModel);
    }

    public AlunoDTO verAlunoId(Long id) {
        AlunoModel alunoModel = Verificacao.verificaID(alunoRepository, id, 0);
        return  AlunoMapper.toDTO(alunoModel);
    }

    public void atualizarAluno(Long id, AlunoDTO alunoDTO) {
        AlunoModel alunoModel = Verificacao.verificaID(alunoRepository, id, 0);

        CursoModel cursoModel = Verificacao.verificaID(cursoRepository, id, 1);
        alunoModel.setCurso(cursoModel);

        AlunoMapper.updateEntity(alunoModel, alunoDTO);
        alunoRepository.save(alunoModel);
    }

    public void deletarAluno(Long id) {
        AlunoModel alunoModel = Verificacao.verificaID(alunoRepository, id, 0);
        alunoRepository.delete(alunoModel);
    }

    // FILTROS

    public AlunoDTO buscaUnicoAluno(String nome) {
        AlunoModel alunoModel = alunoRepository.findBynome(nome)
                .orElseThrow(() -> new NaoEncontrado(Verificacao.message[0]));
        return AlunoMapper.toDTO(alunoModel);
    }

    public List<AlunoDTO> filterAluno(String nome, String dataFormatura) {
        List<AlunoModel> alunoModels = alunoRepository.findAll();

        return alunoModels.stream()
                .filter(a -> nome == null || a.getNome().equalsIgnoreCase(nome))
                .filter(a -> dataFormatura == null || a.getDataFormatura().contains(dataFormatura))
                .map(AlunoMapper::toDTO).collect(Collectors.toList());
    }
}
