package com.socrates.quadra_booking_api.DTO;

public record AuthResponseDTO(
        String token,
        String tipo // "Bearer"
) {
    public AuthResponseDTO(String token) {
        this(token, "Bearer");
    }
}