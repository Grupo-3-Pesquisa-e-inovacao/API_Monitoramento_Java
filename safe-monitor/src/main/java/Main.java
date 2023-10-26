import componentes.*;
import entidades.Componente;
import entidades.HistoricoUsuarios;
import org.springframework.jdbc.object.SqlQuery;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {


        Scanner leitor = new Scanner(System.in);
        Integer opcao = null;

        Query query = new Query();
        Processador cpu = new Processador();
        Memoria memoria = new Memoria();
        Disco disco = new Disco();
        Processos processos = new Processos();
        Janela janelas = new Janela();
        Rede rede = new Rede();
        Login login = new Login();
        HistoricoUsuarios historicoUsuarios = new HistoricoUsuarios();

        Integer idMaquina = 1;

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

        query.conectarMaquina(idMaquina);
        query.inserirDadosHistoricoUsuario(login.getUsuarioLogado().getIdUsuario());


        Timer timer = new Timer();
        TimerTask monitoramentoTempoReal = new TimerTask() {
            @Override
            public void run() {

                //USO CPU
                query.definirComponente("Processador");
                query.definirTipoDados("Uso CPU");
                query.inserirDadosCaptura(cpu.getUso());

                //REDE
                query.definirComponente("Rede");
                rede.definirInformacoesRedeAtual(rede.definirRedeAtual());
                query.definirTipoDados("Pacotes Enviados");
                query.inserirDadosCaptura(rede.getPacotesEnviados().intValue() / 1000);
                query.definirTipoDados("Pacotes Recibidos");
                query.inserirDadosCaptura(rede.getPacotesRecebidos().intValue() / 100);

                //USO MEMORIA
                query.definirComponente("Ram");
                query.definirTipoDados("Uso Ram");
                query.inserirDadosCaptura(query.converterParaGigas(memoria.getUso()));

                //USO DISCO
                query.definirComponente("Disco");
                query.definirTipoDados("Uso disco");

                if(disco.verificarQuantidadeDisco()){
                    for (int i = 0; i < disco.getListDisco().size(); i++) {
                        query.inserirDadosCaptura(query.converterParaGigas(disco.getListaUsoVolume().get(i)));
                    }
                }else{
                    query.inserirDadosCaptura(query.converterParaGigas(disco.getUsoVolume()));
                }

                //JANELA
                janelas.popularListaTitulo();
                janelas.popularListaCaminho();
                janelas.popularListaPid();
                for (int i = 0; i < janelas.getTitulos().size(); i++) {
                    query.inserirDadosJanela(janelas.getPids().get(i),
                            janelas.getTitulos().get(i),
                            janelas.getComandos().get(i));
                }

                //PROCESSOS
                processos.popularListaNome();
                processos.popularListaUsoBytesProcesso();
                processos.popularListaUsoCpuProcesso();
                processos.popularListaPid();
                for (int i = 0; i < processos.getPids().size(); i++) {

                    query.inserirDadosProcesso(
                            processos.getPids().get(i),
                            processos.getNome().get(i),
                            processos.getUsoCPU().get(i),
                            query.converterParaGigas(processos.getBytesUtilizados().get(i)));

                }
            }
        };

        timer.schedule(monitoramentoTempoReal, 3000, 5000);


        if (login.verificarPermissoesUsuario()){
            System.out.println("Bem - vindo(a) administrador!");

            String menuAdmin = String.format(
                    """
                    *--------------------------*
                    |          Menu            |
                    *--------------------------*
                    | 1 - Histórico usuários   |
                    | 2 - Log Componentes      |
                    | 3 - Portas USB           |
                    | 4 - Sair                 |
                    *--------------------------*"""
            );


            do{
                System.out.println(menuAdmin);
                opcao = leitor.nextInt();

                switch(opcao){

                    case 1:
                        query.buscarHistoricoUsuarios();
                        System.out.println(query.getHistoricoUsuarios());
                        break;

                    case 2:
                        Integer idComponente = null;
                        query.buscarComponentes();

                        System.out.println(
                                """
                                 *--------------------------------------------*
                                 |          Selecione um componente           |
                                 *--------------------------------------------* 
                                 """

                        );
                        System.out.println(query.getComponentes());
                        idComponente = leitor.nextInt();


                        query.buscarLogCaptura(idComponente);
                        System.out.println(query.getLogCaptura());
                        break;

                    case 3:
                        Dispositivo dispositivo = new Dispositivo();

                        dispositivo.cadastrarDispositivos();

                        break;

                }

            }while (opcao != 4);


        }else{
            System.out.println("ESTÁ MÁQUINA ESTÁ SENDO MONITORADA...");
        }
    }
}
