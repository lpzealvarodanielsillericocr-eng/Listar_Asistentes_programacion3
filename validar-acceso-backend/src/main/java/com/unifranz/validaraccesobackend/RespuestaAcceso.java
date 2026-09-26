package com.unifranz.validaraccesobackend;

public record RespuestaAcceso(
        boolean permitido,
        String mensaje,
        int edad,
        boolean pago
) {
}