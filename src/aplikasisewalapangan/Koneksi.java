/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasisewalapangan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Syekh Syihabuddin AU
 */
public class Koneksi {
    Connection con;
    Statement st;
    ResultSet rs;
    private String sql;
    
    public Connection config(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/db_sewa_lapangan","root","");
            return con;
        }catch(SQLException|ClassNotFoundException e){
            int input = JOptionPane.showOptionDialog(null, "Koneksi gagal terhubung, karena "+e.getMessage()+"\n\nIngin mencobanya kembali ?", "Gagal Terhubung", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
            if(input == JOptionPane.OK_OPTION)
            {
             config();
            }else{
             System.exit(0);   
            }
            return null;
        }
    }
    
    public ResultSet selectAllAccount(){
        try{
            con = null;
            con = config();
            sql = "select * from tb_account";
            st=con.createStatement();
            rs=st.executeQuery(sql);
            return rs;
        }catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
            return null;
        } 
    }
    
    public ResultSet selectAllMember(){
        try{
            con = null;
            con = config();
            sql = "select * from tb_member";
            st=con.createStatement();
            rs=st.executeQuery(sql);
            return rs;
        }catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
            return null;
        } 
    }
    
    public ResultSet selectAllPesanan(){
        try{
            con = null;
            con = config();
            sql = "select * from tb_pesan";
            st=con.createStatement();
            rs=st.executeQuery(sql);
            return rs;
        }catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
            return null;
        } 
    }
    
    public int getJmlAdmin(){
        try{
            con = null;
            con = config();
            sql = "select count(id_account) from tb_account where status_account='0'";
            st=con.createStatement();
            rs=st.executeQuery(sql);
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
        return 0;
    }
    
    public int getJmlPetugas(){
        try{
            con = null;
            con = config();
            sql = "select count(id_account) from tb_account where status_account='1'";
            st=con.createStatement();
            rs=st.executeQuery(sql);
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
        return 0;
    }
    
    public int getJmlMember(){
        try{
            con = null;
            con = config();
            sql = "select count(id_member) from tb_member";
            st=con.createStatement();
            rs=st.executeQuery(sql);
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
        return 0;
    }
    
    public int getJmlPesanan(){
        try{
            con = null;
            con = config();
            sql = "select count(id_pesan) from tb_pesan";
            st=con.createStatement();
            rs=st.executeQuery(sql);
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
        return 0;
    }
    
    public String getUsername(String id_account){
        rs = selectAllAccount();
        try{
            while(rs.next()){
                if(id_account.equals(rs.getString("id_account"))){
                    return rs.getString("username");
                }
            }
        }catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
        return null;
    }
    
    public String getNameMember(String id_member){
        rs = selectAllMember();
        try{
            while(rs.next()){
                if(id_member.equals(rs.getString("id_member"))){
                    return rs.getString("nama");
                }
            }
        }catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
        return null;
    }
}
