import java.util.Objects;

public class Limite {
    private Double limite;
    private Long fk_notificacao;
    private Long fk_tipoComponente;

    // Construtores
    public Limite() {
    }

    public Limite(Double limite, Long fk_notificacao, Long fk_tipoComponente) {
        this.limite = limite;
        this.fk_notificacao = fk_notificacao;
        this.fk_tipoComponente = fk_tipoComponente;
    }

    // Getters e Setters
    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public Long getFk_notificacao() {
        return fk_notificacao;
    }

    public void setFk_notificacao(Long fk_notificacao) {
        this.fk_notificacao = fk_notificacao;
    }

    public Long getFk_tipoComponente() {
        return fk_tipoComponente;
    }

    public void setFk_tipoComponente(Long fk_tipoComponente) {
        this.fk_tipoComponente = fk_tipoComponente;
    }

    // Outros métodos

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Limite limite1 = (Limite) o;
        return Objects.equals(limite, limite1.limite) &&
                Objects.equals(fk_notificacao, limite1.fk_notificacao) &&
                Objects.equals(fk_tipoComponente, limite1.fk_tipoComponente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(limite, fk_notificacao, fk_tipoComponente);
    }

    @Override
    public String toString() {
        return "Limite{" +
                "limite=" + limite +
                ", fk_notificacao=" + fk_notificacao +
                ", fk_tipoComponente=" + fk_tipoComponente +
                '}';
    }
}
