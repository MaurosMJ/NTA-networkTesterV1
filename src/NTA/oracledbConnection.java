/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NTA;

import java.sql.Connection; import java.sql.DriverManager;import java.sql.ResultSet;
 import java.sql.SQLException;import java.sql.Statement;
 import java.util.Properties;


/**
 *
 * @author 320167484
 */
public class oracledbConnection {
    
    public void databaseM (String url, String usr, String pwd){
        try {
            // Carrega explicitamente o driver JDBC Oracle
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Estabelece a conexão com o banco de dados
            Connection conexao = DriverManager.getConnection(url, usr, pwd);
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
            //conexao.close();
            
                        // Exemplo de consulta SELECT
            String consulta = "select * from log_tasy where cd_log = 4444 and dt_atualizacao >= sysdate-1 order by dt_atualizacao;";
            Statement stmt = conexao.createStatement();
            ResultSet resultado = stmt.executeQuery(consulta);

            // Processa os resultados da consulta
            while (resultado.next()) {
                // Aqui você pode acessar os resultados das colunas
                //int id = resultado.getInt("id");
                //String nome = resultado.getString("nome");
                // Faça o que precisar com os resultados
                //System.out.println("ID: " + id + ", Nome: " + nome);
            }
            // Lembre-se de fechar os recursos ao final
            resultado.close();
            stmt.close();
            conexao.close();
            
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Não foi possível conectar ao banco de dados.");
            e.printStackTrace();
        }
    }
}
