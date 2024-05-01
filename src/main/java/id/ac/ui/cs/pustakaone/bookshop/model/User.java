package id.ac.ui.cs.pustakaone.bookshop.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
//import java.util.List;
import java.util.Collections;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @Column(name = "u_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "bio")
    private String bio;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "no_telp", unique = true)
    private String noTelp;

    @Lob
    @Column(name = "foto")
    private byte[] foto;

    @Column(name = "jenis_kelamin")
    private String jenisKelamin;

    @Column(name = "tanggal_lahir")
    private LocalDate tanggalLahir;

    public User(String username, String email, String noTelp, String password) {
        this.username = username;
        this.email = email;
        this.noTelp = noTelp;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean isAdmin() {
        return isAdmin != null && isAdmin;
    }
}