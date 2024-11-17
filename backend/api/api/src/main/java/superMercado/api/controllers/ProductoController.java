package superMercado.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import superMercado.api.entities.Producto;
import superMercado.api.services.Producto.ProductoService;

import java.util.List;
import java.util.Map;

@RestController
//Dar permiso a los clientes
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    //Listar los productos que no se han vendido
    /*
SELECT p.id_producto, p.nombre_producto, p.precio_producto
FROM producto p
LEFT JOIN factura_producto fp ON p.id_producto = fp.producto_id
WHERE fp.factura_id IS NULL;
*/
    @GetMapping("/segundoReporte/productosNoVendidos")
    public ResponseEntity<List<Map<String, Object>>> productosNoVendidos() {
        try {
            // Llamada al servicio para obtener los datos
            List<Map<String, Object>> reporte = productoService.obtenerProductosNoVendidos();

            // Devuelve los datos con el código HTTP 200 (OK)
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            // Devuelve un error interno del servidor en caso de fallo
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    ///  ENDPOINT TRAER TODOS LOS Productos
    @GetMapping("/getProductos")
    public ResponseEntity<?> getAllProducto() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productoService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }

    }

    // ENDPOINTS TRAER ADMINISTRADRES POR ID-SELECT
//Path variable, variable que estara dentro del endpoint
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductoById(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productoService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }

    //END POINT CREAR Producto-CREATE
    //post mapping, request de tipo post

    @PostMapping("/crearProducto")
    //Request body, el Producto que se crea se hace a travez del body
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productoService.create(producto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }

    //End point update-insert into
@PutMapping("/{id}/actualizarProducto")
    public ResponseEntity<?> actualizarProducto(@PathVariable int id,  @RequestBody Producto producto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productoService.update(id, producto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }

    //End point para delete
    @DeleteMapping("/{id}/eliminarProducto")
    public ResponseEntity<?> eliminarProducto(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(productoService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }


}
