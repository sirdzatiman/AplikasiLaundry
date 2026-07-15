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
import aplikasilaundry.controller.TransaksiController;
//Mengimpor controller pengguna
import aplikasilaundry.controller.PenggunaController;

//Mengimpor model pengguna
import aplikasilaundry.model.Pengguna;
//Mengimpor JOptionPane
import javax.swing.JOptionPane;
//Mengimpor controller layanan
import javax.swing.SwingUtilities;

import aplikasilaundry.view.dialog.popUpEditKonfigurasiLayanan;
import aplikasilaundry.view.dialog.popUpKonfirmasiHapus;
import aplikasilaundry.view.dialog.popUpPensil;
import aplikasilaundry.view.dialog.popUpTambahKonfigurasiLayanan;
import aplikasilaundry.view.dialog.popUpTambahpengguna;
import aplikasilaundry.controller.LayananController;

//Mengimpor model layanan
import aplikasilaundry.model.Layanan;

//Mengimpor collection
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Pengaturan extends javax.swing.JPanel {
//Controller pengaturan

    private PengaturanStrukController controller;
    //Controller pengguna
    private PenggunaController controllerPengguna;
    //Controller transaksi
    private TransaksiController controllerTransaksi;

    //Model pengaturan
    private PengaturanStruk pengaturan;

    //Controller layanan
    private LayananController controllerLayanan;
//Menyimpan daftar layanan
private List<Layanan> daftarLayanan;
    public Pengaturan() {

    initComponents();
    
        StyledDocument doc = tFooter1.getStyledDocument();

        SimpleAttributeSet centerr = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerr, StyleConstants.ALIGN_CENTER);

    doc.setParagraphAttributes(0, doc.getLength(), centerr, false);

    TableStyle.TableStyle(tblPengguna);
    TableStyle.TableStyle(tblJenis);
    TableStyle.TableStyle(tblDetailStruk);

    //Membuat controller pengaturan struk
    controller =
            new PengaturanStrukController();

    //Membuat controller transaksi
    controllerTransaksi =
            new TransaksiController();

    //Membuat controller pengguna
    controllerPengguna =
            new PenggunaController();

    //Membuat controller layanan
    controllerLayanan =
            new LayananController();

    //Mengubah judul kolom preview struk
    DefaultTableModel modelPreview =
            (DefaultTableModel) tblDetailStruk.getModel();

    modelPreview.setColumnIdentifiers(new Object[]{
        "Jenis",
        "Jumlah",
        "Harga",
        "Subtotal"
    });

    //Method-method yang sudah ada
    tampilPengaturan();
    tampilPengguna();

    //Menampilkan data layanan
    tampilLayanan();


        //Mengatur posisi isi kolom preview struk
//Kolom jumlah rata tengah
        DefaultTableCellRenderer centerPreview = new DefaultTableCellRenderer();
        centerPreview.setHorizontalAlignment(JLabel.CENTER);

        tblDetailStruk.getColumnModel().getColumn(1)
                .setCellRenderer(centerPreview);

//Kolom harga dan subtotal rata kanan
        DefaultTableCellRenderer rightPreview = new DefaultTableCellRenderer();
        rightPreview.setHorizontalAlignment(JLabel.RIGHT);

        tblDetailStruk.getColumnModel().getColumn(2)
                .setCellRenderer(rightPreview);

        tblDetailStruk.getColumnModel().getColumn(3)
                .setCellRenderer(rightPreview);

//Mengatur lebar kolom preview struk
        tblDetailStruk.getColumnModel().getColumn(0).setPreferredWidth(170); // Jenis
        tblDetailStruk.getColumnModel().getColumn(1).setPreferredWidth(70);  // Jumlah
        tblDetailStruk.getColumnModel().getColumn(2).setPreferredWidth(90);  // Harga
        tblDetailStruk.getColumnModel().getColumn(3).setPreferredWidth(100); // Subtotal

        tblDetailStruk.getTableHeader().setBackground(
                new java.awt.Color(240, 243, 247));

        tblDetailStruk.getTableHeader().setForeground(
                java.awt.Color.BLACK);

        tblDetailStruk.getTableHeader().setFont(
                new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));

        // Mengubah judul kolom tblJenis
        DefaultTableModel modelJenis
                = (DefaultTableModel) tblJenis.getModel();

        modelJenis.setColumnIdentifiers(new Object[]{
            "No",
            "Jenis Item",
            "Proses Layanan",
            "Satuan",
            "Harga",
            "Keterangan"
        });

        // Mengubah judul kolom tblPengguna
        DefaultTableModel model
                = (DefaultTableModel) tblPengguna.getModel();

        model.setColumnIdentifiers(new Object[]{
            "Nama",
            "Username",
            "Role"
        });

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);

        tblJenis.getColumnModel().getColumn(0).setCellRenderer(center);
        tblJenis.getColumnModel().getColumn(2).setCellRenderer(center);
        tblJenis.getColumnModel().getColumn(3).setCellRenderer(center);
        tblJenis.getColumnModel().getColumn(4).setCellRenderer(center);

        tblJenis.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblJenis.getColumnModel().getColumn(1).setPreferredWidth(220);
        tblJenis.getColumnModel().getColumn(2).setPreferredWidth(220);
        tblJenis.getColumnModel().getColumn(3).setPreferredWidth(80);
        tblJenis.getColumnModel().getColumn(4).setPreferredWidth(120);
        tblJenis.getColumnModel().getColumn(5).setPreferredWidth(180);

        
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
        //Membuat controller layanan
        controllerLayanan
                = new LayananController();

        //Menampilkan data pengaturan
        tampilPengaturan();
        //Menampilkan data pengguna
        tampilPengguna();
        //Menampilkan data layanan
        tampilLayanan();

        //Mempercepat scroll mouse
        jScrollPane3.getVerticalScrollBar().setUnitIncrement(25);

        jPanelPengguna.setBorder(
                javax.swing.BorderFactory.createLineBorder(
                        new java.awt.Color(220, 223, 230), 1));

        jPanelInformasiLaundry.setBorder(
                javax.swing.BorderFactory.createLineBorder(
                        new java.awt.Color(220, 223, 230), 1));

        jPanelPengaturanStruk.setBorder(
                javax.swing.BorderFactory.createLineBorder(
                        new java.awt.Color(220, 223, 230), 1));

    }

    //Method menampilkan data layanan
    public void tampilLayanan() {

        //Mengambil model tabel
        DefaultTableModel model
                = (DefaultTableModel) tblJenis.getModel();

        //Menghapus seluruh isi tabel
        model.setRowCount(0);

       //Mengambil seluruh data layanan
daftarLayanan =
        controllerLayanan.getAll();

        //Nomor urut
        int no = 1;

        //Menampilkan seluruh data
        for (Layanan layanan : daftarLayanan) {

            //Menambahkan data ke tabel
            model.addRow(new Object[]{
                no++,
                layanan.getNamaLayanan(),
                layanan.getProses(),
                layanan.getSatuan(),
                FormatRupiah.format(
                layanan.getHarga()),
                layanan.getKeterangan()

            });

        }

    }

    //Method menampilkan data pengaturan
    private void tampilPengaturan() {

        //Mengambil data pengaturan
        pengaturan = controller.getPengaturan();

        //Menampilkan nama bisnis
        tNamaBisnis.setText(pengaturan.getNamaBisnis());

        //Menampilkan alamat
        tAlamat.setText(pengaturan.getAlamat());

        //Menampilkan nomor HP
        tNoHp.setText(pengaturan.getNoHp());

        //Menampilkan alamat pada preview struk
        lblAlamatLaundry.setText(pengaturan.getAlamat());

        //Menampilkan nomor HP pada preview struk
        lblNoHpLaundry.setText(pengaturan.getNoHp());

        //Menampilkan nama bisnis pada preview struk
        lblNamaBisnis.setText(pengaturan.getNamaBisnis());

        //Menampilkan slogan pada preview struk
        lblKeterangan.setText(pengaturan.getKeterangan());

        //Menampilkan jam operasional
        tJamOperasional.setText(pengaturan.getJamOperasional());

        //Menampilkan layanan
        tLayanan.setText(pengaturan.getLayanan());

        //Menampilkan slogan laundry
        tKeterangan.setText(pengaturan.getKeterangan());

        //Menampilkan footer struk
        tFooter.setText(pengaturan.getFooterStruk());
        //Menampilkan footer pada preview struk
        tFooter1.setText(pengaturan.getFooterStruk());

        //Mengambil transaksi terbaru
        List<Transaksi> daftarTransaksi = controllerTransaksi.getTransaksiTerbaru();

        //Jika terdapat transaksi
        if (!daftarTransaksi.isEmpty()) {

            //Mengambil transaksi paling baru
            Transaksi transaksi = controllerTransaksi.getByNoNota(daftarTransaksi.get(0).getNoNota());

            //Menampilkan nomor nota
            lblNoNota.setText(transaksi.getNoNota());

            //Menampilkan tanggal masuk
            lblTanggal.setText(transaksi.getTanggalMasuk());

            //Menampilkan jam masuk
            lblJamMasuk.setText(transaksi.getJamMasuk());

            //Menampilkan nama pelanggan
            lblPelanggan.setText(transaksi.getNamaPelanggan());

            //Menampilkan nomor HP pelanggan
            lblNoHp.setText(transaksi.getNoHp());

            //Menampilkan alamat pelanggan
            lblAsal.setText(transaksi.getAlamat());
            //Mengambil seluruh detail transaksi
            List<DetailTransaksi> daftarDetail = controllerTransaksi.getDetailByNota(transaksi.getNoNota());

            //Mengambil model tabel yang sudah ada
            DefaultTableModel model = (DefaultTableModel) tblDetailStruk.getModel();

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
        jPanelPengguna = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnEditPengguna = new javax.swing.JButton();
        btnHappusPengguna = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblPengguna = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnTambahPengguna = new javax.swing.JButton();
        jPanelInformasiLaundry = new javax.swing.JPanel();
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
        jPanelPengaturanStruk = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lblNamaBisnis = new javax.swing.JLabel();
        lblAlamatLaundry = new javax.swing.JLabel();
        lblNoHpLaundry = new javax.swing.JLabel();
        lblKeterangan = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        lblNoNota = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lblTanggal = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        lblJamMasuk = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lblPelanggan = new javax.swing.JLabel();
        jPanel45 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel46 = new javax.swing.JPanel();
        lblNoHp = new javax.swing.JLabel();
        jPanel48 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel49 = new javax.swing.JPanel();
        lblAsal = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
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
        tFooter1 = new javax.swing.JTextPane();
        jPanel53 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetailStruk = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tFooter = new javax.swing.JTextField();
        jPanel33 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        tNamaBisnis = new javax.swing.JTextField();
        btnSimpanPengaturan = new javax.swing.JToggleButton();
        btnReset = new javax.swing.JButton();
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

        jPanelPengguna.setLayout(new java.awt.BorderLayout());

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

        jPanelPengguna.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10));
        jPanel4.setLayout(new java.awt.CardLayout());

        tblPengguna.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(tblPengguna);

        jPanel4.add(jScrollPane5, "card2");

        jPanelPengguna.add(jPanel4, java.awt.BorderLayout.CENTER);

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

        jPanelPengguna.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(jPanelPengguna);

        jPanelInformasiLaundry.setLayout(new java.awt.BorderLayout());

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

        jPanelInformasiLaundry.add(jPanel47, java.awt.BorderLayout.PAGE_START);

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
                .addContainerGap(756, Short.MAX_VALUE)
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
                        .addComponent(tLayanan, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
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
                .addComponent(tAlamat, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
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

        jPanelInformasiLaundry.add(jPanel50, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanelInformasiLaundry);

        jPanel40.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanelPengaturanStruk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 243, 247), 10));
        jPanelPengaturanStruk.setMinimumSize(new java.awt.Dimension(1006, 585));
        jPanelPengaturanStruk.setPreferredSize(new java.awt.Dimension(1006, 635));
        jPanelPengaturanStruk.setLayout(new java.awt.BorderLayout());

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

        jPanelPengaturanStruk.add(jPanel14, java.awt.BorderLayout.PAGE_START);

        jPanel15.setLayout(new java.awt.BorderLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel5.setForeground(new java.awt.Color(204, 204, 204));
        jPanel5.setMinimumSize(new java.awt.Dimension(320, 500));
        jPanel5.setPreferredSize(new java.awt.Dimension(320, 535));
        jPanel5.setLayout(new java.awt.CardLayout());

        jPanel12.setBackground(new java.awt.Color(239, 238, 245));
        jPanel12.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 226, 230), 2)));
        jPanel12.setPreferredSize(new java.awt.Dimension(321, 500));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/logo 1.png"))); // NOI18N

        lblNamaBisnis.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNamaBisnis.setText("MOJOSARI LAUNDRY");

        lblAlamatLaundry.setText("Bersih, Suci, Wangi");

        lblNoHpLaundry.setText("Bersih, Suci, Wangi");

        lblKeterangan.setText("Bersih, Suci, Wangi");

        jPanel30.setBackground(new java.awt.Color(239, 238, 245));

        jPanel31.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 16));
        jPanel31.setPreferredSize(new java.awt.Dimension(304, 130));
        jPanel31.setLayout(new java.awt.GridLayout(6, 0, 8, 5));

        jPanel32.setBackground(new java.awt.Color(239, 238, 245));
        jPanel32.setLayout(new java.awt.BorderLayout(13, 0));

        lblNoNota.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblNoNota.setText("INV-260508-001");
        jPanel32.add(lblNoNota, java.awt.BorderLayout.CENTER);

        jPanel17.setPreferredSize(new java.awt.Dimension(85, 15));
        jPanel17.setLayout(new java.awt.BorderLayout());

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel32.setText("No Nota");
        jPanel17.add(jLabel32, java.awt.BorderLayout.LINE_START);

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel33.setText(":");
        jLabel33.setPreferredSize(new java.awt.Dimension(5, 14));
        jPanel17.add(jLabel33, java.awt.BorderLayout.LINE_END);

        jPanel32.add(jPanel17, java.awt.BorderLayout.LINE_START);

        jPanel31.add(jPanel32);

        jPanel9.setBackground(new java.awt.Color(239, 238, 245));
        jPanel9.setLayout(new java.awt.BorderLayout(13, 0));

        lblTanggal.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblTanggal.setText("08/05/2026");
        jPanel9.add(lblTanggal, java.awt.BorderLayout.CENTER);

        jPanel16.setPreferredSize(new java.awt.Dimension(85, 15));
        jPanel16.setLayout(new java.awt.BorderLayout());

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel30.setText("Tanggal");
        jPanel16.add(jLabel30, java.awt.BorderLayout.LINE_START);

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel31.setText(":");
        jLabel31.setPreferredSize(new java.awt.Dimension(5, 14));
        jPanel16.add(jLabel31, java.awt.BorderLayout.LINE_END);

        jPanel9.add(jPanel16, java.awt.BorderLayout.LINE_START);

        jPanel31.add(jPanel9);

        jPanel10.setBackground(new java.awt.Color(239, 238, 245));
        jPanel10.setLayout(new java.awt.BorderLayout(13, 0));

        lblJamMasuk.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblJamMasuk.setText("10:30:45");
        jPanel10.add(lblJamMasuk, java.awt.BorderLayout.CENTER);

        jPanel8.setPreferredSize(new java.awt.Dimension(85, 15));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel24.setText("Jam Masuk");
        jPanel8.add(jLabel24, java.awt.BorderLayout.LINE_START);

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText(":");
        jLabel25.setPreferredSize(new java.awt.Dimension(5, 14));
        jPanel8.add(jLabel25, java.awt.BorderLayout.LINE_END);

        jPanel10.add(jPanel8, java.awt.BorderLayout.LINE_START);

        jPanel31.add(jPanel10);

        jPanel11.setBackground(new java.awt.Color(239, 238, 245));
        jPanel11.setLayout(new java.awt.BorderLayout(13, 0));

        lblPelanggan.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblPelanggan.setText("Siti Aisyah");
        jPanel11.add(lblPelanggan, java.awt.BorderLayout.CENTER);

        jPanel45.setPreferredSize(new java.awt.Dimension(85, 15));
        jPanel45.setLayout(new java.awt.BorderLayout());

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel26.setText("Pelanggan");
        jPanel45.add(jLabel26, java.awt.BorderLayout.LINE_START);

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText(":");
        jLabel27.setPreferredSize(new java.awt.Dimension(5, 14));
        jPanel45.add(jLabel27, java.awt.BorderLayout.LINE_END);

        jPanel11.add(jPanel45, java.awt.BorderLayout.LINE_START);

        jPanel31.add(jPanel11);

        jPanel46.setBackground(new java.awt.Color(239, 238, 245));
        jPanel46.setLayout(new java.awt.BorderLayout(13, 0));

        lblNoHp.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblNoHp.setText("0857-9155-9991");
        jPanel46.add(lblNoHp, java.awt.BorderLayout.CENTER);

        jPanel48.setPreferredSize(new java.awt.Dimension(85, 15));
        jPanel48.setLayout(new java.awt.BorderLayout());

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel28.setText("No HP");
        jPanel48.add(jLabel28, java.awt.BorderLayout.LINE_START);

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setText(":");
        jLabel29.setPreferredSize(new java.awt.Dimension(5, 14));
        jPanel48.add(jLabel29, java.awt.BorderLayout.LINE_END);

        jPanel46.add(jPanel48, java.awt.BorderLayout.LINE_START);

        jPanel31.add(jPanel46);

        jPanel49.setBackground(new java.awt.Color(239, 238, 245));
        jPanel49.setLayout(new java.awt.BorderLayout(13, 0));

        lblAsal.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblAsal.setText("PPP.KH.A.BASTHOMI");
        jPanel49.add(lblAsal, java.awt.BorderLayout.CENTER);

        jPanel18.setPreferredSize(new java.awt.Dimension(85, 15));
        jPanel18.setLayout(new java.awt.BorderLayout());

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel34.setText("Asal");
        jPanel18.add(jLabel34, java.awt.BorderLayout.LINE_START);

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel35.setText(":");
        jLabel35.setPreferredSize(new java.awt.Dimension(5, 14));
        jPanel18.add(jLabel35, java.awt.BorderLayout.LINE_END);

        jPanel49.add(jPanel18, java.awt.BorderLayout.LINE_START);

        jPanel31.add(jPanel49);

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

        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("------------------------------------------------------");
        jLabel49.setPreferredSize(new java.awt.Dimension(304, 16));

        jPanel23.setBackground(new java.awt.Color(239, 238, 245));
        jPanel23.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 16));
        jPanel23.setPreferredSize(new java.awt.Dimension(304, 84));

        tFooter1.setBackground(new java.awt.Color(239, 238, 245));
        tFooter1.setBorder(null);
        tFooter1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tFooter1.setEnabled(false);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tFooter1, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tFooter1, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel53.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 16));
        jPanel53.setPreferredSize(new java.awt.Dimension(304, 124));
        jPanel53.setLayout(new java.awt.CardLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

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

        jLabel20.setBackground(new java.awt.Color(239, 238, 245));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("------------------------------------------------------");
        jLabel20.setPreferredSize(new java.awt.Dimension(304, 16));

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(112, 112, 112))
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel8)
                .addGap(28, 28, 28)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblAlamatLaundry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNamaBisnis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNoHpLaundry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(lblNamaBisnis)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAlamatLaundry)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNoHpLaundry)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblKeterangan))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.add(jPanel12, "card2");

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

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
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
                .addComponent(tNamaBisnis, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
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

        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/SystemUiconsReset.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReset.addActionListener(this::btnResetActionPerformed);

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
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

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
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
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jPanel15.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanelPengaturanStruk.add(jPanel15, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanelPengaturanStruk, java.awt.BorderLayout.PAGE_START);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 707, Short.MAX_VALUE)
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
            .addGap(0, 568, Short.MAX_VALUE)
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
        //Mengambil baris yang dipilih
int baris =
        tblJenis.getSelectedRow();

//Jika belum memilih baris
if(baris == -1){

    JOptionPane.showMessageDialog(
            this,
            "Pilih data layanan yang akan diedit.");

    return;

}

//Mengambil data layanan
Layanan layanan =
        daftarLayanan.get(baris);

//Membuka popup edit
popUpEditKonfigurasiLayanan dialog =
        new popUpEditKonfigurasiLayanan(
                (java.awt.Frame)
                SwingUtilities.getWindowAncestor(this),
                true,
                this,
                layanan);

//Menampilkan popup di tengah
dialog.setLocationRelativeTo(this);

//Menampilkan popup
dialog.setVisible(true);

//Memperbarui tabel layanan
tampilLayanan();

    }//GEN-LAST:event_btnEditJenisActionPerformed

    private void btnTambahKonfigurasiLAyananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahKonfigurasiLAyananActionPerformed
        // TODO add your handling code here:
//Membuka dialog tambah konfigurasi layanan
        popUpTambahKonfigurasiLayanan dialog
                = new popUpTambahKonfigurasiLayanan(
                        (java.awt.Frame) SwingUtilities.getWindowAncestor(this),
                        true);

//Menampilkan dialog di tengah frame
        dialog.setLocationRelativeTo(this);

//Menampilkan dialog
        dialog.setVisible(true);

//Memperbarui tabel layanan
        tampilLayanan();
    }//GEN-LAST:event_btnTambahKonfigurasiLAyananActionPerformed

    private void btnHapusJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusJenisActionPerformed
        // TODO add your handling code here:
//Mengambil baris yang dipilih
int baris =
        tblJenis.getSelectedRow();

//Validasi
if(baris == -1){

    JOptionPane.showMessageDialog(
            this,
            "Pilih konfigurasi layanan yang akan dihapus.");

    return;

}

//Mengambil data layanan
Layanan layanan =
        daftarLayanan.get(baris);

//Membuka popup hapus
//Membuka popup hapus
popUpKonfirmasiHapus dialog =
        new popUpKonfirmasiHapus(
                (java.awt.Frame)
                SwingUtilities.getWindowAncestor(this),
                true,
                this,
                layanan);

//Menampilkan popup
dialog.setLocationRelativeTo(this);
dialog.setVisible(true);

//Refresh tabel
tampilLayanan();
    }//GEN-LAST:event_btnHapusJenisActionPerformed

    private void btnSimpanPengaturanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanPengaturanActionPerformed
        // TODO add your handling code here:

        //Mengambil nama bisnis dari form
        pengaturan.setNamaBisnis(
                tNamaBisnis.getText().trim());

        //Mengambil alamat laundry dari form
        pengaturan.setAlamat(
                tAlamat.getText().trim());

        //Mengambil nomor HP laundry
        pengaturan.setNoHp(
                tNoHp.getText().trim());

        //Mengambil jam operasional
        pengaturan.setJamOperasional(
                tJamOperasional.getText().trim());

        //Mengambil informasi layanan
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

        //Memuat ulang data pengaturan
        tampilPengaturan();

        //Menampilkan informasi berhasil
        JOptionPane.showMessageDialog(
                this,
                "Pengaturan berhasil diperbarui.");


    }//GEN-LAST:event_btnSimpanPengaturanActionPerformed

    private void btnHappusPenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHappusPenggunaActionPerformed

        //Mengambil baris yang dipilih
        int baris = tblPengguna.getSelectedRow();

        //Jika belum memilih data
        if (baris == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pilih pengguna yang akan dihapus.");

            return;

        }

        //Mengambil username dari tabel
        String username
                = tblPengguna.getValueAt(
                        baris,
                        1).toString();

        //Mencari objek pengguna berdasarkan username
        Pengguna data = null;

        for (Pengguna p : controllerPengguna.getAll()) {

            if (p.getUsername().equals(username)) {

                data = controllerPengguna.getById(
                        p.getIdPengguna());

                break;

            }

        }

        //Jika data tidak ditemukan
        if (data == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Data pengguna tidak ditemukan.");

            return;

        }

        //Membuka popup konfirmasi
        popUpKonfirmasiHapus dialog
                = new popUpKonfirmasiHapus(
                        (java.awt.Frame) javax.swing.SwingUtilities
                                .getWindowAncestor(this),
                        true,
                        data.getIdPengguna(),
                        this);

        //Menampilkan popup
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);


    }//GEN-LAST:event_btnHappusPenggunaActionPerformed

    private void btnEditPenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditPenggunaActionPerformed
        // TODO add your handling code here:
        //Mengambil baris yang dipilih
        int baris = tblPengguna.getSelectedRow();

//Jika belum memilih data
        if (baris == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pilih pengguna yang akan diedit.");

            return;

        }

//Mengambil username dari tabel
        String username
                = tblPengguna.getValueAt(
                        baris,
                        1).toString();

//Mencari objek pengguna berdasarkan username
        Pengguna data = null;

        for (Pengguna p : controllerPengguna.getAll()) {

            if (p.getUsername().equals(username)) {

                data = controllerPengguna.getById(
                        p.getIdPengguna());

                break;

            }

        }

//Jika data tidak ditemukan
        if (data == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Data pengguna tidak ditemukan.");

            return;

        }

//Membuka popup edit
        popUpPensil dialog
                = new popUpPensil(
                        (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this),
                        true);

//Mengirim data ke popup
        dialog.setPengguna(data);

//Menampilkan popup
        dialog.setVisible(true);
//Menampilkan kembali data pengguna
        tampilPengguna();
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

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        //Menampilkan kembali data pengaturan dari database
        tampilPengaturan();

        //Memberikan informasi kepada pengguna
        JOptionPane.showMessageDialog(this, "Data berhasil dikembalikan ke pengaturan terakhir.");

    }//GEN-LAST:event_btnResetActionPerformed

//Method menampilkan seluruh data pengguna
    public void tampilPengguna() {

        System.out.println("===== REFRESH PENGGUNA =====");

        DefaultTableModel model
                = (DefaultTableModel) tblPengguna.getModel();

        model.setRowCount(0);

        List<Pengguna> daftar
                = controllerPengguna.getAll();

        System.out.println("Jumlah Data = " + daftar.size());

        for (Pengguna pengguna : daftar) {

            System.out.println(
                    pengguna.getNamaPengguna()
                    + " | "
                    + pengguna.getUsername()
                    + " | "
                    + pengguna.getRole());

            model.addRow(new Object[]{
                pengguna.getNamaPengguna(),
                pengguna.getUsername(),
                pengguna.getRole()
            });

        }

        System.out.println("Jumlah Baris JTable = "
                + model.getRowCount());

        tblPengguna.revalidate();
        tblPengguna.repaint();

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditJenis;
    private javax.swing.JButton btnEditPengguna;
    private javax.swing.JButton btnHappusPengguna;
    private javax.swing.JButton btnHapusJenis;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpanInformasi;
    private javax.swing.JToggleButton btnSimpanPengaturan;
    private javax.swing.JToggleButton btnTambahKonfigurasiLAyanan;
    private javax.swing.JButton btnTambahPengguna;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
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
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
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
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelInformasiLaundry;
    private javax.swing.JPanel jPanelPengaturanStruk;
    private javax.swing.JPanel jPanelPengguna;
    private javax.swing.JScrollPane jScrollPane1;
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
    private javax.swing.JTextPane tFooter1;
    private javax.swing.JTextField tJamOperasional;
    private javax.swing.JTextField tKeterangan;
    private javax.swing.JTextField tLayanan;
    private javax.swing.JTextField tNamaBisnis;
    private javax.swing.JTextField tNoHp;
    private javax.swing.JTable tblDetailStruk;
    private javax.swing.JTable tblJenis;
    private javax.swing.JTable tblPengguna;
    // End of variables declaration//GEN-END:variables
}
