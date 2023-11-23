package entidades;

import javax.management.Query;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CapturaDados {

    private Integer idCaptura;
    private Double valor_monitorado;
    private LocalDateTime dataHora;
    private DateTimeFormatter formatadorDtaHora;
    private String nome;

    Query query;
    public CapturaDados() {
       /* this.dataHora = LocalDateTime.now();*/
        this.formatadorDtaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
        this.query = new Query();
    }

    public Integer getIdCaptura() {
        return idCaptura;
    }

    public void setIdCaptura(Integer idCaptura) {
        this.idCaptura = idCaptura;
    }

    public Double getValor_monitorado() {
        return valor_monitorado;
    }

    public void setValor_monitorado(Double valor_monitorado) {
        this.valor_monitorado = valor_monitorado;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public DateTimeFormatter getFormatadorDtaHora() {
        return formatadorDtaHora;
    }

    public void setFormatadorDtaHora(DateTimeFormatter formatadorDtaHora) {
        this.formatadorDtaHora = formatadorDtaHora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return String.format("""
                %s -> %.2f -> %s
                """, nome, valor_monitorado, dataHora.format(formatadorDtaHora));
    }
}


