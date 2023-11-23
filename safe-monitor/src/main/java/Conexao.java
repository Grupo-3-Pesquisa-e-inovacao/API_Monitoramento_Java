import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class Conexao {

    private JdbcTemplate conexaoDoBanco;
    private JdbcTemplate conexaoNuvem;

    public Conexao() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/safe_monitor");
        dataSource.setUsername("aluno");
        dataSource.setPassword("sptech");
        conexaoDoBanco = new JdbcTemplate(dataSource);

        BasicDataSource dataSourceNuvem = new BasicDataSource();
        dataSourceNuvem.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceNuvem.setUrl("jdbc:sqlserver://34.231.94.175:1433;databaseName=safe_monitor");
        dataSourceNuvem.setUsername("SafeMonitor");
        dataSourceNuvem.setPassword("Sptech123456");
        conexaoNuvem = new JdbcTemplate(dataSourceNuvem);

    }

    JdbcTemplate getConexaoDoBanco(){
        return conexaoDoBanco;
    }

    public JdbcTemplate getConexaoNuvem() {
        return conexaoNuvem;
    }
}
