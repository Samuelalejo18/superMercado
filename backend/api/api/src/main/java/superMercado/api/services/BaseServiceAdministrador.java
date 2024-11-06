package superMercado.api.services;

import superMercado.api.entities.Administrador;

import java.util.List;

public interface BaseServiceAdministrador{
   //GET-SELECT-READ
    public List<Administrador> findAll() throws Exception;
    public Administrador findById(int id) throws Exception;

    //CREATE
    public Administrador create(Administrador administrador) throws Exception;
    //update
    public Administrador update(int id, Administrador administrador) throws Exception;
    //Detele
    public boolean delete(int id) throws Exception;
}

