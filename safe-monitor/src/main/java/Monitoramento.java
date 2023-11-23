import com.github.britooo.looca.api.core.Looca;
import componentes.*;
import entidades.Maquina;
import entidades.Usuario;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Monitoramento {

    private Integer idEmpresa;

    private Disco disco;
    private Memoria ram;
    private Processador cpu;


    private Query queryBD;

    private Looca looca;

    private List<Janela> janelasAbertas;

    private Maquina maquina;

    private Usuario usuarioLogado;

    public Monitoramento() {
        this.queryBD = new Query();
        this.looca = new Looca();
        this.janelasAbertas = new ArrayList<>();
        this.maquina = new Maquina();
    }

    public Boolean procurarUsuario(String email, String senha){

        Boolean usuarioEncontrado = false;

        queryBD.buscarUsuariosBanco();

        for (int i = 0; i < queryBD.getUsuarios().size(); i++) {

            if(email.equals(queryBD.getUsuarios().get(i).getEmail()) &&
               senha.equals(queryBD.getUsuarios().get(i).getSenha())){
                usuarioLogado = queryBD.getUsuarios().get(i);

                usuarioEncontrado = true;
            }
        }
        return usuarioEncontrado;
    }

    public void definirIdEmpresa(){
        idEmpresa = usuarioLogado.getFkEmpresa();
    }

    public String login(String email, String senha){

        String mensagem = "";

        if (procurarUsuario(email,senha)){
            mensagem = "Parábens! Você logou!";


        }else {
            mensagem = "Email ou senha inválidos!";
        }

        return mensagem;
    }

    public Boolean  verificarPermissoesUsuario(){

        Boolean usuarioAdmin = true;

        queryBD.buscarUsuariosBanco();

        if(usuarioLogado.getCadastrar() == 0 &&
           usuarioLogado.getDeletar() ==0 &&
           usuarioLogado.getAlterar() == 0){
                usuarioAdmin = false;

        }
        return usuarioAdmin;
    }


    public void popularListaJanela(){
        for (int i = 0; i < looca.getGrupoDeJanelas().getJanelasVisiveis().size(); i++) {
            Integer filtro = looca.getGrupoDeJanelas().getJanelasVisiveis().get(i).getComando().indexOf("C:\\Windows");

            if(filtro ==  -1){
                Janela j = new Janela();
                j.definirPID(i);
                j.definirTtiulo(i);
                j.definirComando(i);
                j.definirStatus(i);

                janelasAbertas.add(j);

            }

        }
    }

    public void definirInformacoesComponentes(){
        cpu.definirNome();
        cpu.definirModelo();
        cpu.definirTotal();

        ram.definirTotal();

        disco.definirNome();
        disco.definirModelo();
        disco.definirTotal();
    }


    public void fecharJanelaAtravesDoPid(String caminho){

        try {
            ProcessBuilder processBuilder = null;
            String os = System.getProperty("os.name").toLowerCase();

            if (maquina.getSistemaOperacional().equals("Windows")) {
                processBuilder = new ProcessBuilder("taskkill", "/F", "/IM", caminho);

            } else if (maquina.getIdMaquina().equals("MacOS") || maquina.getIdMaquina().equals("Linux") ) {
                processBuilder = new ProcessBuilder("pkill", "-f", caminho);
            }

            Process process = processBuilder.start();
            process.waitFor();

            System.out.println("O processo com caminho " + caminho + " foi encerrado.");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }



    }

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
}
