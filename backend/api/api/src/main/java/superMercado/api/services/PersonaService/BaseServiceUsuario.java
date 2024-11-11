package superMercado.api.services.PersonaService;

import superMercado.api.Auth.AuthResponse;
import superMercado.api.Auth.LoginRequest;
import superMercado.api.Auth.RegisterRequest;
import superMercado.api.entities.Usuario;
import superMercado.api.excepciones.InvalidCredentialsException;

import java.util.List;

public interface BaseServiceUsuario {
   //GET-SELECT-READ
    public List<Usuario> findAll() throws Exception;
    public Usuario findById(int id) throws Exception;

    //CREATE
    public Usuario create(Usuario usuario) throws Exception;
    //update
    public Usuario update(int id, Usuario usuario) throws Exception;
    //Detele
    public boolean delete(int id) throws Exception;
 public AuthResponse login(LoginRequest request) throws Exception, InvalidCredentialsException;
 public AuthResponse register(RegisterRequest request) ;
}

