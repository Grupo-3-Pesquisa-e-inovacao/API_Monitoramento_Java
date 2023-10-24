package entidades;

import javax.management.Query;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CapturaDados {

    private Integer idCaptura;
    private Double valor_monitorado;
    private LocalDateTime dataHora;
    private DateTimeFormatter formatadorDtaHora;

    public CapturaDados() {
        this.dataHora = LocalDateTime.now();
        this.formatadorDtaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
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

    @Override
    public String toString() {
        return "CapturaDados{" +
                "valor_monitorado=" + valor_monitorado +
                ", dataHora=" + dataHora +
                ", formatadorDtaHora=" + formatadorDtaHora +
                '}';
    }
}


