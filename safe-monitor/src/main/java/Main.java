import com.github.britooo.looca.api.core.Looca;
import componentes.*;
import entidades.HistoricoUsuarios;
import entidades.Maquina;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.*;

public class Main {
    public static void main(String[] args) {


        Scanner leitor = new Scanner(System.in);
        Scanner leitorNum = new Scanner(System.in);
        Sistema sistema = new Sistema();
        Integer opcao = null;
        Query query = new Query();
        Processador cpu = new Processador();
        Memoria ram = new Memoria();
        Disco disco = new Disco();
        Janela janela = new Janela();
        Rede rede = new Rede();
        Monitoramento monitoramento = new Monitoramento();
        Dispositivo dispositivo = new Dispositivo();


        System.out.println("Seja bem-vindo(a)!");

        String email = "";
        String senha = "";

        Boolean respostalogin = false;

        do{
            System.out.println("Digite seu email: ");
            email = leitor.nextLine();

            System.out.println("Digite sua senha: ");
            senha = leitor.nextLine();

            System.out.println(monitoramento.login(email, senha));

            respostalogin = monitoramento.procurarUsuario(email, senha);

        }while(!respostalogin);

        monitoramento.definirIdEmpresa();
        rede.definirInformacoesRedeAtual(rede.definirRedeAtual());



        query.conectarMaquina(rede.getHostName());


        while(query.getMaquina() == null){
            Integer idSala = null;

            if (monitoramento.verificarPermissoesUsuario()){
                System.out.println("Ops!Essa máquina não está cadastrada!");
                System.out.println("Deseja cadastrar? S ou N");
                String resposta = leitor.nextLine();

                if(resposta.equalsIgnoreCase("S")){
                    System.out.println("Selecione um número e defina uma sala para essa máquina: ");
                    query.buscarSalas(monitoramento.getIdEmpresa());
                    System.out.println(query.getSalas());
                    idSala = leitorNum.nextInt();

                    Maquina maquina = new Maquina();
                    maquina.definirInfoMaquina();
                    maquina.definirInfoMaquinaHardware(
                            sistema.getSistemaOperacional(),
                            sistema.getFabricante(),
                            sistema.getArquitetura(),
                            rede.getEndereco_ipv4().get(0),
                            rede.getEnderecoMac(),
                            rede.getHostName()
                    );

                    monitoramento.definirInformacoesComponentes();
                    query.inserirDaodosMaquina(maquina, monitoramento.getIdEmpresa(), idSala);
                    query.inserirComponentes(monitoramento.getCpu(), monitoramento.getRam(), monitoramento.getDisco());
                    query.conectarMaquina(rede.getHostName());

                }else{
                    System.out.println("Ok, até mais!");
                    System.exit(0);
                }

            }else{
                System.out.println("Ops!Essa máquina não está cadastrada!");
                System.out.println("Por favor, reporte ao responsável pelo monitoramento dela!");
                System.exit(0);
            }
        }


        query.inserirDadosHistoricoUsuario(monitoramento.getUsuarioLogado().getIdUsuario());


        Timer timer = new Timer();
        TimerTask monitoramentoTempoReal = new TimerTask() {
            @Override
            public void run() {

                //USO CPU
                query.definirTipoComponente("Processador");
                query.definirComponente();
                query.definirTipoDados("Uso");
                cpu.definirUso();
                query.inserirDadosCaptura(cpu.getUso());


              //USO MEMORIA
                query.definirTipoComponente("Ram");
                query.definirComponente();
                query.definirTipoDados("Uso");
                ram.definirUso();
                query.inserirDadosCaptura(ram.converterParaGigas(ram.getUso()));


                //USO DISCO
                query.definirTipoComponente("Disco");
                query.definirComponente();
                query.definirTipoDados("Uso");
                disco.definirTotal();
                disco.definirUso();
                query.inserirDadosCaptura(disco.converterParaGigas(disco.getUso()));


                //JANELA
                monitoramento.popularListaJanela();
                query.buscarJanelas();

                for (int i = 0; i < monitoramento.getJanelasAbertas().size(); i++) {

                    boolean encontrado = false;
                    for (int j = 0; j < query.getJanelas().size(); j++) {

                        if (monitoramento.getJanelasAbertas().get(i).getComando().equals(query.getJanelas().get(j).getComando())) {
                            encontrado = true;
                            break;
                        }
                    }

                    if (!encontrado) {
                        query.inserirDadosJanela(monitoramento.getJanelasAbertas().get(i));
                    }
                }

                for (Janela j : query.getJanelas()) {
                    if (j.getMatar() != null) {
                        monitoramento.fecharJanelaAtravesDoPid(j.getComando());
                        query.alterarStatusJanelaFechada(j);
                    }
                }

                System.out.println("SUA MÁQUINA ESTÁ SENDO MONITORADA");

            }
        };

        timer.schedule(monitoramentoTempoReal, 3000, 1000);



        if (monitoramento.verificarPermissoesUsuario()){
            System.out.println("Bem - vindo(a) administrador!");

            String menuAdmin = String.format(
                    """
                    *---------------------------*
                    |          Menu             |
                    *---------------------------*
                    | 1 - Histórico usuários    |
                    | 2 - Janelas               |
                    | 3 - Infomações da máquina |
                    | 4 - Sair                  |
                    *---------------------------*"""
            );


            do{
                System.out.println(menuAdmin);
                opcao = leitorNum.nextInt();

                switch(opcao){

                    case 1:
                        query.buscarHistoricoUsuarios();

                        List<HistoricoUsuarios> usuarios = query.getHistoricoUsuarios();
                        for (HistoricoUsuarios u: usuarios) {
                            System.out.println(u);
                        }

                        break;

                    case 2:
                        System.out.println(monitoramento.getJanelasAbertas());

                    case 3:
                        System.out.println(query.getMaquina());
                        break;

                    case 7:
                        System.exit(0);
                        break;
                }

            }while (true);


        }else{
            System.out.println("ESTÁ MÁQUINA ESTÁ SENDO MONITORADA...");
        }
    }
}
