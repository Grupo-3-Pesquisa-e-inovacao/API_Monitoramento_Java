import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

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

    public void iniciarAplicacao() {
        try {
            //limparLog();
            iniciarLog();

        } catch (IOException erro) {
            erro.printStackTrace();
        }
    }

    public void encerrarAplicacao() throws IOException {
        fecharLog();
        registrarEncerramentoAplicacao();
    }

    public void iniciarLog() throws IOException {
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatterNomeArquivo = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatterDataHora = DateTimeFormatter.ofPattern("'['dd/MM/yyyy | HH:mm:ss']'");
        String nomeArquivo = now.format(formatterNomeArquivo);
        String dataHora = now.format(formatterDataHora);
        Path path = Paths.get(nomeArquivo + "-funcionamento-inovacao.txt");
        Boolean jaInicializado = Files.exists(path);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo + "-funcionamento-inovacao.txt", true))) {
            if (!jaInicializado) {
                registrarInicioAplicacao();
                registrarInformacoesMaquina();
                registrarInformacoesJanela();
            } else {
                registrarInformacoesJanela();
            }
        }
    }


    public void registrarInformacoesMaquina() throws IOException {
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatterNomeArquivo = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String nomeArquivo = now.format(formatterNomeArquivo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'['dd/MM/yyyy | HH:mm:ss']'");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo + "-funcionamento-inovacao.txt", true))) {
            writer.write("%s REGISTRANDO INFORMAÇÕES MÁQUINAS\n".formatted(now.format(formatter)));
            monitoramento.definirInformacoesComponentes();
            writer.write("%s CPU: %s\n".formatted(now.format(formatter), monitoramento.getCpu().getNome()));
            writer.write("%s RAM DISPONÍVEL: %.1f GB\n".formatted(now.format(formatter), monitoramento.getRam().getTotal() - monitoramento.getRam().getUso()));
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

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo + "-funcionamento-inovacao.txt", true))) {
            monitoramento.popularListaJanela();
            monitoramento.getTituloJanelasAbertas();
            writer.write("%s REGISTRANDO INFORMAÇÕES JANELAS\n".formatted(now.format(formatter)));
            writer.write("%s Janelas: %s\n".formatted(now.format(formatter), monitoramento.getTituloJanelasAbertas()));
           // writer.write("%s Janelas Fechadas: %s\n".formatted(now.format(formatter), monitoramento.janelasFechadas()));

        }
    }
    public void registrarInicioAplicacao() throws IOException {
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatterNomeArquivo = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String nomeArquivo = now.format(formatterNomeArquivo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'['dd/MM/yyyy | HH:mm:ss']'");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo + "-funcionamento-inovacao.txt", true))) {
            writer.write("---------------------------------- %s ----------------------------------\n".formatted(now.format(formatter)));
            writer.write("%s Iniciando a aplicação...\n".formatted(now.format(formatter)));
            writer.write("%s Aplicação iniciada\n".formatted(now.format(formatter)));
        }
    }

    public void limparLog() throws IOException {

        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatterNomeArquivo = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String nomeArquivo = now.format(formatterNomeArquivo);
        Path path = Paths.get(nomeArquivo);

        if (Files.exists(path)) {
            Files.delete(path);
        }
    }

    public void registrarEncerramentoAplicacao() throws IOException {
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatterNomeArquivo = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String nomeArquivo = now.format(formatterNomeArquivo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'['dd/MM/yyyy | HH:mm:ss']'");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo + "-funcionamento-inovacao.txt", true))) {
            writer.write("%s Aplicação encerrada\n".formatted(now.format(formatter)));
        }
    }

    public void fecharLog() throws IOException {
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatterNomeArquivo = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String nomeArquivo = now.format(formatterNomeArquivo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'['dd/MM/yyyy | HH:mm:ss']'");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo+"-funcionamento-inovacao.txt", true))) {
            writer.write("%s Encerrando a aplicação...\n".formatted(now.format(formatter)));
        }
    }
}
