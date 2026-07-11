package aplikasilaundry.view.panel;

//Mengimpor model tabel
import javax.swing.table.DefaultTableModel;

//Mengimpor model detail transaksi
import aplikasilaundry.model.DetailTransaksi;

//Mengimpor format rupiah
import aplikasilaundry.util.FormatRupiah;
import aplikasilaundry.util.TableStyle;
import java.math.BigDecimal;
//Mengimpor controller pengaturan
import aplikasilaundry.controller.PengaturanStrukController;
import aplikasilaundry.model.Transaksi;
//Mengimpor model pengaturan
import aplikasilaundry.model.PengaturanStruk;
import java.util.List;
import aplikasilaundry.controller.TransaksiController;
//Mengimpor controller pengguna
import aplikasilaundry.controller.PenggunaController;

//Mengimpor model pengguna
import aplikasilaundry.model.Pengguna;
//Mengimpor JOptionPane
import javax.swing.JOptionPane;

import aplikasilaundry.view.dialog.popUpEditKonfigurasiLayanan;
import aplikasilaundry.view.dialog.popUpKonfirmasiHapus;
import aplikasilaundry.view.dialog.popUpPensil;
import aplikasilaundry.view.dialog.popUpTambahKonfigurasiLayanan;
import aplikasilaundry.view.dialog.popUpTambahpengguna;

public class Pengaturan extends javax.swing.JPanel {
//Controller pengaturan

    private PengaturanStrukController controller;
//Controller pengguna
    private PenggunaController controllerPengguna;
//Controller transaksi
    private TransaksiController controllerTransaksi;

//Model pengaturan
    private PengaturanStruk pengaturan;

    public Pengaturan() {

        initComponents();
        
        jScrollPane3.getVerticalScrollBar().setUnitIncrement(35);
//Membuat footer preview seperti struk asli
        tFooter1.setEditable(false);
        tFooter1.setOpaque(false);
        tFooter1.setBorder(null);
        tFooter1.setFocusable(false);
//Preview tabel tidak boleh dipilih
        tblDetailStruk.setRowSelectionAllowed(false);
        tblDetailStruk.setCellSelectionEnabled(false);
        tblDetailStruk.setFocusable(false);
//Tengah nama bisnis
        lblNamaBisnis.setHorizontalAlignment(
                javax.swing.SwingConstants.CENTER);

//Tengah slogan
        lblKeterangan.setHorizontalAlignment(
                javax.swing.SwingConstants.CENTER);

//Tengah alamat
        lblAlamatLaundry.setHorizontalAlignment(
                javax.swing.SwingConstants.CENTER);

//Tengah nomor HP
        lblNoHpLaundry.setHorizontalAlignment(
                javax.swing.SwingConstants.CENTER);
        //Membuat controller
        controller
                = new PengaturanStrukController();
        //Membuat controller transaksi
        controllerTransaksi
                = new TransaksiController();
//Membuat controller pengguna
        controllerPengguna
                = new PenggunaController();

        //Menampilkan data pengaturan
        tampilPengaturan();
//Menampilkan data pengguna
        tampilPengguna();
    }
//Method menampilkan data pengaturan

    private void tampilPengaturan() {

        //Mengambil data pengaturan
        pengaturan
                = controller.getPengaturan();

        //Menampilkan nama bisnis
        tNamaBisnis.setText(
                pengaturan.getNamaBisnis());

        //Menampilkan alamat
        tAlamat.setText(
                pengaturan.getAlamat());

        //Menampilkan nomor HP
        tNoHp.setText(
                pengaturan.getNoHp());
        //Menampilkan alamat pada preview struk
        lblAlamatLaundry.setText(
                pengaturan.getAlamat());

//Menampilkan nomor HP pada preview struk
        lblNoHpLaundry.setText(
                pengaturan.getNoHp());

        //Menampilkan nama bisnis pada preview struk
        lblNamaBisnis.setText(
                pengaturan.getNamaBisnis());

//Menampilkan slogan pada preview struk
        lblKeterangan.setText(
                pengaturan.getKeterangan());

        //Menampilkan jam operasional
        tJamOperasional.setText(
                pengaturan.getJamOperasional());

        //Menampilkan layanan
        tLayanan.setText(
                pengaturan.getLayanan());

//Menampilkan slogan laundry
        tKeterangan.setText(
                pengaturan.getKeterangan());

//Menampilkan footer struk
        tFooter.setText(
                pengaturan.getFooterStruk());
//Menampilkan footer pada preview struk
        tFooter1.setText(
                pengaturan.getFooterStruk());

//Mengambil transaksi terbaru
        List<Transaksi> daftarTransaksi
                = controllerTransaksi.getTransaksiTerbaru();

//Jika terdapat transaksi
        if (!daftarTransaksi.isEmpty()) {

            //Mengambil transaksi paling baru
            Transaksi transaksi
                    = controllerTransaksi.getByNoNota(
                            daftarTransaksi.get(0).getNoNota());

            //Menampilkan nomor nota
            lblNoNota.setText(
                    transaksi.getNoNota());

            //Menampilkan tanggal masuk
            lblTanggal.setText(
                    transaksi.getTanggalMasuk());

            //Menampilkan jam masuk
            lblJamMasuk.setText(
                    transaksi.getJamMasuk());

            //Menampilkan nama pelanggan
            lblPelanggan.setText(
                    transaksi.getNamaPelanggan());

            //Menampilkan nomor HP pelanggan
            lblNoHp.setText(
                    transaksi.getNoHp());

            //Menampilkan alamat pelanggan
            lblAsal.setText(
                    transaksi.getAlamat());
            //Mengambil seluruh detail transaksi
            List<DetailTransaksi> daftarDetail
                    = controllerTransaksi.getDetailByNota(
                            transaksi.getNoNota());

//Mengambil model tabel yang sudah ada
            DefaultTableModel model
                    = (DefaultTableModel) tblDetailStruk.getModel();

//Mengosongkan isi tabel
            model.setRowCount(0);

//Nilai awal total
            double totalBerat = 0;
            int totalBiji = 0;
            BigDecimal totalHarga = BigDecimal.ZERO;

//Mengulang seluruh detail
            for (DetailTransaksi detail : daftarDetail) {

                //Menambah baris tabel
                model.addRow(new Object[]{
                    detail.getNamaLayanan()
                    + " - "
                    + detail.getNamaProses(),
                    detail.getQty()
                    + " "
                    + detail.getSatuan(),
                    FormatRupiah.format(
                    detail.getHarga()),
                    FormatRupiah.format(
                    detail.getSubtotal())
                });

                //Menghitung total berat
                if (detail.getSatuan()
                        .equalsIgnoreCase("Kg")) {

                    totalBerat += detail.getQty();

                }

                //Menghitung total biji
                if (detail.getSatuan()
                        .equalsIgnoreCase("Biji")) {

                    totalBiji
                            += (int) detail.getQty();

                }

                //Menghitung total harga
                totalHarga
                        = totalHarga.add(
                                detail.getSubtotal());

            }

//Menampilkan tabel
            tblDetailStruk.setModel(model);

//Mengatur tampilan tabel
//TableStyle.style(tblDetailStruk);
            TableStyle.TableStyle(tblDetailStruk);

//Menampilkan total berat
            lblTotalBerat.setText(
                    totalBerat + " Kg");

//Menampilkan total biji
            lblTotalBiji.setText(
                    totalBiji + " Biji");

//Menampilkan total harga
            lblTotalHarga.setText(
                    FormatRupiah.format(
                            totalHarga));

        } else {

            //Mengosongkan informasi transaksi
            lblNoNota.setText("-");
            lblTanggal.setText("-");
            lblJamMasuk.setText("-");
            lblPelanggan.setText("-");
            lblNoHp.setText("-");
            lblAsal.setText("-");

            //Mengosongkan total
            lblTotalBerat.setText("0 Kg");
            lblTotalBiji.setText("0 Biji");
            lblTotalHarga.setText("Rp0");

            //Mengosongkan tabel
            DefaultTableModel model
                    = (DefaultTableModel) tblDetailStruk.getModel();

            model.setRowCount(0);

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

        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel40 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnEditPengguna = new javax.swing.JButton();
        btnHappusPengguna = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPengguna = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnTambahPengguna = new javax.swing.JButton();
        jPanel46 = new javax.swing.JPanel();
        jPanel47 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel50 = new javax.swing.JPanel();
        jPanel51 = new javax.swing.JPanel();
        btnSimpanInformasi = new javax.swing.JButton();
        jPanel52 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        tJamOperasional = new javax.swing.JTextField();
        tLayanan = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        tAlamat = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        lblNoNota = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        lblTanggal = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        lblJamMasuk = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        lblPelanggan = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        lblNoHp = new javax.swing.JLabel();
        jPanel48 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        lblAsal = new javax.swing.JLabel();
        jPanel49 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        lblTotalBerat = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        lblTotalBiji = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        lblTotalHarga = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tFooter1 = new javax.swing.JTextArea();
        jPanel53 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetailStruk = new javax.swing.JTable();
        lblNoHpLaundry = new javax.swing.JLabel();
        lblKeterangan = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblNamaBisnis = new javax.swing.JLabel();
        lblAlamatLaundry = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tFooter = new javax.swing.JTextField();
        jPanel33 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        tNamaBisnis = new javax.swing.JTextField();
        btnSimpanPengaturan = new javax.swing.JToggleButton();
        tReset = new javax.swing.JButton();
        jPanel34 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        tNoHp = new javax.swing.JTextField();
        jPanel35 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        tKeterangan = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnEditJenis = new javax.swing.JButton();
        btnHapusJenis = new javax.swing.JButton();
        jPanel38 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblJenis = new javax.swing.JTable();
        jPanel37 = new javax.swing.JPanel();
        jPanel39 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        jToggleButton2 = new javax.swing.JToggleButton();
        btnTambahKonfigurasiLAyanan = new javax.swing.JToggleButton();
        jToggleButton6 = new javax.swing.JToggleButton();
        jPanel44 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(240, 243, 247));
        setMinimumSize(new java.awt.Dimension(1006, 728));
        setLayout(new java.awt.CardLayout());

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 243, 247), 20));
        jPanel40.setMinimumSize(new java.awt.Dimension(1006, 1500));
        jPanel40.setPreferredSize(new java.awt.Dimension(1006, 1600));
        jPanel40.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 243, 247), 10));
        jPanel1.setMinimumSize(new java.awt.Dimension(1006, 273));
        jPanel1.setPreferredSize(new java.awt.Dimension(1006, 546));
        jPanel1.setLayout(new java.awt.GridLayout(2, 1, 2, 20));

        jPanel45.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        jPanel2.setMinimumSize(new java.awt.Dimension(1006, 50));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("1. Pengguna");

        btnEditPengguna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/LucidePencil.png"))); // NOI18N
        btnEditPengguna.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditPengguna.addActionListener(this::btnEditPenggunaActionPerformed);

        btnHappusPengguna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/SolarTrashBinTrashOutline.png"))); // NOI18N
        btnHappusPengguna.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHappusPengguna.addActionListener(this::btnHappusPenggunaActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEditPengguna)
                .addGap(18, 18, 18)
                .addComponent(btnHappusPengguna))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnEditPengguna, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHappusPengguna))
                    .addComponent(jLabel1))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel45.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10));
        jPanel4.setLayout(new java.awt.CardLayout());

        tblPengguna.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblPengguna.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "Owner", "Owner"},
                {"2", "Kasir", "Kasir"}
            },
            new String [] {
                "No", "Nama", "Role"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPengguna.setGridColor(new java.awt.Color(204, 204, 204));
        tblPengguna.setRowHeight(50);
        tblPengguna.setSelectionBackground(new java.awt.Color(0, 51, 204));
        tblPengguna.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblPengguna.setShowGrid(true);
        jScrollPane2.setViewportView(tblPengguna);

        jPanel4.add(jScrollPane2, "card2");

        jPanel45.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setMinimumSize(new java.awt.Dimension(1006, 65));

        btnTambahPengguna.setBackground(new java.awt.Color(51, 0, 204));
        btnTambahPengguna.setForeground(new java.awt.Color(255, 255, 255));
        btnTambahPengguna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/IonPlus.png"))); // NOI18N
        btnTambahPengguna.setText("Tambah Pengguna");
        btnTambahPengguna.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTambahPengguna.addActionListener(this::btnTambahPenggunaActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTambahPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnTambahPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel45.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(jPanel45);

        jPanel46.setLayout(new java.awt.BorderLayout());

        jPanel47.setBackground(new java.awt.Color(255, 255, 255));
        jPanel47.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        jPanel47.setMinimumSize(new java.awt.Dimension(1006, 50));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("2. Informasi Laundry");

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(791, Short.MAX_VALUE))
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel46.add(jPanel47, java.awt.BorderLayout.PAGE_START);

        jPanel50.setLayout(new java.awt.BorderLayout());

        jPanel51.setBackground(new java.awt.Color(255, 255, 255));
        jPanel51.setMinimumSize(new java.awt.Dimension(100, 50));
        jPanel51.setPreferredSize(new java.awt.Dimension(1006, 50));

        btnSimpanInformasi.setBackground(new java.awt.Color(0, 51, 204));
        btnSimpanInformasi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSimpanInformasi.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpanInformasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/Frame (7).png"))); // NOI18N
        btnSimpanInformasi.setText("Simpan Informasi");
        btnSimpanInformasi.setIconTextGap(10);
        btnSimpanInformasi.addActionListener(this::btnSimpanInformasiActionPerformed);

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel51Layout.createSequentialGroup()
                .addContainerGap(759, Short.MAX_VALUE)
                .addComponent(btnSimpanInformasi)
                .addContainerGap())
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSimpanInformasi, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
        );

        jPanel50.add(jPanel51, java.awt.BorderLayout.PAGE_END);

        jPanel52.setBackground(new java.awt.Color(255, 255, 255));
        jPanel52.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));

        jLabel55.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/PixelCalenderSolid.png"))); // NOI18N
        jLabel55.setText("Layanan");
        jLabel55.setIconTextGap(15);

        jLabel54.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/GravityUiClock.png"))); // NOI18N
        jLabel54.setText("Jam Operasional");
        jLabel54.setIconTextGap(15);

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tLayanan, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tJamOperasional))))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tJamOperasional, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tLayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel52.add(jPanel28);

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/PixelLocationPin.png"))); // NOI18N
        jLabel57.setText("Alamat Laundry");
        jLabel57.setIconTextGap(15);

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tAlamat, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        jPanel52.add(jPanel29);

        jPanel50.add(jPanel52, java.awt.BorderLayout.CENTER);

        jPanel46.add(jPanel50, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel46);

        jPanel40.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 243, 247), 10));
        jPanel12.setMinimumSize(new java.awt.Dimension(1006, 585));
        jPanel12.setPreferredSize(new java.awt.Dimension(1006, 635));
        jPanel12.setLayout(new java.awt.BorderLayout());

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setMinimumSize(new java.awt.Dimension(1006, 85));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("3. Pengaturan Struk");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Preview Struk");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel5))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(858, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel12.add(jPanel14, java.awt.BorderLayout.PAGE_START);

        jPanel15.setLayout(new java.awt.BorderLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel5.setForeground(new java.awt.Color(204, 204, 204));
        jPanel5.setMinimumSize(new java.awt.Dimension(320, 500));
        jPanel5.setPreferredSize(new java.awt.Dimension(320, 535));

        jPanel30.setBackground(new java.awt.Color(239, 238, 245));

        jPanel31.setBackground(new java.awt.Color(239, 238, 245));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel14.setText("No Nota                  :");

        lblNoNota.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblNoNota.setText("INV-260508-001");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNoNota, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNoNota, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel9.setBackground(new java.awt.Color(239, 238, 245));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel15.setText("Tanggal                   :");

        lblTanggal.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblTanggal.setText("08/05/2026");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lblTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel10.setBackground(new java.awt.Color(239, 238, 245));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel16.setText("Jam Masuk             :");

        lblJamMasuk.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblJamMasuk.setText("10:30:45");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblJamMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(lblJamMasuk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel11.setBackground(new java.awt.Color(239, 238, 245));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel17.setText("Pelanggan               :");

        lblPelanggan.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblPelanggan.setText("Siti Aisyah");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17))
            .addComponent(lblPelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel32.setBackground(new java.awt.Color(239, 238, 245));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel18.setText("No. Hp                     :");

        lblNoHp.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblNoHp.setText("0857-9155-9991");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNoHp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel48.setBackground(new java.awt.Color(239, 238, 245));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel19.setText("Asal                          :");

        lblAsal.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblAsal.setText("PPP.KH.A.BASTHOMI");

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAsal, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19))
            .addComponent(lblAsal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel49.setBackground(new java.awt.Color(239, 238, 245));

        jLabel20.setBackground(new java.awt.Color(239, 238, 245));
        jLabel20.setText("----------------------------------------------------------");

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20))
        );

        jPanel19.setBackground(new java.awt.Color(239, 238, 245));
        jPanel19.setFocusTraversalPolicyProvider(true);

        jPanel20.setBackground(new java.awt.Color(239, 238, 245));

        lblTotalBerat.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblTotalBerat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalBerat.setText("2.0 Kg");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        jLabel44.setText("TOTAL BERAT");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTotalBerat, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblTotalBerat)
                .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel21.setBackground(new java.awt.Color(239, 238, 245));

        lblTotalBiji.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblTotalBiji.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalBiji.setText("1 Biji");

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        jLabel46.setText("TOTAL BIJI");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTotalBiji, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblTotalBiji)
                .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel22.setBackground(new java.awt.Color(239, 238, 245));

        lblTotalHarga.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblTotalHarga.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalHarga.setText("Rp 18.000");

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        jLabel48.setText("TOTAL HARGA");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblTotalHarga)
                .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel49.setText("---------------------------------------------------------");

        jPanel23.setBackground(new java.awt.Color(239, 238, 245));

        tFooter1.setBackground(new java.awt.Color(245, 245, 245));
        tFooter1.setColumns(20);
        tFooter1.setRows(5);
        jScrollPane5.setViewportView(tFooter1);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel53.setLayout(new java.awt.CardLayout());

        tblDetailStruk.setBackground(new java.awt.Color(239, 238, 245));
        tblDetailStruk.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblDetailStruk);

        jPanel53.add(jScrollPane1, "card2");

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(106, 106, 106))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel30Layout.createSequentialGroup()
                                .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblNoHpLaundry.setText("Bersih, Suci, Wangi");

        lblKeterangan.setText("Bersih, Suci, Wangi");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/logo 1.png"))); // NOI18N

        lblNamaBisnis.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNamaBisnis.setText("MOJOSARI LAUNDRY");

        lblAlamatLaundry.setText("Bersih, Suci, Wangi");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAlamatLaundry)
                            .addComponent(lblNamaBisnis)
                            .addComponent(lblNoHpLaundry)
                            .addComponent(lblKeterangan)))
                    .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lblNamaBisnis)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAlamatLaundry)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNoHpLaundry))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblKeterangan)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel15.add(jPanel5, java.awt.BorderLayout.LINE_START);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 10));
        jPanel6.setMinimumSize(new java.awt.Dimension(686, 500));
        jPanel6.setPreferredSize(new java.awt.Dimension(626, 535));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Footer Sruk");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Header Struk");

        tFooter.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tFooter.addActionListener(this::tFooterActionPerformed);

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setText("Nama Bisnis");

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addComponent(tNamaBisnis, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tNamaBisnis, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnSimpanPengaturan.setBackground(new java.awt.Color(51, 0, 204));
        btnSimpanPengaturan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpanPengaturan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/IonPlus.png"))); // NOI18N
        btnSimpanPengaturan.setText("Simpan Pengaturan");
        btnSimpanPengaturan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSimpanPengaturan.addActionListener(this::btnSimpanPengaturanActionPerformed);

        tReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/SystemUiconsReset.png"))); // NOI18N
        tReset.setText("Reset");
        tReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setText("Nama Bisnis");

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addComponent(tNoHp)
                .addContainerGap())
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setText("Keterangan");

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addComponent(tKeterangan)
                .addContainerGap())
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tKeterangan, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tFooter)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnSimpanPengaturan, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(tReset, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 321, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tFooter, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpanPengaturan, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tReset, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jPanel15.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel15, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel12, java.awt.BorderLayout.PAGE_START);

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 243, 247), 10));
        jPanel13.setMinimumSize(new java.awt.Dimension(1006, 642));
        jPanel13.setLayout(new java.awt.BorderLayout());

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));
        jPanel36.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        jPanel36.setMinimumSize(new java.awt.Dimension(1006, 50));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("4. Jenis Layanan & Proses");

        btnEditJenis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/LucidePencil.png"))); // NOI18N
        btnEditJenis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditJenis.addActionListener(this::btnEditJenisActionPerformed);

        btnHapusJenis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/SolarTrashBinTrashOutline.png"))); // NOI18N
        btnHapusJenis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHapusJenis.addActionListener(this::btnHapusJenisActionPerformed);

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 647, Short.MAX_VALUE)
                .addComponent(btnEditJenis)
                .addGap(18, 18, 18)
                .addComponent(btnHapusJenis))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 8, Short.MAX_VALUE))
                    .addComponent(btnHapusJenis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditJenis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel13.add(jPanel36, java.awt.BorderLayout.PAGE_START);

        jPanel38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10));
        jPanel38.setMinimumSize(new java.awt.Dimension(1006, 512));
        jPanel38.setLayout(new java.awt.CardLayout());

        tblJenis.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tblJenis);

        jPanel38.add(jScrollPane4, "card2");

        jPanel13.add(jPanel38, java.awt.BorderLayout.CENTER);

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));
        jPanel37.setMinimumSize(new java.awt.Dimension(1006, 80));
        jPanel37.setPreferredSize(new java.awt.Dimension(1006, 80));
        jPanel37.setLayout(new java.awt.BorderLayout());

        jPanel39.setBackground(new java.awt.Color(255, 255, 255));
        jPanel39.setMinimumSize(new java.awt.Dimension(219, 80));

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 219, Short.MAX_VALUE)
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        jPanel37.add(jPanel39, java.awt.BorderLayout.LINE_END);

        jPanel41.setBackground(new java.awt.Color(255, 255, 255));
        jPanel41.setMinimumSize(new java.awt.Dimension(219, 80));

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 219, Short.MAX_VALUE)
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        jPanel37.add(jPanel41, java.awt.BorderLayout.LINE_START);

        jPanel42.setBackground(new java.awt.Color(255, 255, 255));
        jPanel42.setLayout(new java.awt.BorderLayout());

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));
        jPanel43.setMinimumSize(new java.awt.Dimension(100, 45));
        jPanel43.setPreferredSize(new java.awt.Dimension(5, 45));
        jPanel43.setLayout(new java.awt.GridLayout(1, 3));

        jToggleButton2.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton2.setBorder(null);
        jPanel43.add(jToggleButton2);

        btnTambahKonfigurasiLAyanan.setBackground(new java.awt.Color(51, 0, 204));
        btnTambahKonfigurasiLAyanan.setForeground(new java.awt.Color(255, 255, 255));
        btnTambahKonfigurasiLAyanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/IonPlus.png"))); // NOI18N
        btnTambahKonfigurasiLAyanan.setText("Tambah konfigurasi layanan");
        btnTambahKonfigurasiLAyanan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTambahKonfigurasiLAyanan.addActionListener(this::btnTambahKonfigurasiLAyananActionPerformed);
        jPanel43.add(btnTambahKonfigurasiLAyanan);

        jToggleButton6.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton6.setBorder(null);
        jPanel43.add(jToggleButton6);

        jPanel42.add(jPanel43, java.awt.BorderLayout.PAGE_START);

        jPanel44.setBackground(new java.awt.Color(255, 255, 255));
        jPanel44.setMinimumSize(new java.awt.Dimension(508, 35));

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 508, Short.MAX_VALUE)
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        jPanel42.add(jPanel44, java.awt.BorderLayout.CENTER);

        jPanel37.add(jPanel42, java.awt.BorderLayout.CENTER);

        jPanel13.add(jPanel37, java.awt.BorderLayout.PAGE_END);

        jPanel7.add(jPanel13, java.awt.BorderLayout.CENTER);

        jPanel40.add(jPanel7, java.awt.BorderLayout.CENTER);

        jScrollPane3.setViewportView(jPanel40);

        add(jScrollPane3, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void tFooterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tFooterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tFooterActionPerformed

    private void btnEditJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditJenisActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnEditJenisActionPerformed

    private void btnTambahKonfigurasiLAyananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahKonfigurasiLAyananActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnTambahKonfigurasiLAyananActionPerformed

    private void btnHapusJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusJenisActionPerformed
        // TODO add your handling code here:
//        popUpKonfirmasiHapus popup =
//            new popUpKonfirmasiHapus(getParentFrame(), true);
//
//    popup.setLocationRelativeTo(getParentFrame());
//    popup.setVisible(true);
    }//GEN-LAST:event_btnHapusJenisActionPerformed

    private void btnSimpanPengaturanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanPengaturanActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnSimpanPengaturanActionPerformed

    private void btnHappusPenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHappusPenggunaActionPerformed
//        //        // TODO add your handling code here:
//        popUpKonfirmasiHapus popup = new popUpKonfirmasiHapus(getParentFrame(), true);
//        popup.setLocationRelativeTo(getParentFrame());
//        popup.setVisible(true);
    }//GEN-LAST:event_btnHappusPenggunaActionPerformed

    private void btnEditPenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditPenggunaActionPerformed
        // TODO add your handling code here:
        //Mengambil baris yang dipilih
int baris = tblPengguna.getSelectedRow();

//Jika belum memilih data
if(baris == -1){

    JOptionPane.showMessageDialog(
            this,
            "Pilih pengguna yang akan diedit.");

    return;

}

//Mengambil username dari tabel
String username =
        tblPengguna.getValueAt(
                baris,
                1).toString();

//Mencari objek pengguna berdasarkan username
Pengguna data = null;

for(Pengguna p : controllerPengguna.getAll()){

    if(p.getUsername().equals(username)){

        data = controllerPengguna.getById(
                p.getIdPengguna());

        break;

    }

}

//Jika data tidak ditemukan
if(data == null){

    JOptionPane.showMessageDialog(
            this,
            "Data pengguna tidak ditemukan.");

    return;

}

//Membuka popup edit
popUpPensil dialog =
        new popUpPensil(
                (java.awt.Frame)
                javax.swing.SwingUtilities.getWindowAncestor(this),
                true);

//Mengirim data ke popup
dialog.setPengguna(data);

//Menampilkan popup
dialog.setVisible(true);
    }//GEN-LAST:event_btnEditPenggunaActionPerformed

    private void btnTambahPenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahPenggunaActionPerformed
        // TODO add your handling code here:
        //Membuka popup tambah pengguna
        popUpTambahpengguna dialog
                = new popUpTambahpengguna(
                        (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this),
                        true,
                        this);

        //Menampilkan popup
        dialog.setVisible(true);
    }//GEN-LAST:event_btnTambahPenggunaActionPerformed

    private void btnSimpanInformasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanInformasiActionPerformed
        // TODO add your handling code here:
        //Mengambil data dari form
        pengaturan.setNamaBisnis(
                tNamaBisnis.getText().trim());

        pengaturan.setAlamat(
                tAlamat.getText().trim());

        pengaturan.setNoHp(
                tNoHp.getText().trim());

        pengaturan.setJamOperasional(
                tJamOperasional.getText().trim());

//Mengambil layanan laundry
        pengaturan.setLayanan(
                tLayanan.getText().trim());

//Mengambil slogan laundry
        pengaturan.setKeterangan(
                tKeterangan.getText().trim());

//Mengambil footer struk
        pengaturan.setFooterStruk(
                tFooter.getText().trim());

//Menyimpan perubahan ke database
        controller.updatePengaturan(
                pengaturan);

//Menampilkan pesan berhasil
        JOptionPane.showMessageDialog(
                this,
                "Informasi laundry berhasil diperbarui.");

//Menampilkan ulang data terbaru
        tampilPengaturan();

    }//GEN-LAST:event_btnSimpanInformasiActionPerformed

//Method menampilkan seluruh data pengguna
    public void tampilPengguna() {

        //Mengambil model tabel
        DefaultTableModel model
                = (DefaultTableModel) tblPengguna.getModel();

        //Mengosongkan isi tabel
        model.setRowCount(0);

        //Mengambil seluruh pengguna
        List<Pengguna> daftar
                = controllerPengguna.getAll();

        //Menampilkan seluruh pengguna
        for (Pengguna pengguna : daftar) {

            model.addRow(new Object[]{
                pengguna.getNamaPengguna(),
                pengguna.getUsername(),
                pengguna.getRole()

            });

        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditJenis;
    private javax.swing.JButton btnEditPengguna;
    private javax.swing.JButton btnHappusPengguna;
    private javax.swing.JButton btnHapusJenis;
    private javax.swing.JButton btnSimpanInformasi;
    private javax.swing.JToggleButton btnSimpanPengaturan;
    private javax.swing.JToggleButton btnTambahKonfigurasiLAyanan;
    private javax.swing.JButton btnTambahPengguna;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton6;
    private javax.swing.JLabel lblAlamatLaundry;
    private javax.swing.JLabel lblAsal;
    private javax.swing.JLabel lblJamMasuk;
    private javax.swing.JLabel lblKeterangan;
    private javax.swing.JLabel lblNamaBisnis;
    private javax.swing.JLabel lblNoHp;
    private javax.swing.JLabel lblNoHpLaundry;
    private javax.swing.JLabel lblNoNota;
    private javax.swing.JLabel lblPelanggan;
    private javax.swing.JLabel lblTanggal;
    private javax.swing.JLabel lblTotalBerat;
    private javax.swing.JLabel lblTotalBiji;
    private javax.swing.JLabel lblTotalHarga;
    private javax.swing.JTextField tAlamat;
    private javax.swing.JTextField tFooter;
    private javax.swing.JTextArea tFooter1;
    private javax.swing.JTextField tJamOperasional;
    private javax.swing.JTextField tKeterangan;
    private javax.swing.JTextField tLayanan;
    private javax.swing.JTextField tNamaBisnis;
    private javax.swing.JTextField tNoHp;
    private javax.swing.JButton tReset;
    private javax.swing.JTable tblDetailStruk;
    private javax.swing.JTable tblJenis;
    private static volatile javax.swing.JTable tblPengguna;
    // End of variables declaration//GEN-END:variables
}
