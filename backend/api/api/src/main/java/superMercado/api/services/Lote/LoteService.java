package superMercado.api.services.Lote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import superMercado.api.entities.Lote;
import superMercado.api.repositories.LoteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LoteService implements BaseServiceLote {
    @Autowired
    private LoteRepository LoteRepository;

    @Override
// GET-SELECT
    public List<Lote> findAll() throws Exception {

        try {
            //SELECT * FROM railway.Lote
            return LoteRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }


    }

    //CREATE-INSERT INTO
    @Override
    public Lote create(Lote lote) throws Exception {
        try {
            //INSERT INTO `railway`.`Lote` (`nombre_Lote`, `contacto_Lote`, `numero_documento_admin`, `correo_Lote`, `password_Lote`)
            //VALUES ('?', '?', '?', '?', '?');
            return LoteRepository.save(lote);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //GET-SELECT
    @Override
    public Lote findById(int id) throws Exception {
        try {
            //SELECT * FROM railway.Lote where id_Lote= "?"
            Lote lote = LoteRepository.findById(id).orElse(null);
            return lote;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    //UPDATE
    @Override
    public Lote update(int id, Lote entity) throws Exception {
        //PDATE `railway`.`Lote` SET `nombre_Lote` = '?', `contacto_Lote` = '?', `numero_documento_admin` = '?', `correo_Lote` = '?', `password_Lote` = '?'
        // WHERE (`id_Lote` = '?');
        try {
            Optional<Lote> entityOpcional = LoteRepository.findById(id);
            Lote lote = entityOpcional.get();
            lote = LoteRepository.save(entity);
            return lote;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    //delete
    @Override
    public boolean delete(int id) throws Exception {

///DELETE  FROM railway.Lote where id_Lote = ?
        try {
            if (LoteRepository.existsById(id)) {
                LoteRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return false;
    }
}
