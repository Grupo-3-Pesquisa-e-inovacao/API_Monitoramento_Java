import entidades.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class Query {
    private Conexao conexao;
    private JdbcTemplate con;
    private Maquina maquina;
    private Componente componente;
    private CapturaDados camposCaptura;
    private TipoDados tipoDados;
    private List<Usuario> usuarios;
    private List<CapturaDados> logCaptura;



    public Query() {
        this.conexao = new Conexao();
        this.con = conexao.getConexaoDoBanco();
        this.maquina = new Maquina();
        this.componente = new Componente();
        this.camposCaptura = new CapturaDados();
        this.logCaptura = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.tipoDados = new TipoDados();
    }

    public void conectarMaquina(Integer idMaquina){
        maquina = con.queryForObject("SELECT * FROM maquina WHERE idMaquina = ?",
                new BeanPropertyRowMapper<>(Maquina.class), idMaquina);
    }

    public void definirComponente(String nome){
        componente = con.queryForObject("SELECT * FROM componente WHERE nome = ?",
                new BeanPropertyRowMapper<>(Componente.class),nome);
    }

    public void definirTipoDados(String nome){
        tipoDados = con.queryForObject("SELECT * FROM tipo_dados WHERE nome = ?;",
                new BeanPropertyRowMapper<>(TipoDados.class),nome);
    }

    public Double converterParaGigas(Double valor){
        return valor / (1*Math.pow(10,9));
    }


    public void inserirDadosCaptura(Double valor){
        con.update("INSERT INTO captura_dados (`valor_monitorado`, `fk_tiposDados`, `fk_maquina`, fk_componente)" +
                "VALUES (?, ?, ?, ?);", valor, tipoDados.getIdTipoDados(), maquina.getIdMaquina(), componente.getIdComponente());
    }

    public void inserirDadosCaptura(Integer valor){
        con.update("INSERT INTO captura_dados (`valor_monitorado`, `fk_tiposDados`, `fk_maquina`, fk_componente)" +
                "VALUES (?, ?, ?, ?);",
                valor, tipoDados.getIdTipoDados(), maquina.getIdMaquina(), componente.getIdComponente());
    }

    public void inserirDadosProcesso(Integer pid, String nome, Double usoCpu, Double bytes){
        con.update("INSERT INTO processo (`pid`, `nome`, `usoCPU`, `bytesUtilizados`,`fk_maquina`)" +
                "VALUES (?, ?, ?, ?, ?)", pid, nome, usoCpu, bytes, maquina.getIdMaquina());
    }

    public void inserirDadosJanela(Integer pid, String nome, String caminho){
        con.update("INSERT INTO janela (`pid`, `titulos`, `comandos`, `fk_maquina`) " +
                "VALUES (?, ?, ?, ?)", pid, nome, caminho, maquina.getIdMaquina());
    }

    public void buscarUsuariosBanco(){
        usuarios = con.query("SELECT * FROM usuario WHERE capturar = 1",
                new BeanPropertyRowMapper<>(Usuario.class));
    }

    public void mostrarLogCaptura(String nomeMonitoramento){
        logCaptura = con.query("SELECT * FROM captura_dados WHERE nome_monitoramento = ? AND fk_componente = ?",
                new BeanPropertyRowMapper<>(CapturaDados.class), nomeMonitoramento, componente.getIdComponente());
    }

    public List<CapturaDados> getLogCaptura() {
        return logCaptura;
    }

    public void setLogCaptura(List<CapturaDados> logCaptura) {
        this.logCaptura = logCaptura;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public Conexao getConexao() {
        return conexao;
    }

    public void setConexao(Conexao conexao) {
        this.conexao = conexao;
    }

    public JdbcTemplate getCon() {
        return con;
    }

    public void setCon(JdbcTemplate con) {
        this.con = con;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }


    public Componente getComponente() {
        return componente;
    }

    public void setComponente(Componente componente) {
        this.componente = componente;
    }

    public CapturaDados getCamposCaptura() {
        return camposCaptura;
    }

    public void setCamposCaptura(CapturaDados camposCaptura) {
        this.camposCaptura = camposCaptura;
    }
}