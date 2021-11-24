package br.com.ucsal;

import br.com.ucsal.analisador.AnalisadorLexico;
import br.com.ucsal.tabela.TabelaSimbolos;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

/* FIXME: Os prints estão ai somente para verificação as saidas do que e gerado pelo programa */

public class Main {
    private static AnalisadorLexico al = new AnalisadorLexico();
    private static TabelaSimbolos tabela = new TabelaSimbolos();
    private static Map<String, String> tabelaSimbolos = tabela.obterTabela();
    private static final String PATH = "..\\ORMPlus2021-2\\src\\br\\com\\ucsal\\compiler\\";

    private static char[] content;

    private String jota;

    public static void main(String[] args) throws Exception {

        StringBuffer sb = new StringBuffer();
        String arquivo = "MeuTeste.202";
        Scanner scanner = new Scanner(new File(PATH + arquivo));
        System.out.println(tabelaSimbolos);
        int aux = 0, key = 0;
        while (scanner.hasNextLine()) {
            //linha++;
            String line = scanner.nextLine();
            content = line.toCharArray();
            char atomo;
            StringBuffer token = new StringBuffer();

            System.out.println(line + " Fim da Linha");

            while (aux < content.length) {

                atomo = content[aux];

                //Verificação de o atomo pertence a linguagem e do espaço para delimitar o token ou atomo
                if (al.verificarListaParam(atomo) || al.verificarBloco(atomo) || al.verificarDigito(atomo) || al.verificarCaracter(atomo) || al.verificarOperador(atomo) ||
                        al.verificarOperadorSoma(atomo) || al.verificarOperadorMult(atomo) || al.verificarVeriavel(atomo)
                        && !al.verificarEspaco(atomo)) {

                    //Verifica se chegou no final da linha
                    if (!al.eof(line, (aux + 1))) {
                        token.append(atomo);
                    } else { //Se a linha acabou, vai adicionar o ultimo token/atomo e vai verificar se pertence a tabela de simbolos e adicionar novos tokens
                        key++;
                        token.append(atomo);
                        System.out.println(token);
                        String tokenStr = token.toString();
                        if (tabelaSimbolos.containsValue(tokenStr)) {
                            System.out.println("Ja está na tabela, então não faz nada");
                        } else {
                            tabela.adicionarToken("T" + key, tokenStr);
                            System.out.println("Como o atomo/token não está na tabela ele vai adicionado a tabela");
                        }
                    }
                } else { //Se encontar um delimitador "um espaço"
                    if (token.length() != 0) { //Um ou mais espaços estão formando um token de tamanho 0, por conta da validação anterior do else
                        System.out.println(token);
                        String tokenStr = token.toString();
                        //vai verificar se pertence a tabela de simbolos e adicionar novos tokens
                        if (tabelaSimbolos.containsValue(tokenStr)) {
                            System.out.println("Ja está na tabela, então não faz nada");
                        } else {
                            key++;
                            tabela.adicionarToken("T" + key, tokenStr);
                            System.out.println("Como o atomo/token não está na tabela ele vai adicionado a tabela");
                        }
                        token.delete(0, token.length());
                    }

                    //if (token.length() == 0 && !al.verificarEspaco(atomo)) {
                    //throw new Exception("Atomo '" + atomo + "' da linha " + linha + " não pertence a linguagem" +
                    //"\nArquivo: " + arquivo);
                    //}
                }
                aux++;
            }
            aux = 0;
        }

        System.out.println(tabelaSimbolos);
    }
}