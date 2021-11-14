package br.com.ucsal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static final String PATH = "C:\\Users\\Joaldo\\IdeaProjects\\ORMPlus2021-2\\src\\br\\com\\ucsal\\compiler\\";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(PATH + "Teste.java"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
        }
    }
}
