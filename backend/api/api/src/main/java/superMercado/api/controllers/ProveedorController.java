package superMercado.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import superMercado.api.entities.Proveedor;
import superMercado.api.services.Proveedor.ProveedorService;

@RestController
//Dar permiso a los clientes
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/proveedor")
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    ///  ENDPOINT TRAER TODOS LOS ProveedorES
    @GetMapping("/getProveedores")
    public ResponseEntity<?> getAllProveedor() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(proveedorService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }

    }

    // ENDPOINTS TRAER ADMINISTRADRES POR ID-SELECT
//Path variable, variable que estara dentro del endpoint
    @GetMapping("/{id}")
    public ResponseEntity<?> getProveedorById(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(proveedorService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }

    //END POINT CREAR Proveedor-CREATE
    //post mapping, request de tipo post

    @PostMapping("/crearProveedor")
    //Request body, el Proveedor que se crea se hace a travez del body
    public ResponseEntity<?> crearProveedor(@RequestBody Proveedor proveedor) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(proveedorService.create(proveedor));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }

    //End point update-insert into
@PutMapping("/{id}/actualizarProveedor")
    public ResponseEntity<?> actualizarProveedor(@PathVariable int id,  @RequestBody Proveedor proveedor) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(proveedorService.update(id, proveedor));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }

    //End point para delete
    @DeleteMapping("/{id}/eliminarProveedor")
    public ResponseEntity<?> eliminarProveedor(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(proveedorService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }


}
