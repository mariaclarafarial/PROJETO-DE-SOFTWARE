package CONEXOES_PS;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class conexao_ps {
    private static String url = "jdbc:postgresql://200.18.128.65/aula";
    private static String usuario = "aula";
    private static String senha = "aula";
    
    private static Connection CONEXAO = null;
    
    public static Connection getConexao(){
        try {
            if(conexao_ps.CONEXAO == null){
                conexao_ps.CONEXAO = DriverManager.getConnection(url, usuario, senha);
            } 
            return CONEXAO;
        }
        catch (SQLException ex) {
            Logger.getLogger(conexao_ps.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro de conexão ao banco de dados!" + ex.getMessage());
        }
        return null;
    }
} 