import componentes.*;
import entidades.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Query {


    private Conexao conexao;
    private JdbcTemplate con;
    private JdbcTemplate conNuvem;
    private List<Usuario> usuarios;
    private List<HistoricoUsuarios> historicoUsuarios;
    private List<Janela> janelas;
    private List<Componente> componentes;
    private List<CapturaDados> logCaptura;
    private Maquina maquina;
    private Dado idComponenteList;
    private Dado TipoDados;
    private Dado TipoComponente;
    private List<Dado> salas;



    public Query() {
        this.salas = new ArrayList<>();
        this.maquina = new Maquina();
        this.conexao = new Conexao();
        this.con = conexao.getConexaoDoBanco();
        this.conNuvem = conexao.getConexaoNuvem();
        this.logCaptura = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.componentes = new ArrayList<>();
        this.historicoUsuarios = new ArrayList<>();
    }


    public void conectarMaquina(String hostname){

        maquina = null;

        try {
            maquina = con.queryForObject("SELECT * FROM maquina WHERE hostname = ?;",
                    new BeanPropertyRowMapper<>(Maquina.class), hostname);

            /*maquina = conNuvem.queryForObject("SELECT * FROM maquina WHERE hostname = ?;",
                    new BeanPropertyRowMapper<>(Maquina.class), hostname);*/

        } catch (EmptyResultDataAccessException e) {
            System.out.println("Nenhum resultado encontrado para o hostname: " + hostname);
        }
    }

    public void inserirDaodosMaquina(Maquina maquina, Integer idEmpresa, Integer idSala){
       con.update(" INSERT INTO maquina (hostName, nome, modelo, marca, numero_serie,  sistema_operacional, arquitetura, fabricante, " +
                       "endereco_ipv4, endereco_mac, fk_empresa, fk_sala)" +
                       "\t    VALUE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
               maquina.getHostName(),
               maquina.getNome(),
               maquina.getModelo(),
               maquina.getMarca(),
               maquina.getNumeroSerie(),
               maquina.getSistemaOperacional(),
               maquina.getArquitetura(),
               maquina.getFabricante(),
               maquina.getEndereco_ipv4(),
               maquina.getEnderecoMac(),
               idEmpresa, idSala);

      /*  conNuvem.update(" INSERT INTO maquina (hostName, nome, modelo, marca, numero_serie,  sistema_operacional, arquitetura, fabricante, " +
                        "endereco_ipv4, endereco_mac, fk_empresa, fk_sala)" +
                        "\t    VALUE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                maquina.getHostName(),
                maquina.getNome(),
                maquina.getModelo(),
                maquina.getMarca(),
                maquina.getNumeroSerie(),
                maquina.getSistemaOperacional(),
                maquina.getArquitetura(),
                maquina.getFabricante(),
                maquina.getEndereco_ipv4(),
                maquina.getEnderecoMac(),
                idEmpresa, idSala);*/
    }

    public void inserirComponentes(Processador cpu, Memoria ram, Disco disco){
        con.update(" INSERT INTO componente (nome, modelo, total, fk_tipoComponente, fk_maquina) " +
                "VALUES (?, ?, ?, 1, ?)", cpu.getNome(), cpu.getModelo(), cpu.getTotal(), maquina.getIdMaquina());

        con.update(" INSERT INTO componente (total, fk_tipoComponente, fk_maquina) " +
                "VALUES (?, 2, ?)", ram.converterParaGigas(ram.getTotal()), maquina.getIdMaquina());

        con.update(" INSERT INTO componente (nome, modelo, total, fk_tipoComponente, fk_maquina) " +
                "VALUES (?, ?, ?, 3, ?)", disco.getNome(), disco.getModelo(), disco.converterParaGigas(disco.getTotal()), maquina.getIdMaquina());

       /* conNuvem.update(" INSERT INTO componente (nome, modelo, total, fk_tipoComponente, fk_maquina) " +
                "VALUES (?, ?, ?, 1, ?)", cpu.getNome(), cpu.getModelo(), cpu.getTotal(), maquina.getIdMaquina());

        conNuvem.update(" INSERT INTO componente (total, fk_tipoComponente, fk_maquina) " +
                "VALUES (?, 2, ?)", ram.getTotal(), maquina.getIdMaquina());

        conNuvem.update(" INSERT INTO componente (nome, modelo, total, fk_tipoComponente, fk_maquina) " +
                "VALUES (?, ?, ?, 3, ?)", disco.getNome(), disco.getModelo(), disco.getTotal(), maquina.getIdMaquina());*/
    }



    public void definirTipoComponente(String nome){
        TipoComponente = con.queryForObject("SELECT idTipoComponente as id, nome AS nome FROM tipo_componente WHERE nome = ?;",
                new BeanPropertyRowMapper<>(Dado.class),nome);

      /*  TipoComponente = conNuvem.queryForObject("SELECT idTipoComponente as id, nome AS nome FROM tipo_componente WHERE nome = ?;",
                new BeanPropertyRowMapper<>(Dado.class),nome);*/
    }

    public void definirComponente(){
        idComponenteList = con.queryForObject("SELECT idComponente as id, nome FROM componente WHERE fk_tipoComponente = ? AND fk_maquina = ?",
                new BeanPropertyRowMapper<>(Dado.class), TipoComponente.getId(), maquina.getIdMaquina());

       /* idComponenteList = conNuvem.queryForObject("SELECT idComponente as id, nome FROM componente WHERE fk_tipoComponente = ? AND fk_maquina = ?",
                new BeanPropertyRowMapper<>(Dado.class), TipoComponente.getId(), maquina.getIdMaquina());*/
    }

    public void definirTipoDados(String nome){
        TipoDados = con.queryForObject("SELECT idTipoDados as id, nome AS nome FROM tipo_dados WHERE nome = ?;",
                new BeanPropertyRowMapper<>(Dado.class),nome);

        /*TipoDados = conNuvem.queryForObject("SELECT idTipoDados as id, nome AS nome FROM tipo_dados WHERE nome = ?;",
                new BeanPropertyRowMapper<>(Dado.class),nome);*/
    }


    public  void inserirTiposDeDados(String tipoDado, Integer tipoComponente){
        con.update("INSERT INTO tipo_dados (nome, fk_componente, fk_maquina, fk_tipoComponente) VALUES (?, ?, ?, ?);",
                tipoDado, idComponenteList.getId(), maquina.getIdMaquina(), tipoComponente);
    }


    public void inserirDadosCaptura(Double valor){
            con.update("INSERT INTO captura_dados (dt_hora, valor_monitorado, fk_tipoDados, fk_maquina, fk_componente, fk_tipoComponente)" +
                    "VALUES (now(), ?, ?, ?, ?,?);", valor, TipoDados.getId(), maquina.getIdMaquina(), idComponenteList.getId(), TipoComponente.getId());

      /*  conNuvem.update("INSERT INTO captura_dados (dt_hora, valor_monitorado, fk_tiposDados, fk_maquina, fk_componente, fk_tipoComponente)" +
                "VALUES (now(), ?, ?, ?, ?,?);", valor, TipoDados.getId(), maquina.getIdMaquina(), 1, TipoComponente.getId());*/

    }

    public void buscarJanelas(){
        janelas = con.query("SELECT pid, titulos as titulo, comandos as comando, matar, stt FROM janela WHERE fk_maquina = ? AND stt = 'Aberta';",
                new BeanPropertyRowMapper<>(Janela.class), maquina.getIdMaquina());

        /*janelas = conNuvem.query("SELECT pid, titulos as titulo, comandos as comando, matar, stt FROM janela WHERE fk_maquina = ? AND stt = 'Aberta';",
                new BeanPropertyRowMapper<>(Janela.class), maquina.getIdMaquina());*/
    }

    public void buscarSalas(Integer idEmpresa){
        salas = con.query("SELECT idSala as id, nome FROM sala_de_aula WHERE fk_empresa = ?;",
                new BeanPropertyRowMapper<>(Dado.class), idEmpresa);

       /* salas = conNuvem.query("SELECT idSala as id, nome FROM sala_de_aula WHERE fk_empresa = ?;",
                new BeanPropertyRowMapper<>(Dado.class), idEmpresa);*/
    }

    public void inserirDadosJanela(Janela janela){
        con.update("INSERT INTO janela (pid, titulos,comandos, fk_maquina, stt) " +
                "VALUES (?, ?, ?, ?, ?)", janela.getPid(), janela.getTitulo(), janela.getComando(), maquina.getIdMaquina(), "Aberta");

      /*  conNuvem.update("INSERT INTO janela (pid, titulos,comandos, fk_maquina, stt) " +
                "VALUES (?, ?, ?, ?, ?)", janela.getPid(), janela.getTitulo(), janela.getComando(), maquina.getIdMaquina(), "Aberta");*/
    }

    public void alterarStatusJanelaFechada(Janela janela){
        con.update("UPDATE janela SET stt = 'Fechada' WHERE fk_maquina = ? AND pid = ?;"
                , maquina.getIdMaquina(), janela.getPid());

      /*  conNuvem.update("UPDATE janela SET stt = 'Fechada' WHERE fk_maquina = ? AND pid = ?;"
                , maquina.getIdMaquina(), janela.getPid());*/
    }

    // public void buscarJanelasFechadas(){
    //    janelas = con.query("SELECT titulos FROM janela WHERE stt = 'Fechada'",
    //            new BeanPropertyRowMapper<>(Janela.class));

    //}

    public void inserirDadosHistoricoUsuario(Integer idUsuario){
        con.update("INSERT INTO historico_usuarios (fk_usuario, fk_maquina) " +
                "VALUES (?, ?)", idUsuario, maquina.getIdMaquina());

       /* conNuvem.update("INSERT INTO historico_usuarios (fk_usuario, fk_maquina) " +
                "VALUES (?, ?)", idUsuario, maquina.getIdMaquina());*/
    }

    public void buscarComponentes(){
        componentes = con.query("SELECT * FROM componente",
                new BeanPropertyRowMapper<>(Componente.class));

        /*componentes = conNuvem.query("SELECT * FROM componente",
                new BeanPropertyRowMapper<>(Componente.class));*/
    }

  public void buscarUsuariosBanco(){
        usuarios = con.query("SELECT * FROM usuario WHERE capturar = 1",
                new BeanPropertyRowMapper<>(Usuario.class));

   /*   usuarios = conNuvem.query("SELECT * FROM usuario WHERE capturar = 1",
              new BeanPropertyRowMapper<>(Usuario.class));*/

    }

    public void buscarHistoricoUsuarios(){
        historicoUsuarios = con.query(
                    "SELECT usuario.email as email, usuario.nome as nome, data_hora as dataHora FROM \n" +
                        "\thistorico_usuarios as historico INNER JOIN usuario ON usuario.idUsuario = historico.fk_usuario \n" +
                        "    WHERE historico.fk_maquina = ? ORDER BY data_hora DESC;",
                new BeanPropertyRowMapper<>(HistoricoUsuarios.class), maquina.getIdMaquina());

       /* historicoUsuarios = conNuvem.query(
                "SELECT usuario.email as email, usuario.nome as nome, data_hora as dataHora FROM \n" +
                        "\thistorico_usuarios as historico INNER JOIN usuario ON usuario.idUsuario = historico.fk_usuario \n" +
                        "    WHERE historico.fk_maquina = ? ORDER BY data_hora DESC;",
                new BeanPropertyRowMapper<>(HistoricoUsuarios.class), maquina.getIdMaquina());*/
    }

    public List<Janela> getJanelas() {
        return janelas;
    }

    public void setJanelas(List<Janela> janelas) {
        this.janelas = janelas;
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

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<HistoricoUsuarios> getHistoricoUsuarios() {
        return historicoUsuarios;
    }

    public void setHistoricoUsuarios(List<HistoricoUsuarios> historicoUsuarios) {
        this.historicoUsuarios = historicoUsuarios;
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

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    public Dado getIdComponenteList() {
        return idComponenteList;
    }

    public void setIdComponenteList(Dado idComponenteList) {
        this.idComponenteList = idComponenteList;
    }

    public Dado getTipoDados() {
        return TipoDados;
    }

    public void setTipoDados(Dado tipoDados) {
        TipoDados = tipoDados;
    }

    public Dado getTipoComponente() {
        return TipoComponente;
    }

    public void setTipoComponente(Dado tipoComponente) {
        TipoComponente = tipoComponente;
    }


    public List<Dado> getSalas() {
        return salas;
    }

    public void setSalas(List<Dado> salas) {
        this.salas = salas;
    }
}