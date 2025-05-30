package com.insideIgloo.projeto.igloo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

@Table(name="VW_USUARIO_MEDALHAS")
@Getter
@Setter
@NoArgsConstructor
@Immutable
@Entity
public class MedalhaView {
    @Id
    @Column(name = "ID_USUARIO")
    private Long idUsuario;

    @Column(name = "ID_MEDALHA")
    private Long idMedalha;

    @Column(name = "NOME_MEDALHA")
    private String nomeMedalha;

    @Column(name = "DESCRICAO_MEDALHA")
    private String descricaoMedalha;
}
