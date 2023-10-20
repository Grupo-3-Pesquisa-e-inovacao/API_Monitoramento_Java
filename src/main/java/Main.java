import com.github.britooo.looca.api.core.Looca;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner leitor = new Scanner(System.in);
        Captura var = new Captura();
        Looca looca = new Looca();
        Query query = new Query();
        Captura captura = new Captura();

        Login login = new Login();

        System.out.println("Seja bem-vindo(a)!");

        String email = "";
        String senha = "";

        Boolean respostalogin = false;

        while(!respostalogin) {
            System.out.println("Digite seu email: ");
            email = leitor.nextLine();

            System.out.println("Digite sua senha: ");
            senha = leitor.nextLine();

            System.out.println(login.login(email, senha));

            respostalogin = login.procurarUsuario(email, senha);

        }

        System.out.println("MONITORANDO COMPONENTES...");
        query.conectarMaquina(1);

        //USO CPU
        query.conectarComponente(1);
        query.inserirDados("Uso",captura.getCpuEmUso());
        query.mostrarLogCaptura("Uso");

        System.out.println(query.getComponente());
        System.out.println(query.getLogCaptura());

        //REDE
        query.conectarComponente(2);
        query.inserirDados("Pacotes recebidos",captura.getPacotesRecebidos().doubleValue());
        query.mostrarLogCaptura("Pacotes recebidos");

        System.out.println(query.getComponente());
        System.out.println(query.getLogCaptura());

        DadoFormatado dado = new DadoFormatado();


        //RAM
        query.conectarComponente(3);
        query.inserirDados("Uso", looca.getMemoria().getEmUso().doubleValue());
        query.mostrarLogCaptura("Uso");

        System.out.println(query.getComponente());
        System.out.println(query.getLogCaptura());


        //DISCO
        query.conectarComponente(4);
        Double disco = captura.getDiscoVolumeTotal().doubleValue() - captura.getDiscoVolumeDisponivel().doubleValue();
        query.inserirDados("Uso", query.converterParaGigas(disco));
        query.mostrarLogCaptura("Uso");

        System.out.println(query.getComponente());
        System.out.println(query.getLogCaptura());


        System.out.println("Dados formatados: " + " \n");
        System.out.println(dado.porcentagemVolumeDisponivel + "%" + " \n");

        System.out.println(dado.somaUsoMemoriaProcesso + "%" + " \n");

        System.out.println(var.getCpuEmUso().shortValue() + "%" + " \n");

    }
}
