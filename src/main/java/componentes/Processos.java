package componentes;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processos.Processo;

import java.util.ArrayList;
import java.util.List;

public class Processos {

    private Looca looca;

    private List<Processo> listaProcessos;

    private List<Integer> pids;

    private List<String> nome;

    private List<Double> usoCPU;

    private List<Double> usoMemoria;

    private List<Double> bytesUtilizados;

    public Processos() {
        this.looca = new Looca();
        this.listaProcessos = looca.getGrupoDeProcessos().getProcessos();
        this.pids = new ArrayList<>();
        this.usoCPU = new ArrayList<>();
        this.usoMemoria = new ArrayList<>();
        this.bytesUtilizados = new ArrayList<>();
        this.nome = new ArrayList<>();
    }

    public void popularListaPid() {
        for (int i = 0; i < listaProcessos.size(); i++) {
            pids.add(listaProcessos.get(i).getPid().intValue());
        }
    }

    public void popularListaNome() {
        for (int i = 0; i < listaProcessos.size(); i++) {
            nome.add(listaProcessos.get(i).getNome());
        }
    }

    public void popularListaUsoCpuProcesso() {
        for (int i = 0; i < listaProcessos.size(); i++) {
            usoCPU.add(listaProcessos.get(i).getUsoCpu());
        }
    }
    public void popularListaUsoMemoriaProcesso() {
        for (int i = 0; i < listaProcessos.size(); i++) {
            usoMemoria.add(listaProcessos.get(i).getUsoMemoria());
        }
    }
    public void popularListaUsoBytesProcesso() {
        for (int i = 0; i < listaProcessos.size(); i++) {
            bytesUtilizados.add(listaProcessos.get(i).getBytesUtilizados().doubleValue());
        }
    }

    public List<Integer> getPids() {
        return pids;
    }

    public void setPids(List<Integer> pids) {
        this.pids = pids;
    }

    public List<Double> getUsoCPU() {
        return usoCPU;
    }

    public void setUsoCPU(List<Double> usoCPU) {
        this.usoCPU = usoCPU;
    }

    public List<Double> getUsoMemoria() {
        return usoMemoria;
    }

    public void setUsoMemoria(List<Double> usoMemoria) {
        this.usoMemoria = usoMemoria;
    }

    public List<Double> getBytesUtilizados() {
        return bytesUtilizados;
    }

    public void setBytesUtilizados(List<Double> bytesUtilizados) {
        this.bytesUtilizados = bytesUtilizados;
    }

    public List<String> getNome() {
        return nome;
    }

    public void setNome(List<String> nome) {
        this.nome = nome;
    }
}
