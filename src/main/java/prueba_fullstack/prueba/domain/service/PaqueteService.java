package prueba_fullstack.prueba.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prueba_fullstack.prueba.persistence.repository.PaqueteRepository;

@Service
public class PaqueteService {
    private final PaqueteRepository paqueteRepository;

    @Autowired
    public PaqueteService(PaqueteRepository paqueteRepository) {
        this.paqueteRepository = paqueteRepository;
    }


}
