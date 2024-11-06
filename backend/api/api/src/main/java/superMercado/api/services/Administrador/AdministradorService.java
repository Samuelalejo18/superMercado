package superMercado.api.services.Administrador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import superMercado.api.entities.Administrador;
import superMercado.api.repositories.AdministradorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService implements BaseServiceAdministrador {
    @Autowired
    private AdministradorRepository administradorRepository;

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
}
