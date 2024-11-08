package superMercado.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "administrador")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Administrador implements  UserDetails, Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_administrador;
    private String username;
    private String nombre_administrador;
    private String contacto_administrador;
    private String numero_documento_admin;
    private String email;
    @Column(nullable = false)
    private String password;


    @OneToMany(mappedBy = "administrador", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"administrador", "facturas", "proveedores"})
    private List<Sede> sedes = new ArrayList<Sede>();

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
    @JsonIgnore
    @Override
    public String getPassword() {
        return  "";
    }
    @JsonIgnore
    @Override
    public String getUsername() {
        return "";
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
