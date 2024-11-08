package superMercado.api.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import superMercado.api.entities.Administrador;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseAdmin {
 String token;
 Administrador administrador;

}
