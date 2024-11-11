package superMercado.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import superMercado.api.entities.Administrador;

import java.util.Optional;

@Repository
public interface AdministradorRepository extends PersonaRepository<Administrador,Integer> {

}
