package DadosAPI;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.sistema.Sistema;

public class CpuDados {

    private Looca looca;

    private Sistema sistema;

    private Double usoCpu;

    private Long frequencia;

    private String microarquitetura;

    public CpuDados() {
        this.looca = looca = new Looca();
        this.usoCpu = looca.getProcessador().getUso();
        this.frequencia = looca.getProcessador().getFrequencia();
        this.microarquitetura = looca.getProcessador().getMicroarquitetura();
    }

    public Double getUsoCpu() {
        return usoCpu;
    }

    public Long getFrequencia() {
        return frequencia;
    }

    public String getMicroarquitetura() {
        return microarquitetura;
    }
}
