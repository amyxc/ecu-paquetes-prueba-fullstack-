package prueba_fullstack.prueba.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
//para enviar al frontend el token y los datos limpios en el body
public class AuthResponseDto {
    private String token;
    private String email;
    private String role;
}