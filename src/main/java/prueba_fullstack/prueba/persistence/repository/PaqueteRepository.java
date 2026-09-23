package prueba_fullstack.prueba.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prueba_fullstack.prueba.persistence.entity.EstadoPaquete;
import prueba_fullstack.prueba.persistence.entity.PaqueteEntity;

import java.util.List;

public interface PaqueteRepository extends JpaRepository<PaqueteEntity, Integer> {
    //buscar por estado
    List<PaqueteEntity> findByEstado(EstadoPaquete estado);

    //buscar por destino
    List<PaqueteEntity> findByDestinoContainingIgnoreCase(String destino);
}
