package prueba_fullstack.prueba.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import prueba_fullstack.prueba.persistence.entity.UsuarioEntity;
import prueba_fullstack.prueba.persistence.repository.UsuarioRepository;

@Service
public class UsuarioSeguridadService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioSeguridadService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioEntity usuarioEntity = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email " + email + " no encontrado"));

        return User.builder()
                .username(usuarioEntity.getEmail())
                .password(usuarioEntity.getPassword())
                .roles(usuarioEntity.getRol().name())
                .build();
    }
}
