package superMercado.api.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@Entity
@Table(name = "administrador")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Administrador implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
  private int  id_administrador;

  private String nombre_administrador;
  private String contacto_administrador;
  private String numero_documento_admin;
  private String correo_administrador;
  private String password_administrador;
}
