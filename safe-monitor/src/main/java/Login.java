public class Login {

    private Query queryBD;

    public Login() {
        this.queryBD = new Query();
    }

    public Boolean procurarUsuario(String email, String senha){

        Boolean usuarioEncontrado = false;

        queryBD.buscarUsuariosBanco();

        for (int i = 0; i < queryBD.getUsuarios().size(); i++) {

            if(email.equals(queryBD.getUsuarios().get(i).getEmail()) &&
                    senha.equals(queryBD.getUsuarios().get(i).getSenha())){

                usuarioEncontrado = true;
            }
        }
        return usuarioEncontrado;
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

    
}
