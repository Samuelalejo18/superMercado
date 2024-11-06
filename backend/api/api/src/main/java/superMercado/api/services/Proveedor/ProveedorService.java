package superMercado.api.services.Proveedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import superMercado.api.entities.Proveedor;
import superMercado.api.repositories.ProveedorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService implements BaseServiceProveedor {
    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
// GET-SELECT
    public List<Proveedor> findAll() throws Exception {

        try {
            //SELECT * FROM railway.Proveedor
            return proveedorRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }


    }

    //CREATE-INSERT INTO
    @Override
    public Proveedor create(Proveedor proveedor) throws Exception {
        try {
            //INSERT INTO `railway`.`Proveedor` (`nombre_Proveedor`, `contacto_Proveedor`, `numero_documento_admin`, `correo_Proveedor`, `password_Proveedor`)
            //VALUES ('?', '?', '?', '?', '?');
            return proveedorRepository.save(proveedor);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //GET-SELECT
    @Override
    public Proveedor findById(int id) throws Exception {
        try {
            //SELECT * FROM railway.Proveedor where id_Proveedor= "?"
            Proveedor proveedor = proveedorRepository.findById(id).orElse(null);
            return proveedor;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    //UPDATE
    @Override
    public Proveedor update(int id, Proveedor entity) throws Exception {
        //PDATE `railway`.`Proveedor` SET `nombre_Proveedor` = '?', `contacto_Proveedor` = '?', `numero_documento_admin` = '?', `correo_Proveedor` = '?', `password_Proveedor` = '?'
        // WHERE (`id_Proveedor` = '?');
        try {
            Optional<Proveedor> entityOpcional = proveedorRepository.findById(id);
            Proveedor proveedor = entityOpcional.get();
            proveedor = proveedorRepository.save(entity);
            return proveedor;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    //delete
    @Override
    public boolean delete(int id) throws Exception {

///DELETE  FROM railway.Proveedor where id_Proveedor = ?
        try {
            if (proveedorRepository.existsById(id)) {
                proveedorRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return false;
    }
}
