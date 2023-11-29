import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.github.britooo.looca.api.core.Looca;
import componentes.*;
import entidades.Usuario;

public class Log {
    Scanner leitor = new Scanner(System.in);
    Sistema sistema = new Sistema();
    Query query = new Query();
    Processador cpu = new Processador();
    Memoria memoria = new Memoria();
    Disco disco = new Disco();
    Janela janelas = new Janela();
    Rede rede = new Rede();
    Usuario usuario = new Usuario();
    Monitoramento monitoramento = new Monitoramento();
    Looca looca = new Looca();

    public static void main(String[] args) {
        Log log = new Log();
        try {
            log.iniciarLog();

        } catch (IOException erro) {
            erro.printStackTrace();
        }
    }

//    public void iniciarAplicacao() {
//        try {
//            //limparLog();
//            iniciarLog();
//
//        } catch (IOException erro) {
//            erro.printStackTrace();
//        }
//    }

    public void iniciarLog() throws IOException {
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatterNomeArquivo = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatterDataHora = DateTimeFormatter.ofPattern("'['dd/MM/yyyy | HH:mm:ss']'");

        String nomeArquivo = now.format(formatterNomeArquivo);
        String dataHora = now.format(formatterDataHora);

        Path diretorioLogs = Paths.get("logs");
        if (!Files.exists(diretorioLogs)) {
            Files.createDirectories(diretorioLogs);
        }

        Path path = diretorioLogs.resolve(nomeArquivo + "-funcionamento-inovacao.txt");
        Boolean jaInicializado = Files.exists(path);



        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), true))) {
            if (!jaInicializado) {
                registrarInicioAplicacao();
                registrarInformacoesMaquina();
                registrarInformacoesJanela();
            } else {
                registrarInformacoesJanela();
            }
        }
    }

    public void registrarInicioAplicacao() throws IOException {
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatterNomeArquivo = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String nomeArquivo = now.format(formatterNomeArquivo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'['dd/MM/yyyy | HH:mm:ss']'");

        Path diretorioLogs = Paths.get("logs");
        Path path = diretorioLogs.resolve(nomeArquivo + "-funcionamento-inovacao.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), true))) {
            writer.write("---------------------------------- %s ----------------------------------\n".formatted(now.format(formatter)));
            writer.write("%s Iniciando a aplicação...\n".formatted(now.format(formatter)));
            writer.write("%s Aplicação iniciada\n".formatted(now.format(formatter)));
        }
    }


    public void registrarInformacoesMaquina() throws IOException {
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatterNomeArquivo = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String nomeArquivo = now.format(formatterNomeArquivo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'['dd/MM/yyyy | HH:mm:ss']'");
        Double ramDiponivel = looca.getMemoria().getDisponivel() / 1e9;

        Path diretorioLogs = Paths.get("logs");
        Path path = diretorioLogs.resolve(nomeArquivo + "-funcionamento-inovacao.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), true))) {
            writer.write("%s REGISTRANDO INFORMAÇÕES MÁQUINAS\n".formatted(now.format(formatter)));
            monitoramento.definirInformacoesComponentes();
            writer.write("%s CPU: %s\n".formatted(now.format(formatter), monitoramento.getCpu().getNome()));
            writer.write("%s RAM TOTAL: %.1f GB\n".formatted(now.format(formatter), monitoramento.getRam().getTotal()));
            writer.write("%s RAM DISPONÍVEL: %.1f GB\n".formatted(now.format(formatter), ramDiponivel));
            writer.write("%s SO: %s\n".formatted(now.format(formatter), sistema.getSistemaOperacional()));
            writer.write("%s Arquitetura: %s bits\n".formatted(now.format(formatter), sistema.getArquitetura()));
        }
    }

    public void registrarInformacoesJanela() throws IOException {
        Query query1 = new Query();

        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatterNomeArquivo = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String nomeArquivo = now.format(formatterNomeArquivo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'['dd/MM/yyyy | HH:mm:ss']'");
        Janela listaNomes = new Janela();

        Path diretorioLogs = Paths.get("logs");
        Path path = diretorioLogs.resolve(nomeArquivo + "-funcionamento-inovacao.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), true))) {
            monitoramento.popularListaJanela();
            monitoramento.getTituloJanelasAbertas();
            writer.write("\n\n\n%s REGISTRANDO INFORMAÇÕES JANELAS\n".formatted(now.format(formatter)));
            writer.write("%s Janelas: %s\n".formatted(now.format(formatter), monitoramento.getTituloJanelasAbertas()));

            query.buscarJanelasFechada();


            for (int i = 0; i < query.getJanelasFechadas().size(); i++) {
                System.out.println(query.getJanelasFechadas().get(0));
                writer.write("%s Janelas Fechadas: %s\n".formatted(now.format(formatter), query.getJanelasFechadas().get(i).getTitulo()));
            }



//            monitoramento.getTituloJanelasAbertas();
//
//            Integer tamanhoAntigo = monitoramento.getTituloJanelasAbertas().size();
//
//            for (int i = 0; i == tamanhoAntigo ; i++) {
//                Integer tamanhoAtual = monitoramento.getTituloJanelasAbertas().size();
//
//                if (tamanhoAtual > tamanhoAntigo) {
//                    String novaJanela = monitoramento.getTituloJanelasAbertas().get(tamanhoAtual - 1);
//                    System.out.println("Nova fruta adicionada: " + novaJanela);
//                }
//            }

        }
    }

    public void fecharLog() throws IOException {
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatterNomeArquivo = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String nomeArquivo = now.format(formatterNomeArquivo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'['dd/MM/yyyy | HH:mm:ss']'");


        Path diretorioLogs = Paths.get("logs");
        Path path = diretorioLogs.resolve(nomeArquivo + "-funcionamento-inovacao.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), true))) {
            writer.write("%s Encerrando a aplicação...\n".formatted(now.format(formatter)));
            writer.write("%s Aplicação encerrada\n".formatted(now.format(formatter)));
        }
    }



}
