package com.insideIgloo.projeto.igloo.Repository;

import com.insideIgloo.projeto.igloo.Entity.LoginEntity;
import com.insideIgloo.projeto.igloo.Entity.MedalhaEntity;
import com.insideIgloo.projeto.igloo.Entity.MedalhaUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedalhaUsuarioRepository extends JpaRepository<MedalhaUsuarioEntity, Long> {
    Optional<MedalhaUsuarioEntity> findByIdUsuarioAndIdMedalha(LoginEntity idUsuario, MedalhaEntity idMedalha);

}
