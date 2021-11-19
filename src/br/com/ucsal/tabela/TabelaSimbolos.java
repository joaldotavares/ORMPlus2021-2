package br.com.ucsal.tabela;

import java.util.ArrayList;
import java.util.List;

public class TabelaSimbolos {

    private static List<String>listaSimbolos = new ArrayList();

    public List<String> obterTabela(){
        return listaSimbolos;
    }

    public void adicionarToken(String token){
        listaSimbolos.add(token);
    }
}
