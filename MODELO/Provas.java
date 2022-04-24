package MODELO;

public class Provas {
    private String  Valor;
    private String Conteudo;
    
    public Provas(String Valor, String Contudo) {
        this.Valor = Valor;
        this.Conteudo = Conteudo;
    }

    public Provas() {
        this.Valor = "";
        this.Conteudo = "";
        
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String Valor) {
        this.Valor = Valor;
    }

    public String getConteudo() {
        return Conteudo;
    }

    public void setConteudo(String Conteudo) {
        this.Conteudo = Conteudo;
    }

    @Override
    public String toString() {
        return Valor + "|" + Conteudo + '|';
    }
}