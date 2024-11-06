package superMercado.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import superMercado.api.entities.Factura;
import superMercado.api.services.Factura.FacturaService;

@RestController
//Dar permiso a los clientes
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/factura")
public class FacturaController {
    @Autowired
    private FacturaService facturaService;

    ///  ENDPOINT TRAER TODaS LaS facturas
    @GetMapping("/getFacturas")
    public ResponseEntity<?> getAllFactura() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(facturaService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }

    }

    // ENDPOINTS TRAER fACTURAS POR ID-SELECT
//Path variable, variable que estara dentro del endpoint
    @GetMapping("/{id}")
    public ResponseEntity<?> getFacturaById(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(facturaService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }

    //END POINT CREAR factura-CREATE
    //post mapping, request de tipo post

    @PostMapping("/crearFactura")
    //Request body, el factura que se crea se hace a travez del body
    public ResponseEntity<?> crearfactura(@RequestBody Factura factura) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(facturaService.create(factura));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }

    //End point update-insert into
@PutMapping("/{id}/actualizarFactura")
    public ResponseEntity<?> actualizarFactura(@PathVariable int id,  @RequestBody Factura factura) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(facturaService.update(id, factura));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }

    //End point para delete
    @DeleteMapping("/{id}/eliminarFactura")
    public ResponseEntity<?> eliminarFactura(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(facturaService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error.Porfavor Intente mas tarde.\"}");
        }
    }


}
