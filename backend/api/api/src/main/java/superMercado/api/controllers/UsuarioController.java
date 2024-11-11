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

@RestController
//Dar permiso a los clientes
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/user")
public class UsuarioController {
    //Usuarios
    @Autowired
    private UsuarioService usuarioService;


    //endPoint login
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(usuarioService.login(request));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al iniciar sesión.");
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            return ResponseEntity.ok(usuarioService.register(request));
        } catch (UserNameAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (EmailAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (DocumentAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (InvalidEmailFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (PhoneAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (WeakPasswordException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Violación de integridad de datos.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el administrador.");
        }
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
