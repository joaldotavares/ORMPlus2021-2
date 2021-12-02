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

    public static void main(String[] args) throws Exception {

        String arquivo = "MeuTeste.202";
        Scanner scanner = new Scanner(new File(PATH + arquivo));
        System.out.println(tabelaSimbolos);
        int numLinha = 0;
        while (scanner.hasNextLine()) {
            int posicao = 0;
            numLinha++;
            String line = scanner.nextLine();
            content = line.toCharArray();
            char atomo;
            char proxAtomo = 0;
            StringBuffer token = new StringBuffer();
            boolean filtrarComentarioBloco = false;

            System.out.println(line + " Fim da Linha");

            while (posicao < content.length) {
                System.out.println(posicao + " pos");
                atomo = content[posicao];
                try {
                    proxAtomo = content[posicao + 1];
                } catch (Exception e) {

                }

                //Identificaçao de comentarios
                if (filtrarComentarioBloco) {
                    if (al.verificarComentarioFimBloco(atomo, proxAtomo)) {
                        filtrarComentarioBloco = false;
                        posicao++;
                        token.delete(0, token.length());
                    }
                    posicao++;
                    continue;
                } else {
                    if (al.verificarComentarioInicioLinha(atomo, proxAtomo)) {
                        break; //comentario de linha identificado sai do loop para proxima linha
                    }
                    if (al.verificarComentarioInicioBloco(atomo, proxAtomo)) {
                        filtrarComentarioBloco = true;
                        continue;
                    }
                }

                if (al.verificarPalavrasReservadasSeguidoDeFuncao(atomo, token)) {
                    System.out.println(token);
                    token.delete(0, token.length());
                } else if (al.verificarCaracterValidoSeguidoPorOperacao(atomo, proxAtomo)) {
                    token.append(atomo);
                    System.out.println(token);
                    token.delete(0, token.length());
                } else if (al.verificarCaracterValidoAposOperador(atomo, proxAtomo)) {
                    token.append(atomo);
                    System.out.println(token);
                    token.delete(0, token.length());
                } else if (al.verificarListaParam(atomo) || al.verificarBloco(atomo) || al.verificarDigito(atomo) || al.verificarCaracter(atomo) || al.verificarOperador(atomo) ||
                        al.verificarOperadorSoma(atomo) || al.verificarOperadorMult(atomo) || al.verificarVariavel(atomo)
                        && !al.verificarEspaco(atomo)) {

                    //Verifica se chegou no final da linha
                    if (!al.eof(line, (posicao + 1))) {
                        token.append(atomo);

                    } else { //Se a linha acabou, vai adicionar o ultimo token/atomo e vai verificar se pertence a tabela de simbolos e adicionar novos tokens
                        token.append(atomo);
                        System.out.println(token);
                            /*String tokenStr = token.toString();
                            if (tabelaSimbolos.containsValue(tokenStr)) {
                                System.out.println("Ja está na tabela, então não faz nada");
                            } else {
                                tabela.adicionarToken("T" + key, tokenStr);
                                System.out.println("Como o atomo/token não está na tabela ele vai adicionado a tabela");
                            }*/
                    }
                } else { //Se encontar um delimitador "um espaço"

                    if (token.length() > 0) { //Um ou mais espaços estão formando um token de tamanho 0, por conta da validação anterior do else
                        System.out.println(token);
                        String tokenStr = token.toString();
                        //vai verificar se pertence a tabela de simbolos e adicionar novos tokens
                            /*if (tabelaSimbolos.containsValue(tokenStr)) {
                                System.out.println("Ja está na tabela, então não faz nada");
                            }*/
                        token.delete(0, token.length());
                        System.out.println(token + "-");
                    }

                    //if (token.length() == 0 && !al.verificarEspaco(atomo)) {
                    //throw new Exception("Atomo '" + atomo + "' da linha " + linha + " não pertence a linguagem" +
                    //"\nArquivo: " + arquivo);
                    //}
                }
                posicao++;
            }
            posicao = 0;


        }

        System.out.println(tabelaSimbolos);
    }


}