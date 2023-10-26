package componentes;

import com.github.britooo.looca.api.core.Looca;

public class Sistema {

    private Looca looca;

    private Long tempoAtividade;

    private String sistemaOperacional;

    private Integer arquitetura;

    private String fabricante;
    private Long inicializado;

    public Sistema() {
        this.looca = new Looca();
        this.tempoAtividade = looca.getSistema().getTempoDeAtividade();
        this.sistemaOperacional = looca.getSistema().getSistemaOperacional();
        this.arquitetura = looca.getSistema().getArquitetura();
        this.fabricante = looca.getSistema().getFabricante();
        this.inicializado = looca.getSistema().getTempoDeAtividade();
    }

    public Long getTempoAtividade() {
        return tempoAtividade;
    }

    public void setTempoAtividade(Long tempoAtividade) {
        this.tempoAtividade = tempoAtividade;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public Integer getArquitetura() {
        return arquitetura;
    }

    public void setArquitetura(Integer arquitetura) {
        this.arquitetura = arquitetura;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public Long getInicializado() {
        return inicializado;
    }

    public void setInicializado(Long inicializado) {
        this.inicializado = inicializado;
    }
}
