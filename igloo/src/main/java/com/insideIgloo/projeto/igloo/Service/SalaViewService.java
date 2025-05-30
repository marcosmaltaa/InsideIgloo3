package com.insideIgloo.projeto.igloo.Service;

import com.insideIgloo.projeto.igloo.DTO.ListaSalaDTO;
import com.insideIgloo.projeto.igloo.DTO.LoginRankXPDTO;
import com.insideIgloo.projeto.igloo.DTO.LoginXpDTO;
import com.insideIgloo.projeto.igloo.Entity.*;
import com.insideIgloo.projeto.igloo.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.insideIgloo.projeto.igloo.Specification.SalaUsuarioParteViewSpecs.hasSalaId;
import static com.insideIgloo.projeto.igloo.Specification.SalaUsuarioParteViewSpecs.hasUsuarioId;

@Service
public class SalaViewService {

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    SalaUsuarioParteViewRepository salaUsuarioParteViewRepository;

    @Autowired
    LoginXpViewRepository loginXpViewRepository;

    @Autowired
    SalaConcluidaRepository salaConcluidaRepository;

    @Autowired
    SalaConcluidaViewRepository salaConcluidaViewRepository;

    @Transactional
    public List<ListaSalaDTO> retornaInfoSala(Long idSala, Long idUsuario) {
        List<SalaUsuarioParteView> sala = salaUsuarioParteViewRepository.findAll(
                Specification.where(hasUsuarioId(idUsuario)).and(hasSalaId(idSala))
        );
        return sala.stream()
                .map(ListaSalaDTO::fromEntity)
                .toList();

    }

    @Transactional
    public List<SalaConcluidaView> retornaSalaConcluida(Long idUsuario) {
        return salaConcluidaViewRepository.findByIdAluno(idUsuario);
    }

    @Transactional
    public LoginRankXPDTO posicaoRanking(Long userId){
        Long posicao = loginXpViewRepository.findRankByUsuarioId(userId);

        // Busca os dados do usuário
        LoginXpView login = loginXpViewRepository.findByIdUsuario(userId)
                .orElseThrow(() -> new RuntimeException("Usuario nao enocntrado"));

        // Monta e retorna o DTO
        return new LoginRankXPDTO(posicao, login.getApelido(), login.getExp());

    }

    @Transactional
    public List<LoginXpDTO> topDez(){
    List<LoginXpView> top10entity =  loginXpViewRepository.findTop10ByOrderByExpDesc();

        return top10entity.stream()
                .map(LoginXpDTO::fromEntity)
                .toList();
    }
}
