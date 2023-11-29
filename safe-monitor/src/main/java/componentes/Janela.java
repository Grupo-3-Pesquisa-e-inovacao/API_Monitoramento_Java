package componentes;

import com.github.britooo.looca.api.core.Looca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Janela {

    private Looca looca;
    private Integer pid;
    private String titulo;
    private String comando;
    private String stt;
    private Integer matar;



    public Janela() {
        this.looca = new Looca();
    }

    public void definirPID(Integer index){
        pid = looca.getGrupoDeJanelas().getJanelas().get(index).getPid().intValue();
    }
    public void definirTtiulo(Integer index){
        titulo = looca.getGrupoDeJanelas().getJanelas().get(index).getTitulo();
    }
    public void definirComando(Integer index){
        comando = looca.getGrupoDeJanelas().getJanelas().get(index).getComando();
    }


    public void definirStatus(Integer index){
        stt = "Aberta";
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public Integer getMatar() {
        return matar;
    }

    public void setMatar(Integer matar) {
        this.matar = matar;
    }


    @Override
    public String toString() {
        return String.format("""
               PID: %d
               Titulo: %s
               Comando: %s
               Statu: %s
               Matar: %s
               """, pid,titulo, comando, stt, matar);


    }
}
