package com.insideIgloo.projeto.igloo.Controller;

import com.insideIgloo.projeto.igloo.Entity.MedalhaView;
import com.insideIgloo.projeto.igloo.Service.MedalhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medalha")
public class MedalhaController {

    @Autowired
    MedalhaService service;

    @PostMapping(value = "/darMedalha", produces = MediaType.APPLICATION_JSON_VALUE)
    public MedalhaView darMedalha(@RequestParam(name="user") Long idUsuario,
                                      @RequestParam(name="medal") Long idSala) {
        return service.gerarMedalha(idUsuario, idSala);
    }

    @GetMapping(value = "/medalhasUsuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MedalhaView> pegarMedalhas(@RequestParam(name="user") Long idUsuario) {
        return service.pegarMedalhasUsuario(idUsuario);
    }
}
