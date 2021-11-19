package br.com.ucsal;

import br.com.ucsal.analisador.AnalisadorLexico;

import java.io.File;
import java.util.Scanner;

public class Main {
    private static AnalisadorLexico al = new AnalisadorLexico();
    private static final String PATH = "C:\\Users\\Joaldo\\IdeaProjects\\ORMPlus2021-2\\src\\br\\com\\ucsal\\compiler\\";

    private static char[] content;

    public static void main(String[] args) throws Exception {

        StringBuffer sb = new StringBuffer();
        String arquivo = "MeuTeste.202";
        Scanner scanner = new Scanner(new File(PATH + arquivo));

        int aux = 0, linha = 0;
        while (scanner.hasNextLine()) {
            linha++;
            String line = scanner.nextLine();
            content = line.toCharArray();
            char atomo;
            StringBuffer token = new StringBuffer();

            System.out.println(line + " Fim da Linha");
            while (aux < content.length) {
                atomo = content[aux];

                if (al.verificarDigito(atomo) || al.verificarCaracter(atomo) || al.verificarOperador(atomo) ||
                        al.verificarOperadorSoma(atomo) || al.verificarOperadorMult(atomo) || al.verificarVeriavel(atomo)
                        && !al.verificarEspaco(atomo)) {
                    if (line.length() != (aux + 1)) {
                        token.append(atomo);
                    } else if (al.eof(line, (aux + 1))) {
                        token.append(atomo);
                        System.out.println(token + " token de fim da linha");
                    }
                } else {
                    System.out.println(token);
                    token.delete(0, token.length());
                    if (token.length() == 0 && !al.verificarEspaco(atomo)) {
                        throw new Exception("Atomo '" + atomo + "' da linha " + linha + " não pertence a linguagem" +
                                "\nArquivo: " + arquivo);
                    }
                }
                aux++;
            }
            aux = 0;
        }
    }
}