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
    private String username;
    private String nombre_administrador;
    private String contacto_administrador;
    private String numero_documento_admin;
    private String email;
    private String password;
}
