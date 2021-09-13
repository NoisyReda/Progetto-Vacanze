
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mattia
 */
public class Main {    

    private static Barcode br;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Dati.getInstance();
        Dati.getInstance().setRunning(true);
        Start mf = new Start();
        mf.setVisible(true);
        mf.setExtendedState(mf.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        br = new Barcode(mf);            
        br.start();
        Dati.getInstance().setBr(br);
        try {
            br.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Cassa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
