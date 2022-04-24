package DAO;
import MODELO.Provas;
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

public class ProvasDAO {
    public boolean inserirProvasDAO (Provas ps){ 
        try{
            
            String SQL = "INSERT INTO mariaclara_f.provas (valor, conteudo) VALUES (?,?)";
                
            Connection conectar = conexao_ps.getConexao();
            PreparedStatement comandoSQL = conectar.prepareStatement(SQL);
            comandoSQL.setString(1, ps.getValor());
            comandoSQL.setString(2, ps.getConteudo());
            int retorno = comandoSQL.executeUpdate();
            
            conectar.close();
            if(retorno>0){
                return true;
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(ProvasDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        return false;
    }
public List<Provas> lps(){
        try {
            String SQL = "SELECT valor, conteudo FROM mariaclara_f.provas";
            List<Provas> listaDeProvas = new ArrayList<Provas>();
            Connection conectar = conexao_ps.getConexao();
            PreparedStatement pre = conectar.prepareStatement(SQL);
            ResultSet Resul = pre.executeQuery();
            
            while(Resul.next()){
                Provas atual = new Provas();
                atual = this.pegaDados(Resul);
                listaDeProvas.add(atual);
            }
            return listaDeProvas;
        } catch (SQLException ex) {
            Logger.getLogger(ProvasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}

private Provas pegaDados(ResultSet resultado){
        try {
            Provas ps = new Provas();
            ps.setValor(resultado.getString("valor"));
            ps.setConteudo(resultado.getString("conteudo"));
            return ps;
        } catch (SQLException ex) {
            Logger.getLogger(ProvasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}
public Provas ConsultaPS(Provas ps_Dados){
        try {
            String SQL = "SELECT * FROM mariaclara_f.provas";
            Connection conexao = conexao_ps.getConexao();
            String Filtro ="";
            
            if(ps_Dados != null && ps_Dados.getValor()!= null && !ps_Dados.getValor().equalsIgnoreCase("")){
                if(!Filtro.equalsIgnoreCase("")){
                    Filtro += " AND valor ilike '%"+ps_Dados.getValor()+"%'";
                }
                else{
                Filtro = " WHERE valor ilike '%" + ps_Dados.getValor() +"%'";
                }
            }         
             if(ps_Dados != null && ps_Dados.getConteudo()!= null && !ps_Dados.getConteudo().equalsIgnoreCase("")){
                if(!Filtro.equalsIgnoreCase("")){
                    Filtro += " AND conteudo ilike '%"+ps_Dados.getConteudo()+"%'";
                }
                else{
                Filtro = " WHERE conteudo ilike '%" + ps_Dados.getConteudo() + "%'";
                }
            }
            
            PreparedStatement pre = conexao.prepareStatement(SQL + Filtro);         
            ResultSet Resul = pre.executeQuery();
            
            if(Resul.next()){
               Provas lps = new Provas();
                lps = this.pegaDados(Resul);
                return lps;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ProvasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Provas Consultatual(String Conteudo){
         try {
            String SQL = "SELECT valor FROM mariaclara_f.provas WHERE conteudo = ?";
            
            Connection conexao = conexao_ps.getConexao();
            PreparedStatement pre = conexao.prepareStatement(SQL);
            pre.setString(1, Conteudo);
            ResultSet Resul = pre.executeQuery();
            
           if (Resul.next()){
                Provas ps_ = new Provas();
                ps_ = this.pegaDados(Resul);
                return ps_;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ProvasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     
    }
    public boolean ATUALIZAR_PROVAS(Provas Dadosp_s){
        try {
            String SQL = "UPDATE mariaclara_f.provas SET valor = ? WHERE conteudo = ?";
            Connection conexao = conexao_ps.getConexao();
              PreparedStatement comando = conexao.prepareStatement(SQL);

             comando.setString(1, Dadosp_s.getValor());
              comando.setString(2, Dadosp_s.getConteudo());

             int retornar = comando.executeUpdate();
             if (retornar > 0){
                   return true;
                }


        } catch (SQLException ex) {
            Logger.getLogger(ProvasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}