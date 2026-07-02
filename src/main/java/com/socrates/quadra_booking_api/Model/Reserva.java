package com.socrates.quadra_booking_api.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
@Getter
@Setter
@NoArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //LAZY serve para, por exemplo quando o usuario for acessar a reserva ele nao usa mais uma query pra buscar o usuario nem a quadra. Ele só usa quando de fato precisa, com getters
    @NotNull(message = "Usuário é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @NotNull(message = "Quadra é obrigatória")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quadra_id", nullable = false)
    private Quadra quadra;

    @NotNull(message = "Data e hora de inicio são obrigatórias")
    @Future(message = "A reserva deve ser feita para uma data futura")
    private LocalDateTime dataHoraInicio;

    @NotNull(message = "Data e hora de término são obrigatórias")
    private LocalDateTime dataHoraFim;

    @Enumerated(EnumType.STRING)
    private StatusReserva status = StatusReserva.CONFIRMADA;

    public enum StatusReserva{
        CONFIRMADA, CANCELADA, CONCLUIDA
    }

}
