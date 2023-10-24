package componentes;

import com.github.britooo.looca.api.core.Looca;

public class Processador {

    private Looca looca;

    private  Double uso;

    private Double frequecia;

    private String nome;
    private String arquitetura;
    private Double pacotesFisicos;


    public Processador() {
        this.looca = new Looca();
        this.uso = looca.getProcessador().getUso();
        this.frequecia = looca.getProcessador().getFrequencia().doubleValue();
        this.nome = looca.getProcessador().getNome();
        this.arquitetura = looca.getProcessador().getMicroarquitetura();
        this.pacotesFisicos = looca.getProcessador().getNumeroPacotesFisicos().doubleValue();
    }

    public Double getUso() {
        return uso;
    }

    public void setUso(Double uso) {
        this.uso = uso;
    }

    public Double getFrequecia() {
        return frequecia;
    }

    public void setFrequecia(Double frequecia) {
        this.frequecia = frequecia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArquitetura() {
        return arquitetura;
    }

    public void setArquitetura(String arquitetura) {
        this.arquitetura = arquitetura;
    }

    public Double getPacotesFisicos() {
        return pacotesFisicos;
    }

    public void setPacotesFisicos(Double pacotesFisicos) {
        this.pacotesFisicos = pacotesFisicos;
    }

    @Override
    public String toString() {
        return """
                *----------* Processador *----------*
                Nome: %s
                Frequencia: %.2f
                Arquitetura: %s
                Pacotes Físicos: %.2f
                """.formatted(nome, frequecia, arquitetura, pacotesFisicos);
    }
}
