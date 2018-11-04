package Datenhaltung;

import Fachlogik.Medium;

import java.io.*;
import java.util.List;

public class DAO implements IDAO {
    final File sfile = new File("./Medienlist.ser");
    List<Medium> medienArrayList;

    @Override
    public void speichern(List<Medium> liste) throws PersistenzException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(sfile))) {
            oos.writeObject(liste);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Medium> laden() throws PersistenzException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(sfile))) {
            medienArrayList = (List<Medium>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return medienArrayList;
    }
}
