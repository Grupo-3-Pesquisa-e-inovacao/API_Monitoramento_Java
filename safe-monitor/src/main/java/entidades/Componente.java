package entidades;

import com.github.britooo.looca.api.core.Looca;

abstract public class Componente {

    protected Integer idComponente;

    protected Looca looca;
    protected String nome;

    protected String modelo;

    protected Double uso;

    protected Double total;

    public Componente() {
        this.looca = new Looca();
    }

    public abstract Double converterParaGigas(Double valor);

    public abstract void definirNome();
    public abstract void definirModelo();
    public abstract void definirUso();
    public abstract void definirTotal();

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Looca getLooca() {
        return looca;
    }

    public void setLooca(Looca looca) {
        this.looca = looca;
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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getUso() {
        return uso;
    }

    public void setUso(Double uso) {
        this.uso = uso;
    }

    @Override
    public String toString() {
        return String.format("""
                %d - %s
                """, idComponente, nome);
    }
}
