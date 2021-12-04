package br.com.ucsal.tabela;

import java.util.*;

public class TabelaSimbolos {
    private static Map<String, String> listaPalavrasSimbolosReservados = new HashMap<>();

    private static List<TabelaSimbolosModel> listaTabelaSimbolos = new LinkedList<>();

    static {
        listaPalavrasSimbolosReservados.put("P01", "programa");
        listaPalavrasSimbolosReservados.put("PO2", "declaracoes");
        listaPalavrasSimbolosReservados.put("PO3", "fim-declaracoes");
        listaPalavrasSimbolosReservados.put("PO4", "funcoes");
        listaPalavrasSimbolosReservados.put("PO5", "fim-funcoes");
        listaPalavrasSimbolosReservados.put("PO6", "fim-programa");
        listaPalavrasSimbolosReservados.put("PO7", "tipo-var");
        listaPalavrasSimbolosReservados.put("PO8", "vazio");
        listaPalavrasSimbolosReservados.put("PO9", "real");
        listaPalavrasSimbolosReservados.put("P1O", "inteiro");
        listaPalavrasSimbolosReservados.put("P11", "cadeia");
        listaPalavrasSimbolosReservados.put("P12", "logico");
        listaPalavrasSimbolosReservados.put("P13", "caracter");
        listaPalavrasSimbolosReservados.put("P14", "tipo-func");
        listaPalavrasSimbolosReservados.put("P15", "fim-func");
        listaPalavrasSimbolosReservados.put("P16", "tipo-param");
        listaPalavrasSimbolosReservados.put("P17", "se");
        listaPalavrasSimbolosReservados.put("P18", "fim-se");
        listaPalavrasSimbolosReservados.put("P19", "senao");
        listaPalavrasSimbolosReservados.put("P20", "enquanto");
        listaPalavrasSimbolosReservados.put("P21", "fim-enquanto");
        listaPalavrasSimbolosReservados.put("P22", "retorna");
        listaPalavrasSimbolosReservados.put("P23", "pausa");
        listaPalavrasSimbolosReservados.put("P24", "imprime");
        listaPalavrasSimbolosReservados.put("P25", "true");
        listaPalavrasSimbolosReservados.put("P26", "false");
        listaPalavrasSimbolosReservados.put("S01", ";");
        listaPalavrasSimbolosReservados.put("S02", "[");
        listaPalavrasSimbolosReservados.put("S03", "]");
        listaPalavrasSimbolosReservados.put("S04", ":");
        listaPalavrasSimbolosReservados.put("S05", ",");
        listaPalavrasSimbolosReservados.put("S06", "(");
        listaPalavrasSimbolosReservados.put("S07", ")");
        listaPalavrasSimbolosReservados.put("S08", "?");
        listaPalavrasSimbolosReservados.put("S09", "{");
        listaPalavrasSimbolosReservados.put("S10", "}");
        listaPalavrasSimbolosReservados.put("S11", "<=");
        listaPalavrasSimbolosReservados.put("S12", "<");
        listaPalavrasSimbolosReservados.put("S13", ">");
        listaPalavrasSimbolosReservados.put("S14", ">=");
        listaPalavrasSimbolosReservados.put("S15", "==");
        listaPalavrasSimbolosReservados.put("S16", "!=");
        listaPalavrasSimbolosReservados.put("S17", "#");
        listaPalavrasSimbolosReservados.put("S18", "+");
        listaPalavrasSimbolosReservados.put("S19", "-");
        listaPalavrasSimbolosReservados.put("S20", "*");
        listaPalavrasSimbolosReservados.put("S21", "/");
        listaPalavrasSimbolosReservados.put("S22", "%");
        listaPalavrasSimbolosReservados.put("S23", ":=");
        listaPalavrasSimbolosReservados.put("I01", "nom-programa");
        listaPalavrasSimbolosReservados.put("I02", "variavel");
        listaPalavrasSimbolosReservados.put("I03", "nom-funcao");
        listaPalavrasSimbolosReservados.put("I04", "cons-inteiro");
        listaPalavrasSimbolosReservados.put("I05", "cons-real");
        listaPalavrasSimbolosReservados.put("I06", "cons-cadeia");
        listaPalavrasSimbolosReservados.put("I07", "cons-caracter");
    }

    public Map<String, String> obterTabelaReservada() {
        return listaPalavrasSimbolosReservados;
    }

    public void adicionarToken(String token, String codAtomo, Integer qtdCaracterAntesTruncagem, Integer qtdCaracterDepoisTruncagem, String tipo, String linhasOcorrencia) {
        int indice = listaTabelaSimbolos.size();
        TabelaSimbolosModel elemento = new TabelaSimbolosModel(indice, codAtomo, token, qtdCaracterAntesTruncagem, qtdCaracterDepoisTruncagem, tipo, linhasOcorrencia);
        listaTabelaSimbolos.add(elemento);
    }

    public void atualizaToken(String posicao, TabelaSimbolosModel simbolosModel) {
        String linhasOcorrencia = simbolosModel.getLinhasOcorrencia();
        String[] split = linhasOcorrencia.split(",");
        if (split.length < 5) {
            linhasOcorrencia += ", " + posicao;
        }
        simbolosModel.setLinhasOcorrencia(linhasOcorrencia);
    }

    /*
    Pesquisa se já existe o token na tabela de simbolos e retorna o elemento identificado
     */
    public TabelaSimbolosModel obterTokenExistente(String token) {
        Optional<TabelaSimbolosModel> result = listaTabelaSimbolos.stream().parallel()
                .filter(t -> t.lexame.equals(token)).findFirst();
        return result.isPresent() ? result.get() : null;
    }

    public List<TabelaSimbolosModel> obterTabelaSimbolos() {
        return listaTabelaSimbolos;
    }

    public TabelaSimbolosModel obterUltimoElementoAdicionado() {
        return listaTabelaSimbolos.get(listaTabelaSimbolos.size() - 1);
    }
}
