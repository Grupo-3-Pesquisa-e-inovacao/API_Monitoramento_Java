import componentes.*;
import entidades.HistoricoUsuarios;
import entidades.Maquina;
import entidades.Usuario;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {


        JSONObject json = new JSONObject();
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

        // Uso nas coisas do slack
        Usuario usuarioLogado = monitoramento.getUsuarioLogado();
        String nomeUsuario = usuarioLogado.getNome();
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = agora.format(formato);
        // Fim do uso nas coisas do slack

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

                    for (int i = 0; i < query.getSalas().size(); i++) {
                        System.out.println(query.getSalas().get(i));
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

                    // Armazenando o nome do usuário que está cadastrando a máquina
                    usuarioLogado = monitoramento.getUsuarioLogado();
                    // Fim do armazenando o nome do usuário que está cadastrando a máquina

                    monitoramento.definirInformacoesComponentes();
                    query.inserirDaodosMaquina(maquina, monitoramento.getIdEmpresa(), idSala);
                    query.conectarMaquina(rede.getHostName());
                    query.inserirComponentes(monitoramento.getCpu(), monitoramento.getRam(), monitoramento.getDisco());

                    // Notificação no Slack ao cadastrar com sucesso
                    JSONObject mensagemCadastroSucesso = new JSONObject();
                    mensagemCadastroSucesso.put("text", usuarioLogado.getNome() + " cadastrou uma nova máquina para ser monitorada com sucesso em " + dataFormatada + " \uD83D\uDE09");
                    Slack.enviarMensagem(mensagemCadastroSucesso);
                    // Fim notificação no Slack ao cadastrar com sucesso
                    query.definirTipoComponente("Processador");
                    query.definirComponente();
                    query.inserirTiposDeDados("Uso CPU", 1);

                    query.definirTipoComponente("Ram");
                    query.definirComponente();
                    query.inserirTiposDeDados("Uso RAM", 2);

                    query.definirTipoComponente("Disco");
                    query.definirComponente();
                    query.inserirTiposDeDados("Uso DISCO", 3);


                }else{
                    // Notificação no Slack se a resposta for "N"
                    JSONObject mensagemCadastroFalha = new JSONObject();
                    mensagemCadastroFalha.put("text", usuarioLogado.getNome() + " não conseguiu cadastrar uma máquina com sucesso em " + dataFormatada);
                    Slack.enviarMensagem(mensagemCadastroFalha);
                    // Fim do notificação no Slack se a resposta for "N"

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
                query.definirTipoDados("Uso CPU");
                cpu.definirUso();
                double usoCpu = (cpu.getUso());
                query.inserirDadosCaptura(cpu.getUso());


                // Verifica se o uso da CPU ultrapassa o limite
                if (usoCpu > LIMITE_CPU) {
                    try {
                        enviarAlertaSlack("CPU", "\uD83D\uDEA8 Uso da CPU ultrapassou o limite \uD83D\uDEA8");
                    } catch (IOException e) {
                        System.out.println("Não consegui enviar o alerta para o Slack");
                    }
                } else if (usoCpu > LIMITE_CPU * 0.9) {
                    try {
                        enviarAlertaSlack("CPU", "⚠ Uso da CPU está chegando perto de exceder! ⚠");
                    } catch (IOException e) {
                        System.out.println("Não consegui enviar o alerta para o Slack");
                    }
                }


              //USO MEMORIA
                query.definirTipoComponente("Ram");
                query.definirComponente();
                query.definirTipoDados("Uso RAM");
                ram.definirUso();
                Double totalMemoria = ram.getTotal();
                double usoMemoria = ram.converterParaGigas(ram.getUso());
                query.inserirDadosCaptura(usoMemoria);
                //query.inserirDadosCaptura(ram.converterParaGigas(ram.getUso()));

                // Verifica se o uso de memória ultrapassa o limite
                if (usoMemoria > LIMITE_MEMORIA) {
                    try {
                        enviarAlertaSlack("Memória", "\uD83D\uDEA8 Uso de memória ultrapassou o limite \uD83D\uDEA8");
                    } catch (IOException e) {
                        System.out.println("Não consegui enviar o alerta para o Slack");
                    }
                }
                double margemErro = 0.0001;
                if (usoMemoria > LIMITE_MEMORIA * (1 - margemErro)) {
                    try {
                        enviarAlertaSlack("Memória", "⚠ Uso de memória está chegando perto de exceder! ⚠");
                    } catch (IOException e) {
                        System.out.println("Não consegui enviar o alerta para o Slack");
                    }
                }


                //USO DISCO
                query.definirTipoComponente("Disco");
                query.definirComponente();
                query.definirTipoDados("Uso DISCO");
                disco.definirTotal();
                disco.definirUso();
                double usoDisco = disco.converterParaGigas(disco.getUso());
                query.inserirDadosCaptura(usoDisco);
                //query.inserirDadosCaptura(disco.converterParaGigas(disco.getUso()));

                // Verifica se o uso do disco ultrapassa o limite
                if (usoDisco > LIMITE_DISCO) {
                    try {
                        enviarAlertaSlack("Disco", "\uD83D\uDEA8 Uso do disco ultrapassou o limite \uD83D\uDEA8");
                    } catch (IOException e) {
                        System.out.println("Não consegui enviar o alerta para o Slack");
                    }
                }

                 if (usoDisco > LIMITE_DISCO * (1 - margemErro)) {
                    try {
                        enviarAlertaSlack("Disco", "⚠ Uso do disco está chegando perto de exceder! ⚠");
                    } catch (IOException e) {
                        System.out.println("Não consegui enviar o alerta para o Slack");
                    }
                }


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




                System.out.println("SUA MÁQUINA ESTÁ SENDO MONITORADA");

            }
        };

        timer.schedule(monitoramentoTempoReal, 0, 5 * 60 * 1000);



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
                        break;

                    case 3:
                        System.out.println(query.getMaquina());
                        break;

                    case 4:
                        System.out.println("Saindo da aplicação...");

                        System.exit(0);
                        log.fecharLog();
                        break;
                }

            }while (true);


        }else{
            System.out.println("ESTA MÁQUINA ESTÁ SENDO MONITORADA...");
        }


    }
    private static void enviarAlertaSlack(String hardware, String mensagem) throws IOException{
        // Monta a mensagem indicando qual hardware está excedendo o limite
        String mensagemAlerta = "[" + hardware + "] " + mensagem;

        // Cria o objeto JSON para enviar a mensagem ao Slack
        JSONObject mensagemAlertaJson = new JSONObject();
        mensagemAlertaJson.put("text", mensagemAlerta);


        // Envia a mensagem ao Slack
        try {
            Slack.enviarMensagem(mensagemAlertaJson);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
