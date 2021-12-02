package br.com.ucsal.analisador;

import br.com.ucsal.tabela.TabelaSimbolos;
import br.com.ucsal.tabela.TabelaSimbolosModel;

import java.util.Map;

public class AnalisadorLexico {

    private static TabelaSimbolos tabelaSimbolos = new TabelaSimbolos();
    private static Map<String, String> tabelaReservada = tabelaSimbolos.obterTabelaReservada();

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
        return atomo == ',' || atomo == ';' || atomo == '?' || atomo == '.';
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
        return verificarDigito(atomo) || verificarCaracter(atomo) && verificarOperador(proxAtomo);
    }

    public boolean verificarCaracterValidoAposOperador(char atomo, char proxAtomo) {
        return verificarOperador(atomo) && verificarCaracter(proxAtomo) || verificarDigito(proxAtomo);
    }

    public boolean verificarMetodoValido(char atomo, char atomosPos) {
        if (verificarDigito(atomo) || verificarCaracter(atomo) && verificarBloco(atomosPos)) {
            return true;
        }
        return false;
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
       /* if (codAtomo.equals("PO2")) {
            TabelaSimbolosModel ultimoElemento = tabelaSimbolos.obterUltimoElementoAdicionado();
            return tabelaReservada.get(ultimoElemento.codAtomo);
        }*/
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

}
