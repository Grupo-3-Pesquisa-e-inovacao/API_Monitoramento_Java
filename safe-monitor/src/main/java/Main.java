import componentes.*;
import entidades.HistoricoUsuarios;
import entidades.Maquina;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


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
        Log log = new Log();
        List<String> janelaAntesDeRemoverDoBanco = new ArrayList<>();



        System.out.println("Seja bem-vindo(a)!");

        String email = "";
        String senha = "";

        Boolean respostalogin = false;

        log.iniciarLog();

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






        while(!query.conectarMaquinaLocal(rede.getHostName())){
            Integer idSala = null;

            if (monitoramento.verificarPermissoesUsuario()){
                System.out.println("Ops!Essa máquina não está cadastrada!");
                System.out.println("Deseja cadastrar? S ou N");
                String resposta = leitor.nextLine();

                if(resposta.equalsIgnoreCase("S")){
                    System.out.println("Selecione um número e defina uma sala para essa máquina: ");
                    query.buscarSalas(monitoramento.getIdEmpresa());

                    for (int i = 0; i < query.getSalas().size(); i++) {
                        System.out.println(query.getSalas().get(i).getId() + " - " + query.getSalas().get(i).getNome());
                    }

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

                  /*  if(!query.conectarMaquinaNuvem(rede.getHostName())){
                        query.inserirDaodosMaquinaNuvem(maquina, monitoramento.getIdEmpresa(), idSala);
                        query.conectarMaquinaNuvem(rede.getHostName());
                        query.inserirComponentesNuvem(monitoramento.getCpu(), monitoramento.getRam(), monitoramento.getDisco());

                        query.definirTipoComponente("Processador");
                        query.definirComponente();
                        query.inserirTiposDeDadosNuvem("Uso CPU", 1);

                        query.definirTipoComponente("Ram");
                        query.definirComponente();
                        query.inserirTiposDeDadosNuvem("Uso RAM", 2);

                        query.definirTipoComponente("Disco");
                        query.definirComponente();
                        query.inserirTiposDeDadosNuvem("Uso DISCO", 3);
                    }*/

                    if(!query.conectarMaquinaLocal(rede.getHostName())){
                        System.out.println("Cadastrou máquina");
                        query.inserirDaodosMaquinaLocal(maquina, monitoramento.getIdEmpresa(), idSala);

                        query.conectarMaquinaLocal(rede.getHostName());
                        System.out.println("Conectou máquina");

                        query.inserirComponentesLocal(monitoramento.getCpu(), monitoramento.getRam(), monitoramento.getDisco());
                        System.out.println("Inseriu componentes");

                        query.definirTipoComponente("Processador");
                        query.definirComponente();
                        query.inserirTiposDeDadosLocal("Uso CPU", 1);
                        System.out.println("Inseriu tipo dados CPU");

                        query.definirTipoComponente("Ram");
                        query.definirComponente();
                        query.inserirTiposDeDadosLocal("Uso RAM", 2);
                        System.out.println("Inseriu tipo dados RAM");

                        query.definirTipoComponente("Disco");
                        query.definirComponente();
                        query.inserirTiposDeDadosLocal("Uso DISCO", 3);
                        System.out.println("Inseriu tipo dados DISCO");
                    }



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


        if (query.conectarMaquinaLocal(rede.getHostName())){
            query.inserirDadosHistoricoUsuario(monitoramento.getUsuarioLogado().getIdUsuario());

            Timer timer = new Timer();
            TimerTask monitoramentoTempoReal = new TimerTask() {
                @Override
                public void run() {




                    //USO CPU
                    query.definirTipoComponente("Processador");
                    query.definirComponente();
                    query.definirTipoDados("Uso CPU");
                    cpu.definirUso();
                    query.inserirDadosCaptura(cpu.getUso());


                    //USO MEMORIA
                    query.definirTipoComponente("Ram");
                    query.definirComponente();
                    query.definirTipoDados("Uso RAM");
                    ram.definirUso();
                    query.inserirDadosCaptura(ram.converterParaGigas(ram.getUso()));


                    //USO DISCO
                    query.definirTipoComponente("Disco");
                    query.definirComponente();
                    query.definirTipoDados("Uso DISCO");
                    disco.definirTotal();
                    disco.definirUso();
                    query.inserirDadosCaptura(disco.converterParaGigas(disco.getUso()));


                    //JANELA
                    monitoramento.getJanelasAbertas().clear();

                    monitoramento.popularListaJanela();
                    query.buscarJanelas();

                    // Remover janelas que não estão mais abertas
                    for (Janela jBD : query.getJanelas()) {
                        boolean janelaEncontrada = false;

                        for (Janela j : monitoramento.getJanelasAbertas()) {
                            if (jBD.getComando().equals(j.getComando())) {
                                janelaEncontrada = true;
                                break;
                            }
                        }

                        if (!janelaEncontrada) {
                            query.removerJanelaFechada(jBD);
                        }
                    }


                    //INSERIR JANELAS NO BANCO
                    for (int i = 0; i < monitoramento.getJanelasAbertas().size(); i++) {

                        boolean encontrado = false;
                        for (int j = 0; j < query.getJanelas().size(); j++) {

                            if (monitoramento.getJanelasAbertas().get(i).getComando().equals(query.getJanelas().get(j).getComando())
                                    && monitoramento.getJanelasAbertas().get(i).getPid().equals(query.getJanelas().get(j).getPid())) {
                                encontrado = true;
                                break;
                            }
                        }

                        if (!encontrado) {
                            if(!monitoramento.getJanelasAbertas().get(i).getTitulo().isEmpty()){
                                query.inserirDadosJanela(monitoramento.getJanelasAbertas().get(i));
                            }

                        }
                    }

                    //VERIFICAR COMANDO PARA MATAR
                    for (Janela j : query.getJanelas()) {
                        if(j.getMatar() != null){
                            String nomeProcesso = monitoramento.pegarNomeProcessoPeloComando(j.getComando());
                            monitoramento.matarProcessoPorNome(nomeProcesso);

                            //Variaveis para conseguir adicionar isso ao log
                            ZonedDateTime now = ZonedDateTime.now();
                            DateTimeFormatter formatterNomeArquivo = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            String nomeArquivo = now.format(formatterNomeArquivo);
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'['dd/MM/yyyy | HH:mm:ss']'");
                            Path diretorioLogs = Paths.get("logs");
                            Path path = diretorioLogs.resolve(nomeArquivo + "-funcionamento-inovacao.txt");
                            try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), true))) {

                                writer.write("%s %s foi fechado com sucesso!\n".formatted(now.format(formatter), nomeProcesso));

                            }catch (IOException erro) {
                                erro.printStackTrace();
                            }


                        }

                    }






                }
            };

            timer.schedule(monitoramentoTempoReal, 3000, 1000);



            if (monitoramento.verificarPermissoesUsuario()){
                System.out.println("SUA MÁQUINA ESTÁ SENDO MONITORADA");
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

                        case 4:
                            System.exit(0);
                            log.fecharLog();
                            break;
                    }

                }while (true);


            }else{
                System.out.println("ESTA MÁQUINA ESTÁ SENDO MONITORADA...");
            }
        }
    }
}
