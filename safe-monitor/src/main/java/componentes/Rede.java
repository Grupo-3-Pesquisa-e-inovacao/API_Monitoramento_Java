package componentes;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.RedeInterface;

import java.util.List;

public class Rede {

    private Looca looca;
    private List<RedeInterface> redes;
    private List<String> endereco_ipv4;
    private String enderecoMac;

    private String hostName;


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
        endereco_ipv4 = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(redeAtual).getEnderecoIpv4();
        enderecoMac = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(redeAtual).getEnderecoMac();
        hostName = looca.getRede().getParametros().getHostName();

    }

    public List<RedeInterface> getRedes() {
        return redes;
    }

    public void setRedes(List<RedeInterface> redes) {
        this.redes = redes;
    }

    public List<String> getEndereco_ipv4() {
        return endereco_ipv4;
    }

    public void setEndereco_ipv4(List<String> endereco_ipv4) {
        this.endereco_ipv4 = endereco_ipv4;
    }

    public String getEnderecoMac() {
        return enderecoMac;
    }

    public void setEnderecoMac(String enderecoMac) {
        this.enderecoMac = enderecoMac;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
}
