package componentes;

import com.github.britooo.looca.api.core.Looca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Janela {

    private Looca looca = new Looca();

    private List<com.github.britooo.looca.api.group.janelas.Janela> janelas;

    private List<Integer> pids;

    private List<String> titulos;

    private List<String> comandos;


    public Janela() {
        this.looca = new Looca();
        this.janelas = looca.getGrupoDeJanelas().getJanelasVisiveis();
        this.pids = new ArrayList<>();
        this.titulos = new ArrayList<>();
        this.comandos = new ArrayList<>();
    }


    public void popularListaPid() {
        for (int i = 0; i < janelas.size(); i++) {
            pids.add(janelas.get(i).getPid().intValue());
        }
    }

    public void popularListaCaminho() {
        for (int i = 0; i < janelas.size(); i++) {
            comandos.add(janelas.get(i).getComando());
        }
    }
    public void popularListaTitulo() {
        for (int i = 0; i < janelas.size(); i++) {
            if (!janelas.get(i).getTitulo().equals("")) {
                titulos.add(janelas.get(i).getTitulo());
            }

        }
    }

    public void fecharJanelaAtravesDoPid(Integer pid, Boolean fecharJanela){

        if (fecharJanela) {
            try {
                Process processo = Runtime.getRuntime().exec("taskkill /F /PID " + pid);
                processo.waitFor();
                System.out.println("O processo com PID " + pid + " foi encerrado.");

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public List<com.github.britooo.looca.api.group.janelas.Janela> getJanelas() {
        return janelas;
    }

    public void setJanelas(List<com.github.britooo.looca.api.group.janelas.Janela> janelas) {
        this.janelas = janelas;
    }

    public List<Integer> getPids() {
        return pids;
    }

    public void setPids(List<Integer> pids) {
        this.pids = pids;
    }

    public List<String> getTitulos() {
        return titulos;
    }

    public void setTitulos(List<String> titulos) {
        this.titulos = titulos;
    }

    public List<String> getComandos() {
        return comandos;
    }

    public void setComandos(List<String> comandos) {
        this.comandos = comandos;
    }

    @Override
    public String toString() {
        return "JanelaComponente{" +
                "pids=" + pids +
                ", titulos=" + titulos +
                ", comandos=" + comandos +
                '}';
    }
}
