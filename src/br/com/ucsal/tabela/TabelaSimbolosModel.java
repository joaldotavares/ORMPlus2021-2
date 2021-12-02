package br.com.ucsal.tabela;

public class TabelaSimbolosModel {
    public Integer numEntrada;
    public String codAtomo;
    public String lexame;
    public Integer QtdCaracterAntesTruncagem;
    public Integer QtdCaracterDepoisTruncagem;
    public String tipo;
    public String linhasOcorrencia;

    public TabelaSimbolosModel(Integer numEntrada, String codAtomo, String lexame, Integer qtdCaracterAntesTruncagem, Integer qtdCaracterDepoisTruncagem, String tipo, String linhasOcorrencia) {
        this.numEntrada = numEntrada;
        this.codAtomo = codAtomo;
        this.lexame = lexame;
        this.QtdCaracterAntesTruncagem = qtdCaracterAntesTruncagem;
        this.QtdCaracterDepoisTruncagem = qtdCaracterDepoisTruncagem;
        this.tipo = tipo;
        this.linhasOcorrencia = linhasOcorrencia;
    }

    public Integer getNumEntrada() {
        return numEntrada;
    }

    public void setNumEntrada(Integer numEntrada) {
        this.numEntrada = numEntrada;
    }

    public String getCodAtomo() {
        return codAtomo;
    }

    public void setCodAtomo(String codAtomo) {
        this.codAtomo = codAtomo;
    }

    public String getLexame() {
        return lexame;
    }

    public void setLexame(String lexame) {
        this.lexame = lexame;
    }

    public Integer getQtdCaracterAntesTruncagem() {
        return QtdCaracterAntesTruncagem;
    }

    public void setQtdCaracterAntesTruncagem(Integer qtdCaracterAntesTruncagem) {
        QtdCaracterAntesTruncagem = qtdCaracterAntesTruncagem;
    }

    public Integer getQtdCaracterDepoisTruncagem() {
        return QtdCaracterDepoisTruncagem;
    }

    public void setQtdCaracterDepoisTruncagem(Integer qtdCaracterDepoisTruncagem) {
        QtdCaracterDepoisTruncagem = qtdCaracterDepoisTruncagem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLinhasOcorrencia() {
        return linhasOcorrencia;
    }

    public void setLinhasOcorrencia(String linhasOcorrencia) {
        this.linhasOcorrencia = linhasOcorrencia;
    }

    @Override
    public String toString() {
        return "{" +
                "numEntrada=" + numEntrada +
                ", codAtomo='" + codAtomo + '\'' +
                ", lexame='" + lexame + '\'' +
                ", QtdCaracterAntesTruncagem=" + QtdCaracterAntesTruncagem +
                ", QtdCaracterDepoisTruncagem=" + QtdCaracterDepoisTruncagem +
                ", tipo='" + tipo + '\'' +
                ", linhasOcorrencia='" + linhasOcorrencia + '\'' +
                '}' + "\n";
    }
}
