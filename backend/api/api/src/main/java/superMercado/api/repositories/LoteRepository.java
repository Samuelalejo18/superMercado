package superMercado.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import superMercado.api.entities.Lote;
import superMercado.api.entities.Producto;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Integer> {

}
