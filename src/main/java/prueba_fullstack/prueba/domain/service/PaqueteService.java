package prueba_fullstack.prueba.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prueba_fullstack.prueba.domain.dto.PaqueteDto;
import prueba_fullstack.prueba.persistence.entity.EstadoPaquete;
import prueba_fullstack.prueba.persistence.entity.PaqueteEntity;
import prueba_fullstack.prueba.persistence.mapper.PaqueteMapper;
import prueba_fullstack.prueba.persistence.repository.PaqueteRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaqueteService {

    private final PaqueteRepository paqueteRepository;
    private final PaqueteMapper paqueteMapper;

    public PaqueteDto save(PaqueteDto dto) {
        PaqueteEntity entity = this.paqueteMapper.toEntity(dto);

        entity.setFechaRegistro(LocalDateTime.now());
        entity.setEstado(EstadoPaquete.REGISTRADO);

        PaqueteEntity guardado = this.paqueteRepository.save(entity);
        return this.paqueteMapper.toDto(guardado);
    }

    public List<PaqueteDto> getAll() {
        return this.paqueteMapper.toDtoList(this.paqueteRepository.findAll());
    }

    public List<PaqueteDto> findByEstado(EstadoPaquete estado) {
        return this.paqueteMapper.toDtoList(this.paqueteRepository.findByEstado(estado));
    }

    public List<PaqueteDto> findByDestino(String destino) {
        return this.paqueteMapper.toDtoList(this.paqueteRepository.findByDestinoContainingIgnoreCase(destino));
    }
}