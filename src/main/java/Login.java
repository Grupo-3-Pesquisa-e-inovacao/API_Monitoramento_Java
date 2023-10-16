import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class Login {

    private Conexao conexao;

    private List<Usuario> usuariosBanco;

    private JdbcTemplate con;

    public Login() {
        this.conexao = new Conexao();
        this.con = conexao.getConexaoDoBanco();
    }

    public void buscarUsuariosBanco(){
        this.usuariosBanco = con.query("SELECT * FROM usuario WHERE capturar = 1",
                new BeanPropertyRowMapper<>(Usuario.class));
    }

    public Boolean logar(String email, String senha){

        Boolean usuarioEncontrado = false;

        for (int i = 0; i < usuariosBanco.size(); i++) {

            if(email.equals(usuariosBanco.get(i).getEmail()) &&
                    senha.equals(usuariosBanco.get(i).getSenha())){

                usuarioEncontrado = true;
            }
        }
        return usuarioEncontrado;
    }



}
