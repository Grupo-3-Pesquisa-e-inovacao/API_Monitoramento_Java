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
    private Double armazenamentoDisco;
    private Double espacoLivreDisco;
    private String endereco_ipv4;
    private Double capacidadeTotalRam;

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

    public Double getArmazenamentoDisco() {
        return armazenamentoDisco;
    }

    public void setArmazenamentoDisco(Double armazenamentoDisco) {
        this.armazenamentoDisco = armazenamentoDisco;
    }

    public Double getEspacoLivreDisco() {
        return espacoLivreDisco;
    }

    public void setEspacoLivreDisco(Double espacoLivreDisco) {
        this.espacoLivreDisco = espacoLivreDisco;
    }

    public String getEnderecoIPV4() {
        return endereco_ipv4;
    }

    public void setEnderecoIPV4(String enderecoIPV4) {
        this.endereco_ipv4 = enderecoIPV4;
    }

    public Double getCapacidadeTotalRam() {
        return capacidadeTotalRam;
    }

    public void setCapacidadeTotalRam(Double capacidadeTotalRam) {
        this.capacidadeTotalRam = capacidadeTotalRam;
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
                Armazenamento de Disco: %.2f
                Espaço Livre: %.2f
                Endereço IPV4: %s
                Capacidade Total: %.2f
                """,nome,idMaquina, modelo, numeroSerie, marca, sistemaOperacional,
                armazenamentoDisco, espacoLivreDisco, endereco_ipv4, capacidadeTotalRam);
    }
}
