package br.com.ucsal.analisador;

import br.com.ucsal.tabela.TabelaSimbolos;

import java.util.Map;

public class AnalisadorLexico {

    private static TabelaSimbolos tabela = new TabelaSimbolos();
    private static Map<String, String> tabelaSimbolos = tabela.obterTabela();

    public boolean verificarDigito(char atomo) {
        return atomo >= '0' && atomo <= '9';
    }

    public boolean verificarCaracter(char atomo) {
        return (atomo >= 'a' && atomo <= 'z') || (atomo >= 'A' && atomo <= 'Z');
    }

    public boolean verificarOperador(char atomo) {
        return atomo == '<' || atomo == '>' || atomo == '=' || atomo == '!' || atomo == '#' || atomo == ':';
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

    public boolean verificarListaParam(char atomo) {
        return atomo == ',' || atomo == ';'|| atomo == '?' || atomo == '.';
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
        return tabelaSimbolos.containsValue(token) || verificarBloco(atomo);
    }

    public boolean verificarCaracterValidoSeguidoPorOperacao(char atomo, char proxAtomo) {
        return verificarDigito(atomo) || verificarCaracter(atomo) && verificarOperador(proxAtomo);
    }

    public boolean verificarCaracterValidoAposOperador(char atomo, char proxAtomo) {
        return verificarOperador(atomo) && verificarCaracter(proxAtomo) || verificarDigito(proxAtomo);
    }

    public boolean verificarMetodoValido(char atomo, char atomosPos){
        if(verificarDigito(atomo) || verificarCaracter(atomo) && verificarBloco(atomosPos)){
            return true;
        }
        return false;
    }

}
