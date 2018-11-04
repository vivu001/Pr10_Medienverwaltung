package Fachlogik;

import Datenhaltung.IDAO;
import GUI.InputView;
import GUI.MessageView;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Medienverwaltung implements Serializable{
    private static final long serialVersionUID = 1L;
    // final File sfile = new File("./Medienlist.ser"); --> gehoert zur DAO

    private IDAO dao;
    private Stage primaryStage;

    List<Medium> medienArrayList ;

    // Konstruktor
    public Medienverwaltung(IDAO dao) {
        medienArrayList = new ArrayList<>();
        this.dao = dao;
    }

    public Medienverwaltung() {}

    // ein neues Medium in die Medienverwaltung aufnehmen
    public void aufnehmen(Medium neuesMedium) {
        medienArrayList.add(neuesMedium);
    }

    // die Daten saemtlicher gespeicherter Medien ausgeben
    public void zeigeMedien() {
        Iterator<Medium> it = iterator();
        if (it.hasNext()) {
            Collections.sort(medienArrayList);          //die Medien nach dem Erscheinungsjahr aufsteigend sortieren

            for (Medium m : medienArrayList) {
                m.druckeDaten(System.out);
            }
        }
    }

    // ein Iterator-Objekt erzeugen, um die interne Medienliste zu durchlaufen
    public Iterator<Medium> iterator() {
        return medienArrayList.iterator();
    }

    // die Daten des Mediums mit dem juengsten Erscheinungsjahr ausgeben
    public Medium sucheNeuesMedium() {
        int index = -1;

        if (medienArrayList.size() > 0) {
            index = 0;
            Medium neuesMedium = medienArrayList.get(0);

            for (Medium m : medienArrayList)
                if (m.getJahr() > neuesMedium.getJahr()) {
                    neuesMedium = m;
                    index = neuesMedium.getId();
                }

            MessageView.create(primaryStage, "Neuestes Medium", neuesMedium.toString()).showView();
            neuesMedium.druckeDaten();
        } else System.out.println("Es gibt kein Medium");

        return medienArrayList.get(index);
    }

    // das durchschnittliche Erscheinungsjahr der Medien berechnen
    public double berechneErscheinungsjahr() {
        double average = 0.0;
        int s = 0;

        for (Medium m : medienArrayList) {
            s += m.getJahr();
        }

        average = (double) s / medienArrayList.size();
        MessageView.create(primaryStage, "Das durchschnittliche Erscheinungsjahr der Medien", average +"" ).showView();

        return average;
    }

    // Medienliste in Datei schreiben
    public void zeigeMedienFile() {
        boolean isError;
        String dateiName = "";

        do {
            isError = true;
            try {
                // eingabeFenster
                dateiName = InputView.create(primaryStage, "Eingabe", "Dateiname", null).showView();
                if (dateiName.isEmpty()) throw new EmptyFilenameException("Bitte guetiger Dateiname");

                // die MedienListe in Datei schreiben
                isError = inDateiSchreiben(dateiName);

            } catch (EmptyFilenameException efe) {
                int dialogButton = JOptionPane.showConfirmDialog(null, "Dateiname ist leer! Neuen Dateinamen waehlen?", "Hinweis", JOptionPane.YES_NO_OPTION);
                if (dialogButton == JOptionPane.NO_OPTION) { isError = false; }
            }
        } while (isError);
    }

    // die aktuelle Medienliste persistent speichern
    public void speichern() throws IDAO.PersistenzException {
        dao.speichern(medienArrayList);
    }

    // die gespeicherte Medienliste laden
    public void laden() throws IDAO.PersistenzException {
        dao.laden();
    }

    // "EmptyFilename" Exception
    private class EmptyFilenameException extends Throwable {
        public EmptyFilenameException(String bitte_guetiger_dateiname) {}
    }

    // in Datei mit "try-with-resources" schreiben
    public boolean inDateiSchreiben(String dateiName) {
        Iterator<Medium> it = medienArrayList.iterator();
        try (FileOutputStream out = new FileOutputStream(dateiName)) {
            if (!it.hasNext()) {
                JOptionPane.showMessageDialog(null, "Es gibt kein Medium");
                return true;
            }

            //die Medien nach dem Erscheinungsjahr aufsteigend sortieren
            Collections.sort(medienArrayList);

            for (Medium m : medienArrayList)
                m.druckeDaten(out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

}