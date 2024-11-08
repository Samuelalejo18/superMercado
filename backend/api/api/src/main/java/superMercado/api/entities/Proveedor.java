package superMercado.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "proveedor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Proveedor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_proveedor;
    private String nombre_proveedor;
    private String contacto_proveedor;
    private String correo_proveedor;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name="producto_proveedor",
            joinColumns=@JoinColumn(name="id_producto"),
            inverseJoinColumns = @JoinColumn(name="id_proveedor")
    )
    @JsonIgnoreProperties({"lote","facturas","proveedores"})
    private List<Producto> productosProveedor = new ArrayList<Producto>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name="sede_proveedor",
            joinColumns=@JoinColumn(name="id_proveedor"),
            inverseJoinColumns = @JoinColumn(name="id_sede")
    )
    @JsonIgnoreProperties({"administrador","facturas","proveedores"})
    private List<Sede> sedes = new ArrayList<Sede>();


}
