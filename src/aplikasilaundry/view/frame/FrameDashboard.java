/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package aplikasilaundry.view.frame;

import aplikasilaundry.config.Session;

import aplikasilaundry.view.panel.Dasboard;
import aplikasilaundry.view.panel.DataLaundry;
import aplikasilaundry.view.panel.LaporanPemasukan;
import aplikasilaundry.view.panel.Pengaturan;
import aplikasilaundry.view.panel.RiwayatLaundry;
import aplikasilaundry.view.panel.TambahLaundry;
import aplikasilaundry.view.panel.popUpLogout;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Sirdzat
 */
public class FrameDashboard extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrameDashboard.class.getName());
    private CardLayout cardLayout;

    /**
     * Creates new form mainFrame
     */
    private DataLaundry dataLaundry;

    public FrameDashboard() {
        initComponents();
        inisiasiPanel();
        resetMenu();

        setMenuAktif(pnlDasboard);
//        System.out.println(Session.getNamaPengguna());
//        System.out.println(Session.getRole());
    }

    void inisiasiPanel() {
        cardLayout = (CardLayout) panelContent.getLayout();

        panelContent.add(new Dasboard(), "dashboard");
        panelContent.add(new TambahLaundry(), "tambahLaundry");

        dataLaundry = new DataLaundry();
        panelContent.add(dataLaundry, "semua");

        panelContent.add(new RiwayatLaundry(), "riwayat");
        panelContent.add(new LaporanPemasukan(), "laporanPemasukan");
        panelContent.add(new Pengaturan(), "pengaturan");

        panelContent.revalidate();
        panelContent.repaint();
    }

    public DataLaundry getDataLaundry() {
        return dataLaundry;
    }

    private void setWarnaMenu(JPanel panel, Color bg, Color fg) {

        panel.setBackground(bg);

        for (Component c : panel.getComponents()) {
            if (c instanceof JLabel lbl) {
                lbl.setForeground(fg);
            }
        }
    }

    private void setMenuAktif(JPanel panel) {

        resetMenu();
        resetIconMenu();

        // Panel aktif menjadi putih dan melengkung
        panel.putClientProperty(
                FlatClientProperties.STYLE,
                "arc:25;"
                + "background:#FFFFFF;"
        );

        // Warna teks menjadi biru
        for (Component c : panel.getComponents()) {
            if (c instanceof JLabel lbl) {
                lbl.setForeground(new Color(37, 99, 235));
            }
        }

        // Ganti icon sesuai menu aktif
        if (panel == pnlDasboard) {
            jLabel2.setIcon(new ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/Home_biru.png")));
        } else if (panel == pnlTambah) {
            jLabel4.setIcon(new ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/Tambah_Biru.png")));
        } else if (panel == pnlData) {
            jLabel6.setIcon(new ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/Data_Biru.png")));
        } else if (panel == pnlRiwayat) {
            jLabel8.setIcon(new ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/Riwayat_Biru.png")));
        } else if (panel == pnlLaporan) {
            jLabel10.setIcon(new ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/Laporan_biru.png")));
        } else if (panel == pnlPengaturan) {
            jLabel16.setIcon(new ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/Pengaturan_Biru.png")));
        }
    }

    private void resetIconMenu() {

        jLabel2.setIcon(new ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/Home_putih.png")));
        jLabel4.setIcon(new ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/Tambah_putih.png")));
        jLabel6.setIcon(new ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/data_putih.png")));
        jLabel8.setIcon(new ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/Riwayat_putih.png")));
        jLabel10.setIcon(new ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/Laporan_putih.png")));
        jLabel16.setIcon(new ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/pengaturan_putih.png")));
    }

    public void panggilHalaman(String namaHalaman) {

        cardLayout.show(panelContent, namaHalaman);

        switch (namaHalaman) {

            case "dashboard":
                setMenuAktif(pnlDasboard);
                break;

            case "tambahLaundry":
                setMenuAktif(pnlTambah);
                break;

            case "riwayat":
                setMenuAktif(pnlRiwayat);
                break;

            case "laporanPemasukan":
                setMenuAktif(pnlLaporan);
                break;

            case "pengaturan":
                setMenuAktif(pnlPengaturan);
                break;

            case "laundryMasuk":
            case "diproses":
            case "selesaiBelumDiambil":
            case "semua":
                setMenuAktif(pnlData);
                break;
        }
    }

    private void resetMenu() {

        Color warnaNormal = new Color(37, 99, 235);
        Color teksNormal = Color.WHITE;

        setWarnaMenu(pnlDasboard, warnaNormal, teksNormal);
        setWarnaMenu(pnlTambah, warnaNormal, teksNormal);
        setWarnaMenu(pnlData, warnaNormal, teksNormal);
        setWarnaMenu(pnlRiwayat, warnaNormal, teksNormal);
        setWarnaMenu(pnlLaporan, warnaNormal, teksNormal);
        setWarnaMenu(pnlPengaturan, warnaNormal, teksNormal);

        pnlDasboard.putClientProperty(FlatClientProperties.STYLE, null);
        pnlTambah.putClientProperty(FlatClientProperties.STYLE, null);
        pnlData.putClientProperty(FlatClientProperties.STYLE, null);
        pnlRiwayat.putClientProperty(FlatClientProperties.STYLE, null);
        pnlLaporan.putClientProperty(FlatClientProperties.STYLE, null);
        pnlPengaturan.putClientProperty(FlatClientProperties.STYLE, null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        buttonGroup1 = new javax.swing.ButtonGroup();
        panelSidebar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlDasboard = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnlTambah = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        pnlData = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        pnlRiwayat = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pnlLaporan = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        pnlLogout = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        pnlPengaturan = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        panelContent = new javax.swing.JPanel();

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelSidebar.setBackground(new java.awt.Color(37, 99, 235));
        panelSidebar.setMinimumSize(new java.awt.Dimension(274, 728));

        jLabel1.setBackground(new java.awt.Color(37, 99, 235));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasilaundry/asset/img/logo.png"))); // NOI18N

        pnlDasboard.setBackground(new java.awt.Color(37, 99, 235));
        pnlDasboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlDasboard.setMinimumSize(new java.awt.Dimension(112, 40));
        pnlDasboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlDasboardMouseClicked(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/Home.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Dasboard");

        javax.swing.GroupLayout pnlDasboardLayout = new javax.swing.GroupLayout(pnlDasboard);
        pnlDasboard.setLayout(pnlDasboardLayout);
        pnlDasboardLayout.setHorizontalGroup(
            pnlDasboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDasboardLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDasboardLayout.setVerticalGroup(
            pnlDasboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlTambah.setBackground(new java.awt.Color(37, 99, 235));
        pnlTambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlTambah.setMinimumSize(new java.awt.Dimension(112, 40));
        pnlTambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTambahMouseClicked(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/Tambah_putih.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tambah Laundry");

        javax.swing.GroupLayout pnlTambahLayout = new javax.swing.GroupLayout(pnlTambah);
        pnlTambah.setLayout(pnlTambahLayout);
        pnlTambahLayout.setHorizontalGroup(
            pnlTambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTambahLayout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTambahLayout.setVerticalGroup(
            pnlTambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlData.setBackground(new java.awt.Color(37, 99, 235));
        pnlData.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlData.setMinimumSize(new java.awt.Dimension(112, 40));
        pnlData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlDataMouseClicked(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/Data_putih.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Data Laundry");

        javax.swing.GroupLayout pnlDataLayout = new javax.swing.GroupLayout(pnlData);
        pnlData.setLayout(pnlDataLayout);
        pnlDataLayout.setHorizontalGroup(
            pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataLayout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDataLayout.setVerticalGroup(
            pnlDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlRiwayat.setBackground(new java.awt.Color(37, 99, 235));
        pnlRiwayat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlRiwayat.setMinimumSize(new java.awt.Dimension(112, 40));
        pnlRiwayat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlRiwayatMouseClicked(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/Riwayat_putih.png"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Riwayat Laundry");

        javax.swing.GroupLayout pnlRiwayatLayout = new javax.swing.GroupLayout(pnlRiwayat);
        pnlRiwayat.setLayout(pnlRiwayatLayout);
        pnlRiwayatLayout.setHorizontalGroup(
            pnlRiwayatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRiwayatLayout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlRiwayatLayout.setVerticalGroup(
            pnlRiwayatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlLaporan.setBackground(new java.awt.Color(37, 99, 235));
        pnlLaporan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlLaporan.setMinimumSize(new java.awt.Dimension(112, 40));
        pnlLaporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlLaporanMouseClicked(evt);
            }
        });

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/Laporan_putih.png"))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Laporan Pemasukan");

        javax.swing.GroupLayout pnlLaporanLayout = new javax.swing.GroupLayout(pnlLaporan);
        pnlLaporan.setLayout(pnlLaporanLayout);
        pnlLaporanLayout.setHorizontalGroup(
            pnlLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLaporanLayout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlLaporanLayout.setVerticalGroup(
            pnlLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlLogout.setBackground(new java.awt.Color(37, 99, 235));
        pnlLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlLogout.setMinimumSize(new java.awt.Dimension(112, 40));
        pnlLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlLogoutMouseClicked(evt);
            }
        });

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/logout.png"))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Logout");

        javax.swing.GroupLayout pnlLogoutLayout = new javax.swing.GroupLayout(pnlLogout);
        pnlLogout.setLayout(pnlLogoutLayout);
        pnlLogoutLayout.setHorizontalGroup(
            pnlLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLogoutLayout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlLogoutLayout.setVerticalGroup(
            pnlLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlPengaturan.setBackground(new java.awt.Color(37, 99, 235));
        pnlPengaturan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlPengaturan.setMinimumSize(new java.awt.Dimension(112, 40));
        pnlPengaturan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlPengaturanMouseClicked(evt);
            }
        });

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasilaundry/asset/icon/Pengaturan_putih.png"))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Pengaturan");

        javax.swing.GroupLayout pnlPengaturanLayout = new javax.swing.GroupLayout(pnlPengaturan);
        pnlPengaturan.setLayout(pnlPengaturanLayout);
        pnlPengaturanLayout.setHorizontalGroup(
            pnlPengaturanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPengaturanLayout.createSequentialGroup()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlPengaturanLayout.setVerticalGroup(
            pnlPengaturanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelSidebarLayout = new javax.swing.GroupLayout(panelSidebar);
        panelSidebar.setLayout(panelSidebarLayout);
        panelSidebarLayout.setHorizontalGroup(
            panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelSidebarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDasboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlRiwayat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlPengaturan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelSidebarLayout.setVerticalGroup(
            panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSidebarLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlDasboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlRiwayat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlPengaturan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 247, Short.MAX_VALUE)
                .addComponent(pnlLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(panelSidebar, java.awt.BorderLayout.LINE_START);

        panelContent.setBackground(new java.awt.Color(255, 255, 255));
        panelContent.setMinimumSize(new java.awt.Dimension(1006, 728));
        panelContent.setPreferredSize(new java.awt.Dimension(1006, 728));
        panelContent.setLayout(new java.awt.CardLayout());
        getContentPane().add(panelContent, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pnlDasboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDasboardMouseClicked
        // TODO add your handling code here:
        cardLayout.show(panelContent, "dashboard");
        setMenuAktif(pnlDasboard);
    }//GEN-LAST:event_pnlDasboardMouseClicked

    private void pnlTambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTambahMouseClicked
        // TODO add your handling code here:
        cardLayout.show(panelContent, "tambahLaundry");
        setMenuAktif(pnlTambah);
    }//GEN-LAST:event_pnlTambahMouseClicked

    private void pnlDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDataMouseClicked
        // TODO add your handling code here:
        cardLayout.show(panelContent, "semua");
        setMenuAktif(pnlData);
    }//GEN-LAST:event_pnlDataMouseClicked

    private void pnlRiwayatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlRiwayatMouseClicked
        // TODO add your handling code here:
        cardLayout.show(panelContent, "riwayat");
        setMenuAktif(pnlRiwayat);
    }//GEN-LAST:event_pnlRiwayatMouseClicked

    private void pnlLaporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlLaporanMouseClicked
        // TODO add your handling code here:
        cardLayout.show(panelContent, "laporanPemasukan");
        setMenuAktif(pnlLaporan);
    }//GEN-LAST:event_pnlLaporanMouseClicked

    private void pnlPengaturanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlPengaturanMouseClicked
        // TODO add your handling code here:
        cardLayout.show(panelContent, "pengaturan");
        setMenuAktif(pnlPengaturan);
    }//GEN-LAST:event_pnlPengaturanMouseClicked

    private void pnlLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlLogoutMouseClicked
        // TODO add your handling code here:
        popUpLogout dialog = new popUpLogout(
                (java.awt.Frame) SwingUtilities.getWindowAncestor(this),
                true
        );

        dialog.setLocationRelativeTo(this); // tampil di tengah frame
        dialog.setVisible(true);
    }//GEN-LAST:event_pnlLogoutMouseClicked

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
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
        }

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FrameDashboard().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelContent;
    private javax.swing.JPanel panelSidebar;
    private javax.swing.JPanel pnlDasboard;
    private javax.swing.JPanel pnlData;
    private javax.swing.JPanel pnlLaporan;
    private javax.swing.JPanel pnlLogout;
    private javax.swing.JPanel pnlPengaturan;
    private javax.swing.JPanel pnlRiwayat;
    private javax.swing.JPanel pnlTambah;
    // End of variables declaration//GEN-END:variables

}
