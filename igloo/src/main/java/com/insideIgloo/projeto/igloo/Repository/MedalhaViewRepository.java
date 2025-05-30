package com.insideIgloo.projeto.igloo.Repository;

import com.insideIgloo.projeto.igloo.Entity.MedalhaUsuarioEntity;
import com.insideIgloo.projeto.igloo.Entity.MedalhaView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedalhaViewRepository extends JpaRepository<MedalhaView, Long> {
    Optional<MedalhaView> findByIdUsuarioAndIdMedalha(Long idUsuario, Long idMedalha);

    List<MedalhaView> findByIdUsuario(Long idUsuario);

}
