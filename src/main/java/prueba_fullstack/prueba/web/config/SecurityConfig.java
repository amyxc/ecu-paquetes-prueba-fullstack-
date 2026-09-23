package prueba_fullstack.prueba.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //deshabilitar CSRF
                .csrf(csrf -> csrf.disable())
                //conectar la configuración de CORS
                .cors(Customizer.withDefaults())
                //no genera sesiones de estado en el servidor (stateless)
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //reglas de autorización según roles del requerimiento
                .authorizeHttpRequests(auth -> auth
                        // Rutas públicas: Autenticación y documentación
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                        // Gestión de paquetes (rol secretaria)
                        .requestMatchers("/api/paquetes/**").hasAnyRole("SECRETARIA", "ADMIN")

                        // Gestión de rutas y consulta de entregas (rol admin)
                        .requestMatchers("/api/rutas/**").hasRole("ADMIN")
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")

                        // Entregas exclusivas para el repartidor
                        .requestMatchers("/api/entregas/**").hasRole("REPARTIDOR")

                        //cualquier otra solicitud requiere autenticación previa
                        .anyRequest().authenticated()
                )
                //interceptar las solicitudes en el filtro q cree antes del filtro de usuario/contraseña
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}