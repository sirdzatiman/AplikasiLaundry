package aplikasilaundry.controller;

//Mengimpor DAO pengguna
import aplikasilaundry.dao.PenggunaDAO;
//Mengimpor model Pengguna
import aplikasilaundry.model.Pengguna;
//Mengimpor Frame Dashboard
import aplikasilaundry.view.frame.FrameDashboard;
//Mengimpor Frame Login
import aplikasilaundry.view.frame.FrameLogin;
//Mengimpor JOptionPane untuk menampilkan dialog
import javax.swing.JOptionPane;
//Mengimpor Session untuk mengelola data login pengguna
import aplikasilaundry.config.Session;

//Controller yang menangani proses login pengguna
public class LoginController {
    //Menyimpan objek FrameLogin
    private FrameLogin frame;
    //Constructor untuk menghubungkan controller dengan FrameLogin
    public LoginController(FrameLogin frame) {
        this.frame = frame;
    }

    //Method untuk memproses login pengguna
    public void login() {
        //Mengambil data username dan password dari form login
        String username = frame.getUsername();
        String password = frame.getPassword();

        //Memvalidasi agar username tidak kosong
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(
                    frame,
                    "Username tidak boleh kosong!"
            );
            return;
        }

        //Memvalidasi agar password tidak kosong
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(
                    frame,
                    "Password tidak boleh kosong!"
            );
            return;
        }

        //Membuat objek DAO untuk proses autentikasi pengguna
        PenggunaDAO dao = new PenggunaDAO();
        //Mencari data pengguna berdasarkan username dan password
        Pengguna pengguna = dao.login(username, password);
        //Jika data pengguna ditemukan
        if (pengguna != null) {

            //Menyimpan data pengguna ke dalam Session
            Session.login(
                    pengguna.getIdPengguna(),
                    pengguna.getNamaPengguna(),
                    pengguna.getUsername(),
                    pengguna.getRole()
            );

            //Menanyakan apakah session ingin disimpan di perangkat
            int pilihan = JOptionPane.showConfirmDialog(
                    frame,
                    "Simpan data login di perangkat ini?",
                    "Simpan Sesi",
                    JOptionPane.YES_NO_OPTION
            );

            //Menyimpan atau menghapus session sesuai pilihan pengguna
            if (pilihan == JOptionPane.YES_OPTION) {
                //Menyimpan session
                Session.simpanSesi();

            } else {
                //Menghapus session yang tersimpan
                Session.hapusSesi();
            }

            //Menampilkan pesan bahwa login berhasil
            JOptionPane.showMessageDialog(
                    frame,
                    "Login Berhasil. Selamat datang "
                    + pengguna.getNamaPengguna()
            );

            //Membuka halaman Dashboard
            FrameDashboard dashboard = new FrameDashboard();
            dashboard.setVisible(true);
            //Menutup form login
            frame.dispose();

        } else {
            //Menampilkan pesan jika login gagal
            JOptionPane.showMessageDialog(
                    frame,
                    "Username atau Password salah!"
            );
        }
    }
}
