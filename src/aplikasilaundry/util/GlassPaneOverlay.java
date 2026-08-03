package aplikasilaundry.util;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import javax.swing.JPanel;

/**
 * Panel transparan yang ditampilkan di atas seluruh konten FrameMain
 * saat PopupBayar atau PratinjauStruk muncul.
 * Memberikan efek latar belakang gelap semi-transparan.
 */
  
public class GlassPaneOverlay extends JPanel {

    // tingkat kegelapan overlay (0.0 = transparan, 1.0 = hitam penuh)
    private float alpha = 0.45f;

    public GlassPaneOverlay() {
        setOpaque(false);

        // blokir semua klik mouse agar tidak tembus ke panel di bawahnya
        addMouseListener(new MouseAdapter() {});
        addMouseMotionListener(new MouseAdapter() {});
    }

    /**
     * Tampilkan overlay (aktifkan efek gelap)
     */
    public void tampilkan() {
        setVisible(true);
        repaint();
    }

    /**
     * Sembunyikan overlay
     */
    public void sembunyikan() {
        setVisible(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        // aktifkan anti-aliasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // gambar lapisan hitam semi-transparan di atas seluruh area
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.dispose();
    }
}

