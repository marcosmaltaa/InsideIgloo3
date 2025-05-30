package com.insideIgloo.projeto.igloo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="TB_MEDALHA_USUARIO")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class MedalhaUsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USUARIO_ID")
    private LoginEntity idUsuario;

    @ManyToOne
    @JoinColumn(name = "MEDALHA_ID")
    private MedalhaEntity idMedalha;
}
