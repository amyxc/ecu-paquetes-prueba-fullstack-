package prueba_fullstack.prueba.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import prueba_fullstack.prueba.domain.dto.PaqueteDto;
import prueba_fullstack.prueba.persistence.entity.PaqueteEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaqueteMapper {

    PaqueteDto toDto(PaqueteEntity entity);

    @Mapping(target = "ruta", ignore = true)
    @InheritInverseConfiguration
    PaqueteEntity toEntity(PaqueteDto dto);

    List<PaqueteDto> toDtoList(List<PaqueteEntity> entities);
}