package com.insideIgloo.projeto.igloo.Service;

import com.insideIgloo.projeto.igloo.Entity.LoginEntity;
import com.insideIgloo.projeto.igloo.Entity.MedalhaEntity;
import com.insideIgloo.projeto.igloo.Entity.MedalhaUsuarioEntity;
import com.insideIgloo.projeto.igloo.Entity.MedalhaView;
import com.insideIgloo.projeto.igloo.Repository.LoginRepository;
import com.insideIgloo.projeto.igloo.Repository.MedalhaRepository;
import com.insideIgloo.projeto.igloo.Repository.MedalhaUsuarioRepository;
import com.insideIgloo.projeto.igloo.Repository.MedalhaViewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedalhaService {

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    MedalhaRepository medalhaRepository;

    @Autowired
    MedalhaUsuarioRepository medalhaUsuarioRepository;

    @Autowired
    MedalhaViewRepository medalhaViewRepository;

    @Transactional
    public MedalhaView gerarMedalha(Long idUsuario, Long idMedalha) {
        LoginEntity login = loginRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        MedalhaEntity medalha = medalhaRepository.findById(idMedalha)
                .orElseThrow(() -> new RuntimeException("Medalha não encontrada"));

        boolean jaExiste = medalhaUsuarioRepository.findByIdUsuarioAndIdMedalha(login, medalha).isPresent();
        if (jaExiste) {
            throw new RuntimeException("Essa medalha já foi atribuída a este usuário.");
        }

        MedalhaUsuarioEntity nova = new MedalhaUsuarioEntity();
        nova.setIdUsuario(login);
        nova.setIdMedalha(medalha);
        medalhaUsuarioRepository.saveAndFlush(nova);

        return medalhaViewRepository.findByIdUsuarioAndIdMedalha(idUsuario, idMedalha)
                .orElseThrow(() -> new RuntimeException("Erro ao buscar a medalha vinculada ao usuário."));
    }

    @Transactional
    public List<MedalhaView> pegarMedalhasUsuario(Long idUsuario){
        return medalhaViewRepository.findByIdUsuario(idUsuario);
    }

}
