package superMercado.api.services.Administrador;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import superMercado.api.Auth.AuthResponseAdmin;
import superMercado.api.Auth.LoginRequestAdmin;
import superMercado.api.Auth.RegisterRequestAdmin;
import superMercado.api.entities.Administrador;
import superMercado.api.repositories.AdministradorRepository;
import superMercado.api.services.jwt.JwtService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministradorService implements BaseServiceAdministrador {
    @Autowired
    private final AdministradorRepository administradorRepository;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private final AuthenticationManager authenticationManager;

    @Override
// GET-SELECT
    public List<Administrador> findAll() throws Exception {

        try {
            //SELECT * FROM railway.administrador
            return administradorRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }


    }

    //CREATE-INSERT INTO
    @Override
    public Administrador create(Administrador administrador) throws Exception {
        try {
            //INSERT INTO `railway`.`administrador` (`nombre_administrador`, `contacto_administrador`, `numero_documento_admin`, `correo_administrador`, `password_administrador`)
            //VALUES ('?', '?', '?', '?', '?');
            return administradorRepository.save(administrador);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //GET-SELECT
    @Override
    public Administrador findById(int id) throws Exception {
        try {
            //SELECT * FROM railway.administrador where id_administrador= "?"
            Administrador administrador = administradorRepository.findById(id).orElse(null);
            return administrador;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    //UPDATE
    @Override
    public Administrador update(int id, Administrador entity) throws Exception {
        //PDATE `railway`.`administrador` SET `nombre_administrador` = '?', `contacto_administrador` = '?', `numero_documento_admin` = '?', `correo_administrador` = '?', `password_administrador` = '?'
        // WHERE (`id_administrador` = '?');
        try {
            Optional<Administrador> entityOpcional = administradorRepository.findById(id);
            Administrador administrador = entityOpcional.get();
            administrador = administradorRepository.save(entity);
            return administrador;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    //delete
    @Override
    public boolean delete(int id) throws Exception {

///DELETE  FROM railway.administrador where id_administrador = ?
        try {
            if (administradorRepository.existsById(id)) {
                administradorRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return false;
    }

    public AuthResponseAdmin login(LoginRequestAdmin request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            Administrador administrador = administradorRepository.findByUsername(request.getUsername())
                    .orElseThrow();

            return AuthResponseAdmin.builder()
                    .token(jwtService.getToken(administrador))
                    .build();

        } catch (BadCredentialsException e) {
            throw new RuntimeException("Credenciales inválidas: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error en autenticación: " + e.getMessage());
        }
    }

    @Override
    public AuthResponseAdmin register(RegisterRequestAdmin request) {
        Administrador administrador = Administrador.builder()
                .username(request.getUsername())
                .nombre_administrador(request.getNombre_administrador())
                .contacto_administrador(request.getContacto_administrador())
                .numero_documento_admin(request.getNumero_documento_admin())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))  // Asegúrate de que esto se esté haciendo correctamente
                .build();

        try {
            create(administrador);
            return AuthResponseAdmin.builder()
                    .token(jwtService.getToken(administrador))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
