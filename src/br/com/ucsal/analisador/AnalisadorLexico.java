package br.com.ucsal.analisador;

public class AnalisadorLexico {

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
        return atomo == ',' || atomo == ';'|| atomo == '?';
    }

    public boolean verificarVeriavel(char atomo) {
        return atomo == '_';
    }

    public boolean eof(String line, int aux) {
        return line.length() == aux;
    }
}
