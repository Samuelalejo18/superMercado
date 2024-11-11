package superMercado.api.services.PersonaService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import superMercado.api.Auth.AuthResponse;
import superMercado.api.Auth.LoginRequest;
import superMercado.api.Auth.RegisterRequest;
import superMercado.api.entities.Administrador;
import superMercado.api.excepciones.*;
import superMercado.api.repositories.AdministradorRepository;
import superMercado.api.services.jwt.JwtService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministradorService implements BaseServiceAdministrador {
    private final AdministradorRepository administradorRepository;
    private final JwtService jwtService;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) throws InvalidCredentialsException {
        // Buscar el usuario por nombre de usuario
        UserDetails user = administradorRepository.findByUsername(request.getUsername())
                .orElseThrow(() ->  new UserNotFoundException("El usuario no existe"));

        try {
            // Autenticar el usuario
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (Exception e) {
            // Lanza excepción personalizada si las credenciales son incorrectas
            throw new InvalidCredentialsException("Credenciales incorrectas. Verifique su nombre de usuario y contraseña.");
        }

        // Generar el token JWT si la autenticación es exitosa
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }


    public AuthResponse register(RegisterRequest request) {
        // Validar el formato del correo electrónico
        if (!isValidEmail(request.getEmail())) {
            throw new InvalidEmailFormatException("El formato del correo electrónico es inválido.");
        }
        //Verificar si el email ya existe

        if(administradorRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("El correo ya esta registrado con otro usuario");
        }

        // Validar la fuerza de la contraseña
        if (!isStrongPassword(request.getPassword())) {
            throw new WeakPasswordException("La contraseña debe tener al menos 8 caracteres, incluyendo mayúsculas, minúsculas y números.");
        }

        // Verificar si el usuario ya existe
        if (administradorRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UserNameAlreadyExistsException("El nombre de usuario ya existe.");
        }

        //Verificar si el correo ya existe
        if (administradorRepository.findByDocumento(request.getDocumento()).isPresent()) {
            throw new DocumentAlreadyExistsException("El documento ya existe");
        }

        //Verificar si el telefono ya existe

        if(administradorRepository.findByTelefono(request.getTelefono()).isPresent()){
            throw  new PhoneAlreadyExistsException("El telefono ya esta registrado con otro usuario");
        }
        // Crear el administrador si pasa todas las validaciones
        Administrador administrador = new Administrador(
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                request.getNombre(),
                request.getEmail(),
                request.getDocumento(),
                request.getTelefono()
        );

        try {
            create(administrador);
            return AuthResponse.builder()
                    .token(jwtService.getToken(administrador))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Error al registrar el administrador.");
        }
    }

    // Métodos de validación auxiliares
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    private boolean isStrongPassword(String password) {
        return password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*[a-z].*") && password.matches(".*\\d.*");
    }
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
            System.out.println(administrador.getId_administrador()+" "+ administrador.getPassword());
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
            administrador.setPassword( passwordEncoder.encode(administrador.getPassword()));
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

}
