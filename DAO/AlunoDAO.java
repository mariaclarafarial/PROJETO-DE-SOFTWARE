package DAO;
import MODELO.Aluno;
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

public class AlunoDAO {
    public boolean inserirAlunoDAO (Aluno a){ 
        try{
            
            String SQL = "INSERT INTO mariaclara_f.aluno (nome, email_institucional, curso, serie) VALUES (?,?,?,?)";
                
            Connection conectar = conexao_ps.getConexao();
            PreparedStatement comandoSQL = conectar.prepareStatement(SQL);
            comandoSQL.setString(1, a.getNome());
            comandoSQL.setString(2, a.getemail_Institucional());
            comandoSQL.setString(3, a.getCurso());
            comandoSQL.setString(4, a.getSerie());
            int retorno = comandoSQL.executeUpdate();
            
            conectar.close();
            if(retorno>0){
                return true;
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        return false;
    }
public List<Aluno> la(){
        try {
            String SQL = "SELECT nome, email_institucional, curso, serie FROM mariaclara_f.aluno";
            List<Aluno> listaDeAluno = new ArrayList<Aluno>();
            Connection conectar = conexao_ps.getConexao();
            PreparedStatement pre = conectar.prepareStatement(SQL);
            ResultSet Resul = pre.executeQuery();
            
            while(Resul.next()){
                Aluno atual = new Aluno();
                atual = this.pegaDados(Resul);
                listaDeAluno.add(atual);
            }
            return listaDeAluno;
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}

private Aluno pegaDados(ResultSet resultado){
        try {
            Aluno a = new Aluno();
            a.setNome(resultado.getString("nome"));
            a.setemail_Institucional(resultado.getString("email_institucional"));
            a.setCurso(resultado.getString("curso"));
            a.setSerie(resultado.getString("serie"));
            return a;
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}
public Aluno ConsultaA(Aluno a_Dados){
        try {
            String SQL = "SELECT * FROM mariaclara_f.aluno";
            Connection conexao = conexao_ps.getConexao();
            String Filtro ="";
            
            if(a_Dados != null && a_Dados.getNome()!= null && !a_Dados.getNome().equalsIgnoreCase("")){
                if(!Filtro.equalsIgnoreCase("")){
                    Filtro += " AND nome ilike '%"+a_Dados.getNome()+"%'";
                }
                else{
                Filtro = " WHERE nome ilike '%" + a_Dados.getNome() +"%'";
                }
            }         
             if(a_Dados != null && a_Dados.getemail_Institucional()!= null && !a_Dados.getemail_Institucional().equalsIgnoreCase("")){
                if(!Filtro.equalsIgnoreCase("")){
                    Filtro += " AND email_institucional ilike '%"+a_Dados.getemail_Institucional()+"%'";
                }
                else{
                Filtro = " WHERE email_institucional ilike '%" + a_Dados.getemail_Institucional() + "%'";
                }
            }
            if(a_Dados != null && a_Dados.getCurso()!= null && !a_Dados.getCurso().equalsIgnoreCase("")){
                if(!Filtro.equalsIgnoreCase("")){
                    Filtro += " AND curso ilike '%"+a_Dados.getCurso()+"%'";
                }
                else{
                Filtro = " WHERE curso ilike '%" + a_Dados.getCurso()+ "%'";
                }
            } 
             if(a_Dados != null && a_Dados.getSerie()!= null && !a_Dados.getSerie().equalsIgnoreCase("")){
                if(!Filtro.equalsIgnoreCase("")){
                    Filtro += " AND serie ilike '%"+a_Dados.getSerie()+"%'";
                }
                else{
                Filtro = " WHERE serie ilike '%" + a_Dados.getSerie()+ "%'";
                }
            } 
            
            PreparedStatement pre = conexao.prepareStatement(SQL + Filtro);         
            ResultSet Resul = pre.executeQuery();
            
            if(Resul.next()){
               Aluno la = new Aluno();
                la = this.pegaDados(Resul);
                return la;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Aluno Consultatual(String email_Institucional){
         try {
            String SQL = "SELECT nome, curso, serie FROM mariaclara_f.aluno WHERE email_Institucional = ?";
            
            Connection conexao = conexao_ps.getConexao();
            PreparedStatement pre = conexao.prepareStatement(SQL);
            pre.setString(1, email_Institucional);
            ResultSet Resul = pre.executeQuery();
            
           if (Resul.next()){
                Aluno al_ = new Aluno();
                al_ = this.pegaDados(Resul);
                return al_;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     
    }
    public boolean ATUALIZAR_ALUNO(Aluno Dadosa_l){
        try {
            String SQL = "UPDATE mariaclara_f.aluno SET nome = ?, curso = ?, serie = ? WHERE email_institucional = ?";
            Connection conexao = conexao_ps.getConexao();
              PreparedStatement comando = conexao.prepareStatement(SQL);

             comando.setString(1, Dadosa_l.getNome());
              comando.setString(2, Dadosa_l.getemail_Institucional());
               comando.setString(3, Dadosa_l.getCurso());
                comando.setString(4, Dadosa_l.getSerie());

             int retornar = comando.executeUpdate();
             if (retornar > 0){
                   return true;
                }


        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
