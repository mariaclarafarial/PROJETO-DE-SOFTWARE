package DAO;
import MODELO.Datas;
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

public class DatasDAO {
    public boolean inserirDatasDAO (Datas d){ 
        try{
            
            String SQL = "INSERT INTO mariaclara_f.datas (dia, mês, ano) VALUES (?,?,?)";
                
            Connection conectar = conexao_ps.getConexao();
            PreparedStatement comandoSQL = conectar.prepareStatement(SQL);
            comandoSQL.setString(1, d.getDia());
            comandoSQL.setString(2, d.getMês());
            comandoSQL.setString(3, d.getAno());
            int retorno = comandoSQL.executeUpdate();
            
            conectar.close();
            if(retorno>0){
                return true;
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(DatasDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        return false;
    }
public List<Datas> ld(){
        try {
            String SQL = "SELECT dia, mês, ano FROM mariaclara_f.datas";
            List<Datas> listaDeDatas = new ArrayList<Datas>();
            Connection conectar = conexao_ps.getConexao();
            PreparedStatement pre = conectar.prepareStatement(SQL);
            ResultSet Resul = pre.executeQuery();
            
            while(Resul.next()){
                Datas atual = new Datas();
                atual = this.pegaDados(Resul);
                listaDeDatas.add(atual);
            }
            return listaDeDatas;
        } catch (SQLException ex) {
            Logger.getLogger(DatasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}

private Datas pegaDados(ResultSet resultado){
        try {
            Datas a = new Datas();
            a.setDia(resultado.getString("dia"));
            a.setMês(resultado.getString("mês"));
            a.setAno(resultado.getString("ano"));
            return a;
        } catch (SQLException ex) {
            Logger.getLogger(DatasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}
public Datas ConsultaD(Datas d_Dados){
        try {
            String SQL = "SELECT * FROM mariaclara_f.datas";
            Connection conexao = conexao_ps.getConexao();
            String Filtro ="";
            
            if(d_Dados != null && d_Dados.getDia()!= null && !d_Dados.getDia().equalsIgnoreCase("")){
                if(!Filtro.equalsIgnoreCase("")){
                    Filtro += " AND dia ilike '%"+d_Dados.getDia()+"%'";
                }
                else{
                Filtro = " WHERE dia ilike '%" + d_Dados.getDia() +"%'";
                }
            }         
             if(d_Dados != null && d_Dados.getMês()!= null && !d_Dados.getMês().equalsIgnoreCase("")){
                if(!Filtro.equalsIgnoreCase("")){
                    Filtro += " AND mês ilike '%"+d_Dados.getMês()+"%'";
                }
                else{
                Filtro = " WHERE mês ilike '%" + d_Dados.getMês() + "%'";
                }
            }
            if(d_Dados != null && d_Dados.getAno()!= null && !d_Dados.getAno().equalsIgnoreCase("")){
                if(!Filtro.equalsIgnoreCase("")){
                    Filtro += " AND ano ilike '%"+d_Dados.getAno()+"%'";
                }
                else{
                Filtro = " WHERE ano ilike '%" + d_Dados.getAno()+ "%'";
                }
            } 
            
            PreparedStatement pre = conexao.prepareStatement(SQL + Filtro);         
            ResultSet Resul = pre.executeQuery();
            
            if(Resul.next()){
               Datas ld = new Datas();
                ld = this.pegaDados(Resul);
                return ld;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DatasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Datas Consultatual(String Ano){
         try {
            String SQL = "SELECT dia, mês FROM mariaclara_f.datas WHERE ano = ?";
            
            Connection conexao = conexao_ps.getConexao();
            PreparedStatement pre = conexao.prepareStatement(SQL);
            pre.setString(1, Ano);
            ResultSet Resul = pre.executeQuery();
            
           if (Resul.next()){
                Datas dt_ = new Datas();
                dt_ = this.pegaDados(Resul);
                return dt_;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DatasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     
    }
    public boolean ATUALIZAR_DATAS(Datas Dadosd_t){
        try {
            String SQL = "UPDATE mariaclara_f.datas SET dia = ?, mês = ? WHERE ano = ?";
            Connection conexao = conexao_ps.getConexao();
              PreparedStatement comando = conexao.prepareStatement(SQL);

             comando.setString(1, Dadosd_t.getDia());
              comando.setString(2, Dadosd_t.getMês());
               comando.setString(3, Dadosd_t.getAno());

             int retornar = comando.executeUpdate();
             if (retornar > 0){
                   return true;
                }


        } catch (SQLException ex) {
            Logger.getLogger(DatasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
