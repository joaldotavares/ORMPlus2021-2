package br.com.ucsal.tabela;

import java.util.HashMap;
import java.util.Map;

public class TabelaSimbolos {
    private static Map<String, String> listaSimbolos = new HashMap<>();

    static{
        listaSimbolos.put("P01", "programa");
        listaSimbolos.put("PO2", "declaracoes");
        listaSimbolos.put("PO3", "fim-declaracoes");
        listaSimbolos.put("PO4", "funcoes");
        listaSimbolos.put("PO5", "fim-funcoes");
        listaSimbolos.put("PO6", "fim-programa");
        listaSimbolos.put("PO7", "tipo-var");
        listaSimbolos.put("PO8", "vazio");
        listaSimbolos.put("PO9", "real");
        listaSimbolos.put("P1O", "inteiro");
        listaSimbolos.put("P11", "cadeia");
        listaSimbolos.put("P12", "logico");
        listaSimbolos.put("P13", "caracter");
        listaSimbolos.put("P14", "tipo-func");
        listaSimbolos.put("P15", "fim-func");
        listaSimbolos.put("P16", "tipo-param");
        listaSimbolos.put("P17", "se");
        listaSimbolos.put("P18", "fim-se");
        listaSimbolos.put("P19", "senao");
        listaSimbolos.put("P20", "enquanto");
        listaSimbolos.put("P21", "fim-enquanto");
        listaSimbolos.put("P22", "retorna");
        listaSimbolos.put("P23", "pausa");
        listaSimbolos.put("P24", "imprime");
        listaSimbolos.put("P25", "true");
        listaSimbolos.put("P26", "false");
        listaSimbolos.put("S01", ";");
        listaSimbolos.put("S02", "[");
        listaSimbolos.put("S03", "]");
        listaSimbolos.put("S04", ":");
        listaSimbolos.put("S05", ",");
        listaSimbolos.put("S06", "(");
        listaSimbolos.put("S07", ")");
        listaSimbolos.put("S08", "?");
        listaSimbolos.put("S09", "{");
        listaSimbolos.put("S10", "}");
        listaSimbolos.put("S11", "<=");
        listaSimbolos.put("S12", "<");
        listaSimbolos.put("S13", ">");
        listaSimbolos.put("S14", ">=");
        listaSimbolos.put("S15", "==");
        listaSimbolos.put("S16", "!=");
        listaSimbolos.put("S17", "#");
        listaSimbolos.put("S18", "+");
        listaSimbolos.put("S19", "-");
        listaSimbolos.put("S20", "*");
        listaSimbolos.put("S21", "/");
        listaSimbolos.put("S22", "%");
        listaSimbolos.put("S23", ":=");
        listaSimbolos.put("I01", "nom-programa");
        listaSimbolos.put("I02", "variavel");
        listaSimbolos.put("I03", "nom-funcao");
        listaSimbolos.put("I04", "cons-inteiro");
        listaSimbolos.put("I05", "cons-real");
        listaSimbolos.put("I06", "cons-cadeia");
        listaSimbolos.put("I07", "cons-caracter");
    }

    public Map<String, String> obterTabela(){
        return listaSimbolos;
    }

    public void adicionarToken(String chave, String token){
        listaSimbolos.put(chave, token);
    }
    
}
