package com.talentos_banco.talentos_proz.alunos.service;

import com.talentos_banco.talentos_proz.alunos.dto.AlunoDTO;
import com.talentos_banco.talentos_proz.alunos.mapper.AlunoMapper;
import com.talentos_banco.talentos_proz.alunos.model.AlunoModel;
import com.talentos_banco.talentos_proz.alunos.repository.AlunoRepository;
import com.talentos_banco.talentos_proz.errors.NaoEncontrado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoMapper alunoMapper;
    private final AlunoRepository alunoRepository;


    private AlunoModel verificaAluno(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontrado("Aluno não encontrado"));
    }

    public List<AlunoDTO> verAlunos() {
        List<AlunoModel> alunoModel = alunoRepository.findAll();
        return alunoModel.stream().map(alunoMapper::toDTO).collect(Collectors.toList());
    }

    public AlunoModel adicionarAluno(AlunoDTO alunoDTO) {
        AlunoModel alunoModel = alunoMapper.toEntity(alunoDTO);
        return alunoRepository.save(alunoModel);
    }

    public AlunoDTO verAlunoId(Long id) {
        AlunoModel alunoModel = verificaAluno(id);
        return  alunoMapper.toDTO(alunoModel);
    }

    public void atualizarAluno(Long id, AlunoDTO alunoDTO) {
        AlunoModel alunoModel = verificaAluno(id);
        alunoMapper.updateEntity(alunoModel, alunoDTO);
        alunoRepository.save(alunoModel);
    }

    public void deletarAluno(Long id) {
        AlunoModel alunoModel = verificaAluno(id);

        alunoRepository.delete(alunoModel);
    }
}
