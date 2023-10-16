import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private Conexao conexao;
    private JdbcTemplate con;
    private Maquina maquina;

    private Dados dados;
    private Componente componente;
    private CamposCaptura camposCaptura;

    private List<CamposCaptura> logDados;

    public Sistema() {
        this.conexao = new Conexao();
        this.con = conexao.getConexaoDoBanco();
        this.maquina = new Maquina();
        this.componente = new Componente();
        this.camposCaptura = new CamposCaptura();
        this.logDados = new ArrayList<>();
        this.dados = new Dados();
    }

    public void conectarMaquina(Integer idMaquina){
        maquina = con.queryForObject("SELECT * FROM maquina WHERE idMaquina = ?",
                new BeanPropertyRowMapper<>(Maquina.class), idMaquina);
        System.out.println(maquina.toString());
    }

    public void conectarComponente(Integer idComponente){
        componente = con.queryForObject("SELECT * FROM componente WHERE idComponente = ? AND fk_maquina_proc = ?;",
                new BeanPropertyRowMapper<>(Componente.class),idComponente, maquina.getIdMaquina());
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


}
