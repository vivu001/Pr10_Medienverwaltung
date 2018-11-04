package Fachlogik;

import java.io.OutputStream;
import java.io.PrintWriter;

public class Audio extends Medium {
    private static final long serialVersionUID = 1L;
    private String interpret;
    private int dauer;

    public Audio(String titel, int jahr, String interpret, int dauer) {
        super(titel, jahr);
        this.interpret = interpret;
        try {
            assert (dauer > 0) : "Dauer muss grosser als 0 sein";
            this.dauer = dauer;
        } catch (AssertionError ae){
            System.err.println(ae);
        }
    }

    public String getInterpret() {
        return interpret;
    }

    public void setInterpret(String interpret) {
        this.interpret = interpret;
    }

    public int getDauer() {
        return dauer;
    }

    public void setDauer(int dauer) {
        this.dauer = dauer;
    }

    @Override
    public void druckeDaten() {
        System.out.println("ID = " + getId() + " \"" + getTitel() + "\"" + " von "
                + this.interpret + " aus " + getJahr() + " Spieldauer: " + this.dauer + " sek.");
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
        return "ID = " + getId() + " \"" + getTitel() + "\"" + " von "
                + this.interpret + " aus " + getJahr() + " Spieldauer: " + this.dauer + " sek.\n";
    }
}
