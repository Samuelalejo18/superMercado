package superMercado.api.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestAdmin {
    String username;
    String nombre_administrador;
    String contacto_administrador;
    String numero_documento_admin;
    String email;
    String password;
}
