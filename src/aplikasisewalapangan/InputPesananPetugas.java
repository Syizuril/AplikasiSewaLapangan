/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasisewalapangan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Syekh Syihabuddin AU
 */
public class InputPesananPetugas extends javax.swing.JFrame {
    private int statusLogin=0, index=0;
    DefaultTableModel modelPesanan;
    InputPesan inputPesan;
    InputAkun inputAkun;
    TampilPesananPetugas tp;
    private int selectedRow=0;
    private int edit=0;
    /**
     * Creates new form DashboardAdmin
     */
    public InputPesananPetugas() {
        inputPesan = new InputPesan();
        inputAkun = new InputAkun();
        initComponents();
        this.setTitle("Input Data Pesanan - Petugas");
        this.setLocationRelativeTo(null);
        kodeBookingTF.setText(null);
        kodeBookingTF.setEditable(false);
    }
    
    public InputPesananPetugas(int status, ArrayList<Akun> akun, ArrayList<Pesanan> pn, int index){
        inputAkun = new InputAkun();
        inputAkun.setListAkun(akun);
        inputPesan = new InputPesan();
        inputPesan.setListPesanan(pn);
        this.statusLogin = status;
        this.index = index;
        initData();
    }
    
    public void hapus(){
        java.util.Date date=new java.util.Date();  
        tglBookingDate.setDate(date);
        kodeBookingTF.setText("BF00"+String.valueOf(inputPesan.getSize()+1));
        kodeBookingTF.setEditable(false);
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
    
    public InputPesananPetugas(int status, ArrayList<Akun> akun, ArrayList<Pesanan> pn,int index, int row){
        inputAkun = new InputAkun();
        inputAkun.setListAkun(akun);
        inputPesan = new InputPesan();
        inputPesan.setListPesanan(pn);
        this.statusLogin = status;
        this.index = index;
        this.selectedRow = row;
        initDataEdit();
    }
    
    public void clear(){
        this.setTitle("Input Data Pesanan - Petugas");
        this.setLocationRelativeTo(null);
        viewDataTable();
    }
    
    public void initData(){
        initComponents();
        clear();
        namaPetugasLabel.setText(inputAkun.get(index).getUsername());
        java.util.Date date=new java.util.Date();
        tglBookingDate.setDate(date);
        kodeBookingTF.setText("BF00"+String.valueOf(inputPesan.getSize()+1));
        kodeBookingTF.setEditable(false);
        durasiTF.setEditable(false);
        hargaTF.setEditable(false);
        sisaTF.setEditable(false);
        haiLabel.setText("Hallo, "+inputAkun.get(index).getUsername());
    }
    
    public void initDataEdit(){
        initComponents();
        clear();
        tglBookingDate.setDate(inputPesan.get(selectedRow).getTglBooking());
        kodeBookingTF.setText(inputPesan.get(selectedRow).getKodeBooking());
        kodeBookingTF.setEditable(false);
        waktuAwalCB.setSelectedItem("0"+inputPesan.get(selectedRow).getWaktuAwal()+".00");
        waktuAkhirCB.setSelectedItem("0"+inputPesan.get(selectedRow).getWaktuAkhir()+".00");
        durasiTF.setText(String.valueOf(inputPesan.get(selectedRow).getLamaWaktu()));
        namaTimTF.setText(inputPesan.get(selectedRow).getNamaTim());
        noTelpTF.setText(inputPesan.get(selectedRow).getNoHP()); 
        hargaTF.setText(String.valueOf(inputPesan.get(selectedRow).getHargaBooking()));
        tagihanTF.setText(String.valueOf(inputPesan.get(selectedRow).getBayarBooking()));
        sisaTF.setText(String.valueOf(inputPesan.get(selectedRow).getSisaBooking()));
        keteranganTF.setText(inputPesan.get(selectedRow).getKetBooking());
        haiLabel.setText("Hallo, "+inputAkun.get(index).getUsername());
        namaPetugasLabel.setText(inputAkun.get(index).getUsername());
        edit=1;
        kodeBookingTF.setEditable(false);
        durasiTF.setEditable(false);
        hargaTF.setEditable(false);
        sisaTF.setEditable(false);
    }
    
    public final void viewDataTable(){
        String[] namakolom={"Kode Booking","Tanggal Booking","Waktu Mulai","Waktu Berakhir","Nama Tim","No. Telp","Harga","Tagihan","Sisa","Keterangan"};
        Object[][]objectPesan = new Object[inputPesan.getAll().size()][10];
        int i = 0;
        for(Pesanan pn: inputPesan.getAll()){
            String arrayPesanan[]={
                pn.getKodeBooking(),
                String.valueOf(new SimpleDateFormat("dd-MMM-yyyy").format(pn.getTglBooking())),
                String.valueOf(pn.getWaktuAwal()),
                String.valueOf(pn.getWaktuAkhir()),
                pn.getNamaTim(),
                pn.getNoHP(),
                String.valueOf(pn.getHargaBooking()),
                String.valueOf(pn.getBayarBooking()),
                String.valueOf(pn.getSisaBooking()),
                pn.getKetBooking()
            };
            objectPesan[i]=arrayPesanan;
            i++;
        }
        modelPesanan = new DefaultTableModel(objectPesan,namakolom);
        tp = new TampilPesananPetugas(statusLogin, inputAkun.getAll(),inputPesan.getAll(),index);
        tp.pesanTable.setModel(modelPesanan);
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
        tglBookingDate = new com.toedter.calendar.JDateChooser();
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
        signOutLabel = new javax.swing.JLabel();

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
        jLabel4.setText("Tanggal Booking                  :");

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

        tglBookingDate.setBackground(new java.awt.Color(51, 51, 51));
        tglBookingDate.setForeground(new java.awt.Color(255, 255, 255));
        tglBookingDate.setToolTipText("Tekan tombol kalender untuk memproses");

        waktuAwalCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "07.00", "08.00", "09.00", "10.00", "11.00", "12.00", "13.00", "14.00", "15.00", "16.00", "17.00", "18.00", "19.00", "20.00", "21.00", "22.00", "23.00", "24.00" }));

        waktuAkhirCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "07.00", "08.00", "09.00", "10.00", "11.00", "12.00", "13.00", "14.00", "15.00", "16.00", "17.00", "18.00", "19.00", "20.00", "21.00", "22.00", "23.00", "24.00" }));
        waktuAkhirCB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                waktuAkhirCBMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                waktuAkhirCBMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                waktuAkhirCBMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                waktuAkhirCBMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                waktuAkhirCBMouseReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("s/d");

        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Nomor Telepon                    :");

        noTelpTF.setToolTipText("Masukkan nomor telepon Anda");

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

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("07.00 - 15.00 = Rp.70.000");

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("15.00 - 24.00 = Rp.90.000");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(namaTimTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(noTelpTF, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hargaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tagihanTF, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(waktuAwalCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addComponent(jLabel12)
                                    .addGap(20, 20, 20)
                                    .addComponent(waktuAkhirCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(kodeBookingTF)
                                .addComponent(tglBookingDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE))
                            .addComponent(durasiTF, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(saveBT, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(prosesBT, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(seeBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18))))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {prosesBT, saveBT, seeBT});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(namaPetugasLabel))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(tglBookingDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(kodeBookingTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(waktuAkhirCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(waktuAwalCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(durasiTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(namaTimTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(noTelpTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addComponent(jLabel18)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(tagihanTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(sisaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(eFootsallLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                inputPesan.deleteData(selectedRow);
            }
            inputPesan.InsertData( 
                    kodeBookingTF.getText(),
                    namaTimTF.getText(),
                    noTelpTF.getText(), 
                    keteranganTF.getText(), 
                    "1", 
                    namaPetugasLabel.getText(), 
                    Integer.parseInt("7"),
                    Integer.parseInt("8"),
                    Integer.parseInt(durasiTF.getText()), 
                    Integer.parseInt(hargaTF.getText()),
                    Integer.parseInt(tagihanTF.getText()), 
                    Integer.parseInt(sisaTF.getText()), 
                    tglBookingDate.getDate(),
                    index
            );
            hapus();
            JOptionPane.showMessageDialog(this, "Data pesanan berhasil Anda masukkan.", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
            viewDataTable();        
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
                    DashboardPetugas dp = new DashboardPetugas(1,inputAkun.getAll(),inputPesan.getAll(),index);
                    dp.setVisible(true);
                }
            });
    }//GEN-LAST:event_jLabel2MouseClicked

    private void signOutLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signOutLabelMouseClicked
        this.setVisible(false);
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        Login ln = new Login(inputAkun.getAll(),inputPesan.getAll(),index);
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
            durasiTF.setText(String.valueOf(pn.getLamaWaktu()));
            hargaTF.setText(String.valueOf(pn.getHargaBooking()));
            pn.setHargaBooking(Integer.parseInt(hargaTF.getText()));
            pn.setBayarBooking(Integer.parseInt(tagihanTF.getText()));
            sisaTF.setText(String.valueOf(pn.getSisaBooking()));
            keteranganTF.setText(pn.getKetBooking());
        }
    }//GEN-LAST:event_prosesBTActionPerformed

    private void waktuAkhirCBMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_waktuAkhirCBMousePressed
        harga();
    }//GEN-LAST:event_waktuAkhirCBMousePressed

    private void waktuAkhirCBMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_waktuAkhirCBMouseReleased
        harga();
    }//GEN-LAST:event_waktuAkhirCBMouseReleased

    private void waktuAkhirCBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_waktuAkhirCBMouseClicked
        harga();
    }//GEN-LAST:event_waktuAkhirCBMouseClicked

    private void waktuAkhirCBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_waktuAkhirCBMouseExited
        harga();
    }//GEN-LAST:event_waktuAkhirCBMouseExited

    private void waktuAkhirCBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_waktuAkhirCBMouseEntered
        harga();
    }//GEN-LAST:event_waktuAkhirCBMouseEntered

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int reply = JOptionPane.showConfirmDialog(this, "Yakin ingin keluar dari Aplikasi ?","Konfirmasi Keluar", JOptionPane.YES_NO_OPTION);
            if(reply==JOptionPane.YES_OPTION){
                System.exit(0);
            }else{
                setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            }
    }//GEN-LAST:event_formWindowClosing

    public void harga(){
        Pesanan pn = new Pesanan(waktuAwalCB.getSelectedItem().toString(),waktuAkhirCB.getSelectedItem().toString());
        durasiTF.setText(String.valueOf(pn.getLamaWaktu()));
        hargaTF.setText(String.valueOf(pn.getHargaBooking()));
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
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea keteranganTF;
    private javax.swing.JTextField kodeBookingTF;
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
    private javax.swing.JComboBox<String> waktuAkhirCB;
    private javax.swing.JComboBox<String> waktuAwalCB;
    // End of variables declaration//GEN-END:variables
}
