
package aplikasilaundry.model;
//Class model untuk menyimpan data pengguna
public class Pengguna {
     //Menyimpan id pengguna
    private int idPengguna;

    //Menyimpan nama pengguna
    private String namaPengguna;

    //Menyimpan username
    private String username;

    //Menyimpan password
    private String password;

    //Menyimpan role pengguna
    private String role;

    //Constructor kosong
    public Pengguna() {
    }

    //Method untuk mengambil id pengguna
    public int getIdPengguna() {
        return idPengguna;
    }

    //Method untuk mengisi id pengguna
    public void setIdPengguna(int idPengguna) {
        this.idPengguna = idPengguna;
    }

    //Method untuk mengambil nama pengguna
    public String getNamaPengguna() {
        return namaPengguna;
    }

    //Method untuk mengisi nama pengguna
    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

    //Method untuk mengambil username
    public String getUsername() {
        return username;
    }

    //Method untuk mengisi username
    public void setUsername(String username) {
        this.username = username;
    }

    //Method untuk mengambil password
    public String getPassword() {
        return password;
    }

    //Method untuk mengisi password
    public void setPassword(String password) {
        this.password = password;
    }

    //Method untuk mengambil role
    public String getRole() {
        return role;
    }

    //Method untuk mengisi role
    public void setRole(String role) {
        this.role = role;
    }
}
