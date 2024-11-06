package superMercado.api.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sede")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Sede implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_sede;
    private String nombre_sede;
    private String direccion_sede;
    @ManyToOne
    @JoinColumn(name = "id_administrador") // Nombre de la columna en la tabla 'sede' que actúa como clave foránea
// Ignora la lista de "sedes" al serializar el Administrador en Sede
    @JsonIgnoreProperties({"sedes"})
    private Administrador administrador;

    @OneToMany(mappedBy = "sede", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"sede"})
    private List<Factura> facturas = new ArrayList<Factura>();

}
