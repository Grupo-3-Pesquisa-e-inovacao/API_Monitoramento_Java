package componentes;

import com.github.britooo.looca.api.core.Looca;
import entidades.Componente;

public class Memoria extends Componente {

    public Double converterParaGigas(Double valor) {
        return valor / 1000000000;
    }

    @Override
    public void definirModelo() {
        modelo = null;
    }

    @Override
    public void definirNome() {
        nome = null;
    }

    @Override
    public void definirUso() {
        uso = looca.getMemoria().getEmUso().doubleValue();
    }

    @Override
    public void definirTotal() {
        total = looca.getMemoria().getTotal().doubleValue();
    }
}
