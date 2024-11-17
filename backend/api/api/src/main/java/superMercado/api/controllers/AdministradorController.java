package superMercado.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import superMercado.api.Auth.LoginRequest;
import superMercado.api.Auth.RegisterRequest;
import superMercado.api.entities.Administrador;
import superMercado.api.excepciones.*;
import superMercado.api.services.PersonaService.AdministradorService;

import java.util.List;
import java.util.Map;

@RestController
//Dar permiso a los clientes
@CrossOrigin(origins = "*")
@RequestMapping(path = "/administrador")
@RequiredArgsConstructor
public class AdministradorController {
    @Autowired
    private final AdministradorService administradorService;




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
    public ResponseEntity<?> actualizarAdministrador(@PathVariable int id, @RequestBody Administrador administrador) {
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
