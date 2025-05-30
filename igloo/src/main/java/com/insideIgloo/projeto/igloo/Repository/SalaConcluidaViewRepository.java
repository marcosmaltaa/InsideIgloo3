package com.insideIgloo.projeto.igloo.Repository;

import com.insideIgloo.projeto.igloo.Entity.SalaConcluidaView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaConcluidaViewRepository extends JpaRepository<SalaConcluidaView, Long> {
    List<SalaConcluidaView> findByIdAluno(Long idAluno);
}
