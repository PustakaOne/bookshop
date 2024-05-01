package id.ac.ui.cs.pustakaone.bookshop.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    User user = new User("John Doe", "john@example.com", "1234567890", "password");

    @Test
    void getterName() {
        assertEquals("John Doe", user.getName());
    }

    @Test
    void getterEmail() {
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    void getterNoTelp() {
        assertEquals("1234567890", user.getNoTelp());
    }

    @Test
    void getterPassword() {
        assertEquals("password", user.getPassword());
    }

    @Test
    void getterFoto() {
        assertEquals(null, user.getFoto());
    }

    @Test
    void getterJenisKelamin() {
        assertEquals(null, user.getJenisKelamin());
    }

    @Test
    void getterTanggalLahir() {
        assertEquals(null, user.getTanggalLahir());
    }

    @Test
    void getterBio() {
        assertEquals(null, user.getBio());
    }

    @Test
    void setterName() {
        user.setName("Jane Doe");
        assertEquals("Jane Doe", user.getName());
    }

    @Test
    void setterEmail() {
        user.setEmail("jane@example.com");
        assertEquals("jane@example.com", user.getEmail());
    }

    @Test
    void setterNoTelp() {
        user.setNoTelp("0987654321");
        assertEquals("0987654321", user.getNoTelp());
    }

    @Test
    void setterPassword() {
        user.setPassword("newpassword");
        assertEquals("newpassword", user.getPassword());
    }

    @Test
    void setterFoto() {
        byte[] foto = {1, 2, 3};
        user.setFoto(foto);
        assertEquals(foto, user.getFoto());
    }

    @Test
    void setterJenisKelamin() {
        user.setJenisKelamin("Laki-laki");
        assertEquals("Laki-laki", user.getJenisKelamin());
    }

    @Test
    void setterTanggalLahir() {
        user.setTanggalLahir(LocalDate.of(1990, 1, 1));
        assertEquals(LocalDate.of(1990, 1, 1), user.getTanggalLahir());
    }

    @Test
    void setterBio() {
        user.setBio("A simple person");
        assertEquals("A simple person", user.getBio());
    }

    @Test
    void implementUserDetail() {
        User user = new User("Jane Doe", "jane@example.com", "0987654321", "newpassword");
        user.setIsAdmin(true);
        assertEquals(true, user.isAccountNonExpired());
        assertEquals(true, user.isAccountNonLocked());
        assertEquals(true, user.isCredentialsNonExpired());
        assertEquals(true, user.isEnabled());
        assertEquals(true, user.getIsAdmin());
        assertEquals(null, user.getFoto());
        assertEquals(null, user.getBio());
        assertEquals(null, user.getJenisKelamin());
        assertEquals(null, user.getTanggalLahir());
    }
}