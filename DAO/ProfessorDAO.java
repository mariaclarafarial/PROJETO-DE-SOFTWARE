package DAO;
import MODELO.Professor;
import CONEXOES_PS.conexao_ps;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ProfessorDAO {
    public boolean inserirProfessorDAO (Professor p){ 
        try{
            
            String SQL = "INSERT INTO mariaclara_f.professor (nome, email_institucional, disciplina_lecionada) VALUES (?,?,?)";
                
            Connection conectar = conexao_ps.getConexao();
            PreparedStatement comandoSQL = conectar.prepareStatement(SQL);
            comandoSQL.setString(1, p.getNome());
            comandoSQL.setString(2, p.getemail_Institucional());
            comandoSQL.setString(3, p.getDisciplina_Lecionada());
            int retorno = comandoSQL.executeUpdate();
            
            conectar.close();
            if(retorno>0){
                return true;
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        return false;
    }
public List<Professor> lp(){
        try {
            String SQL = "SELECT nome, email_institucional, disciplina_lecionada FROM mariaclara_f.professor";
            List<Professor> listaDeProfessor = new ArrayList<Professor>();
            Connection conectar = conexao_ps.getConexao();
            PreparedStatement pre = conectar.prepareStatement(SQL);
            ResultSet Resul = pre.executeQuery();
            
            while(Resul.next()){
                Professor atual = new Professor();
                atual = this.pegaDados(Resul);
                listaDeProfessor.add(atual);
            }
            return listaDeProfessor;
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}

private Professor pegaDados(ResultSet resultado){
        try {
            Professor p = new Professor();
            p.setNome(resultado.getString("nome"));
            p.setemail_Institucional(resultado.getString("email_institucional"));
            p.setDisciplina_Lecionada(resultado.getString("disciplina_lecionada"));
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}
public Professor ConsultaP(Professor p_Dados){
        try {
            String SQL = "SELECT * FROM mariaclara_f.professor";
            Connection conexao = conexao_ps.getConexao();
            String Filtro ="";
            
            if(p_Dados != null && p_Dados.getNome()!= null && !p_Dados.getNome().equalsIgnoreCase("")){
                if(!Filtro.equalsIgnoreCase("")){
                    Filtro += " AND nome ilike '%"+p_Dados.getNome()+"%'";
                }
                else{
                Filtro = " WHERE nome ilike '%" + p_Dados.getNome() +"%'";
                }
            }         
             if(p_Dados != null && p_Dados.getemail_Institucional()!= null && !p_Dados.getemail_Institucional().equalsIgnoreCase("")){
                if(!Filtro.equalsIgnoreCase("")){
                    Filtro += " AND email_institucional ilike '%"+p_Dados.getemail_Institucional()+"%'";
                }
                else{
                Filtro = " WHERE email_institucional ilike '%" + p_Dados.getemail_Institucional() + "%'";
                }
            }
            if(p_Dados != null && p_Dados.getDisciplina_Lecionada()!= null && !p_Dados.getDisciplina_Lecionada().equalsIgnoreCase("")){
                if(!Filtro.equalsIgnoreCase("")){
                    Filtro += " AND disciplina_lecionada ilike '%"+p_Dados.getDisciplina_Lecionada()+"%'";
                }
                else{
                Filtro = " WHERE disciplina_lecionada ilike '%" + p_Dados.getDisciplina_Lecionada()+ "%'";
                }
            } 
            
            PreparedStatement pre = conexao.prepareStatement(SQL + Filtro);         
            ResultSet Resul = pre.executeQuery();
            
            if(Resul.next()){
               Professor lp = new Professor();
                lp = this.pegaDados(Resul);
                return lp;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Professor Consultatual(String email_Institucional){
         try {
            String SQL = "SELECT nome, disciplina_lecionada FROM mariaclara_f.professor WHERE email_institucional = ?";
            
            Connection conexao = conexao_ps.getConexao();
            PreparedStatement pre = conexao.prepareStatement(SQL);
            pre.setString(1, email_Institucional);
            ResultSet Resul = pre.executeQuery();
            
           if (Resul.next()){
                Professor pr_ = new Professor();
                pr_ = this.pegaDados(Resul);
                return pr_;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     
    }
    public boolean ATUALIZAR_PROFESSOR(Professor Dadosp_r){
        try {
            String SQL = "UPDATE mariaclara_f.professor SET nome = ?, disciplina_lecionada = ? WHERE email_institucional = ?";
            Connection conexao = conexao_ps.getConexao();
              PreparedStatement comando = conexao.prepareStatement(SQL);

             comando.setString(1, Dadosp_r.getNome());
              comando.setString(2, Dadosp_r.getemail_Institucional());
               comando.setString(3, Dadosp_r.getDisciplina_Lecionada());

             int retornar = comando.executeUpdate();
             if (retornar > 0){
                   return true;
                }


        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
