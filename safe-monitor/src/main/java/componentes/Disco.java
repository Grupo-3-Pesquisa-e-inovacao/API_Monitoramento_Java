package componentes;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Volume;

import java.util.ArrayList;
import java.util.List;

public class Disco {

    private Looca looca;
    private List<com.github.britooo.looca.api.group.discos.Disco> listDisco;
    private List<Volume> listaVolume;
    private List<Long> listaVolumeTotal;
    private List<Long> listaVolumeDisponivel;

    private Double volumeTotal;

    private Double volumeDisponivel;

    private Double usoVolume;

    private List<Double> listaUsoVolume;

    private Integer quantidadeDeDisco;
    private String nome;
    private String modelo;


    public Disco() {
        this.looca = new Looca();
        this.listDisco = looca.getGrupoDeDiscos().getDiscos();
        this.listaVolume = looca.getGrupoDeDiscos().getVolumes();
        this.volumeTotal = looca.getGrupoDeDiscos().getVolumes().get(0).getTotal().doubleValue();
        this.volumeDisponivel = looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel().doubleValue();
        this.quantidadeDeDisco = looca.getGrupoDeDiscos().getQuantidadeDeDiscos();
        this.listaVolumeTotal = new ArrayList<>();
        this.listaVolumeDisponivel = new ArrayList<>();
        this.listaUsoVolume = new ArrayList<>();
        this.usoVolume = volumeTotal - volumeDisponivel;
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

    public void nomeDiscoPorPosicao(Integer posicaoDisco){
        nome = looca.getGrupoDeDiscos().getDiscos().get(posicaoDisco).getNome();
    }

    public void modeloDiscoPorPosicao(Integer posicaoDisco){
        modelo = looca.getGrupoDeDiscos().getDiscos().get(posicaoDisco).getModelo();
    }

    public List<com.github.britooo.looca.api.group.discos.Disco> getListDisco() {
        return listDisco;
    }

    public Double getUsoVolume() {
        return usoVolume;
    }

    public void setUsoVolume(Double usoVolume) {
        this.usoVolume = usoVolume;
    }

    public List<Double> getListaUsoVolume() {
        return listaUsoVolume;
    }

    public void setListaUsoVolume(List<Double> listaUsoVolume) {
        this.listaUsoVolume = listaUsoVolume;
    }

    public void setListDisco(List<com.github.britooo.looca.api.group.discos.Disco> listDisco) {
        this.listDisco = listDisco;
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

    public Double getVolumeTotal() {
        return volumeTotal;
    }

    public void setVolumeTotal(Double volumeTotal) {
        this.volumeTotal = volumeTotal;
    }

    public Double getVolumeDisponivel() {
        return volumeDisponivel;
    }

    public void setVolumeDisponivel(Double volumeDisponivel) {
        this.volumeDisponivel = volumeDisponivel;
    }

    public Integer getQuantidadeDeDisco() {
        return quantidadeDeDisco;
    }

    public void setQuantidadeDeDisco(Integer quantidadeDeDisco) {
        this.quantidadeDeDisco = quantidadeDeDisco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "DiscoComponente{" +
                "looca=" + looca +
                ", listDisco=" + listDisco +
                ", listaVolume=" + listaVolume +
                ", listaVolumeTotal=" + listaVolumeTotal +
                ", listaVolumeDisponivel=" + listaVolumeDisponivel +
                ", volumeTotal=" + volumeTotal +
                ", volumeDisponivel=" + volumeDisponivel +
                ", quantidadeDeDisco=" + quantidadeDeDisco +
                ", nome='" + nome + '\'' +
                ", modelo='" + modelo + '\'' +
                '}';
    }
}
