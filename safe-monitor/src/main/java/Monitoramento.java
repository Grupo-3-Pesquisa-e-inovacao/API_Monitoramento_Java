import com.github.britooo.looca.api.core.Looca;
import componentes.*;
import entidades.Maquina;
import entidades.Usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Monitoramento {

    private Integer idEmpresa;

    private Disco disco;
    private Memoria ram;
    private Processador cpu;


    private Query queryBD;

    private Looca looca;

    private List<String> tituloJanelasAbertas;
    private List<Janela> janelasAbertas;

    private List<Janela> janelaFechada;
    private List<String> tituloJanelasFechadas;

    private Maquina maquina;

    private Usuario usuarioLogado;



    public Monitoramento() {
        this.queryBD = new Query();
        this.looca = new Looca();
        this.janelasAbertas = new ArrayList<>();
        this.maquina = new Maquina();
        this.disco = new Disco();
        this.ram = new Memoria();
        this.cpu = new Processador();
        this.tituloJanelasAbertas = new ArrayList<>();
        this.janelaFechada = new ArrayList<>();
    }

    public Boolean procurarUsuario(String email, String senha) {

        Boolean usuarioEncontrado = false;

        queryBD.buscarUsuariosBanco();

        for (int i = 0; i < queryBD.getUsuarios().size(); i++) {

            if (email.equals(queryBD.getUsuarios().get(i).getEmail()) &&
                    senha.equals(queryBD.getUsuarios().get(i).getSenha())) {
                usuarioLogado = queryBD.getUsuarios().get(i);

                usuarioEncontrado = true;
            }
        }
        return usuarioEncontrado;
    }

    public void definirIdEmpresa() {
        idEmpresa = usuarioLogado.getFkEmpresa();
    }

    public String login(String email, String senha) {

        String mensagem = "";

        if (procurarUsuario(email, senha)) {
            mensagem = "Parábens! Você logou!";


        } else {
            mensagem = "Email ou senha inválidos!";
        }

        return mensagem;
    }

    public Boolean verificarPermissoesUsuario() {

        Boolean usuarioAdmin = true;

        queryBD.buscarUsuariosBanco();

        if (usuarioLogado.getCadastrar() == 0 &&
                usuarioLogado.getDeletar() == 0 &&
                usuarioLogado.getAlterar() == 0) {
            usuarioAdmin = false;

        }
        return usuarioAdmin;
    }


    public void popularListaJanela() {
        for (int i = 0; i < looca.getGrupoDeJanelas().getJanelasVisiveis().size(); i++) {
            Integer filtro = looca.getGrupoDeJanelas().getJanelasVisiveis().get(i).getComando().indexOf("C:\\Windows");

            if (filtro == -1) {
                Janela j = new Janela();
                j.definirPID(i);
                j.definirTtiulo(i);
                j.definirComando(i);
                j.definirStatus(i);
                janelasAbertas.add(j);

            }

        }
    }

    public void definirInformacoesComponentes() {
        cpu.definirNome();
        cpu.definirModelo();
        cpu.definirTotal();

        ram.definirTotal();
        ram.definirUso();
        ram.definirDisponivel();

        disco.definirNome();
        disco.definirModelo();
        disco.definirTotal();
    }


    public void fecharJanelaAtravesDoPid(Integer pid, String so) {


        try {
            ProcessBuilder processBuilder = null;
            String os = System.getProperty("os.name").toLowerCase();

            if (so.equals("Windows")) {
                processBuilder = new ProcessBuilder("taskkill", "/F", "/PID" + pid);

            } else if (so.equals("MacOS") || so.equals("Linux")) {
                processBuilder = new ProcessBuilder("kill " + pid);
            }

            Process process = processBuilder.start();
            process.waitFor();

            System.out.println("O processo com caminho " + pid + " foi encerrado.");


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public List<String> getTituloJanelasAbertas() {

        for (Janela janela : janelasAbertas) {

            tituloJanelasAbertas.add(janela.getTitulo());
        }

        return tituloJanelasAbertas;
    }

//    public void verificarAppNovo(){
//        getTituloJanelasAbertas();
//
//        Integer tamanhoAntigo = getTituloJanelasAbertas().size();
//
//        for (int i = 0; i == tamanhoAntigo ; i++) {
//            Integer tamanhoAtual = getTituloJanelasAbertas().size();
//
//            if (tamanhoAtual > tamanhoAntigo) {
//                String novaJanela = getTituloJanelasAbertas().get(tamanhoAtual - 1);
//                System.out.println("Nova fruta adicionada: " + novaJanela);
//            }
//        }
//    }

    public List<Janela> getJanelasAbertas() {
        return janelasAbertas;
    }

    public void setJanelasAbertas(List<Janela> janelasAbertas) {
        this.janelasAbertas = janelasAbertas;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Disco getDisco() {
        return disco;
    }

    public void setDisco(Disco disco) {
        this.disco = disco;
    }

    public Memoria getRam() {
        return ram;
    }


    public void setRam(Memoria ram) {
        this.ram = ram;
    }

    public Processador getCpu() {
        return cpu;
    }

    public void setCpu(Processador cpu) {
        this.cpu = cpu;
    }

    public void setTituloJanelasAbertas(List<String> tituloJanelasAbertas) {
        this.tituloJanelasAbertas = tituloJanelasAbertas;
    }

    public List<Janela> getJanelaFechada() {
        return janelaFechada;
    }

    public void setJanelaFechada(List<Janela> janelaFechada) {
        this.janelaFechada = janelaFechada;
    }

    public List<String> getTituloJanelasFechadas() {
        return tituloJanelasFechadas;
    }

    public void setTituloJanelasFechadas(List<String> tituloJanelasFechadas) {
        this.tituloJanelasFechadas = tituloJanelasFechadas;
    }
}
