package superMercado.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "factura")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Factura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_factura;
    private double total_factura;
    private Date fecha_factura;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_sede") // Clave foránea en Sede
    @JsonIgnoreProperties({"facturas","administrador"})
    private Sede sede;


    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario")
    @JsonIgnoreProperties({"facturas","password_usuario"})
    private Usuario usuario;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name="factura_producto",
            joinColumns=@JoinColumn(name="factura_id"),
            inverseJoinColumns = @JoinColumn(name="producto_id")
    )
    @JsonIgnoreProperties({"lote","facturas"})
    private List<Producto> productos = new ArrayList<Producto>();
}
