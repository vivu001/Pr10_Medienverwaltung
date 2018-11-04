package Datenhaltung;

import Fachlogik.Medium;

import java.util.List;

public interface IDAO {
    void speichern(List<Medium> liste) throws PersistenzException;
    List<Medium> laden() throws PersistenzException;

    class PersistenzException extends Exception {
    }
}
