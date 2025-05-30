package com.insideIgloo.projeto.igloo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

@Table(name="VW_SALA_CONCLUIDA")
@Getter
@Setter
@NoArgsConstructor
@Immutable
@Entity
public class SalaConcluidaView {
    @Id
    @Column(name = "id_virtual")
    Long id;

    @Column(name = "id_conclusao")
    Long idConclusao;

    @Column(name = "id_aluno")
    Long idAluno;

    @Column(name = "nome_aluno")
    String nomeAluno;

    @Column(name = "id_sala")
    Long idSala;

    @Column(name = "nome_sala")
    String nomeSala;

    @Column(name = "concluido")
    Boolean concluido;
}
