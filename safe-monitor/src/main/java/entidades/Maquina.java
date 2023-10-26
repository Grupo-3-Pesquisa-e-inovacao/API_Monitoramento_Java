package entidades;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class Maquina {

    private Integer idMaquina;
    private String nome;
    private String modelo;
    private String numeroSerie;
    private String marca;
    private String sistemaOperacional;
    private String fabricante;

    private Integer arquitetura;

    public Maquina() {}

    public Integer getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public Integer getArquitetura() {
        return arquitetura;
    }

    public void setArquitetura(Integer arquitetura) {
        this.arquitetura = arquitetura;
    }

    @Override
    public String toString() {
        return String.format("""
                *----- Máquina %s -----*
                Id: %d
                Modelo: %s
                Número de Série: %s
                Marca: %s
                Sistema Operacional: %s
                Fabricante: %s
                Arquitetura: %d
                """,nome,idMaquina, modelo, numeroSerie, marca, sistemaOperacional, fabricante, arquitetura);
    }
}
