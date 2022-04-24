package MODELO;

public class Aluno {
    private String  Nome;
    private String email_Institucional;
    private String Curso;
    private String Serie;
    

    public Aluno(String Nome, String email_Institucional, String Curso, String Serie) {
        this.Nome = Nome;
        this.email_Institucional = email_Institucional;
        this.Curso = Curso;
        this.Serie = Serie;
    }

    public Aluno() {
    this.Nome = "";
        this.email_Institucional = "";
        this.Curso = "";
        this.Serie = "";
        
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

    public String getCurso() {
        return Curso;
    }

    public void setCurso(String Curso) {
        this.Curso = Curso;
    }
    public String getSerie() {
        return Serie;
    }

    public void setSerie(String Serie) {
        this.Serie = Serie;
    }

    @Override
    public String toString() {
        return Nome + "|" + email_Institucional + "|" + Curso + "|" + Serie + '|';
    }
}
