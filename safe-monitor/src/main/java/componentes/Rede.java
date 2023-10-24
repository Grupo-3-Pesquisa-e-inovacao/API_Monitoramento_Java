package componentes;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.RedeInterface;

import java.util.List;

public class Rede {

    private Looca looca;

    private List<RedeInterface> redes;
    private String nome;
    private List<String> endereco_ipv4;
    private List<String> endereco_ipv6;
    private String enderecoMac;
    private Double pacotesRecebidos;
    private Double pacotesEnviados;


    public Rede() {
        this.looca = new Looca();
        this.redes = looca.getRede().getGrupoDeInterfaces().getInterfaces();
    }

    public Integer definirRedeAtual() {

        Integer posicaoRede = null;

        for (int i = 0; i < redes.size(); i++) {
            if (redes.get(i).getBytesRecebidos() != 0) {
                posicaoRede = i;
            }
        }

        return posicaoRede;
    }


    public void definirInformacoesRedeAtual(Integer redeAtual){
        nome = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(redeAtual).getNome();
        endereco_ipv4 = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(redeAtual).getEnderecoIpv4();
        endereco_ipv6 = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(redeAtual).getEnderecoIpv6();
        enderecoMac = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(redeAtual).getEnderecoMac();
        pacotesRecebidos = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(2).getPacotesRecebidos().doubleValue();
        pacotesEnviados = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(redeAtual).getPacotesEnviados().doubleValue();
    }

    public List<RedeInterface> getRedes() {
        return redes;
    }

    public void setRedes(List<RedeInterface> redes) {
        this.redes = redes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getEndereco_ipv4() {
        return endereco_ipv4;
    }

    public void setEndereco_ipv4(List<String> endereco_ipv4) {
        this.endereco_ipv4 = endereco_ipv4;
    }

    public List<String> getEndereco_ipv6() {
        return endereco_ipv6;
    }

    public void setEndereco_ipv6(List<String> endereco_ipv6) {
        this.endereco_ipv6 = endereco_ipv6;
    }

    public String getEnderecoMac() {
        return enderecoMac;
    }

    public void setEnderecoMac(String enderecoMac) {
        this.enderecoMac = enderecoMac;
    }

    public Double getPacotesRecebidos() {
        return pacotesRecebidos;
    }

    public void setPacotesRecebidos(Double pacotesRecebidos) {
        this.pacotesRecebidos = pacotesRecebidos;
    }

    public Double getPacotesEnviados() {
        return pacotesEnviados;
    }

    public void setPacotesEnviados(Double pacotesEnviados) {
        this.pacotesEnviados = pacotesEnviados;
    }

    @Override
    public String toString() {
        return "Rede{" +
                ", nome='" + nome + '\'' +
                ", endereco_ipv4=" + endereco_ipv4 +
                ", endereco_ipv6=" + endereco_ipv6 +
                ", enderecoMac='" + enderecoMac + '\'' +
                ", pacotesRecebidos=" + pacotesRecebidos +
                ", pacotesEnviados=" + pacotesEnviados +
                '}';
    }
}
