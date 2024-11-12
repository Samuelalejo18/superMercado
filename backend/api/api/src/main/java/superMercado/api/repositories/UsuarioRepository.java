package superMercado.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import superMercado.api.entities.Sede;
import superMercado.api.entities.Usuario;

import java.util.List;
import java.util.Map;

public interface UsuarioRepository extends PersonaRepository<Usuario, Integer> {
    //Primer reporte

    /// Determinar un listado de los clientes con el código de la orden, el nombre de cada producto comprado,
    //su fecha de compra, la cantidad, el valor unitario y el valor total
   /*
    SELECT nombre AS NOMBRE_USUARIO,
    producto_id AS ID_PRODUCTO,
    nombre_producto AS NOMBRE_PRODUCTO,
     fecha_factura AS FECHA_FACTURA,
     cantidad_productos AS CANTIDAD_PRODUCTO,
     precio_producto AS PRECIO_PRODUCTO,
     total_factura AS TOTAL
    FROM usuario u
    INNER JOIN factura f
    ON u.id_usuario = f.id_usuario
    INNER JOIN factura_producto fp
    ON f.id_factura = fp.factura_id
    INNER JOIN producto p
    ON fp.producto_id = p.id_producto;
     */
    @Query(value = "SELECT  u.nombre as nombre, " +
            "p.id_producto AS ID_PRODUCTO, " +
            "p.nombre_producto AS NOMBRE_PRODUCTO, " +
            "f.fecha_factura AS FECHA_FACTURA, " +
            "fp.cantidad_productos AS CANTIDAD_PRODUCTO, " +
            "p.precio_producto AS PRECIO_PRODUCTO, " +
            "f.total_factura AS TOTAL " +
            "FROM railway.usuario u " +
            "INNER JOIN factura f ON u.id_usuario = f.id_usuario " +
            "INNER JOIN factura_producto fp ON f.id_factura = fp.factura_id " +
            "INNER JOIN producto p ON fp.producto_id = p.id_producto",
            nativeQuery = true)
    List<Map<String, Object>> obtenerListadoDeFacturasConProductos();

}
