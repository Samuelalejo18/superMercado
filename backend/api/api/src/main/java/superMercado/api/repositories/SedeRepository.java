package superMercado.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import superMercado.api.entities.Administrador;
import superMercado.api.entities.Sede;

public interface SedeRepository extends JpaRepository<Sede, Integer> {
    //Primer reporte
    ///Determinar un listado de los clientes con el código de la orden, el nombre de cada producto comprado,
    //su fecha de compra, la cantidad, el valor unitario y el valor total

}
