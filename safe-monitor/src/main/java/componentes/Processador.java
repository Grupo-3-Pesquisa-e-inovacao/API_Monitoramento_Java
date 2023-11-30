package componentes;

import com.github.britooo.looca.api.core.Looca;
import entidades.Componente;

public class Processador extends Componente {

    @Override
    public void definirTotal() {
        total = looca.getProcessador().getNumeroCpusLogicas().doubleValue();
    }


    @Override
    public void definirUso() {
        uso = looca.getProcessador().getUso();
    }

    @Override
    public void definirNome() {
        nome = looca.getProcessador().getNome();
    }

    @Override
    public void definirModelo() {
        modelo = looca.getProcessador().getMicroarquitetura();
    }

    @Override
    public Double converterParaGigas(Double valor) {
        return valor * 1;
    }
}
