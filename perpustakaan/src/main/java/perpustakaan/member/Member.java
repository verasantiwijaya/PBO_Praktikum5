/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perpustakaan.member;

import perpustakaan.main.Database;

import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author HP
 */
public class Member extends Database{
    private int id;
    
    public String nama;
    public String email;
    public String nomorTelepon;
    
    public int getId() {
        return this.id;
    }
    
    
    //create
    public boolean create() {
        boolean isOperationSuccess = false;
        
        try {
            this.openConnection();
            
            String sql = "INSERT INTO member VALUES (null, ?, ?, ?)";
            this.preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            this.preparedStatement.setString(1, this.nama);
            this.preparedStatement.setString(2, this.email);
            this.preparedStatement.setString(3, this.nomorTelepon);
            
            int result = this.preparedStatement.executeUpdate();
            
            this.id = this.generateLastId();
            
            isOperationSuccess = result > 0;
        } catch (SQLException ex) {
            this.displayErrors(ex);
        } finally {
            this.closeConnection();
        }
        
        return isOperationSuccess;
    }
    
    //find
    public boolean find(int id) {
        boolean isOperationSuccess = false;
        
        try {
            this.openConnection();
            
            this.preparedStatement = this.connection.prepareStatement("SELECT * FROM member WHERE id = ?");
            
            this.preparedStatement.setInt(1, id);
            
            this.resultSet = this.preparedStatement.executeQuery();
            
            if (this.resultSet.next()) {
                this.id = id;
                this.nama = this.resultSet.getString("nama");
                this.email = this.resultSet.getString("email");
                this.nomorTelepon = this.resultSet.getString("no_telepon");
                
                isOperationSuccess = true;
            }
        } catch (SQLException ex) {
            this.displayErrors(ex);
        } finally {
            this.closeConnection();
        }
        
        return isOperationSuccess;
    }
    
    //update
    public boolean update() {
        boolean isOperationSuccess = false;
        
        try {
            this.openConnection();
            
            String sql = "UPDATE member "
                    + "SET nama = ?, "
                    + "email = ?, "
                    + "no_telepon = ? "
                    + "WHERE id = ?";
            
            this.preparedStatement = this.connection.prepareStatement(sql);
            
            this.preparedStatement.setString(1, this.nama);
            this.preparedStatement.setString(2, this.email);
            this.preparedStatement.setString(3, this.nomorTelepon);
            this.preparedStatement.setInt(4, this.id);
            
            int result = this.preparedStatement.executeUpdate();
            
            isOperationSuccess = result > 0;
        } catch (SQLException ex) {
            this.displayErrors(ex);
        } finally {
            this.closeConnection();
        }
        
        return isOperationSuccess;
    }
    
    
    //delete
    public boolean delete() {
        boolean isOperationSuccess = false;
        
        try {
            this.openConnection();
            
            String sql = "DELETE FROM member WHERE id = ?";
            this.preparedStatement = this.connection.prepareStatement(sql);
            
            this.preparedStatement.setInt(1, this.id);
            
            int result = this.preparedStatement.executeUpdate();
            
            isOperationSuccess = result > 0;
        } catch (SQLException ex) {
            this.displayErrors(ex);
        } finally {
            this.closeConnection();
        }
        
        return isOperationSuccess;
    }
}
