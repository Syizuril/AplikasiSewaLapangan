/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasisewalapangan;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.*;
import javax.swing.JDialog;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author Syekh Syihabuddin AU
 */
public class Model {
    Connection con;
    Statement st;
    ResultSet rs;
    
    public Model(){
        String Database = "db_sewa_lapangan";
        String DatabaseUser = "root";
        String DatabasePassword = "";
        String strConn = "jdbc:mysql://localhost/"+ Database +"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
        try {
            con = DriverManager.getConnection(strConn, DatabaseUser, DatabasePassword);
            st = con.createStatement();
        }catch(SQLException e){
            System.err.println("Error: "+e);
        }
    }
    
    public void viewReportShowForm(String strTitleForm, String strQuery, String strReportFileLocation){
        try{
            rs = st.executeQuery(strQuery);
            JasperPrint jasperPrint;
            JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
            JasperReport jasperReport = JasperCompileManager.compileReport(strReportFileLocation);
            jasperPrint = JasperFillManager.fillReport(jasperReport, null,jrRS);
            JRViewer aViewer = new JRViewer(jasperPrint);
            JDialog viewer = new JDialog();
            viewer.setTitle(strTitleForm);
            viewer.setAlwaysOnTop(true);
            viewer.getContentPane().add(aViewer);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            viewer.setBounds(0,0,screenSize.width, screenSize.height);
            viewer.setVisible(true);
        }catch(HeadlessException|SecurityException|SQLException|JRException e){
        }
    }
}
