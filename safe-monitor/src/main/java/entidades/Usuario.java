package entidades;

import org.springframework.jdbc.core.JdbcTemplate;

public class Usuario {

    private String email;

    private String senha;

    private Integer capturar;

    public Usuario() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getCapturar() {
        return capturar;
    }

    public void setCapturar(Integer capturar) {
        this.capturar = capturar;
    }


}
