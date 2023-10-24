package entidades;

public class TipoDados {
    private Integer idTipoDados;

    private String nome;

    public TipoDados() {}

    public Integer getIdTipoDados() {
        return idTipoDados;
    }

    public void setIdTipoDados(Integer idTipoDados) {
        this.idTipoDados = idTipoDados;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "TipoDados{" +
                "idTipoDados=" + idTipoDados +
                ", nome='" + nome + '\'' +
                '}';
    }
}
