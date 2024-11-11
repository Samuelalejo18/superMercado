package superMercado.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import superMercado.api.entities.Sede;
import superMercado.api.entities.Usuario;

public interface UsuarioRepository  extends PersonaRepository<Usuario,Integer> {
}
