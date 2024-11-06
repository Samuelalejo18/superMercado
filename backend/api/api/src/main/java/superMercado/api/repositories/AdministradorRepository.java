package superMercado.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import superMercado.api.entities.Administrador;
@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {

}
