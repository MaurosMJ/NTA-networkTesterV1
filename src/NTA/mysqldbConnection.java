/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NTA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 320167484
 */
public class mysqldbConnection {
    
    public void databaseM (String url, String usr, String pwd){

        try {
            // Carrega explicitamente o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Estabelece a conexão com o banco de dados
            Connection conexao = DriverManager.getConnection(url, usr, pwd);
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
            conexao.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Não foi possível conectar ao banco de dados.");
            e.printStackTrace();
        }
    }
}
