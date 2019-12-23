/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasisewalapangan;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;

/**
 *
 * @author Syekh Syihabuddin AU (171023), Leomongga Oktaria Sihombing (171123), Ryandi Johannsah P (171191)
 */
public class TampilPetugas extends javax.swing.JFrame {
    Koneksi DB = new Koneksi();
    Model model = new Model();
    Connection con;
    Statement st;
    ResultSet rs;
    private String sql=null;
    private int statusLogin=0;
    String id_account=null;
    InputPetugas ip;
    
    /**
     * Creates new form DashboardAdmin
     */
    public TampilPetugas() {
        initComponents();
        tambahan();
    }
        
    public TampilPetugas(int status, String id_account){
        this.statusLogin = status;
        this.id_account = id_account;
        initData();
    }
    
    public void tambahan(){
        this.setTitle("Tampil Data Petugas - Admin");
        this.setLocationRelativeTo(null);
        if(con==null){
            con = DB.config();
        }
        try{
            sql = "select status_account from tb_account where id_account='"+id_account+"'";
            st =con.createStatement();
            rs =st.executeQuery(sql);
            if(rs.next()){
                int status = rs.getInt("status_account");
                if(status==0){
                    printBT.setVisible(true);
                    adminRB.setVisible(true);
                    petugasRB.setVisible(true);
                }else{
                    printBT.setVisible(false);
                    adminRB.setVisible(false);
                    petugasRB.setVisible(false);
                }
            }
        }catch(SQLException e){
            System.err.println("Error : "+e.getMessage());
        }
    }
    
    public void initData(){
        initComponents();
        tambahan();
        haiLabel.setText("Hallo, "+DB.getUsername(id_account));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        printPilihan = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        haiLabel = new javax.swing.JLabel();
        haiLabel1 = new javax.swing.JLabel();
        eFootsallLabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pegawaiTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        hapusBT = new javax.swing.JButton();
        editBT = new javax.swing.JButton();
        signOutLabel = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        printBT = new javax.swing.JButton();
        adminRB = new javax.swing.JRadioButton();
        petugasRB = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Input Pegawai Baru");

        haiLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        haiLabel.setForeground(new java.awt.Color(255, 255, 255));
        haiLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        haiLabel.setText("Hallo");

        haiLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        haiLabel1.setForeground(new java.awt.Color(255, 255, 255));
        haiLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        haiLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gambar1.png"))); // NOI18N

        eFootsallLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        eFootsallLabel.setForeground(new java.awt.Color(255, 255, 255));
        eFootsallLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        eFootsallLabel.setText("e-Footsall");

        jPanel5.setForeground(new java.awt.Color(0, 102, 153));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Dashboard");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(0, 102, 153));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Kelola Petugas");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 153));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Kelola Data Pesanan");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        pegawaiTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(pegawaiTable);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 102, 153));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/two-arrows24.png"))); // NOI18N
        jButton1.setText(" KEMBALI");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        hapusBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        hapusBT.setForeground(new java.awt.Color(0, 102, 153));
        hapusBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete24.png"))); // NOI18N
        hapusBT.setText("HAPUS");
        hapusBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusBTActionPerformed(evt);
            }
        });

        editBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        editBT.setForeground(new java.awt.Color(0, 102, 153));
        editBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit24.png"))); // NOI18N
        editBT.setText("EDIT");
        editBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jScrollPane2)
                .addGap(11, 11, 11))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(editBT, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(hapusBT, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(hapusBT)
                    .addComponent(editBT))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addContainerGap())
        );

        signOutLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        signOutLabel.setForeground(new java.awt.Color(255, 255, 255));
        signOutLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        signOutLabel.setText("Sign Out");
        signOutLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signOutLabelMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 153));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Kelola Member");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        printBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        printBT.setForeground(new java.awt.Color(0, 102, 153));
        printBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer (3).png"))); // NOI18N
        printBT.setText(" PRINT");
        printBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBTActionPerformed(evt);
            }
        });

        printPilihan.add(adminRB);
        adminRB.setForeground(new java.awt.Color(255, 255, 255));
        adminRB.setText("Admin");
        adminRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminRBActionPerformed(evt);
            }
        });

        printPilihan.add(petugasRB);
        petugasRB.setForeground(new java.awt.Color(255, 255, 255));
        petugasRB.setText("Petugas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(310, 310, 310)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(haiLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(signOutLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(haiLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(eFootsallLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(adminRB)
                        .addGap(9, 9, 9)
                        .addComponent(petugasRB)
                        .addGap(18, 18, 18)
                        .addComponent(printBT, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)))
                .addGap(50, 50, 50))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(haiLabel1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(haiLabel)
                        .addGap(1, 1, 1)
                        .addComponent(signOutLabel)
                        .addGap(13, 13, 13)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(eFootsallLabel)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(printBT)
                    .addComponent(adminRB)
                    .addComponent(petugasRB))
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if(statusLogin==0){
            JOptionPane.showMessageDialog(this, "Anda belum melakukan login, \nSilahkan login terlebih dahulu!", "Autentikasi Gagal", JOptionPane.ERROR_MESSAGE);
            this.setVisible(false);
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new Login().setVisible(true);
                }
            });
        }
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ip = new InputPetugas(statusLogin,id_account);
                ip.setVisible(true);
            }
        });
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:this.setVisible(false);
        this.setVisible(false);
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    DashboardAdmin da = new DashboardAdmin(statusLogin,id_account);
                    da.setVisible(true);
                }
            });
    }//GEN-LAST:event_jLabel2MouseClicked

    private void hapusBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusBTActionPerformed
        if(pegawaiTable.getSelectedRow()>=0){
            int column = 0;
            int row = pegawaiTable.getSelectedRow();
            String id = pegawaiTable.getModel().getValueAt(row, column).toString();
            int reply = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini ?","Konfirmasi Hapus Data", JOptionPane.YES_NO_OPTION);
            if(reply==JOptionPane.YES_OPTION){
                try{
                    if(con==null){
                        con = null;
                        con = DB.config();
                    }
                    sql = "delete from tb_account where id_account='"+id+"'";
                    st = con.createStatement();
                    st.execute(sql);
                }catch(SQLException e){
                    System.err.println("Error : "+e);
                }
                ip = new InputPetugas(statusLogin,id_account);
                pegawaiTable.setModel(ip.modelPegawai);
                ip.viewDataTable();
            }else{
                setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            }
            
        }else{
            JOptionPane.showMessageDialog(this, "Silahkan klik baris pada table yang ingin dihapus", "Perhatian !", WARNING_MESSAGE);
        }
    }//GEN-LAST:event_hapusBTActionPerformed

    private void editBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBTActionPerformed
        if(pegawaiTable.getSelectedRow()>=0){
            this.setVisible(false);
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        int column = 0;
                        int row = pegawaiTable.getSelectedRow();
                        ip = new InputPetugas(statusLogin, id_account,pegawaiTable.getModel().getValueAt(row, column).toString());
                        ip.setVisible(true);
                    }
                });
        }else{
            JOptionPane.showMessageDialog(this, "Silahkan klik baris pada table yang ingin diedit", "Perhatian !", WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editBTActionPerformed

    private void signOutLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signOutLabelMouseClicked
        this.setVisible(false);
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        Login ln = new Login();
                        ln.setVisible(true);
                    }
                });
    }//GEN-LAST:event_signOutLabelMouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        this.setVisible(false);
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    InputPesanan ip = new InputPesanan(statusLogin,id_account);
                    ip.setVisible(true);
                }
            });
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        this.setVisible(false);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InputPetugas ip = new InputPetugas(statusLogin,id_account);
                ip.setVisible(true);
            }
        });
    }//GEN-LAST:event_jLabel6MouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int reply = JOptionPane.showConfirmDialog(this, "Yakin ingin keluar dari Aplikasi ?","Konfirmasi Keluar", JOptionPane.YES_NO_OPTION);
            if(reply==JOptionPane.YES_OPTION){
                System.exit(0);
            }else{
                setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            }
    }//GEN-LAST:event_formWindowClosing

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        this.setVisible(false);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InputMember im = new InputMember(statusLogin,id_account);
                im.setVisible(true);
            }
        });
    }//GEN-LAST:event_jLabel8MouseClicked

    private void printBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBTActionPerformed
        if((!adminRB.isSelected())&&(!petugasRB.isSelected())){
            JOptionPane.showMessageDialog(this, "Silahkan pilih data admin atau petugas untuk dicetak", "Perhatian", JOptionPane.WARNING_MESSAGE);
        }else if(adminRB.isSelected()){
            int jmlAdmin = DB.getJmlAdmin();
            if(jmlAdmin==0){
                JOptionPane.showMessageDialog(this, "Tidak ada dalam data Admin, Silahkan isi data terlebih dahulu !", "Gagal !", JOptionPane.ERROR_MESSAGE);
            }else{
                try{
                model.viewReportShowForm("Laporan Data Admin", "SELECT * FROM tb_account where status_account='0'", "./src/aplikasisewalapangan/DataPetugas.jrxml");
                }catch(Exception e){
                    JOptionPane.showMessageDialog(rootPane, e);
                }
            }
        }else if(petugasRB.isSelected()){
            int jmlPetugas = DB.getJmlPetugas();
            if(jmlPetugas==0){
                JOptionPane.showMessageDialog(this, "Tidak ada dalam data Petugas, Silahkan isi data terlebih dahulu !", "Gagal !", JOptionPane.ERROR_MESSAGE);
            }else{
                try{
                model.viewReportShowForm("Laporan Data Petugas", "SELECT * FROM tb_account where status_account='1'", "./src/aplikasisewalapangan/DataAdmin.jrxml");
                }catch(Exception e){
                    JOptionPane.showMessageDialog(rootPane, e);
                }
            }
        }
    }//GEN-LAST:event_printBTActionPerformed

    private void adminRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminRBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adminRBActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TampilPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TampilPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TampilPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TampilPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TampilPetugas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton adminRB;
    private javax.swing.JLabel eFootsallLabel;
    private javax.swing.JButton editBT;
    private javax.swing.JLabel haiLabel;
    private javax.swing.JLabel haiLabel1;
    private javax.swing.JButton hapusBT;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable pegawaiTable;
    private javax.swing.JRadioButton petugasRB;
    private javax.swing.JButton printBT;
    private javax.swing.ButtonGroup printPilihan;
    private javax.swing.JLabel signOutLabel;
    // End of variables declaration//GEN-END:variables
}
