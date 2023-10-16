import DadosAPI.CpuDados;
import DadosAPI.DiscoDados;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;

import java.util.Scanner;

public class TesteBD {
    public static void main(String[] args) {
        Login login = new Login();
        DiscoDados disco = new DiscoDados();
        Scanner leitor = new Scanner(System.in);

       /* System.out.println("Seja bem-vindo(a)!");

        System.out.println("Digite seu email: ");
        String email = leitor.nextLine();

        System.out.println("Digite sua senha: ");
        String senha = leitor.nextLine();

        login.buscarUsuariosBanco();
        System.out.println(login.logar(email, senha));*/

        System.out.println(disco.getBytesLeituras());
        System.out.println(disco.getTempoDeTransferencia());
        System.out.println(disco.getTamanhoAtualFila());
        System.out.println(disco.getBytesEscritas());






    }
}
