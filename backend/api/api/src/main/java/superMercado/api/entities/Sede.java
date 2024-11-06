package superMercado.api.entities;

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
    @JoinColumn(name = "id_administrador") // Clave foránea en Sede
    private Administrador administrador;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Factura>facturas = new ArrayList<Factura>();

}
