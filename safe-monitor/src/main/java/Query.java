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
    private List<HistoricoUsuarios> historicoUsuarios;
    private List<Componente> componentes;
    private List<CapturaDados> logCaptura;



    public Query() {
        this.conexao = new Conexao();
        this.con = conexao.getConexaoDoBanco();
        this.maquina = new Maquina();
        this.componente = new Componente();
        this.camposCaptura = new CapturaDados();
        this.logCaptura = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.componentes = new ArrayList<>();
        this.historicoUsuarios = new ArrayList<>();
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

    public void inserirDadosHistoricoUsuario(Integer idUsuario){
        con.update("INSERT INTO historico_usuarios (`fk_usuario`, `fk_maquina`) " +
                "VALUES (?, ?)", idUsuario, maquina.getIdMaquina());
    }

    public void buscarComponentes(){
        componentes = con.query("SELECT * FROM componente",
                new BeanPropertyRowMapper<>(Componente.class));
    }

    public void buscarUsuariosBanco(){
        usuarios = con.query("SELECT * FROM usuario WHERE capturar = 1",
                new BeanPropertyRowMapper<>(Usuario.class));
    }

    public void buscarLogCaptura(Integer idComponente){
        logCaptura = con.query(
                "SELECT td.nome as nome, valor_monitorado, dt_hora as dataHora FROM \n" +
                "\tcaptura_dados as cd INNER JOIN tipo_dados as td ON td.idTipoDados = cd.fk_tiposDados\n" +
                "\t\tWHERE fk_maquina = ? AND fk_componente = ? ORDER BY dt_hora DESC",
                new BeanPropertyRowMapper<>(CapturaDados.class), maquina.getIdMaquina(), idComponente);
    }

    public void buscarHistoricoUsuarios(){
        historicoUsuarios = con.query(
                    "SELECT usuario.email as email, usuario.nome as nome, data_hora FROM \n" +
                        "\thistorico_usuarios as historico INNER JOIN usuario ON usuario.idUsuario = historico.fk_usuario \n" +
                        "    WHERE historico.fk_maquina = ? ORDER BY data_hora DESC;",
                new BeanPropertyRowMapper<>(HistoricoUsuarios.class), maquina.getIdMaquina());
    }

    public TipoDados getTipoDados() {
        return tipoDados;
    }

    public List<Componente> getComponentes() {
        return componentes;
    }

    public void setComponentes(List<Componente> componentes) {
        this.componentes = componentes;
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

    public List<HistoricoUsuarios> getHistoricoUsuarios() {
        return historicoUsuarios;
    }

    public void setHistoricoUsuarios(List<HistoricoUsuarios> historicoUsuarios) {
        this.historicoUsuarios = historicoUsuarios;
    }
}