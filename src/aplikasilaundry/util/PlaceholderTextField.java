package aplikasilaundry.util;

//Mengimpor Color untuk mengatur warna tulisan placeholder
import java.awt.Color;

//Mengimpor Graphics untuk menggambar placeholder
import java.awt.Graphics;

//Mengimpor Graphics2D untuk menggambar placeholder dengan lebih fleksibel
import java.awt.Graphics2D;

//Mengimpor Insets untuk mengambil jarak bagian dalam JTextField
import java.awt.Insets;

//Mengimpor JTextField sebagai komponen dasar text field
import javax.swing.JTextField;

//Mengimpor UIManager untuk mengambil warna placeholder dari tampilan aplikasi
import javax.swing.UIManager;

//Mengimpor DocumentEvent untuk mendeteksi perubahan isi text field
import javax.swing.event.DocumentEvent;

//Mengimpor DocumentListener untuk memantau perubahan isi text field
import javax.swing.event.DocumentListener;


//Class JTextField khusus yang memiliki fitur placeholder
public class PlaceholderTextField extends JTextField {

    //Menyimpan teks placeholder
    private String placeholder = "";

    //Constructor default
    public PlaceholderTextField() {

        //Memanggil constructor dari JTextField
        super();

        //Mengatur listener untuk placeholder
        initPlaceholder();
    }

    //Constructor dengan placeholder
    public PlaceholderTextField(String placeholder) {

        //Memanggil constructor dari JTextField
        super();

        //Menyimpan teks placeholder yang diberikan
        this.placeholder = placeholder;

        //Mengatur listener untuk placeholder
        initPlaceholder();
    }

    //Method untuk mengatur teks placeholder
    public void setPlaceholder(String placeholder) {

        //Menyimpan teks placeholder
        this.placeholder = placeholder;

        //Memperbarui tampilan text field
        repaint();
    }

    //Method untuk mengambil teks placeholder
    public String getPlaceholder() {

        //Mengembalikan teks placeholder
        return placeholder;
    }

    //Method untuk mengatur listener placeholder
    private void initPlaceholder() {

        //Mendeteksi perubahan isi pada text field
        getDocument().addDocumentListener(new DocumentListener() {

            //Dipanggil ketika teks ditambahkan
            @Override
            public void insertUpdate(DocumentEvent e) {

                //Memperbarui tampilan text field
                repaint();
            }

            //Dipanggil ketika teks dihapus
            @Override
            public void removeUpdate(DocumentEvent e) {

                //Memperbarui tampilan text field
                repaint();
            }

            //Dipanggil ketika atribut dokumen berubah
            @Override
            public void changedUpdate(DocumentEvent e) {

                //Memperbarui tampilan text field
                repaint();
            }
        });

        //Mendeteksi perubahan fokus pada text field
        addFocusListener(new java.awt.event.FocusAdapter() {

            //Dipanggil ketika text field mendapatkan fokus
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {

                //Memperbarui tampilan agar placeholder menghilang
                repaint();
            }

            //Dipanggil ketika text field kehilangan fokus
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {

                //Memperbarui tampilan agar placeholder muncul kembali jika kosong
                repaint();
            }
        });
    }

    //Method untuk menggambar isi text field
    @Override
    protected void paintComponent(Graphics g) {

        //Memanggil proses menggambar JTextField bawaan
        super.paintComponent(g);

        //Menampilkan placeholder hanya jika text field kosong,
        //tidak sedang dipilih, dan placeholder tersedia
        if (getText().isEmpty() && !hasFocus() && placeholder != null
                && !placeholder.isEmpty()) {

            //Membuat objek Graphics2D baru agar tidak mengubah graphics asli
            Graphics2D g2 = (Graphics2D) g.create();

            //Menggunakan font yang sama dengan JTextField
            g2.setFont(getFont());

            //Mengambil warna placeholder dari pengaturan tampilan aplikasi
            Color warnaPlaceholder =
                    UIManager.getColor("TextField.placeholderForeground");

            //Jika warna placeholder belum tersedia,
            //menggunakan warna abu-abu sebagai warna default
            if (warnaPlaceholder == null) {
                warnaPlaceholder = Color.GRAY;
            }

            //Mengatur warna tulisan placeholder
            g2.setColor(warnaPlaceholder);

            //Mengambil jarak bagian dalam text field
            Insets insets = getInsets();

            //Menentukan posisi horizontal placeholder
            int x = insets.left;

            //Menentukan posisi vertikal placeholder agar berada di tengah
            int y = (getHeight() - g2.getFontMetrics().getHeight()) / 2
                    + g2.getFontMetrics().getAscent();

            //Menggambar tulisan placeholder pada text field
            g2.drawString(placeholder, x, y);

            //Melepaskan objek Graphics2D setelah selesai digunakan
            g2.dispose();
        }
    }
}