package componentes;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Volume;
import entidades.Componente;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Disco extends Componente {
    private List<Volume> listaVolume;
    private List<Long> listaVolumeTotal;
    private List<Long> listaVolumeDisponivel;

    private Double volumeDisponivel;

    private List<Double> listaUsoVolume;

    private Integer quantidadeDeDisco;



    public Disco() {
        this.listaVolume = looca.getGrupoDeDiscos().getVolumes();
        this.volumeDisponivel = looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel().doubleValue();
        this.quantidadeDeDisco = looca.getGrupoDeDiscos().getQuantidadeDeDiscos();
        this.listaVolumeTotal = new ArrayList<>();
        this.listaVolumeDisponivel = new ArrayList<>();
        this.listaUsoVolume = new ArrayList<>();

    }

    public Boolean verificarQuantidadeDisco(){

        Boolean maisDeUmDisco = false;

        if(quantidadeDeDisco > 1){
            maisDeUmDisco = true;
        }

        return maisDeUmDisco;
    }

    public void popularListaVolumeTotal() {
        for (int i = 0; i < listaVolume.size(); i++) {

            if (listaVolume.get(i).getTotal() != 0) {
                listaVolumeTotal.add(listaVolume.get(i).getTotal());
            }

        }
    }


    public void popularListaVolumeDisponivel() {
        for (int i = 0; i < listaVolume.size(); i++) {
            if (listaVolume.get(i).getDisponivel() != 0) {
                listaVolumeDisponivel.add(listaVolume.get(i).getDisponivel());
            }

        }
    }

    public void popularListaVolumeEmUso() {
        for (int i = 0; i < listaVolume.size(); i++) {
            if (listaVolume.get(i).getDisponivel() != 0) {
                listaUsoVolume.add(listaVolume.get(i).getTotal().doubleValue() - listaVolume.get(i).getDisponivel().doubleValue());
            }

        }
    }

    @Override
    public void definirUso() {
       uso = total - looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel().doubleValue();
    }

    @Override
    public void definirTotal() {
        total = looca.getGrupoDeDiscos().getVolumes().get(0).getTotal().doubleValue();
    }

    @Override
    public void definirNome() {
        nome = looca.getGrupoDeDiscos().getDiscos().get(0).getNome();
    }

    @Override
    public void definirModelo() {
        modelo = looca.getGrupoDeDiscos().getDiscos().get(0).getModelo();
    }

    @Override
    public Double converterParaGigas(Double valor) {
        return valor / 1000000000;
    }

    public List<Volume> getListaVolume() {
        return listaVolume;
    }

    public void setListaVolume(List<Volume> listaVolume) {
        this.listaVolume = listaVolume;
    }

    public List<Long> getListaVolumeTotal() {
        return listaVolumeTotal;
    }

    public void setListaVolumeTotal(List<Long> listaVolumeTotal) {
        this.listaVolumeTotal = listaVolumeTotal;
    }

    public List<Long> getListaVolumeDisponivel() {
        return listaVolumeDisponivel;
    }

    public void setListaVolumeDisponivel(List<Long> listaVolumeDisponivel) {
        this.listaVolumeDisponivel = listaVolumeDisponivel;
    }

    public Double getVolumeDisponivel() {
        return volumeDisponivel;
    }

    public void setVolumeDisponivel(Double volumeDisponivel) {
        this.volumeDisponivel = volumeDisponivel;
    }

    public List<Double> getListaUsoVolume() {
        return listaUsoVolume;
    }

    public void setListaUsoVolume(List<Double> listaUsoVolume) {
        this.listaUsoVolume = listaUsoVolume;
    }

    public Integer getQuantidadeDeDisco() {
        return quantidadeDeDisco;
    }

    public void setQuantidadeDeDisco(Integer quantidadeDeDisco) {
        this.quantidadeDeDisco = quantidadeDeDisco;
    }
}
