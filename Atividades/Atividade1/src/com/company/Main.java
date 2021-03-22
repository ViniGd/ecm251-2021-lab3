package com.company;

import conta.Conta;
import transação.Transacao;
import usuario.Usuario;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner userInput = new Scanner(System.in);
        int escolhaMenuInicial;
        int escolhaMenuUsuario;
        int escolhaMenuTrocaDeDados;
        Conta[] contas = {};
        String[] dadosTemps;
        Conta contaTemp;
        Conta contaAReceberTemp;
        double valorAReceberTemp;
        String dadoAMudarTemp;
        String idContaTemp;
        String email,senha,nome;
        double saldoInicial;
        do {
            menuInicial();
            escolhaMenuInicial = userInput.nextInt();

            switch (escolhaMenuInicial) {
                case 1 -> {
                    dadosTemps = menuLogin(userInput);
                    email = dadosTemps[0];
                    senha = dadosTemps[1];
                    Arrays.fill(dadosTemps, null);
                    contaTemp = acharContaPorCredenciais(contas, email, senha);
                    if (contaTemp != null) {
                        do {
                            menuUsuario(contaTemp);
                            escolhaMenuUsuario = userInput.nextInt();

                            switch (escolhaMenuUsuario) {
                                case 1:
                                    printarDadosConta(contaTemp);
                                    System.out.print(" Precione enter para voltar: ");
                                    userInput.nextLine();
                                    break;
                                case 2:
                                    menuDeTransferencia(contas, contaTemp, userInput);
                                    System.out.println("Qual o ID da Conta para Transferir?\n");
                                    idContaTemp = userInput.nextLine();

                                    contaAReceberTemp = acharContaPorId(contas, idContaTemp);

                                    if (contaAReceberTemp != null) {
                                        System.out.println("Qual o Valor da Transferencia?\n");
                                        valorAReceberTemp = userInput.nextDouble();
                                        Transacao.transferencia(Transacao.gerarQRCode(contaAReceberTemp, valorAReceberTemp), contaTemp, contaAReceberTemp);
                                    } else {
                                        System.out.println("Id Invalido!!!\n");
                                        System.out.print(" Precione enter para voltar: \n");
                                        userInput.nextLine();
                                    }

                                    break;

                                case 3:
                                    do {
                                        menuTrocarDados();
                                        escolhaMenuTrocaDeDados = userInput.nextInt();

                                        switch (escolhaMenuTrocaDeDados) {
                                            case 1:
                                                System.out.println("Qual o Novo Email?");
                                                dadoAMudarTemp = userInput.nextLine();

                                                contaTemp.setEmailUsuario(email, senha, dadoAMudarTemp);

                                                System.out.println("Dado Atualizado");
                                                System.out.print(" Precione enter para voltar: ");
                                                userInput.nextLine();

                                            case 2:
                                                System.out.println("Qual o Novo Senha?");
                                                dadoAMudarTemp = userInput.nextLine();

                                                contaTemp.setSenhaUsuario(email, senha, dadoAMudarTemp);

                                                System.out.println("Dado Atualizado");
                                                System.out.print(" Precione enter para voltar: ");
                                                userInput.nextLine();

                                            case 3:
                                                System.out.println("Qual o Novo Nome?");
                                                dadoAMudarTemp = userInput.nextLine();

                                                contaTemp.setNomeUsuario(email, senha, dadoAMudarTemp);

                                                System.out.println("Dado Atualizado");
                                                System.out.print(" Precione enter para voltar: ");
                                                userInput.nextLine();

                                            case 4:
                                                System.out.println("Voltando Você!!!");
                                                break;
                                            default:
                                                System.out.println("Opção Inválida!!!");
                                                System.out.print(" Precione enter para voltar: ");
                                                userInput.nextLine();
                                        }
                                    } while (escolhaMenuTrocaDeDados != 4);

                                    break;
                                case 4:
                                    System.out.println("Voltando Você!!!");
                                    break;
                                default:
                                    System.out.println("Opção Inválida!!!");
                                    System.out.print(" Precione enter para voltar: ");
                                    userInput.nextLine();
                            }
                        } while (escolhaMenuUsuario != 4);
                    }
                }
                case 2 -> {
                    dadosTemps = menuCadastrar(userInput);
                    email = dadosTemps[0];
                    senha = dadosTemps[1];
                    nome = dadosTemps[2];
                    saldoInicial = Double.parseDouble(dadosTemps[3]);
                    adicionarContaAoSistema(contas, new Conta(new Usuario(nome, senha, email), saldoInicial));
                    System.out.println("Conta Adicionada!Faça Login Para Acessar Sua Conta!!!");
                    System.out.print(" Precione enter para voltar: ");
                    userInput.nextLine();
                }
                case 3 -> {
                    menuAdmin(contas);
                    System.out.print(" Precione enter para voltar: ");
                    userInput.nextLine();
                }
                case 4 -> System.out.println("Volte sempre!!!");
                default -> {
                    System.out.println("Opção Inválida!!!");
                    System.out.print(" Precione enter para voltar: ");
                    userInput.nextLine();
                }
            }
        } while(escolhaMenuInicial!=4);
    }

    private static Conta acharContaPorCredenciais(Conta[] contas,String email, String senha) {
        for (Conta conta : contas) {
            if (conta.checarDadosDeUsuario(email,senha)) {
                return conta;
            }
        }
        return null;
    }

    private static Conta acharContaPorId(Conta[] contas,String idConta) {
        for (Conta conta : contas) {
            if (conta.getIdConta().equals(idConta)) {
                return conta;
            }
        }
        return null;
    }

    private static void menuInicial() {
        System.out.println(
                "============\n" +
                "Menu Inicial\n" +
                "1 - Fazer Login\n" +
                "2 - Criar Conta\n" +
                "3 - Entrar Como Admin\n" +
                "4 - Sair"
        );
    }

    private static String[] menuLogin(Scanner userInput) {
        System.out.println(
                "============\n" +
                "Login\n" +
                "Email: "
        );
        String email = userInput.nextLine();
        System.out.println(
                "Senha: "
        );
        String senha = userInput.nextLine();

        return new String[]{email, senha};
    }

    private static void menuUsuario(Conta conta) {
        System.out.println(
                "============\n" +
                "Bem Vindo " + conta.getNomeUsuario() + "\n" +
                "1 - Ver Dados da Conta\n" +
                "2 - Fazer Transferencia\n" +
                "3 - Trocar Dados\n" +
                "4 - Voltar Ao Menu Anterior"
        );
    }

    private static void printarDadosConta(Conta conta) {
        System.out.println(
                "============\n" +
                "Nome do Usuario: " + conta.getNomeUsuario() + "\n" +
                "Email do Usuario: " + conta.getEmailUsuario() + "\n" +
                "ID: " + conta.getIdConta() + "\n" +
                "Saldo: " + conta.getSaldo()
        );
    }

    private static void menuDeTransferencia(Conta[] contas,Conta contaAPesquisar,Scanner userInput) {
        System.out.println(
                "============\n" +
                "Nome:\tID:\n"
        );

        if (contas.length > 1) {
            for (Conta conta : contas) {
                if (!conta.getIdConta().equals(contaAPesquisar.getIdConta())) {
                    System.out.println(conta.getNomeUsuario() + "\t" + conta.getIdConta());
                }
            }
        } else {
            System.out.println("Não Há Outras Contas Para Transferir!!!\n");
            System.out.print(" Precione enter para voltar: ");
            userInput.nextLine();
        }
    }

    private static void menuTrocarDados() {
        System.out.println(
                "============\n" +
                "Quer Trocar O Que? \n" +
                "1 - Email \n" +
                "2 - Senha \n" +
                "3 - Nome \n" +
                "4 - Voltar Ao Menu Anterior"
        );
    }

    private static String[] menuCadastrar(Scanner userInput) {
        System.out.println(
                "============\n" +
                "Cadastrar\n" +
                "Email: "
        );
        String email = userInput.nextLine();
        System.out.println(
                "Senha: "
        );
        String senha = userInput.nextLine();
        System.out.println(
                "Nome: "
        );
        String nome = userInput.nextLine();
        System.out.println(
                "Saldo Inicial: "
        );
        String saldo = String.valueOf(userInput.nextDouble());

        return new String[]{email, senha, nome, saldo};
    }

    private static void adicionarContaAoSistema(Conta[] contas, Conta contaNova) {
        contas = Arrays.copyOf(contas, contas.length+1);
        contas[contas.length-1] = contaNova;
    }

    private static void menuAdmin(Conta[] contas) {
        System.out.println(
                "============\n" +
                "Dados dos Usuarios:\n" +
                "Nome:\tID:\tSaldo:\tEmail:"
        );

        if(contas.length > 0) {
            for(Conta conta : contas) {
                System.out.println(conta.getNomeUsuario() + "\t" + conta.getIdConta() + "\t" + conta.getSaldo() + "\t" + conta.getEmailUsuario());
            }
        } else {
            System.out.println("Ainda Não Há Contas Cadastradas!!!");
        }
    }
}
