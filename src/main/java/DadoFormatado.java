import com.github.britooo.looca.api.group.processos.Processo;

import java.util.List;

public class DadoFormatado {
    Captura cap = new Captura();

    public Long porcentagemVolumeDisponivel = (cap.getDiscoVolumeDisponivel() * 100) / cap.getDiscoVolumeTotal();

    public Double usoConsumoMemoriaProcessos() {
        List<Double> processos = cap.getListaUsoMemoriaProcesso();
        Double usoMemoriaTotal = 0.0;

        for(int i = 0; i < processos.size(); i++) {
            usoMemoriaTotal += processos.get(i);
        }
        return usoMemoriaTotal;
    }
    public Short somaUsoMemoriaProcesso = usoConsumoMemoriaProcessos().shortValue();





}
