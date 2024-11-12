package superMercado.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import superMercado.api.entities.Producto;
import superMercado.api.services.Producto.ProductoService;

@RestController
//Dar permiso a los clientes
@CrossOrigin(origins = "*")
@RequestMapping(path = "/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

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
