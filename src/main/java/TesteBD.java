
import com.github.britooo.looca.api.core.Looca;

import java.io.IOException;
import java.util.Scanner;

public class TesteBD {
    public static void main(String[] args) {
        Login login = new Login();
        Scanner leitor = new Scanner(System.in);
        Captura var = new Captura();
        Looca looca = new Looca();
       /* System.out.println("Seja bem-vindo(a)!");

        System.out.println("Digite seu email: ");
        String email = leitor.nextLine();

        System.out.println("Digite sua senha: ");
        String senha = leitor.nextLine();

        login.buscarUsuariosBanco();
        System.out.println(login.logar(email, senha));*/

       /* System.out.println(disco.getBytesLeituras());
        System.out.println(disco.getTempoDeTransferencia());
        System.out.println(disco.getTamanhoAtualFila());
        System.out.println(disco.getBytesEscritas());

        System.out.println(looca.getGrupoDeJanelas().getJanelas());*/

        //Processador
      /*  System.out.println(looca.getProcessador().getUso());


        //Memória
        System.out.println(looca.getMemoria().getEmUso());

        //Rede
        System.out.println(dados.getRedeAtual());*/

        System.out.println(looca.getGrupoDeJanelas().getJanelas());



        Scanner in = new Scanner(System.in);

        int pid = in.nextInt();
        boolean fecharJanela = true;

        if (fecharJanela) {
            try {
                Process processo = Runtime.getRuntime().exec("taskkill /F /PID " + pid);
                processo.waitFor();
                System.out.println("O processo com PID " + pid + " foi encerrado.");

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        /*System.out.println("Lista de espaço total presente no volume: ");
        System.out.println(var.getListaVolumeTotal() + " \n");

        System.out.println("Lista de espaço disponivel presente no volume: ");
        System.out.println(var.getListaVolumeDisponivel()  + " \n");

        System.out.println("Valor de espaço total presente no volume: ");
        System.out.println(var.getDiscoVolumeTotal() + " \n");

        System.out.println("Valor de espaço disponivel presente no volume: ");
        System.out.println(var.getDiscoVolumeDisponivel()  + " \n");

        System.out.println("Memoria total:");
        System.out.println(var.getMemoriaTotal()  + " \n");

        System.out.println("Memoria disponivel:");
        System.out.println(var.getMemoriaDisponivel()  + " \n");

        System.out.println("CPU em uso:");
        System.out.println(var.getCpuEmUso()  + " \n");

        System.out.println("Lista de uso de memoria processos:");
        System.out.println(var.getListaUsoMemoriaProcesso()  + " \n");

        System.out.println("Lista de uso de cpu processos:");
        System.out.println(var.getListaUsoCpuProcesso() + " \n");

        System.out.println("Lista de uso de bytes processos:");
        System.out.println(var.getListaUsoBytesProcesso() + " \n");

        System.out.println("Lista rede atual:");
        System.out.println(var.getRedeAtual() + " \n");

        System.out.println("Pacotes recebidos:");
        System.out.println(var.getPacotesRecebidos() + " \n");

        System.out.println("Pacotes enviados:");
        System.out.println(var.getPacotesEnviados() + " \n");

        System.out.println("Lista de janelas: ");
        System.out.println(var.getListaPidProcessos());
*/


    }
}
