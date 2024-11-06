package superMercado.api.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;

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

}
