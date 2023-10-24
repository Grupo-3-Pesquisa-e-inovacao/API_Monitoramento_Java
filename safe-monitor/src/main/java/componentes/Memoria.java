package componentes;

import com.github.britooo.looca.api.core.Looca;

public class Memoria {

    private Looca looca;
    private Double uso;
    private Double total;
    private Double disponivel;

    public Memoria() {
        this.looca = new Looca();
        this.uso = looca.getMemoria().getEmUso().doubleValue();
        this.total = looca.getMemoria().getTotal().doubleValue();
        this.disponivel =looca.getMemoria().getDisponivel().doubleValue();
    }

    public Double getUso() {
        return uso;
    }

    public Double getTotal() {
        return total;
    }

    public Double getDisponivel() {
        return disponivel;
    }

    @Override
    public String toString() {
        return "Memoria{" +
                "uso=" + uso +
                ", total=" + total +
                ", disponivel=" + disponivel +
                '}';
    }
}
