package superMercado.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import superMercado.api.Auth.LoginRequest;
import superMercado.api.Auth.RegisterRequest;
import superMercado.api.entities.Usuario;
import superMercado.api.excepciones.*;
import superMercado.api.services.PersonaService.UsuarioService;

import java.util.List;
import java.util.Map;

@RestController
//Dar permiso a los clientes
@CrossOrigin(origins = "*")
@RequestMapping(path = "/user")
public class UsuarioController {
    //Usuarios
    @Autowired
    private UsuarioService usuarioService;

    /// path primer reporte

    //Primer reporte
    /// Determinar un listado de los clientes con el código de la orden, el nombre de cada producto comprado,
    //su fecha de compra, la cantidad, el valor unitario y el valor total


    /*
    SELECT nombre AS NOMBRE_USUARIO, producto_id AS ID_PRODUCTO, nombre_producto AS NOMBRE_PRODUCTO, fecha_factura AS FECHA_FACTURA, cantidad_productos AS CANTIDAD_PRODUCTO, precio_producto AS PRECIO_PRODUCTO, total_factura AS TOTAL
    FROM usuario u
    INNER JOIN factura f
    ON u.id_usuario = f.id_usuario
    INNER JOIN factura_producto fp
    ON f.id_factura = fp.factura_id
    INNER JOIN producto p
    ON fp.producto_id = p.id_producto;
     */
    @GetMapping("/primerReporte/facturas-productos")
    public List<Map<String, Object>> obtenerListadoDeFacturasConProductos() {
        return usuarioService.obtenerListadoDeFacturasConProductos();
    }


    ///  ENDPOINT TRAER TODOS LOS usuarios
    @GetMapping("/getUsuarios")
    public ResponseEntity<?> getAllUsuario() {
        try {

            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }

    }

    // ENDPOINTS TRAER usuarios POR ID-SELECT
//Path variable, variable que estara dentro del endpoint
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }

    //END POINT CREAR usuario-CREATE
    //post mapping, request de tipo post

    @PostMapping("/crearUsuario")
    //Request body, el usuario que se crea se hace a travez del body
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.create(usuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }

    //End point update-insert into
    @PutMapping("/{id}/actualizarUsuario")
    public ResponseEntity<?> actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.update(id, usuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }

    //End point para delete
    @DeleteMapping("/{id}/eliminarUsuario")
    public ResponseEntity<?> eliminarUsuario(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(usuarioService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }
}
