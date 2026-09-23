package prueba_fullstack.prueba.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prueba_fullstack.prueba.persistence.entity.UsuarioEntity;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    //para buscar por email en el login
    Optional<UsuarioEntity> findByEmail(String email);
}
