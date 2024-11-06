package superMercado.api.services.Sede;

import superMercado.api.entities.Administrador;
import superMercado.api.entities.Sede;

import java.util.List;

public interface BaseServiceSede {
   //GET-SELECT-READ
    public List<Sede> findAll() throws Exception;
    public Sede findById(int id) throws Exception;
    //CREATE
    public Sede create(Sede sede) throws Exception;
    //update
    public Sede update(int id, Sede sede) throws Exception;
    //Detele
    public boolean delete(int id) throws Exception;
}

