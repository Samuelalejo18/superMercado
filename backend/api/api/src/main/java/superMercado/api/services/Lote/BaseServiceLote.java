package superMercado.api.services.Lote;

import superMercado.api.entities.Lote;

import java.util.List;

public interface BaseServiceLote {
   //GET-SELECT-READ
    public List<Lote> findAll() throws Exception;
    public Lote findById(int id) throws Exception;

    //CREATE
    public Lote create(Lote Lote) throws Exception;
    //update
    public Lote update(int id, Lote Lote) throws Exception;
    //Detele
    public boolean delete(int id) throws Exception;
}

