
import java.util.ArrayList;

public class Dati {

    private float totale;
    private static Dati _instance;
    private boolean Running;
    private String nome;
    private boolean resupply;
    private String Barcode;
    private Prodotti element;
    private String open;
    private Barcode br;
    private ArrayList<Prodotti> pr;

    public synchronized ArrayList<Prodotti> getPr() {
        return pr;
    }

    public void deletePr() {
        pr.remove(pr.size() - 1);
    }

    public void Reset() {
        this.totale = totale;
        this.nome = nome;
        this.Barcode = Barcode;
        this.element = element;
        this.pr = pr;
        this.prezzo = prezzo;
    }

    public synchronized void FreePr() {
        pr = new ArrayList<>();
    }

    public synchronized void setPr(Prodotti pr) {
        this.pr.add(pr);
    }

    public synchronized Barcode getBr() {
        return br;
    }

    public synchronized void setBr(Barcode br) {
        this.br = br;
    }

    public synchronized String getOpen() {
        return open;
    }

    public synchronized void setOpen(String open) {
        this.open = open;
    }

    public synchronized Prodotti getElement() {
        if (element != null) {
            return element;
        } else {
            return element = new Prodotti();
        }
    }

    public synchronized void setElement(Prodotti element) {
        this.element = element;
    }

    public synchronized String getBarcode() {
        return Barcode;
    }

    public synchronized void setBarcode(String Barcode) {
        this.Barcode = Barcode;
    }

    public synchronized boolean isResupply() {
        return resupply;
    }

    public synchronized void setResupply(boolean resupply) {
        this.resupply = resupply;
    }
    private int prezzo;

    public synchronized float getTotale() {
        return totale;
    }

    public synchronized void setTotale(float totale) {
        this.totale = totale;
    }

    public synchronized boolean isRunning() {
        return Running;
    }

    public synchronized void setRunning(boolean Running) {
        this.Running = Running;
    }

    private Dati() {
        this.totale = 0;
        this.prezzo = 0;
        this.nome = "";
        this.Running = false;
    }

    public synchronized String getNome() {
        return nome;
    }

    public synchronized void setNome(String nome) {
        this.nome = nome;
    }

    public synchronized int getPrezzo() {
        return prezzo;
    }

    public synchronized void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    public static Dati getInstance() {
        if (_instance == null)
    synchronized (Dati.class) {
            if (_instance == null) {
                _instance = new Dati();
            }
        }
        return _instance;
    }
}
