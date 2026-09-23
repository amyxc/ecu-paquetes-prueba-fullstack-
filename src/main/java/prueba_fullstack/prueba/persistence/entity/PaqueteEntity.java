package prueba_fullstack.prueba.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "paquetes")
@Entity
public class PaqueteEntity {
    //(id, peso, destino, emisor, receptor, tipoCarga, estado, rutaId, fechaRegistro)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPaquete;

    //nullable en false no permite valores nulos
    @Column(nullable = false, columnDefinition = "Decimal(5,2)")
    private Double peso;

    @Column(nullable = false, length = 200)
    private String destino;

    @Column(nullable = false, length = 200)
    private String emisor;

    @Column(nullable = false, length = 200)
    private String receptor;

    @Column(nullable = false, length = 200)
    private String tipoCarga;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPaquete estado;

    //nullable true porque al crearse el paquete todavia no tiene una ruta asignada
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ruta", nullable = true)
    private RutaEntity ruta;

    @Column(nullable = false)
    private LocalDateTime fechaRegistro;
}
