import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class Conexao {

    private JdbcTemplate conexaoDoBanco;

    public Conexao() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://34.231.94.175:3306/safe_monitor");
        dataSource.setUsername("SafeMonitor ");
        dataSource.setPassword("Sptech123456");

        conexaoDoBanco = new JdbcTemplate(dataSource);
    }

    JdbcTemplate getConexaoDoBanco(){
        return conexaoDoBanco;
    }
}
