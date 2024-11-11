package superMercado.api.services.PersonaService;

import superMercado.api.Auth.AuthResponse;
import superMercado.api.Auth.LoginRequest;
import superMercado.api.Auth.RegisterRequest;
import superMercado.api.entities.Administrador;
import superMercado.api.excepciones.InvalidCredentialsException;

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

    public AuthResponse login(LoginRequest request) throws Exception, InvalidCredentialsException;
    public AuthResponse register(RegisterRequest request) ;
}

