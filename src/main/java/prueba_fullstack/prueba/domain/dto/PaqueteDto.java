package prueba_fullstack.prueba.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import prueba_fullstack.prueba.persistence.entity.EstadoPaquete;

import java.time.LocalDateTime;

@Data
public class PaqueteDto {
    /*
    Peso mayor a 0.
    Destino, emisor y receptor obligatorios.
    Tipo de carga obligatorio.
     */

    private Integer idPaquete;
    private EstadoPaquete estado;
    private LocalDateTime fechaRegistro;

    @NotNull
    @Positive
    private Double peso;

    @NotBlank
    private String destino;

    @NotBlank
    private String emisor;

    @NotBlank
    private String receptor;

    @NotBlank
    private String tipoCarga;
}
