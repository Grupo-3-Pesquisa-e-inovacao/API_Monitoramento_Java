package entidades;

public class Componente {

    private Integer idComponente;
    private String nome;

    public Componente() {}

    public Integer getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(Integer idComponente) {
        this.idComponente = idComponente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return String.format(" +----- %s -----+ ", nome);
    }
}
