package superMercado.api.services.Factura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import superMercado.api.entities.Factura;
import superMercado.api.repositories.FacturaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaService implements BaseServiceFactura {
    @Autowired
    private FacturaRepository facturaRepository;

    @Override
// GET-SELECT
    public List<Factura> findAll() throws Exception {

        try {
            //SELECT * FROM railway.Factura
            return facturaRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }


    }

    //CREATE-INSERT INTO
    @Override
    public Factura create(Factura factura) throws Exception {
        try {
            //INSERT INTO `railway`.`Factura` (`nombre_Factura`, `contacto_Factura`, `numero_documento_admin`, `correo_Factura`, `password_Factura`)
            //VALUES ('?', '?', '?', '?', '?');
            return facturaRepository.save(factura);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //GET-SELECT
    @Override
    public Factura findById(int id) throws Exception {
        try {
            //SELECT * FROM railway.factura where id_Factura= "?"
            Factura Factura = facturaRepository.findById(id).orElse(null);
            return Factura;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    //UPDATE
    @Override
    public Factura update(int id, Factura entity) throws Exception {
        //PDATE `railway`.`Factura` SET `nombre_Factura` = '?', `contacto_Factura` = '?', `numero_documento_admin` = '?', `correo_Factura` = '?', `password_Factura` = '?'
        // WHERE (`id_Factura` = '?');
        try {
            Optional<Factura> entityOpcional = facturaRepository.findById(id);
            Factura factura = entityOpcional.get();
            factura = facturaRepository.save(entity);
            return factura;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    //delete
    @Override
    public boolean delete(int id) throws Exception {

///DELETE  FROM railway.Factura where id_Factura = ?
        try {
            if (facturaRepository.existsById(id)) {
                facturaRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return false;
    }
}
