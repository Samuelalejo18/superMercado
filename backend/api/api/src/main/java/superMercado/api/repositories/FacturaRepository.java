package superMercado.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import superMercado.api.entities.Administrador;
import superMercado.api.entities.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {

}
