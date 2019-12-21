/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasisewalapangan;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Syekh Syihabuddin AU (171023), Leomongga Oktaria Sihombing (171123), Ryandi Johannsah P (171191)
 */
public class InputPetugas extends javax.swing.JFrame {
    Koneksi DB = new Koneksi();
    Connection con;
    Statement st;
    ResultSet rs;
    String sql;
    private int statusLogin=0;
    String id_account=null;
    DefaultTableModel modelPegawai;
    TampilPetugas tp;
    private String id_temp=null;
    private int edit=0;
    /**
     * Creates new form DashboardAdmin
     */
    public InputPetugas() {
        initComponents();
        DB.config();
        con = DB.con;
        this.setTitle("Input Data Petugas - Admin");
        this.setLocationRelativeTo(null);
        kdPegawaiTF.setText(null);
        kdPegawaiTF.setEditable(false);
    }
    
    public InputPetugas(int status, String id_account){
        this.statusLogin = status;
        this.id_account = id_account;
        initData();
    }
    
    public void hapus(){
        java.util.Date date=new java.util.Date();  
        daftarDate.setDate(date);
        if(stAccountCB.getSelectedItem()=="Administrator"){            
            kdPegawaiTF.setText("AD"+String.valueOf(String.format("%03d", DB.getJmlAdmin()+1)));
        }else{
            kdPegawaiTF.setText("PG"+String.valueOf(String.format("%03d", DB.getJmlPetugas()+1)));
        }
        namaTF.setText(null);
        passwordTF.setText(null);
        password2TF.setText(null);
        noTelpTF.setText(null);
        alamatTF.setText(null);
    }
    
    public InputPetugas(int status, String id_account, String id_temp){
        this.statusLogin = status;
        this.id_account = id_account;
        this.id_temp = id_temp;
        initDataEdit();
    }
    
    public void clear(){
        this.setTitle("Input Data Petugas - Admin");
        this.setLocationRelativeTo(null);
        viewDataTable();
    }
    
    public void initData(){
        initComponents();
        clear();
        if(stAccountCB.getSelectedItem()=="Administrator"){            
            kdPegawaiTF.setText("AD"+String.valueOf(String.format("%03d", DB.getJmlAdmin()+1)));
        }else{
            kdPegawaiTF.setText("PG"+String.valueOf(String.format("%03d", DB.getJmlPetugas()+1)));
        }
        haiLabel.setText("Hallo, "+DB.getUsername(id_account));
        kdPegawaiTF.setEditable(false);
        java.util.Date date=new java.util.Date();  
        daftarDate.setDate(date);
    }
    
    public void initDataEdit(){
        initComponents();
        clear();
        rs = DB.selectAllAccount();
        try{
            while(rs.next()){
                if(id_temp.equals(rs.getString("id_account"))){
                    haiLabel.setText("Hallo, "+DB.getUsername(id_account));
                    daftarDate.setDate(rs.getDate("create_date"));
                    kdPegawaiTF.setText(rs.getString("id_account"));
                    namaTF.setText(rs.getString("username"));
                    noTelpTF.setText(rs.getString("no_telp"));
                    alamatTF.setText(rs.getString("alamat"));
                    edit=1;
                    kdPegawaiTF.setEditable(false);
                }
            }
        }catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
    }
    
    private void clearTabel(){
        int row = modelPegawai.getRowCount();
        for (int i = 0; i < row; i++) {
          modelPegawai.removeRow(0);
        }
    }
    
    public final void viewDataTable(){
        
        String[] namakolom={"Kode Pegawai","Tanggal Daftar","Nama","No. Telp","Alamat","Terakhir Login"};
        modelPegawai = new DefaultTableModel(null, namakolom){
            Class[]types = new Class[]{
               java.lang.String.class,
               java.lang.String.class,
               java.lang.String.class,
               java.lang.String.class,
               java.lang.String.class,
               java.lang.String.class
            };
            public Class getColumnClass (int columnIndex){
               return types [columnIndex];
            }
            //agar tabel tidak bisa diedit
            public boolean isCellEditable(int row, int col){
            int cola = modelPegawai.getColumnCount();
            return (col < cola)? false : true;
            }
        };
        try{
            if(con==null){
                con = null;
                con = DB.config();
            }
            clearTabel();
            sql = " select * from tb_account order by id_account asc";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()){
                String id_account = rs.getString("id_account");
                String create_date = String.valueOf(new SimpleDateFormat("dd-MMM-yyyy").format(rs.getDate("create_date")));
                String username = rs.getString("username");
                String no_telp = rs.getString("no_telp");
                String alamat = rs.getString("alamat");
                String last_login = String.valueOf(new SimpleDateFormat("dd-MMM-yyyy").format(rs.getDate("last_login")));

                Object[]data = {id_account, create_date, username, no_telp, alamat, last_login};
                modelPegawai.addRow(data);
                tp = new TampilPetugas(statusLogin, this.id_account);
                tp.pegawaiTable.setModel(modelPegawai);
              }
        } catch(SQLException e){
          JOptionPane.showMessageDialog(this, "Error :"+e);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jLabel3 = new javax.swing.JLabel();
        daftarDate = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        kdPegawaiTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        namaTF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        passwordTF = new javax.swing.JPasswordField();
        password2TF = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        alamatTF = new javax.swing.JTextArea();
        noTelpTF = new javax.swing.JTextField();
        saveBT = new javax.swing.JButton();
        seeBT = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        stAccountCB = new javax.swing.JComboBox<>();
        signOutLabel = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();

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
        jLabel7.setText("Kelola Member");
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

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tanggal Daftar                      :");

        daftarDate.setBackground(new java.awt.Color(51, 51, 51));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Kode Pegawai                       :");

        kdPegawaiTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kdPegawaiTFActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nama                                    :");

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Password                              :");

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Konfirmasi Password            :");

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Nomor Telepon                    :");

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Alamat                                 :");

        alamatTF.setColumns(20);
        alamatTF.setRows(5);
        jScrollPane1.setViewportView(alamatTF);

        noTelpTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                noTelpTFKeyTyped(evt);
            }
        });

        saveBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        saveBT.setForeground(new java.awt.Color(0, 102, 153));
        saveBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save24.png"))); // NOI18N
        saveBT.setText("SIMPAN");
        saveBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBTActionPerformed(evt);
            }
        });

        seeBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        seeBT.setForeground(new java.awt.Color(0, 102, 153));
        seeBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/see24.png"))); // NOI18N
        seeBT.setText("LIHAT DATA");
        seeBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seeBTActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Status Account                     :");

        stAccountCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrator", "Petugas" }));
        stAccountCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stAccountCBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stAccountCB, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(daftarDate, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(kdPegawaiTF, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(namaTF, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(passwordTF, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(password2TF, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(noTelpTF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(saveBT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seeBT, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(35, 35, 35))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {saveBT, seeBT});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {daftarDate, jScrollPane1, kdPegawaiTF, namaTF, noTelpTF, password2TF, passwordTF});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(saveBT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(seeBT)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(daftarDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(kdPegawaiTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(namaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(passwordTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(password2TF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(noTelpTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(stAccountCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30))))
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

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 153));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Kelola Data Pesanan");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(haiLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(signOutLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(haiLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(eFootsallLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(haiLabel)
                        .addGap(2, 2, 2)
                        .addComponent(signOutLabel))
                    .addComponent(haiLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(eFootsallLabel)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void kdPegawaiTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kdPegawaiTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdPegawaiTFActionPerformed

    private void saveBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBTActionPerformed
        if((daftarDate.getDate()==null)||(namaTF.getText().equals("")||(passwordTF.getText().equals("")||(password2TF.getText().equals("")||(noTelpTF.getText().equals("")||(alamatTF.getText().equals(""))))))){
            JOptionPane.showMessageDialog(this, "Pastikan semua form telah diisi untuk melanjutkan !", "Gagal", JOptionPane.ERROR_MESSAGE);
        }else if(!passwordTF.getText().equals(password2TF.getText())){
            JOptionPane.showMessageDialog(this, "Inputan Password dan Konfirmasi Password tidak sesuai !", "Gagal", JOptionPane.ERROR_MESSAGE);
        }else{
            if(edit==1){
                try{
                    if(con==null){
                        con = null;
                        con = DB.config();
                    }
                    sql = "delete from tb_account where id_account='"+id_temp+"'";
                    st = con.createStatement();
                    st.execute(sql);
                } catch(SQLException e){
                  JOptionPane.showMessageDialog(this, "Error :"+e);
                }
            }
            String password=HashingUtils.preparePassword(passwordTF.getText(), kdPegawaiTF.getText());
            int status;
            if(stAccountCB.getSelectedItem()=="Administrator"){
                status=0;
            }else if(stAccountCB.getSelectedItem()=="Petugas"){
                status=1;
            }else{
                status=1;
            }
            java.util.Date utilDate = daftarDate.getDate();
            java.util.Date dateNow=new java.util.Date();
            java.sql.Date daftarDate = new java.sql.Date(utilDate.getTime());
            java.sql.Date dateNowSQL = new java.sql.Date(dateNow.getTime());
            try{
                if(con==null){
                    con = null;
                    con = DB.config();
                }
                sql = "insert into tb_account values('"+kdPegawaiTF.getText()+"','"
                    +namaTF.getText()+"','"
                    +password+"','"
                    +daftarDate+"','"
                    +noTelpTF.getText()+"','"
                    +alamatTF.getText()+"','"
                    +status+"','"
                    +dateNowSQL+"')";
                System.out.println(sql);
                st=con.createStatement();
                st.execute(sql);
                hapus();
                viewDataTable();
                JOptionPane.showMessageDialog(this, "Data pegawai berhasil Anda masukkan.", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this, "Data akun gagal dimasukkan karena "+e.getMessage()+".", "Gagal", JOptionPane.ERROR_MESSAGE);
            }   
        }
    }//GEN-LAST:event_saveBTActionPerformed

    private void seeBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seeBTActionPerformed
        this.setVisible(false);
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    tp.setVisible(true);
                    viewDataTable();
                }
            });
    }//GEN-LAST:event_seeBTActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.setVisible(false);
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    DashboardAdmin da = new DashboardAdmin(1,id_account);
                    da.setVisible(true);
                }
            });
    }//GEN-LAST:event_jLabel2MouseClicked

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
                    InputMember im = new InputMember(statusLogin,id_account);
                    im.setVisible(true);
                }
            });
    }//GEN-LAST:event_jLabel7MouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int reply = JOptionPane.showConfirmDialog(this, "Yakin ingin keluar dari Aplikasi ?","Konfirmasi Keluar", JOptionPane.YES_NO_OPTION);
            if(reply==JOptionPane.YES_OPTION){
                System.exit(0);
            }else{
                setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            }
    }//GEN-LAST:event_formWindowClosing

    private void stAccountCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stAccountCBActionPerformed
        if(stAccountCB.getSelectedItem()=="Administrator"){            
            kdPegawaiTF.setText("AD"+String.valueOf(String.format("%03d", DB.getJmlAdmin()+1)));
        }else{
            kdPegawaiTF.setText("PG"+String.valueOf(String.format("%03d", DB.getJmlPetugas()+1)));
        }
    }//GEN-LAST:event_stAccountCBActionPerformed

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        this.setVisible(false);
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    InputPesanan im = new InputPesanan(statusLogin,id_account);
                    im.setVisible(true);
                }
            });
    }//GEN-LAST:event_jLabel12MouseClicked

    private void noTelpTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noTelpTFKeyTyped
        char c = evt.getKeyChar();
        if (!((c >= '0') && (c <= '9') ||
           (c == KeyEvent.VK_BACK_SPACE) ||
           (c == KeyEvent.VK_DELETE))) {
          getToolkit().beep();
          evt.consume();
        }
    }//GEN-LAST:event_noTelpTFKeyTyped

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
            java.util.logging.Logger.getLogger(InputPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InputPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InputPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InputPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InputPetugas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alamatTF;
    private com.toedter.calendar.JDateChooser daftarDate;
    private javax.swing.JLabel eFootsallLabel;
    private javax.swing.JLabel haiLabel;
    private javax.swing.JLabel haiLabel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField kdPegawaiTF;
    private javax.swing.JTextField namaTF;
    private javax.swing.JTextField noTelpTF;
    private javax.swing.JPasswordField password2TF;
    private javax.swing.JPasswordField passwordTF;
    private javax.swing.JButton saveBT;
    private javax.swing.JButton seeBT;
    private javax.swing.JLabel signOutLabel;
    private javax.swing.JComboBox<String> stAccountCB;
    // End of variables declaration//GEN-END:variables
}
