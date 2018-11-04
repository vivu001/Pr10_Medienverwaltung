package Fachlogik;

import java.io.OutputStream;
import java.io.Serializable;

public abstract class Medium implements Comparable<Medium>, Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String titel;
    private int jahr;
    private static int anzMedium = 0;

    public Medium(String titel, int jahr) {
        this.id = anzMedium++;
        this.titel = titel;
        this.jahr = jahr;
    }

    public int getId() { return id; }

    public String getTitel() { return titel; }

    public int getJahr() { return jahr; }

    public int alter() { return java.time.LocalDate.now().getYear() - jahr; }

    public abstract void druckeDaten();

    // Praktikum 5
    public abstract void druckeDaten(OutputStream stream);

    @Override
    public int compareTo(Medium med) { return getJahr() - med.getJahr(); }

}
