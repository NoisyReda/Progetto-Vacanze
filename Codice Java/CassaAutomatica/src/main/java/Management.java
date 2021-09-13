
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mattia
 */
public class Management {
    
    public ArrayList ReadFile() throws FileNotFoundException {
        ArrayList<Prodotti> prodotti = new ArrayList<Prodotti>();
        Scanner scanner = new Scanner(new File("Data.txt"));
        while (scanner.hasNextLine()) {
            prodotti.add((ReadFromFile(scanner.nextLine())));
        }
        scanner.close();
        return prodotti;
    }
    
    private Prodotti ReadFromFile(String s) {
        Prodotti pr = new Prodotti();
        String[] spl = s.split("_");
        pr.setPrezzo(spl[0]);
        pr.setNome(spl[1]);
        pr.setBarcode(spl[2]);
        return pr;
    }
    
}
