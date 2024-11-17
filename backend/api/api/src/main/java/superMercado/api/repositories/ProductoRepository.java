package superMercado.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import superMercado.api.entities.Administrador;
import superMercado.api.entities.Producto;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    //Listar los productos que no se han vendido
    /*
SELECT p.id_producto, p.nombre_producto, p.precio_producto
FROM producto p
LEFT JOIN factura_producto fp ON p.id_producto = fp.producto_id
WHERE fp.factura_id IS NULL;

    * */
    @Query(value = "SELECT p.* " +
            "FROM producto p " +
            "LEFT JOIN factura_producto fp ON p.id_producto = fp.producto_id " +
            "WHERE fp.factura_id IS NULL",
            nativeQuery = true)
    List<Map<String, Object>> findProductosNoVendidos();
}
