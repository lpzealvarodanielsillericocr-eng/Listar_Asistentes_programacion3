package com.unifranz.validaraccesobackend;

public record Asistente(
        Long id,
        String nombre,
        int edad,
        boolean pago,
        boolean permitido
) {
}