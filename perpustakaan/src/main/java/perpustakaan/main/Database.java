/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perpustakaan.main;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class Database {
    private final static  String dbhost = "localhost";
    private final static  String dbname = "pbo_perpustakaan";
    private final static  String username = "root";
    private final static  String password = "";
    
    protected Connection connection = null;
    
    protected Statement statement;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;
    
    //Membuka koneksi database.
    protected final void openConnection() {
        try {
            // Bentuk stringnya: "jdbc:mysql://dbhost/dbname?user=username&password=password"
            this.connection = DriverManager.getConnection(
                "jdbc:mysql://"
                + Database.dbhost
                + "/"
                + Database.dbname
                + "?user="
                + Database.username
                + "&password="
                + Database.password
            );
        } catch (SQLException ex) {
            this.displayErrors(ex);
        }
    }
    
    //Menutup koneksi database.
    protected final void closeConnection() {
        try {
            if (this.resultSet != null) this.resultSet.close();
            if (this.statement != null) this.statement.close();
            if (this.preparedStatement != null) this.preparedStatement.close();
            if (this.connection != null) this.connection.close();
            
            this.resultSet = null;
            this.statement = null;
            this.preparedStatement = null;
            this.connection = null;
        } catch (SQLException ex) {
            this.displayErrors(ex);
        }
    }
    
    //Method ALL ambil data return array list
    protected final ArrayList<ArrayList> all(String query) {
        ArrayList<ArrayList> rows = new ArrayList();
        
        try {
            this.statement = this.connection.createStatement();
            this.resultSet = this.statement.executeQuery(query);
            
            while (this.resultSet.next()) {
                // mengambil jumlah kolom yang ada di tabel
                ResultSetMetaData rsMetaData = this.resultSet.getMetaData();
                int columnCount = rsMetaData.getColumnCount();
                
                ArrayList<String> columns = new ArrayList();
                
                for (int i = 1; i <= columnCount; i++) {
                    columns.add(this.resultSet.getString(i));
                }
                
                rows.add(columns);
            }
        } catch (SQLException ex) {
            this.displayErrors(ex);
        }
        
        return rows;
    }
    
    //Untuk mengambil id data yang baru aja di create atau di update.
    protected final int generateLastId() {
        try {
            if (this.statement != null) {
                this.resultSet = this.statement.getGeneratedKeys();
            }
            
            if (this.preparedStatement != null) {
                this.resultSet = this.preparedStatement.getGeneratedKeys();
            }
            
            if (this.resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            this.displayErrors(ex);
        }
        
        return 0;
    }
    
    // Menampilkan error
    protected final void displayErrors(SQLException ex) {
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
    }
}
