package superMercado.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import superMercado.api.entities.Administrador;
import superMercado.api.entities.Sede;

public interface SedeRepository extends JpaRepository<Sede, Integer> {
}
