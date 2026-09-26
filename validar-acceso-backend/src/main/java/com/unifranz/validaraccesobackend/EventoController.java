package com.unifranz.validaraccesobackend;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/evento")
@CrossOrigin(origins = "http://localhost:4200")
public class EventoController {

    private final ValidarAccesoService validarAccesoService;

    public EventoController(ValidarAccesoService validarAccesoService) {
        this.validarAccesoService = validarAccesoService;
    }

    @PostMapping("/validarAcceso")
    public RespuestaAcceso validarAcceso(
            @RequestBody SolicitudAcceso solicitud
    ) {
        return validarAccesoService.validarAcceso(solicitud);
    }

    @GetMapping("/asistentes")
    public List<Asistente> listarAsistentes() {
        return List.of(
                crearAsistente(1L, "Alvaro", 20, true),
                crearAsistente(2L, "Luis", 17, true),
                crearAsistente(3L, "Ana", 22, false),
                crearAsistente(4L, "Maria", 19, true)
        );
    }

    private Asistente crearAsistente(
            Long id, String nombre, int edad, boolean pago
    ) {
        RespuestaAcceso resultado = validarAccesoService.validarAcceso(
                new SolicitudAcceso(edad, pago)
        );

        return new Asistente(
                id, nombre, edad, pago, resultado.permitido()
        );
    }
}