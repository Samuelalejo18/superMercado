package superMercado.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import superMercado.api.entities.Administrador;
import superMercado.api.entities.Persona;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface PersonaRepository < E extends Persona, ID extends Serializable> extends JpaRepository<E, ID> {
    Optional<E> findByUsername(String username);
    Optional<E> findByEmail(String email);
    Optional<E> findByDocumento(String documento);
    Optional<E> findByTelefono(String telefono);
}
