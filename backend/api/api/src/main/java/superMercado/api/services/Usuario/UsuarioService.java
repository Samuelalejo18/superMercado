package superMercado.api.services.Usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import superMercado.api.Auth.AuthResponseAdmin;
import superMercado.api.Auth.LoginRequest;
import superMercado.api.Auth.RegisterRequest;
import superMercado.api.entities.Administrador;
import superMercado.api.entities.Usuario;
import superMercado.api.repositories.AdministradorRepository;
import superMercado.api.repositories.UsuarioRepository;
import superMercado.api.services.Administrador.BaseServiceAdministrador;
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

    @Override
    public AuthResponseAdmin login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = usuarioRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponseAdmin.builder()
                .token(token)
                .build();

    }


    @Override
    public AuthResponseAdmin register(RegisterRequest request) {
        Usuario usuario = new Usuario(request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                request.getNombre(),
                request.getEmail(),
                request.getDocumento(),
                request.getTelefono());
        try {
            create(usuario);
            return AuthResponseAdmin.builder()
                    .token(jwtService.getToken(usuario))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
