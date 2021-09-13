
import com.github.sarxos.webcam.Webcam;
import com.pqscan.barcodereader.BarCodeType;
import com.pqscan.barcodereader.BarcodeResult;
import com.pqscan.barcodereader.BarcodeScanner;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mattia
 */
public class BarcodeRes extends Thread {

    private BarcodeResult[] results;

    @Override
    public void run() {
        Webcam webcam = Webcam.getDefault();
        webcam.open();
        while (Dati.getInstance().isResupply()) {
            for (;;) {
                try {
                    ImageIO.write(webcam.getImage(), "PNG", new File("Barcode.png"));
                    results = BarcodeScanner.Scan("Barcode.png", BarCodeType.EAN13);
                    Thread.sleep(100);
                    if (results != null) {
                        break;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Barcode.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BarcodeRes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Path img = Paths.get("Barcode.png");
            Dati.getInstance().setBarcode(results[0].getData());
            Dati.getInstance().setResupply(false);
            try {
                if(Files.exists(img))
                Files.delete(img);
            } catch (IOException ex) {
                Logger.getLogger(Barcode.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
