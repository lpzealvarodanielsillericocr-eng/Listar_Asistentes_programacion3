package com.unifranz.validaraccesobackend;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ValidarAccesoService {

    public RespuestaAcceso validarAcceso(SolicitudAcceso solicitud) {
        if (solicitud.edad() == null || solicitud.edad() < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La edad es obligatoria y no puede ser negativa."
            );
        }

        if (solicitud.pago() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Debes indicar si realizó el pago."
            );
        }

        int edad = solicitud.edad();
        boolean pago = solicitud.pago();
        boolean permitido = edad >= 18 && pago;

        String mensaje;

        if (permitido) {
            mensaje = "Acceso permitido. Bienvenido.";
        } else if (edad < 18 && !pago) {
            mensaje = "Acceso denegado: es menor de edad y no realizó el pago.";
        } else if (edad < 18) {
            mensaje = "Acceso denegado: es menor de edad.";
        } else {
            mensaje = "Acceso denegado: no realizó el pago.";
        }

        return new RespuestaAcceso(permitido, mensaje, edad, pago);
    }
}