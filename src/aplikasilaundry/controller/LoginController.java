 
package aplikasilaundry.controller;

import aplikasilaundry.dao.PenggunaDAO;
import aplikasilaundry.model.Pengguna;
import aplikasilaundry.view.frame.FrameDashboard;
import aplikasilaundry.view.frame.FrameLogin;
import javax.swing.JOptionPane;
import aplikasilaundry.config.Session;

public class LoginController {
     // Menyimpan objek FrameLogin
    private FrameLogin frame;

    // Constructor
    public LoginController(FrameLogin frame) {

        // Menghubungkan controller dengan FrameLogin
        this.frame = frame;

    }

  // Method untuk proses login
public void login() {

    // Mengambil username dari FrameLogin
    String username = frame.getUsername();

    // Mengambil password dari FrameLogin
    String password = frame.getPassword();

    // Mengecek apakah username masih kosong
    if (username.isEmpty()) {
        JOptionPane.showMessageDialog(
                frame,
                "Username tidak boleh kosong!"
        );
        return;
    }

    // Mengecek apakah password masih kosong
    if (password.isEmpty()) {
        JOptionPane.showMessageDialog(
                frame,
                "Password tidak boleh kosong!"
        );
        return;
    }

    // Membuat objek DAO
    PenggunaDAO dao = new PenggunaDAO();

    // Mencari data pengguna
    Pengguna pengguna = dao.login(username, password);

    // Jika data ditemukan
    if (pengguna != null) {

        // Menyimpan data pengguna ke Session
    Session.login(
            pengguna.getIdPengguna(),
            pengguna.getNamaPengguna(),
            pengguna.getUsername(),
            pengguna.getRole()
    );
        //Menanyakan apakah session ingin disimpan
        int pilihan = JOptionPane.showConfirmDialog(
                frame,
                "Simpan data login di perangkat ini?",
                "Simpan Sesi",
                JOptionPane.YES_NO_OPTION
        );

        if (pilihan == JOptionPane.YES_OPTION) {

            //Menyimpan session
            Session.simpanSesi();

        } else {

            //Menghapus session yang mungkin tersimpan sebelumnya
            Session.hapusSesi();

        }

    JOptionPane.showMessageDialog(
            frame,
            "Login Berhasil. Selamat datang "
            + pengguna.getNamaPengguna()
    );

    // Membuka Dashboard
    FrameDashboard dashboard = new FrameDashboard();
    dashboard.setVisible(true);

    // Menutup form login
    frame.dispose();


    } else {

        JOptionPane.showMessageDialog(
                frame,
                "Username atau Password salah!"
        );

    }

}

}
