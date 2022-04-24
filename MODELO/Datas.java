package MODELO;

public class Datas {
    private String  Dia;
    private String Mês;
    private String Ano;
    

    public Datas(String Dia, String Mês, String Ano) {
        this.Dia = Dia;
        this.Mês = Mês;
        this.Ano = Ano;
    }

    public Datas() {
        this.Dia = "";
        this.Mês = "";
        this.Ano = "";
        
    }

    public String getDia() {
        return Dia;
    }

    public void setDia(String Dia) {
        this.Dia = Dia;
    }

    public String getMês() {
        return Mês;
    }

    public void setMês(String Mês) {
        this.Mês = Mês;
    }

    public String getAno() {
        return Ano;
    }

    public void setAno(String Ano) {
        this.Ano = Ano;
    }

    @Override
    public String toString() {
        return Dia + "|" + Mês + "|" + Ano + '|';
    }
}