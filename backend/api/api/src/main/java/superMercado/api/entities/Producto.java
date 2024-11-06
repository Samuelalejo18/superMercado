package superMercado.api.entities;

import jakarta.persistence.*;
import lombok.*;

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
}
