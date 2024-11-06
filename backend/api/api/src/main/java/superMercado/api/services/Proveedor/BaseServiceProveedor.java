package superMercado.api.services.Proveedor;

import superMercado.api.entities.Proveedor;

import java.util.List;

public interface BaseServiceProveedor {
   //GET-SELECT-READ
    public List<Proveedor> findAll() throws Exception;
    public Proveedor findById(int id) throws Exception;

    //CREATE
    public Proveedor create(Proveedor proveedor) throws Exception;
    //update
    public Proveedor update(int id, Proveedor proveedor) throws Exception;
    //Detele
    public boolean delete(int id) throws Exception;
}

