package superMercado.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import superMercado.api.entities.Lote;
import superMercado.api.services.Lote.LoteService;

@RestController
//Dar permiso a los clientes
@CrossOrigin(origins = "*")
@RequestMapping(path = "/lote")
public class LoteController {
    @Autowired
    private LoteService loteService;

    ///  ENDPOINT TRAER TODOS LOS Lotes
    @GetMapping("/getLotes")
    public ResponseEntity<?> getAllLote() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(loteService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }

    }

    // ENDPOINTS TRAER Lotes POR ID-SELECT
//Path variable, variable que estara dentro del endpoint
    @GetMapping("/{id}")
    public ResponseEntity<?> getLoteById(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(loteService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }

    //END POINT CREAR Lote-CREATE
    //post mapping, request de tipo post

    @PostMapping("/crearLote")
    //Request body, el Lote que se crea se hace a travez del body
    public ResponseEntity<?> crearLote(@RequestBody Lote lote) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(loteService.create(lote));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }

    //End point update-insert into
@PutMapping("/{id}/actualizarLote")
    public ResponseEntity<?> actualizarLote(@PathVariable int id,  @RequestBody Lote lote) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(loteService.update(id, lote));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }

    //End point para delete
    @DeleteMapping("/{id}/eliminarLote")
    public ResponseEntity<?> eliminarLote(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(loteService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }


}
