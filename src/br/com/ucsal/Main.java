package br.com.ucsal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static final String PATH = "C:\\Users\\Joaldo\\IdeaProjects\\ORMPlus2021-2\\src\\br\\com\\ucsal\\compiler\\";

    private static char[] content;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(PATH + "MeuTeste.202"));
        int aux = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            content = line.toCharArray();
            char c;
            StringBuffer token = new StringBuffer();
            while (aux < content.length) {
                c = content[aux];

                if (c != ' ') {
                    token.append(c);
                } else {
                    token.delete(0, token.length());
                }

                aux++;
               // System.out.print(c + " atomo - " + token + " / ");
            }
            aux = 0;
            System.out.println(line + " Linha ");
        }
    }
}