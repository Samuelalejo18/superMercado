package superMercado.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lote")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Lote implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int  id_lote;
    private String categoria_lote;
    private Date fecha_vencimiento;
    @OneToMany(mappedBy = "lote", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"lote","facturas","productosProveedor"})
    private List<Producto>  productos = new ArrayList<Producto>();
}
