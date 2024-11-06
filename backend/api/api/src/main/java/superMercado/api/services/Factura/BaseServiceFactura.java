package superMercado.api.services.Factura;

import superMercado.api.entities.Factura;

import java.util.List;

public interface BaseServiceFactura {
   //GET-SELECT-READ
    public List<Factura> findAll() throws Exception;
    public Factura findById(int id) throws Exception;

    //CREATE
    public Factura create(Factura Factura) throws Exception;
    //update
    public Factura update(int id, Factura Factura) throws Exception;
    //Detele
    public boolean delete(int id) throws Exception;
}

