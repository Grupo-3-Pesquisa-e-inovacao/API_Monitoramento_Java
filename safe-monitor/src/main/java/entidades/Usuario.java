package entidades;

import org.springframework.jdbc.core.JdbcTemplate;

public class Usuario {

    private Integer idUsuario;

    private Integer fkEmpresa;

    private String email;

    private String senha;

    private String nome;

    private Integer capturar;
    private Integer leitura;
    private Integer alterar;
    private Integer deletar;
    private Integer cadastrar;

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

    public Integer getLeitura() {
        return leitura;
    }

    public void setLeitura(Integer leitura) {
        this.leitura = leitura;
    }

    public Integer getAlterar() {
        return alterar;
    }

    public void setAlterar(Integer alterar) {
        this.alterar = alterar;
    }

    public Integer getDeletar() {
        return deletar;
    }

    public void setDeletar(Integer deletar) {
        this.deletar = deletar;
    }

    public Integer getCadastrar() {
        return cadastrar;
    }

    public void setCadastrar(Integer cadastrar) {
        this.cadastrar = cadastrar;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }
}
