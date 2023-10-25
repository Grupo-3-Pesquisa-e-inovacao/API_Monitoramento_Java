package entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoricoUsuarios {

    private Integer idHistorico;
    private String email;
    private String nome;

    private LocalDateTime dataHora;

    private DateTimeFormatter formatadorDtaHora;

    public HistoricoUsuarios() {
        this.dataHora = LocalDateTime.now();
        this.formatadorDtaHora = DateTimeFormatter.ofPattern(" dd/MM/yyyy hh:mm:ss ");
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

    public Integer getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(Integer idHistorico) {
        this.idHistorico = idHistorico;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return """
                %s -> %s -> %s
                """.formatted(dataHora.format(formatadorDtaHora), nome, email)
                ;
    }


}
