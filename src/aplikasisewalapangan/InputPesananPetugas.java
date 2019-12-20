/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasisewalapangan;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Syekh Syihabuddin AU (171023), Leomongga Oktaria Sihombing (171123), Ryandi Johannsah P (171191)
 */
public class InputPesananPetugas extends javax.swing.JFrame {
    private int statusLogin=0;
    DefaultTableModel modelPesanan;
//    InputPesan inputPesan;
//    InputAkun inputAkun;
    Koneksi DB = new Koneksi();
    Connection con;
    Statement st;
    ResultSet rs;
    
    TampilPesanan tp;
    private String waktuAwal,waktuAkhir;
    private String id_account;
    private String id_temp=null;
    private String sql;
    private String id_member;
    private int edit=0;
    /**
     * Creates new form DashboardAdmin
     */
    public InputPesananPetugas() {
        initComponents();
        this.setTitle("Input Data Pesanan - Petugas");
        this.setLocationRelativeTo(null);
        kodeBookingTF.setText(null);
        kodeBookingTF.setEditable(false);
    }
    
    public InputPesananPetugas(int status, String id_account){
        this.statusLogin = status;
        this.id_account = id_account;
        initData();
    }
    
    public void hapus(){
        java.util.Date date=new java.util.Date();  
        tglBookingDate.setDate(date);
        kodeBookingTF.setText("BF"+String.valueOf(String.format("%03d", DB.getJmlPesanan()+1)));
        kodeBookingTF.setEditable(false);
        tidakRB.setSelected(true);
        waktuAwalCB.setSelectedItem("07.00");
        waktuAkhirCB.setSelectedItem("07.00");
        durasiTF.setText(null);
        namaTimTF.setText(null);
        noTelpTF.setText(null); 
        hargaTF.setText(null);
        tagihanTF.setText(null);
        sisaTF.setText(null);
        keteranganTF.setText(null);
    }
    
    public InputPesananPetugas(int status, String id_account, String id_temp){
        this.statusLogin = status;
        this.id_account = id_account;
        this.id_temp = id_temp;
        initDataEdit();
    }
    
    public void clear(){
        this.setTitle("Input Data Pesanan - Petugas");
        this.setLocationRelativeTo(null);
        memberTF.setVisible(false);
        discountPanel.setVisible(false);
        viewDataTable();
    }
    
    public void initData(){
        initComponents();
        clear();
        namaPetugasLabel.setText(DB.getUsername(id_account));
        java.util.Date date=new java.util.Date();
        tglBookingDate.setDate(date);
        kodeBookingTF.setText("BF"+String.valueOf(String.format("%03d", DB.getJmlPesanan()+1)));
        kodeBookingTF.setEditable(false);
        durasiTF.setEditable(false);
        hargaTF.setEditable(false);
        sisaTF.setEditable(false);
        haiLabel.setText("Hallo, "+DB.getUsername(id_account));
    }
    
    public void initDataEdit(){
        initComponents();
        clear();
        rs = DB.selectAllPesanan();
        try{
            while(rs.next()){
                if(id_temp.equals(rs.getString("id_pesan"))){
                    namaPetugasLabel.setText(DB.getUsername(id_account));
                    if(rs.getString("id_member").isEmpty()){
                        tidakRB.setSelected(true);
                    }else{
                        yaRB.setSelected(true);
                        memberTF.setVisible(true);
                        memberTF.setText(rs.getString("id_member"));
                    }
                    tglBookingDate.setDate(rs.getDate("tgl_booking"));
                    kodeBookingTF.setText(rs.getString("id_pesan"));
                    kodeBookingTF.setEditable(false);
                    waktuAwalCB.setSelectedItem("0"+rs.getString("waktu_awal")+".00");
                    waktuAkhirCB.setSelectedItem("0"+rs.getString("waktu_akhir")+".00");
                    durasiTF.setText(rs.getString("lama_booking"));
                    namaTimTF.setText(rs.getString("nama_tim"));
                    noTelpTF.setText(rs.getString("no_telp")); 
                    hargaTF.setText(rs.getString("harga_booking"));
                    tagihanTF.setText(rs.getString("tagihan"));
                    sisaTF.setText(rs.getString("sisa"));
                    keteranganTF.setText(rs.getString("ket"));
                    haiLabel.setText("Hallo, "+DB.getUsername(id_account));
                    namaPetugasLabel.setText(DB.getUsername(id_account));
                    edit=1;
                    kodeBookingTF.setEditable(false);
                    durasiTF.setEditable(false);
                    hargaTF.setEditable(false);
                    sisaTF.setEditable(false);
                }
            }
        }catch(SQLException e){
            System.err.println("Error : "+e.getMessage());
        }
    }
    
    private void clearTabel(){
        int row = modelPesanan.getRowCount();
        for (int i = 0; i < row; i++) {
          modelPesanan.removeRow(0);
        }
    }
    
    public final void viewDataTable(){
        String[] namakolom={"Kode Booking","Tanggal Booking","Nama Member","Waktu Mulai","Waktu Berakhir","Nama Tim","No. Telp","Harga","Tagihan","Sisa","Keterangan"};
        modelPesanan = new DefaultTableModel(null, namakolom){
            Class[]types = new Class[]{
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
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
            int cola = modelPesanan.getColumnCount();
            return (col < cola)? false : true;
            }
        };
        try{
            con = null;
            con = DB.config();
            clearTabel();
            sql = "select * from tb_pesan order by id_pesan asc";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                String id_pesan = rs.getString("id_pesan");
                String tgl_booking = String.valueOf(new SimpleDateFormat("dd-MMM-yyyy").format(rs.getDate("tgl_booking")));
                String nama_member = DB.getNameMember(rs.getString("id_member"));
                String waktu_awal = rs.getString("waktu_awal");
                String waktu_akhir = rs.getString("waktu_akhir");
                String nama_tim = rs.getString("nama_tim");
                String no_telp = rs.getString("no_telp");
                String harga = rs.getString("harga_booking");
                String tagihan = rs.getString("tagihan");
                String sisa = rs.getString("sisa");
                String keterangan = rs.getString("ket");
                
                Object[]data = {id_pesan, tgl_booking, nama_member, waktu_awal, waktu_akhir, nama_tim, no_telp, harga, tagihan, sisa, keterangan};
                modelPesanan.addRow(data);
                tp = new TampilPesanan(statusLogin, this.id_account);
                tp.pesanTable.setModel(modelPesanan);
            }
        }catch(SQLException e){
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

        memberBG = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        haiLabel = new javax.swing.JLabel();
        haiLabel1 = new javax.swing.JLabel();
        eFootsallLabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        kodeBookingTF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        namaTimTF = new javax.swing.JTextField();
        saveBT = new javax.swing.JButton();
        seeBT = new javax.swing.JButton();
        namaPetugasLabel = new javax.swing.JLabel();
        waktuAwalCB = new javax.swing.JComboBox<>();
        waktuAkhirCB = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        noTelpTF = new javax.swing.JTextField();
        hargaTF = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tagihanTF = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        sisaTF = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        keteranganTF = new javax.swing.JTextArea();
        prosesBT = new javax.swing.JButton();
        durasiTF = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        tglBookingDate = new com.toedter.calendar.JDateChooser();
        tidakRB = new javax.swing.JRadioButton();
        yaRB = new javax.swing.JRadioButton();
        memberTF = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        discountPanel = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        signOutLabel = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();

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
        jLabel1.setText("Input Pesanan Booking");

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

        jPanel7.setBackground(new java.awt.Color(0, 102, 153));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Kelola Data Pesanan");

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
        jLabel3.setText("Nama Petugas                      :");

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Pesanan Member                  :");

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Kode Booking                       :");

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Waktu Booking                     :");

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Lama Booking                       :");

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Nama Tim                             :");

        namaTimTF.setToolTipText("Masukkan nama tim Anda");

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

        namaPetugasLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        namaPetugasLabel.setForeground(new java.awt.Color(255, 255, 255));
        namaPetugasLabel.setText("Nama Petugas");

        waktuAwalCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "07.00", "08.00", "09.00", "10.00", "11.00", "12.00", "13.00", "14.00", "15.00", "16.00", "17.00", "18.00", "19.00", "20.00", "21.00", "22.00", "23.00", "24.00" }));

        waktuAkhirCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "07.00", "08.00", "09.00", "10.00", "11.00", "12.00", "13.00", "14.00", "15.00", "16.00", "17.00", "18.00", "19.00", "20.00", "21.00", "22.00", "23.00", "24.00" }));
        waktuAkhirCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                waktuAkhirCBActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("s/d");

        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Nomor Telepon                    :");

        noTelpTF.setToolTipText("Masukkan nomor telepon Anda");
        noTelpTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                noTelpTFKeyTyped(evt);
            }
        });

        hargaTF.setToolTipText("Silahkan masukkan waktu booking Anda untuk menampilkan harga booking.");

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Harga Booking                      :");

        jLabel15.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Tagihan Dibayar                   :");

        tagihanTF.setToolTipText("Masukkan tagihan yang ingin dibayar, bisa DP");

        jLabel16.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Sisa                                       :");

        sisaTF.setToolTipText("Silahkan tekan proses untuk menampilkan sisa");

        jLabel17.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Keterangan                           :");

        keteranganTF.setColumns(20);
        keteranganTF.setRows(5);
        keteranganTF.setToolTipText("Silahkan tekan proses untuk menampilkan keterangan LUNAS / BELUM.");
        jScrollPane2.setViewportView(keteranganTF);

        prosesBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        prosesBT.setForeground(new java.awt.Color(0, 102, 153));
        prosesBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tactical.png"))); // NOI18N
        prosesBT.setText("PROSES");
        prosesBT.setToolTipText("");
        prosesBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prosesBTActionPerformed(evt);
            }
        });

        durasiTF.setToolTipText("Silahkan atur waktu booking Anda untuk menampilkan lama booking.");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Ket :");

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("15.00 - 24.00 = Rp.90.000");

        jLabel19.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Tanggal Booking                   :");

        tglBookingDate.setBackground(new java.awt.Color(51, 51, 51));
        tglBookingDate.setForeground(new java.awt.Color(255, 255, 255));
        tglBookingDate.setToolTipText("Tekan tombol kalender untuk memproses");

        memberBG.add(tidakRB);
        tidakRB.setForeground(new java.awt.Color(255, 255, 255));
        tidakRB.setText("Tidak");
        tidakRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tidakRBActionPerformed(evt);
            }
        });

        memberBG.add(yaRB);
        yaRB.setForeground(new java.awt.Color(255, 255, 255));
        yaRB.setText("Ya");
        yaRB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                yaRBMouseClicked(evt);
            }
        });
        yaRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yaRBActionPerformed(evt);
            }
        });

        memberTF.setForeground(new java.awt.Color(153, 153, 153));
        memberTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        memberTF.setText("Ketikkan id atau nama member");
        memberTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                memberTFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                memberTFFocusLost(evt);
            }
        });
        memberTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memberTFActionPerformed(evt);
            }
        });
        memberTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                memberTFKeyReleased(evt);
            }
        });

        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("07.00 - 15.00 = Rp.70.000");

        discountPanel.setBackground(new java.awt.Color(51, 51, 51));

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/discount.png"))); // NOI18N
        jLabel22.setText("Discount 10%");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("POTONGAN MEMBER !!");

        javax.swing.GroupLayout discountPanelLayout = new javax.swing.GroupLayout(discountPanel);
        discountPanel.setLayout(discountPanelLayout);
        discountPanelLayout.setHorizontalGroup(
            discountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(discountPanelLayout.createSequentialGroup()
                .addGroup(discountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        discountPanelLayout.setVerticalGroup(
            discountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, discountPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(21, 21, 21)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(namaTimTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(noTelpTF, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(hargaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sisaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(namaPetugasLabel)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel19))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(waktuAwalCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel12)
                                        .addGap(20, 20, 20)
                                        .addComponent(waktuAkhirCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(kodeBookingTF)
                                    .addComponent(jScrollPane2)
                                    .addComponent(tglBookingDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(durasiTF, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(tidakRB)
                                        .addGap(18, 18, 18)
                                        .addComponent(yaRB)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(memberTF, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(30, 30, 30))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(202, 202, 202)
                                .addComponent(tagihanTF, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                .addGap(571, 571, 571)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(saveBT, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                        .addComponent(prosesBT, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(seeBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel18)
                            .addComponent(jLabel20))
                        .addGap(26, 26, 26))
                    .addComponent(discountPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {prosesBT, saveBT, seeBT});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(namaPetugasLabel))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(memberTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(yaRB)
                            .addComponent(tidakRB)
                            .addComponent(jLabel4))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel19)
                            .addComponent(tglBookingDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel5)
                            .addComponent(kodeBookingTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel8)
                            .addComponent(waktuAwalCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(waktuAkhirCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel9)
                            .addComponent(durasiTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel10)
                            .addComponent(namaTimTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(noTelpTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel14)
                            .addComponent(hargaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(prosesBT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveBT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(seeBT, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(tagihanTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(sisaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(discountPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {prosesBT, saveBT, seeBT});

        signOutLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        signOutLabel.setForeground(new java.awt.Color(255, 255, 255));
        signOutLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        signOutLabel.setText("Sign Out");
        signOutLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signOutLabelMouseClicked(evt);
            }
        });

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 102, 153));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Kelola Member");
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
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
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(eFootsallLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
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

    private void saveBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBTActionPerformed
        if((tglBookingDate==null)||(kodeBookingTF.getText().equals(""))||(durasiTF.getText().equals(""))||(namaTimTF.getText().equals(""))||(noTelpTF.getText().equals(""))||(hargaTF.getText().equals(""))||(tagihanTF.getText().equals(""))||(sisaTF.getText().equals(""))||(keteranganTF.getText().equals(""))){
            JOptionPane.showMessageDialog(this, "Silahkan tekan proses terlebih dahulu sebelum menyimpan", "Perhatian", JOptionPane.WARNING_MESSAGE);
        }else{
            if(edit==1){
                try{
                    sql ="delete from tb_pesan where id_pesan='"+id_temp+"'";
                    st = con.createStatement();
                    st.execute(sql);
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(this,"Error : "+e.getMessage());
                }
            }
            waktuAwal = waktuAwalCB.getSelectedItem().toString();
            String[] partAwal = waktuAwal.split("\\.");
            String partWaktuAwal = partAwal[0];
            System.out.println(partWaktuAwal);
            
            waktuAkhir = waktuAkhirCB.getSelectedItem().toString();
            String[] partAkhir = waktuAkhir.split("\\.");
            String partWaktuAkhir = partAkhir[0];
            System.out.println(partWaktuAkhir);
            
            if(tidakRB.isSelected()){
                id_member=null;
            }else if(yaRB.isSelected()){
                id_member=memberTF.getText();
            }
            
            java.util.Date utilDate = tglBookingDate.getDate();
            java.sql.Date bookingDate = new java.sql.Date(utilDate.getTime());;
            try{
                con = null;
                con = DB.config();
                sql = "insert into tb_pesan values('"+kodeBookingTF.getText()+"','"
                        +id_member+"','"
                        +bookingDate+"','"
                        +partWaktuAwal+"','"
                        +partWaktuAkhir+"','"
                        +durasiTF.getText()+"','"
                        +namaTimTF.getText()+"','"
                        +noTelpTF.getText()+"','"
                        +hargaTF.getText()+"','"
                        +tagihanTF.getText()+"','"
                        +sisaTF.getText()+"','"
                        +keteranganTF.getText()+"')";
                st = con.createStatement();
                st.execute(sql);
                hapus();
                viewDataTable();
                JOptionPane.showMessageDialog(this, "Data member berhasil Anda masukkan.", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this, "Data member gagal dimasukkan karena "+e.getMessage()+".", "Gagal", JOptionPane.ERROR_MESSAGE);
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
                    DashboardPetugas da = new DashboardPetugas(statusLogin,id_account);
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

    private void prosesBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prosesBTActionPerformed
        if((tglBookingDate==null)||(kodeBookingTF.getText().equals(""))||(durasiTF.getText().equals(""))||(namaTimTF.getText().equals(""))||(noTelpTF.getText().equals(""))||(hargaTF.getText().equals(""))||(tagihanTF.getText().equals(""))){
            JOptionPane.showMessageDialog(this, "Pastikan semua form telah diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
        }else if(Integer.parseInt(tagihanTF.getText())<20000){
            JOptionPane.showMessageDialog(this, "Minimal pembayaran adalah Rp.20.000 untuk DP", "Perhatian", JOptionPane.WARNING_MESSAGE);
        }else{
            Pesanan pn = new Pesanan(waktuAwalCB.getSelectedItem().toString(),waktuAkhirCB.getSelectedItem().toString());
            memberTF.setText(id_member);
            durasiTF.setText(String.valueOf(pn.getLamaWaktu()));
            hargaTF.setText(String.valueOf(pn.getHargaBooking()));
            pn.setHargaBooking(Integer.parseInt(hargaTF.getText()));
            pn.setBayarBooking(Integer.parseInt(tagihanTF.getText()));
            sisaTF.setText(String.valueOf(pn.getSisaBooking()));
            keteranganTF.setText(pn.getKetBooking());
        }
    }//GEN-LAST:event_prosesBTActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int reply = JOptionPane.showConfirmDialog(this, "Yakin ingin keluar dari Aplikasi ?","Konfirmasi Keluar", JOptionPane.YES_NO_OPTION);
            if(reply==JOptionPane.YES_OPTION){
                System.exit(0);
            }else{
                setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            }
    }//GEN-LAST:event_formWindowClosing

    private void tidakRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tidakRBActionPerformed
        memberTF.setVisible(false);
        namaTimTF.setText(null);
        noTelpTF.setText(null);
        harga();
        revalidate();
    }//GEN-LAST:event_tidakRBActionPerformed

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        this.setVisible(false);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InputMemberPetugas im = new InputMemberPetugas(statusLogin,id_account);
                im.setVisible(true);
            }
        });
    }//GEN-LAST:event_jLabel21MouseClicked

    private void memberTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_memberTFKeyReleased
        rs = DB.selectAllMember();
        try{
            while(rs.next()){
                if((memberTF.getText().equals(rs.getString("id_member")))||(memberTF.getText().equals(rs.getString("nama")))){
                    this.id_member=rs.getString("id_member");
                    namaTimTF.setText(rs.getString("nama_tim"));
                    noTelpTF.setText(rs.getString("no_telp"));
                    discountPanel.setVisible(true);
                    harga();
                }else{
                    this.id_member=null;
                    namaTimTF.setText(null);
                    noTelpTF.setText(null);
                    discountPanel.setVisible(false);
                    harga();
                }
            }
        }catch(SQLException e){
            System.err.println("Error : "+e.getMessage());
        }
        
    }//GEN-LAST:event_memberTFKeyReleased

    private void yaRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yaRBActionPerformed
        memberTF.setVisible(true);
        revalidate();
    }//GEN-LAST:event_yaRBActionPerformed

    private void yaRBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yaRBMouseClicked
        
    }//GEN-LAST:event_yaRBMouseClicked

    private void waktuAkhirCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_waktuAkhirCBActionPerformed
        harga();
    }//GEN-LAST:event_waktuAkhirCBActionPerformed

    private void memberTFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_memberTFFocusGained
        memberTF.setText(null);
        memberTF.setForeground(Color.BLACK);
        memberTF.setHorizontalAlignment(SwingConstants.LEFT);
    }//GEN-LAST:event_memberTFFocusGained

    private void memberTFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_memberTFFocusLost
        memberTF.setText("Ketikkan id atau nama member");
        memberTF.setForeground(Color.GRAY);
        memberTF.setHorizontalAlignment(SwingConstants.CENTER);
    }//GEN-LAST:event_memberTFFocusLost

    private void memberTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_memberTFActionPerformed

    private void noTelpTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noTelpTFKeyTyped
        char c = evt.getKeyChar();
        if (!((c >= '0') && (c <= '9') ||
           (c == KeyEvent.VK_BACK_SPACE) ||
           (c == KeyEvent.VK_DELETE))) {
          getToolkit().beep();
          evt.consume();
        }
    }//GEN-LAST:event_noTelpTFKeyTyped

    public void harga(){
        Pesanan pn = new Pesanan(waktuAwalCB.getSelectedItem().toString(),waktuAkhirCB.getSelectedItem().toString());
        durasiTF.setText(String.valueOf(pn.getLamaWaktu()));
        if((tidakRB.isSelected())||((!tidakRB.isSelected())&&(!yaRB.isSelected()))){
            hargaTF.setText(String.valueOf(pn.getHargaBooking()));
        }else{
            int hargaAwal=pn.getHargaBooking();
            double harga=(int)(hargaAwal-(hargaAwal*0.1));
            DecimalFormat df = new DecimalFormat("###");
            hargaTF.setText(String.valueOf(df.format(harga)));
        }
    }
    
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
            java.util.logging.Logger.getLogger(InputPesananPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InputPesananPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InputPesananPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InputPesananPetugas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new InputPesananPetugas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel discountPanel;
    private javax.swing.JTextField durasiTF;
    private javax.swing.JLabel eFootsallLabel;
    private javax.swing.JLabel haiLabel;
    private javax.swing.JLabel haiLabel1;
    private javax.swing.JTextField hargaTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea keteranganTF;
    private javax.swing.JTextField kodeBookingTF;
    private javax.swing.ButtonGroup memberBG;
    private javax.swing.JTextField memberTF;
    private javax.swing.JLabel namaPetugasLabel;
    private javax.swing.JTextField namaTimTF;
    private javax.swing.JTextField noTelpTF;
    private javax.swing.JButton prosesBT;
    private javax.swing.JButton saveBT;
    private javax.swing.JButton seeBT;
    private javax.swing.JLabel signOutLabel;
    private javax.swing.JTextField sisaTF;
    private javax.swing.JTextField tagihanTF;
    private com.toedter.calendar.JDateChooser tglBookingDate;
    private javax.swing.JRadioButton tidakRB;
    private javax.swing.JComboBox<String> waktuAkhirCB;
    private javax.swing.JComboBox<String> waktuAwalCB;
    private javax.swing.JRadioButton yaRB;
    // End of variables declaration//GEN-END:variables
}
