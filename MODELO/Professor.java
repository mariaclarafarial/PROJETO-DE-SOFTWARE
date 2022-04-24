package MODELO;

public class Professor {
    private String  Nome;
    private String email_Institucional;
    private String Disciplina_Lecionada;
    

    public Professor(String Nome, String email_Institucional, String Disciplina_Lecionada) {
        this.Nome = Nome;
        this.email_Institucional = email_Institucional;
        this.Disciplina_Lecionada = Disciplina_Lecionada;
    }

    public Professor() {
        this.Nome = "";
        this.email_Institucional = "";
        this.Disciplina_Lecionada = "";
        
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getemail_Institucional() {
        return email_Institucional;
    }

    public void setemail_Institucional(String email_Institucional) {
        this.email_Institucional = email_Institucional;
    }

    public String getDisciplina_Lecionada() {
        return Disciplina_Lecionada;
    }

    public void setDisciplina_Lecionada(String Disciplina_Lecionada) {
        this.Disciplina_Lecionada = Disciplina_Lecionada;
    }

    @Override
    public String toString() {
        return Nome + "|" + email_Institucional + "|" + Disciplina_Lecionada + '|';
    }
}
