package br.com.ucsal;

import br.com.ucsal.analisador.AnalisadorLexico;
import br.com.ucsal.tabela.TabelaSimbolos;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

//FIXME: Os prints estão ai somente para verificar as saidas do que e gerado pelo programa, na entrega final devem ser removidos

public class Main {
    private static AnalisadorLexico al = new AnalisadorLexico();

    private static TabelaSimbolos tabela = new TabelaSimbolos();
    private static Map<String, String> tabelaSimbolos = tabela.obterTabelaReservada();
    private static final String PATH = "..\\ORMPlus2021-2\\src\\br\\com\\ucsal\\compiler\\";

    private static char[] content;

    public static void main(String[] args) {
        new IniciarInterface();
    }

    public void IniciarPrograma(String arquivo) throws IOException {
        Scanner scanner = new Scanner(new File(PATH + arquivo+".202"));
        //System.out.println(tabelaSimbolos);
        int numLinha = 0;
        boolean filtrarComentarioBloco = false, filtrarAspas = false;
        while (scanner.hasNextLine()) {
            int posicao = 0;
            numLinha++;
            String line = scanner.nextLine(), tokenStr;
            content = line.toCharArray();
            char atomo;
            char proxAtomo = 0;
            StringBuffer token = new StringBuffer();
            StringBuffer tokenAspas = new StringBuffer();

            while (posicao < content.length) {
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

                if (filtrarAspas) {
                    if (al.verificarAspas(atomo)) {
                        filtrarAspas = false;
                    }
                    tokenAspas.append(atomo);
                    posicao++;
                    continue;
                } else {
                    if (al.verificarAspas(atomo)) {
                        tokenAspas.append(atomo);
                        filtrarAspas = true;
                    }
                }

                if (al.verificarPalavrasReservadasSeguidoDeFuncao(atomo, token)) {
                    System.out.println(token);
                    tokenStr = token.toString();
                    al.salvarToken(tokenStr, numLinha, posicao);
                    token.delete(0, token.length());
                } else if (al.verificarCaracterValidoSeguidoPorOperacao(atomo, proxAtomo)) {
                    token.append(atomo);
                    System.out.println(token);
                    tokenStr = token.toString();
                    al.salvarToken(tokenStr, numLinha, posicao);
                    token.delete(0, token.length());
                } else if (al.verificarCaracterValidoAposOperador(atomo, proxAtomo)) {
                    token.append(atomo);
                    System.out.println(token + "*");
                    tokenStr = token.toString();
                    al.salvarToken(tokenStr, numLinha, posicao);
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
                        tokenStr = token.toString();
                        al.salvarToken(tokenStr, numLinha, posicao);
                    }
                } else { //Se encontar um delimitador "um espaço"

                    if (token.length() > 0) { //Um ou mais espaços estão formando um token de tamanho 0, por conta da validação anterior do else
                        System.out.println(token);
                        tokenStr = token.toString();
                        al.salvarToken(tokenStr, numLinha, posicao);
                        token.delete(0, token.length());
                    }

                    if (tokenAspas.length() > 1) {
                        System.out.println(tokenAspas);
                        tokenStr = tokenAspas.toString();
                        al.salvarToken(tokenStr, numLinha, posicao);
                        tokenAspas.delete(0, tokenAspas.length());
                    }
                }
                posicao++;
            }
        }
        al.gerarArquivoTAB(arquivo);
        al.gerarArquivoLex(arquivo);
        al.limparLista();
    }

}