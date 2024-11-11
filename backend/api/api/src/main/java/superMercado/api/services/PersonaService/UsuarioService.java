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
import superMercado.api.entities.Usuario;
import superMercado.api.excepciones.*;
import superMercado.api.repositories.UsuarioRepository;
import superMercado.api.services.jwt.JwtService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService implements BaseServiceUsuario {
    @Autowired
    private UsuarioRepository usuarioRepository;

    private final JwtService jwtService;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) throws InvalidCredentialsException {
        // Buscar el usuario por nombre de usuario
        UserDetails user = usuarioRepository.findByUsername(request.getUsername())
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

        if(usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("El correo ya esta registrado con otro usuario");
        }

        // Validar la fuerza de la contraseña
        if (!isStrongPassword(request.getPassword())) {
            throw new WeakPasswordException("La contraseña debe tener al menos 8 caracteres, incluyendo mayúsculas, minúsculas y números.");
        }

        // Verificar si el usuario ya existe
        if (usuarioRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UserNameAlreadyExistsException("El nombre de usuario ya existe.");
        }

        //Verificar si el correo ya existe
        if (usuarioRepository.findByDocumento(request.getDocumento()).isPresent()) {
            throw new DocumentAlreadyExistsException("El documento ya existe");
        }

        //Verificar si el telefono ya existe

        if(usuarioRepository.findByTelefono(request.getTelefono()).isPresent()){
            throw  new PhoneAlreadyExistsException("El telefono ya esta registrado con otro usuario");
        }
        // Crear el administrador si pasa todas las validaciones
       Usuario usuario = new Usuario(
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                request.getNombre(),
                request.getEmail(),
                request.getDocumento(),
                request.getTelefono()
        );

        try {
            create(usuario);
            return AuthResponse.builder()
                    .token(jwtService.getToken(usuario))
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
    public List<Usuario> findAll() throws Exception {

        try {
            //SELECT * FROM railway.usuario
            return usuarioRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }


    }

    //CREATE-INSERT INTO
    @Override
    public Usuario create(Usuario usuario) throws Exception {
        try {
            // INSERT INTO `railway`.`usuario` (`nombre_usuario`, `documento_usuario`, `correo_usuario`, `telefono_usuario`)
            // VALUES ('?', '?', '?', '?');
            return usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //GET-SELECT
    @Override
    public Usuario findById(int id) throws Exception {
        try {
            //SELECT * FROM railway.usuario where id_usuario= "?"
            Usuario usuario = usuarioRepository.findById(id).orElse(null);
            return usuario;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    //UPDATE
    @Override
    public Usuario update(int id, Usuario entity) throws Exception {
        //PDATE `railway`.`usuario` SET `nombre_usuario` = '?', `documento_usuario` = '?',
        // `correo_usuario` = '?', `password_usuario `= `?`, `telefono_usuario` = '?' WHERE (`id_usuario` = '?');
        try {
            Optional<Usuario> entityOpcional = usuarioRepository.findById(id);
            Usuario usuario = entityOpcional.get();
            usuario.setPassword(passwordEncoder.encode(entity.getPassword()));
            usuario = usuarioRepository.save(entity);
            return usuario;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    //delete
    @Override
    public boolean delete(int id) throws Exception {

///DELETE  FROM railway.usuario where id_usuario = ?
        try {
            if (usuarioRepository.existsById(id)) {
                usuarioRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return false;
    }
}
