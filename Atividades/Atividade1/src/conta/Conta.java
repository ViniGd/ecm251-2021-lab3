//Fernando Padilha RA: 19.00499-0
//Vinicius Godoy RA: 19.00102-9
package conta;

import usuario.Usuario;

import java.util.Arrays;
import java.util.Random;

public class Conta {
    private String idConta;
    private double saldo;
    private Usuario usuario;

    static String[] idContasRegistradas = {};

    public Conta (Usuario usuario, double saldoInicial) {
        this.idConta = criarIdContaNovo();
        this.saldo = saldoInicial;
        this.usuario = usuario;
    }

    public boolean podePerderSaldo(double valorARetirar) {
        return this.saldo >= valorARetirar;
    }

    public void receberSaldo(double valorAReceber) {
        this.saldo += valorAReceber;
    }

    public void perderSaldo(double valorARetirar) {
        this.saldo -= valorARetirar;
    }

    public boolean checarInfosParaReceber(String idConta, String nome) {
        return idConta.equals(this.idConta) && usuario.checarNome(nome);
    }

    public boolean checarDadosDeUsuario(String email, String senha) {
        return usuario.checarCredenciais(email, senha);
    }

    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private static boolean acharStringEmLista(String[] lista, String itemAAchar) {
        if (lista.length > 0) {
            for (String item : lista) {
                if (itemAAchar.equals(item)) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    private static String criarIdContaNovo () {
        String idConta;
        do {
            idConta = "";
            for (int i = 0;i<ContaConts.idContaLengthLimite;i++) {
                idConta += ContaConts.idContaCharacteres.charAt(getRandomNumberInRange(0,ContaConts.idContaCharacteres.length()-1));
            }
        } while(acharStringEmLista(idContasRegistradas,idConta));
        return idConta;
    }

    public String getDadosParaQRCode() {
        return this.idConta + ";" + this.usuario.getNome();
    }

    public String getNomeUsuario() {
        return this.usuario.getNome();
    }

    public double getSaldo() {
        return this.saldo;
    }

    public String getIdConta() {
        return this.idConta;
    }

    public String getEmailUsuario() {
        return this.usuario.getEmail();
    }

    public void setEmailUsuario(String emailAntigo,String senha,String emailNovo) {
        this.usuario.setEmail(emailAntigo,senha,emailNovo);
    }

    public void setSenhaUsuario(String email,String senhaAntiga,String senhaNova) {
        this.usuario.setSenha(email,senhaAntiga,senhaNova);
    }

    public void setNomeUsuario(String email,String senha,String nome) {
        this.usuario.setNome(email,senha,nome);
    }
}
