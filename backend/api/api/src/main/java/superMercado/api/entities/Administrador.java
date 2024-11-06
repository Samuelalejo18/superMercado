package superMercado.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "administrador")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Administrador implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_administrador;
    private String nombre_administrador;
    private String contacto_administrador;
    private String numero_documento_admin;
    private String correo_administrador;
    private String password_administrador;

    @OneToMany(mappedBy = "administrador", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"administrador","facturas"})
    private List<Sede> sedes = new ArrayList<Sede>();
}
