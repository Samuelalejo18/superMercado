package superMercado.api.services.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import superMercado.api.entities.Administrador;
import superMercado.api.entities.Usuario;
import superMercado.api.repositories.AdministradorRepository;
import superMercado.api.repositories.UsuarioRepository;
import superMercado.api.services.Administrador.BaseServiceAdministrador;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements BaseServiceUsuario {
    @Autowired
    private UsuarioRepository usuarioRepository;

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
    public  Usuario create(Usuario usuario) throws Exception {
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
          Usuario usuario =usuarioRepository.findById(id).orElse(null);
            return usuario;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    //UPDATE
    @Override
    public  Usuario update(int id, Usuario entity) throws Exception {
        //PDATE `railway`.`usuario` SET `nombre_usuario` = '?', `documento_usuario` = '?',
        // `correo_usuario` = '?', `password_usuario `= `?`, `telefono_usuario` = '?' WHERE (`id_usuario` = '?');
        try {
            Optional<Usuario> entityOpcional =usuarioRepository.findById(id);
            Usuario usuario = entityOpcional.get();
           usuario =usuarioRepository.save(entity);
            return  usuario;
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
