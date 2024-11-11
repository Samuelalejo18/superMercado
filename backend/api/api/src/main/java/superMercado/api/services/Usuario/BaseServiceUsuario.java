package superMercado.api.services.Usuario;

import superMercado.api.Auth.AuthResponseAdmin;
import superMercado.api.Auth.LoginRequest;
import superMercado.api.Auth.RegisterRequest;
import superMercado.api.entities.Administrador;
import superMercado.api.entities.Usuario;

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
 public AuthResponseAdmin login(LoginRequest request) throws Exception;
 public AuthResponseAdmin register(RegisterRequest request) ;
}

