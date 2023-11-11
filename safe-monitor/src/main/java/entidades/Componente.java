package entidades;

import com.github.britooo.looca.api.core.Looca;

public abstract class Componente {

    private Integer idComponente;
    private String nome;
    protected Looca looca;

    public Componente(Integer idComponente, String nome) {
        this.idComponente = idComponente;
        this.nome = nome;
        this.looca = new Looca();
    }

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
        return String.format("""
                %d - %s
                """, idComponente, nome);
    }
}
