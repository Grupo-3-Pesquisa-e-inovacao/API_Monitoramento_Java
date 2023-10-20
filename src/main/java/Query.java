import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class Query {
    private Conexao conexao;
    private JdbcTemplate con;
    private Maquina maquina;
    private Captura dados;
    private Componente componente;
    private CamposCaptura camposCaptura;
    private List<Usuario> usuarios;
    private List<CamposCaptura> logCaptura;

    public Query() {
        this.conexao = new Conexao();
        this.con = conexao.getConexaoDoBanco();
        this.maquina = new Maquina();
        this.componente = new Componente();
        this.camposCaptura = new CamposCaptura();
        this.logCaptura = new ArrayList<>();
        this.dados = new Captura();
        this.usuarios = new ArrayList<>();
    }

    public void conectarMaquina(Integer idMaquina){
        maquina = con.queryForObject("SELECT * FROM maquina WHERE idMaquina = ?",
                new BeanPropertyRowMapper<>(Maquina.class), idMaquina);
    }

    public void conectarComponente(Integer idComponente){
        componente = con.queryForObject("SELECT * FROM componente WHERE idComponente = ? AND fk_maquina_proc = ?;",
                new BeanPropertyRowMapper<>(Componente.class),idComponente, maquina.getIdMaquina());

        setComponente(componente);
    }

    public Double converterParaGigas(Double valor){
        return valor / (1*Math.pow(10,9));
    }

    public void inserirInformacoesMaquinas(){

        Double armazenamentoDisco = converterParaGigas(dados.getDiscoVolumeTotal().doubleValue());
        Double espacoLivreDisco = converterParaGigas(dados.getDiscoVolumeDisponivel().doubleValue());
        String enderecoIpv4 = dados.getRedeAtual().getEnderecoIpv4().get(0);
        Double capacidadeRam = converterParaGigas(dados.getMemoriaTotal().doubleValue());

        con.update("UPDATE maquina SET armazenamento_disco = ?,"
                        + "espaco_livre_disco = ?, endereco_ipv4 = ?," +
                        "capacidade_total_ram = ? WHERE idMaquina = ?",
                armazenamentoDisco, espacoLivreDisco, enderecoIpv4, capacidadeRam, maquina.getIdMaquina());
    }

    public void inserirDados(String nome, Double valor){
        con.update("INSERT INTO captura_dados (nome_monitoramento, valor_monitorado, data_hora, fk_componente, `fk_maquina_captura`) " +
                  "VALUES (?, ?, now(), ?, ?)", nome, valor, componente.getIdComponente(), maquina.getIdMaquina());
    }

    public void inserirDados(String nome, Long valor){
        con.update("INSERT INTO captura_dados (nome_monitoramento, valor_monitorado, data_hora, fk_componente, `fk_maquina_captura`) " +
                "VALUES (?, ?, now(), ?, ?)", nome, valor, componente.getIdComponente(), maquina.getIdMaquina());
    }

    public void buscarUsuariosBanco(){
        usuarios = con.query("SELECT * FROM usuario WHERE capturar = 1",
                new BeanPropertyRowMapper<>(Usuario.class));
    }

    public void mostrarLogCaptura(String nomeMonitoramento){
        logCaptura = con.query("SELECT * FROM captura_dados WHERE nome_monitoramento = ? AND fk_componente = ?",
                new BeanPropertyRowMapper<>(CamposCaptura.class), nomeMonitoramento, componente.getIdComponente());
    }

    public List<CamposCaptura> getLogCaptura() {
        return logCaptura;
    }

    public void setLogCaptura(List<CamposCaptura> logCaptura) {
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

    public Captura getDados() {
        return dados;
    }

    public void setDados(Captura dados) {
        this.dados = dados;
    }

    public Componente getComponente() {
        return componente;
    }

    public void setComponente(Componente componente) {
        this.componente = componente;
    }

    public CamposCaptura getCamposCaptura() {
        return camposCaptura;
    }

    public void setCamposCaptura(CamposCaptura camposCaptura) {
        this.camposCaptura = camposCaptura;
    }
}