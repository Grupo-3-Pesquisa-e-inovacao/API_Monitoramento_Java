package entidades;

public class Dado {

    private Integer id;

    private String nome;

    public Dado() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    public String toString() {
        return "Dado{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
