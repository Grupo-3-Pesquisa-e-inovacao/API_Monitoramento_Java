package DadosAPI;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import oshi.hardware.HWDiskStore;

import java.util.ArrayList;
import java.util.List;

public class DiscoDados {

    private Looca looca = new Looca();
    private DiscoGrupo grupoDisco;
    private Disco disco;
    private List<Disco> listDiscos;
    private Long bytesLeituras;
    private Long bytesEscritas;
    private Long tamanhoAtualFila;
    private Long tempoDeTransferencia;

    public DiscoDados() {
        this.grupoDisco = looca.getGrupoDeDiscos();
        this.disco = grupoDisco.getDiscos().get(0);
        this.bytesLeituras = disco.getBytesDeLeitura();
        this.bytesEscritas = disco.getEscritas();
        this.tamanhoAtualFila = disco.getTamanhoAtualDaFila();
        this.tempoDeTransferencia = disco.getTempoDeTransferencia();

    }

    public Disco getDisco() {
        return disco;
    }

    public void setDisco(Disco disco) {
        this.disco = disco;
    }

    public Long getBytesLeituras() {
        return bytesLeituras;
    }

    public void setBytesLeituras(Long bytesLeituras) {
        this.bytesLeituras = bytesLeituras;
    }

    public Long getBytesEscritas() {
        return bytesEscritas;
    }

    public void setBytesEscritas(Long bytesEscritas) {
        this.bytesEscritas = bytesEscritas;
    }

    public Long getTamanhoAtualFila() {
        return tamanhoAtualFila;
    }

    public void setTamanhoAtualFila(Long tamanhoAtualFila) {
        this.tamanhoAtualFila = tamanhoAtualFila;
    }

    public Long getTempoDeTransferencia() {
        return tempoDeTransferencia;
    }

    public void setTempoDeTransferencia(Long tempoDeTransferencia) {
        this.tempoDeTransferencia = tempoDeTransferencia;
    }
}
