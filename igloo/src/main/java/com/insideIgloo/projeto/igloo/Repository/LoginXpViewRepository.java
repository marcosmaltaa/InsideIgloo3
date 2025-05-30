package com.insideIgloo.projeto.igloo.Repository;

import com.insideIgloo.projeto.igloo.Entity.LoginXpView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginXpViewRepository extends JpaRepository<LoginXpView, Long> {
    List<LoginXpView> findTop10ByOrderByExpDesc();

    Optional<LoginXpView> findByIdUsuario(Long id);

    @Query(value = """
        SELECT COUNT(*) + 1
        FROM VW_USUARIO_XP v
        WHERE v.experiencia > (
            SELECT experiencia FROM VW_USUARIO_XP WHERE usuario_id = :userId
        )
        """, nativeQuery = true)
    Long findRankByUsuarioId(@Param("userId") Long userId);
}
