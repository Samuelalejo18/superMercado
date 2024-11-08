package superMercado.api.services.Administrador;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import superMercado.api.entities.Administrador;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class AdministradorDAO {

    private final JdbcTemplate jdbcTemplate;

    public boolean findById(Administrador administrador) {
        String sql = "SELECT * FROM railway.administrador WHERE id_administrador = ?";
        try {
            Administrador result = jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{administrador.getId_administrador()},
                    (rs, rowNum) -> {
                        administrador.setUsername(rs.getString("username"));
                        administrador.setContacto_administrador(rs.getString("contacto_administrador"));
                        administrador.setEmail(rs.getString("email"));
                        administrador.setNombre_administrador(rs.getString("nombre_administrador"));
                        administrador.setNumero_documento_admin(rs.getString("numero_documento_admin"));
                        administrador.setPassword(rs.getString("password"));
                        return administrador;
                    });
            return result != null;
        } catch (Exception e) {
            System.out.println("Error al recuperar administrador por id: " + e.getMessage());
            return false;
        }
    }
}