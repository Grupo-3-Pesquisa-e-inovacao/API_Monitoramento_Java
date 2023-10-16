import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CamposCaptura {

    private Integer idCaptura;
    private String nome_monitoramento;
    private Double valor_monitorado;
    private LocalDateTime dataHora;
    private Integer fkComponente;
    private Integer fkMaquina;

    private DateTimeFormatter formatadorDtaHora;

    public CamposCaptura() {}

    public CamposCaptura(String nome_monitoramento, Double valor_monitorado, Integer fkComponente, Integer fkMaquina) {
        this.nome_monitoramento = nome_monitoramento;
        this.valor_monitorado = valor_monitorado;
        this.dataHora = LocalDateTime.now();
        this.formatadorDtaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
        this.fkComponente = fkComponente;
        this.fkMaquina = fkMaquina;
    }


    public Integer getIdCaptura() {
        return idCaptura;
    }

    public void setIdCaptura(Integer idCaptura) {
        this.idCaptura = idCaptura;
    }

    public String getNome_monitoramento() {
        return nome_monitoramento;
    }

    public void setNome_monitoramento(String nome_monitoramento) {
        this.nome_monitoramento = nome_monitoramento;
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

    public Integer getFkComponente() {
        return fkComponente;
    }

    public void setFkComponente(Integer fkComponente) {
        this.fkComponente = fkComponente;
    }

    public Integer getFkMaquina() {
        return fkMaquina;
    }

    public void setFkMaquina(Integer fkMaquina) {
        this.fkMaquina = fkMaquina;
    }

    @Override
    public String toString() {
        return String.format("""
                %s: %.2f  -> Data e hora: %s
                """, nome_monitoramento, valor_monitorado, dataHora);
    }
}


