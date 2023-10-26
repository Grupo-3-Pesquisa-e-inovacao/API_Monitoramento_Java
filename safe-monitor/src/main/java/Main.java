import componentes.*;
import entidades.CapturaDados;
import entidades.Componente;
import entidades.HistoricoUsuarios;

import java.util.List;
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
        Processo processos = new Processo();
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
        query.inserirDadosMaquina();
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
                    *---------------------------*
                    |          Menu             |
                    *---------------------------*
                    | 1 - Histórico usuários    |
                    | 2 - Registros componentes |
                    | 3 - Processos             |
                    | 4 - Janelas               |
                    | 5 - Infomações da máquina |
                    | 6 - Dispositivos          |
                    | 7 - Sair                  |
                    *---------------------------*"""
            );


            do{
                System.out.println(menuAdmin);
                opcao = leitor.nextInt();

                switch(opcao){

                    case 1:
                        query.buscarHistoricoUsuarios();

                        List<HistoricoUsuarios> usuarios = query.getHistoricoUsuarios();
                        for (HistoricoUsuarios u: usuarios) {
                            System.out.println(u);
                        }

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

                        List<Componente> componentes = query.getComponentes();
                        for (Componente cp: componentes) {
                            System.out.println(cp);
                        }
                        idComponente = leitor.nextInt();


                        query.buscarLogCaptura(idComponente);

                        List<CapturaDados> capturas = query.getLogCaptura();
                        for (CapturaDados c : capturas) {
                            System.out.println(c);
                        }
                        break;

                    case 3:
                        for (int i = 0; i < processos.getNome().size(); i++) {

                            if(processos.getUsoCPU().get(i) > 0.05){
                                System.out.println(String.format("""
                                    PID: %d
                                    Nome: %s
                                    Uso CPU: %.2f
                                    """, processos.getPids().get(i), processos.getNome().get(i), processos.getUsoCPU().get(i)));
                            }
                        }

                        break;

                    case 4:
                        for (int i = 0; i < janelas.getTitulos().size(); i++) {

                            System.out.println(String.format("""
                                    PID: %d
                                    Título: %s
                                    Caminho: %s
                                    """, janelas.getPids().get(i), janelas.getTitulos().get(i), janelas.getComandos().get(i)));

                        }
                        break;

                    case 5:
                        System.out.println(query.getMaquina());

                }

            }while (opcao != 7);


        }else{
            System.out.println("ESTÁ MÁQUINA ESTÁ SENDO MONITORADA...");
        }
    }
}
