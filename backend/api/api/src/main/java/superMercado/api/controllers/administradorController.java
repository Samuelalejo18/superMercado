package superMercado.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import superMercado.api.entities.Administrador;
import superMercado.api.services.AdministradorService;

@RestController
//Dar permiso a los clientes
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/administrador")
public class administradorController {
    @Autowired
    private AdministradorService administradorService;

    ///  ENDPOINT TRAER TODOS LOS ADMINISTRADORES
    @GetMapping("/getAdministradores")
    public ResponseEntity<?> getAllAdministrador() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(administradorService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }

    }

    // ENDPOINTS TRAER ADMINISTRADRES POR ID-SELECT
//Path variable, variable que estara dentro del endpoint
    @GetMapping("/{id}")
    public ResponseEntity<?> getAdministradorById(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(administradorService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }

    //END POINT CREAR ADMINISTRADOR-CREATE
    //post mapping, request de tipo post

    @PostMapping("/crearAdministrador")
    //Request body, el administrador que se crea se hace a travez del body
    public ResponseEntity<?> crearAdministrador(@RequestBody Administrador administrador) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(administradorService.create(administrador));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }

    //End point update-insert into
@PutMapping("/{id}/actualizarAdministrador")
    public ResponseEntity<?> actualizarAdministrador(@PathVariable int id,  @RequestBody Administrador administrador) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(administradorService.update(id, administrador));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }

    //End point para delete
    @DeleteMapping("/{id}/eliminarAdministrador")
    public ResponseEntity<?> eliminarAdministrador(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(administradorService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }


}
