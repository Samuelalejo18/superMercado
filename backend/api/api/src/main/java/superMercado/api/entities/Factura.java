package superMercado.api.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "factura")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Factura implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int  id_factura;
    private double total_factura;
    private Date fecha_factura;

    @ManyToOne(optional= false)
    @JoinColumn(name = "id_sede") // Clave foránea en Sede
    private Sede sede;



    @ManyToOne(optional= false)
    @JoinColumn( name = "id_usuario") // Clave foránea en Sede
    private Usuario usuario;

}
