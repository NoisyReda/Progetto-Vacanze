
import com.github.sarxos.webcam.Webcam;
import com.pqscan.barcodereader.BarCodeType;
import com.pqscan.barcodereader.BarcodeResult;
import com.pqscan.barcodereader.BarcodeScanner;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Barcode extends Thread {

    private BarcodeResult[] results;
    private JFrame frame;
    private Cassa cs;

    public void setCassa(Cassa cs) {
        this.cs = cs;
    }

    public Barcode(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void run() {
        while (Dati.getInstance().isRunning() && !Dati.getInstance().isResupply()) {
            Webcam webcam = Webcam.getDefault();
            webcam.open();
            for (;;) {
                try {
                    ImageIO.write(webcam.getImage(), "PNG", new File("Barcode.png"));
                    results = BarcodeScanner.Scan("Barcode.png", BarCodeType.EAN13);
                    Thread.sleep(400);
                    if (results != null) {
                        break;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Barcode.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Barcode.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Path img = Paths.get("Barcode.png");
            try {
                if (Files.exists(img)) {
                    Files.delete(img);
                }
                Management mm = new Management();
                ArrayList<Prodotti> pr = mm.ReadFile();

                for (int i = 0; i < pr.size(); i++) {
                    if (results[0].getData().equals(pr.get(i).getBarcode())) {
                        Dati.getInstance().setElement(pr.get(i));
                        Dati.getInstance().setTotale(Dati.getInstance().getTotale() + Float.parseFloat(pr.get(i).getPrezzo()));
                        if ("Star".equals(Dati.getInstance().getOpen())) {
                            frame.setVisible(false);
                            Cassa cs = new Cassa();
                            this.cs = cs;
                            cs.setVisible(true);
                            cs.setExtendedState(cs.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                            Thread.sleep(4000);
                        } else {
                            if (cs != null) {
                                cs.Repaint();
                            }
                            Thread.sleep(4000);
                        }
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Barcode.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Barcode.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
