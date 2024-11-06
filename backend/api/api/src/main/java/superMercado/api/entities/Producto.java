package superMercado.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "producto")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Producto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id_producto;
    private String nombre_producto;
    private double precio_producto;
    private String descripcion_producto;
    private String categoria_producto;
    private String img_producto;
    @ManyToOne
    @JoinColumn(name = "id_lote") // Nombre de la columna en la tabla 'producto' que actúa como clave foránea
// Ignora la lista de "productos" al serializar el Administrador en Sede
    @JsonIgnoreProperties({"productos"})
    private Lote lote;

    @ManyToMany(mappedBy = "productos")
    @JsonIgnoreProperties({"usuario","sede","productos"})
    private List<Factura>  facturas= new ArrayList<Factura>();
}
