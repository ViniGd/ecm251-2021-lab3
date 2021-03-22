package transação;

import conta.Conta;

import java.util.Random;

public class Transacao {
    public static void transferencia(String QRCode,Conta contaAPagar,Conta contaAReceber) {
        String[] dados = QRCode.split(";");

        if (contaAReceber.checarInfosParaReceber(dados[0],dados[1])) {
            if (contaAPagar.podePerderSaldo(Double.parseDouble(dados[2]))) {
                contaAPagar.perderSaldo(Double.parseDouble(dados[2]));
                contaAReceber.receberSaldo(Double.parseDouble(dados[2]));
            } else {
                System.out.println(TransacaoConts.saldoInsuficiente);
            }
        } else {
            System.out.println(TransacaoConts.credenciasErradas);
        }
    }

    public static String gerarQRCode(Conta contaAReceber,double valorAReceber) {
        return contaAReceber.getDadosParaQRCode() + ";" + Double.toString(valorAReceber) + ";" + Integer.toString(getRandomNumberInRange(TransacaoConts.numeroMinAleatorio,TransacaoConts.numeroMaxAleatorio));
    }

    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
