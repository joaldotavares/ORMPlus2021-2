package br.com.ucsal.analisador;

import br.com.ucsal.tabela.TabelaSimbolos;
import br.com.ucsal.tabela.TabelaSimbolosModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class AnalisadorLexico {

    private static final String PATH = "..\\ORMPlus2021-2\\src\\br\\com\\ucsal\\compiler\\";
    private static TabelaSimbolos tabelaSimbolos = new TabelaSimbolos();
    private static Map<String, String> tabelaReservada = tabelaSimbolos.obterTabelaReservada();

    public boolean verificarDigito(char atomo) {
        return atomo >= '0' && atomo <= '9';
    }

    public boolean verificarCaracter(char atomo) {
        return (atomo >= 'a' && atomo <= 'z') || (atomo >= 'A' && atomo <= 'Z' || atomo == '.');
    }

    public boolean verificarOperador(char atomo) {
        return atomo == '<' || atomo == '>' || atomo == '=' || atomo == '!' || atomo == '#' || atomo == ':' ;
    }

    public boolean verificarEspaco(char atomo) {
        return atomo == ' ' || atomo == '\t' || atomo == '\n' || atomo == '\r';
    }

    public boolean verificarOperadorMult(char atomo) {
        return atomo == '*' || atomo == '/' || atomo == '%';
    }

    public boolean verificarOperadorSoma(char atomo) {
        return atomo == '+' || atomo == '-';
    }

    public boolean verificarBloco(char atomo) {
        return atomo == '{' || atomo == '}' || atomo == '[' || atomo == ']' || atomo == '(' || atomo == ')';
    }

    public boolean verificarAspas(char atomo) {
        return atomo == '"';
    }

    public boolean verificarListaParam(char atomo) {
        return atomo == ',' || atomo == ';' || atomo == '?';
    }

    public boolean verificarVariavel(char atomo) {
        return atomo == '_';
    }

    public boolean eof(String line, int aux) {
        return line.length() == aux;
    }

    public boolean verificarComentarioInicioLinha(char atomo, char proxAtomo) {
        return (atomo == '/' && proxAtomo == '/');
    }

    public boolean verificarComentarioInicioBloco(char atomo, char proxAtomo) {
        return (atomo == '/' && proxAtomo == '*');
    }

    public boolean verificarComentarioFimBloco(char atomo, char proxAtomo) {
        return (atomo == '*' && proxAtomo == '/');
    }

    public boolean verificarPalavrasReservadasSeguidoDeFuncao(char atomo, StringBuffer token) {
        return tabelaReservada.containsValue(token) || verificarBloco(atomo);
    }

    public boolean verificarCaracterValidoSeguidoPorOperacao(char atomo, char proxAtomo) {
        if(!verificarOperador(proxAtomo)){
            return false;
        }
        return verificarDigito(atomo) || verificarCaracter(atomo) && verificarOperador(proxAtomo);
    }

    public boolean verificarCaracterValidoAposOperador(char atomo, char proxAtomo) {
        if(!verificarOperador(atomo)){
            return false;
        }
        return verificarOperador(atomo) && verificarCaracter(proxAtomo) || verificarDigito(proxAtomo);
    }

    // TODO Verificar se atende as regras
    public String obterCodigoAtomo(String token) {
        for (Map.Entry<String, String> entry : tabelaReservada.entrySet()) {
            if (entry.getValue().equals(token)) {
                return entry.getKey();
            }
        }
        return "PO2"; // declaraçoes
    }

    // TODO Verificar as regras para tipo
    public String obterTipoAtomo(String token, String codAtomo) {
      if(verificarInteiro(token)){
          return "inteiro";
      }else if(verificarReal(token)){
          return "ponto flutuante";
      }else if(verificarBoolean(token)){
          return "booleano";
      }else if(verificarString(token)){
          return "string";
      }
      return "";
    }

    public String trucarToken(String token) {
        return token.substring(0, 30);
    }

    public void salvarToken(String token, int numLinha, int posicao) {
        TabelaSimbolosModel tokenExistente = tabelaSimbolos.obterTokenExistente(token);
        String posicaoLinha = numLinha + ":" + (posicao - token.length() + 1);
        if (tokenExistente == null) {
            String codAtomo = obterCodigoAtomo(token);
            String tipoAtomo = obterTipoAtomo(token, codAtomo);
            Integer tamanhoTotalToken = token.length();
            Integer tamanhoToken = null;
            if (tamanhoTotalToken > 30) {
                token = trucarToken(token);
                tamanhoToken = token.length();
            }
            tabelaSimbolos.adicionarToken(token, codAtomo, tamanhoTotalToken, tamanhoToken, tipoAtomo, posicaoLinha);
        } else {
            tabelaSimbolos.atualizaToken(posicaoLinha, tokenExistente);
        }

    }

    public void limparLista(){
        tabelaSimbolos.clean();
    }

    public static boolean verificarInteiro(String atomo){
        return atomo.chars().allMatch( Character::isDigit );
    }

    private static boolean verificarString(String atomo){
        return atomo.chars().allMatch( Character::isLetterOrDigit ) || atomo.startsWith("\"") && atomo.endsWith("\"");
    }

    private static boolean verificarBoolean(String atomo){
        atomo.trim();
        return atomo.equals("true") || atomo.equals("false");
    }

    private static boolean verificarReal(String atomo){
        float numero = 0.0F;
        try {
            numero = Float.parseFloat(atomo);
        }catch (Exception e){

        }
        return numero != Math.floor(numero);
    }

    public static void gerarArquivoTAB() throws IOException {
        String arquivoTabela = "resultado\\MeuTeste.TAB";
        File arq = new File(PATH + arquivoTabela);
        arq.createNewFile();
        FileWriter fw = new FileWriter( arq );
        BufferedWriter bw = new BufferedWriter( fw );

        for (TabelaSimbolosModel tab: tabelaSimbolos.obterTabelaSimbolos() ) {
            bw.write(String.valueOf(tab));
            bw.newLine();
        }
        bw.close();
        fw.close();
        System.out.println(tabelaSimbolos.obterTabelaSimbolos());
    }

    public static void gerarArquivoLex() throws IOException {
        String arquivoTabela = "resultado\\MeuTeste.lex";
        File arq = new File(PATH + arquivoTabela);
        arq.createNewFile();
        FileWriter fw = new FileWriter( arq );
        BufferedWriter bw = new BufferedWriter( fw );
        bw.write("E05");
        bw.newLine();
        bw.write("==================================");
        bw.newLine();
        bw.write("COMPONENTES:");
        bw.newLine();
        bw.write("Joaldo Tavares Da Silva Junior\tjoaldo.junior@ucsal.edu.br\t(71) 99658-3398");
        bw.newLine();
        bw.write("Renato Russo Gomes De Oliveira\trenato.oliveira@ucsal.edu.br\t(71) 99941-6735");
        bw.newLine();
        bw.write("Yla Maria Buri S. Dos Santos\tyla.santos@ucsal.edu.br\t(71) 98323-4221");
        bw.newLine();
        bw.write("Ezequias Sampaio Ferreira\tezequias.ferreira@ucsal.edu.br\t(71) 99381-9241");
        bw.newLine();
        bw.write("==================================");
        bw.newLine();
        for (TabelaSimbolosModel tab: tabelaSimbolos.obterTabelaSimbolos() ) {
            bw.write(String.valueOf(tab));
            bw.newLine();
        }
        bw.close();
        fw.close();
        System.out.println(tabelaSimbolos.obterTabelaSimbolos());
    }
}
