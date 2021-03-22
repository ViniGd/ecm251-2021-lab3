package usuario;

public class Usuario {
    String nome;
    String senha;
    String email;

    public Usuario (String nome,String senha,String email) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
    }

    public boolean checarCredenciais(String email, String senha) {
        if (this.email == email && this.senha == senha) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checarNome(String nome) {
        return nome.equals(this.nome);
    }

    public void setNome(String nome, String email, String senha) {
        if (checarCredenciais(email,senha)) {
            this.nome = nome;
        } else {
            System.out.println(UsuarioConts.credenciaisInvalidas);
        }
    }

    public void setSenha(String email, String senha, String novaSenha) {
        if (checarCredenciais(email,senha)) {
            this.senha = novaSenha;
        } else {
            System.out.println(UsuarioConts.credenciaisInvalidas);
        }
    }

    public void setEmail(String email, String senha, String novoEmail) {
        if (checarCredenciais(email,senha)) {
            this.email = novoEmail;
        } else {
            System.out.println(UsuarioConts.credenciaisInvalidas);
        }
    }

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }
}
