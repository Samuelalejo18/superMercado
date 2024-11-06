package superMercado.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import superMercado.api.entities.Sede;
import superMercado.api.services.Sede.SedeService;


@RestController
//Dar permiso a los clientes
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/sede")
public class SedeController {
    @Autowired
    private SedeService sedeService;

    ///  ENDPOINT TRAER TODOS Las sedes
    @GetMapping("/getSedes")
    public ResponseEntity<?> getAllSede() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(sedeService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }

    }

    // ENDPOINTS TRAER sedes POR ID-SELECT
//Path variable, variable que estara dentro del endpoint
    @GetMapping("/{id}")
    public ResponseEntity<?> getSedeById(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(sedeService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }

    //END POINT CREAR sede-CREATE
    //post mapping, request de tipo post

    @PostMapping("/crearSede")
    //Request body, el sede que se crea se hace a travez del body
    public ResponseEntity<?> crearSede(@RequestBody Sede sede) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(sedeService.create(sede));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }

    //End point update-insert into
    @PutMapping("/{id}/actualizarSede")
    public ResponseEntity<?> actualizarSede(@PathVariable int id,  @RequestBody Sede sede) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(sedeService.update(id, sede));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }

    //End point para delete
    @DeleteMapping("/{id}/eliminarSede")
    public ResponseEntity<?> eliminarSede(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(sedeService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }


}
