package superMercado.api.services.Producto;

import superMercado.api.entities.Producto;

import java.util.List;

public interface BaseServiceProducto {
   //GET-SELECT-READ
    public List<Producto> findAll() throws Exception;
    public Producto findById(int id) throws Exception;

    //CREATE
    public Producto create(Producto Producto) throws Exception;
    //update
    public Producto update(int id, Producto Producto) throws Exception;
    //Detele
    public boolean delete(int id) throws Exception;
}

