package superMercado.api.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

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
}
