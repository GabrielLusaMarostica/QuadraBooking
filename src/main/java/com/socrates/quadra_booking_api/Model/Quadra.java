package com.socrates.quadra_booking_api.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quadras")
@Getter
@Setter
@NoArgsConstructor
public class Quadra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome da quadra é obrigatório")
    private String nome;

    @NotNull(message = "Tipo de esporte é obrigatório")
    @Enumerated(EnumType.STRING)
    private TipoEsporte tipoEsporte;

    @NotBlank(message = "Endereço é obrigatório")
    private String endereco;

    @NotNull(message = "O valor da hora é obrigatório")
    @Positive(message = "O valor da hora deve ser um valor positivo")
    private Double valorHora;

    private boolean ativa = true;

    @OneToMany(mappedBy = "quadra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas = new ArrayList<>();

    public enum TipoEsporte{
        FUTEBOL_SOCIETY, FUTSAL, VOLEI, BASQUETE, TENIS, BEACH_TENNIS
    }

}
