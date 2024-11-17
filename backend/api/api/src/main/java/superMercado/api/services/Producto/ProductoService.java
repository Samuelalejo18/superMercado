package superMercado.api.services.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import superMercado.api.entities.Producto;
import superMercado.api.repositories.ProductoRepository;

import superMercado.api.services.Producto.BaseServiceProducto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductoService implements BaseServiceProducto {
    @Autowired
    private ProductoRepository productoRepository;

    //Listar los productos que no se han vendido
    /*
SELECT p.id_producto, p.nombre_producto, p.precio_producto
FROM producto p
LEFT JOIN factura_producto fp ON p.id_producto = fp.producto_id
WHERE fp.factura_id IS NULL;

    * */
    public List<Map<String, Object>> obtenerProductosNoVendidos() {
        return productoRepository.findProductosNoVendidos();
    }

    @Override
// GET-SELECT
    public List<Producto> findAll() throws Exception {

        try {
            //SELECT * FROM railway.Producto
            return productoRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }


    }

    //CREATE-INSERT INTO
    @Override
    public Producto create(Producto producto) throws Exception {
        try {
            //INSERT INTO `railway`.`Producto` (`nombre_Producto`, `contacto_Producto`, `numero_documento_admin`, `correo_Producto`, `password_Producto`)
            //VALUES ('?', '?', '?', '?', '?');
            return productoRepository.save(producto);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //GET-SELECT
    @Override
    public Producto findById(int id) throws Exception {
        try {
            //SELECT * FROM railway.Producto where id_Producto= "?"
            Producto producto = productoRepository.findById(id).orElse(null);
            return producto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    //UPDATE
    @Override
    public Producto update(int id, Producto entity) throws Exception {
        //PDATE `railway`.`Producto` SET `nombre_Producto` = '?', `contacto_Producto` = '?', `numero_documento_admin` = '?', `correo_Producto` = '?', `password_Producto` = '?'
        // WHERE (`id_Producto` = '?');
        try {
            Optional<Producto> entityOpcional = productoRepository.findById(id);
            Producto producto = entityOpcional.get();
            producto = productoRepository.save(entity);
            return producto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    //delete
    @Override
    public boolean delete(int id) throws Exception {

///DELETE  FROM railway.Producto where id_Producto = ?
        try {
            if (productoRepository.existsById(id)) {
                productoRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return false;
    }
}
