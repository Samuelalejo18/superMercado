package superMercado.api.services.Sede;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import superMercado.api.entities.Administrador;
import superMercado.api.entities.Sede;
import superMercado.api.repositories.AdministradorRepository;
import superMercado.api.repositories.SedeRepository;
import superMercado.api.services.Administrador.BaseServiceAdministrador;

import java.util.List;
import java.util.Optional;

@Service
public class SedeService implements BaseServiceSede{
    @Autowired
    private SedeRepository sedeRepository;

    @Override
// GET-SELECT
    public List<Sede> findAll() throws Exception {

        try {
            //SELECT * FROM railway.sede
            return  sedeRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }


    }

    //CREATE-INSERT INTO
    @Override
    public Sede create(Sede sede) throws Exception {
        try {
            //NSERT INTO `railway`.`sede` (`nombre_sede`, `direccion_sede`, `id_administrador`) VALUES ('?', '?', '?');
            return sedeRepository.save(sede);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //GET-SELECT
    @Override
    public Sede findById(int id) throws Exception {
        try {
            //SELECT * FROM railway.sede where id_sede=   "?"
           Sede sede =  sedeRepository.findById(id).orElse(null);
            return sede;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    //UPDATE
    @Override
    public Sede update(int id, Sede entity) throws Exception {
       // UPDATE `railway`.`sede` SET `nombre_sede` = '?', `id_administrador` = '?' WHERE (`id_sede` = '?');
        try {
            Optional<Sede> entityOpcional =  sedeRepository.findById(id);
           Sede sede = entityOpcional.get();
            sede =  sedeRepository.save(entity);
            return sede;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    //delete
    @Override
    public boolean delete(int id) throws Exception {

//DELETE  FROM railway.sede where id_sede = ?
        try {
            if ( sedeRepository.existsById(id)) {
                sedeRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return false;
    }
}
