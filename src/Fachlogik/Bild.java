package Fachlogik;

import java.io.OutputStream;
import java.io.PrintWriter;

public class Bild extends Medium {
    private static final long serialVersionUID = 1L;
    private String ort;

    public Bild(String titel, int jahr, String ort) {
        super(titel, jahr);
        this.ort = ort;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    @Override
    public void druckeDaten() {
        System.out.println("ID = " + getId() + " \"" + getTitel() + "\"" + " aufgenommen im Jahr "
                + getJahr() + " in " + this.ort);
    }

    // Praktikum 5
    @Override
    public void druckeDaten(OutputStream stream) {
        PrintWriter out = new PrintWriter(stream);
        out.printf(toString());
        out.flush();
    }

    @Override
    public String toString() {
        return "ID = " + getId() + " \"" + getTitel() + "\"" + " aufgenommen im Jahr "
                + getJahr() + " in " + this.ort + "\n";
    }
}
