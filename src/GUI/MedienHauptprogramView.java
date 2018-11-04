package GUI;

import Datenhaltung.IDAO;
import Fachlogik.Audio;
import Fachlogik.Medienverwaltung;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MedienHauptprogramView extends Application {
    Medienverwaltung mvw = new Medienverwaltung();

    @Override
    public void start(Stage primaryStage) {

        // Menu Datei
        Menu datei = new Menu("Datei");

        MenuItem laden = new MenuItem("Laden");
        laden.setOnAction(event -> {
            try {
                mvw.laden();
            } catch (IDAO.PersistenzException e) {
                e.printStackTrace();
            }
        });

        MenuItem speichern = new MenuItem("Speichern");
        speichern.setOnAction(event -> {
            try {
                mvw.speichern();
            } catch (IDAO.PersistenzException e) {
                e.printStackTrace();
            }
        });

        MenuItem medienListeInDatei = new MenuItem("Medienliste in Datei");
        medienListeInDatei.setOnAction(event -> mvw.zeigeMedienFile());

        MenuItem beenden = new MenuItem("Beenden");
        beenden.setOnAction(event -> Platform.exit());

        datei.getItems().addAll(laden, speichern, new SeparatorMenuItem(), medienListeInDatei, new SeparatorMenuItem(), beenden);

        // Menu Medium
        Menu medium = new Menu("Medium");

        MenuItem audioNeu = new MenuItem("Audio Neu");
        audioNeu.setOnAction(event -> {
           AudioErfassungView aev = new AudioErfassungView(null, primaryStage);
            aev.showView();
        });

        MenuItem bildNeu = new MenuItem("Bild Neu");
        bildNeu.setOnAction(event -> {
            BildErfassungView bev = new BildErfassungView(null, primaryStage);
            bev.showView();
        });

        medium.getItems().addAll(audioNeu, bildNeu);

        // Menu Anzeige
        Menu anzeige = new Menu("Anzeige");

        MenuItem erscheinungsJahr = new MenuItem("Erscheinungsjahr");
        erscheinungsJahr.setOnAction(event -> mvw.berechneErscheinungsjahr());

        MenuItem neuestesMedium = new MenuItem("Neuestes Medium");

        anzeige.getItems().addAll(erscheinungsJahr, neuestesMedium);

        // MenuBar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(datei, medium, anzeige);

        // BorderPane
        BorderPane bp = new BorderPane();
        bp.setTop(menuBar);

        Scene scene = new Scene(bp, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Medienverwaltung");
        primaryStage.show();
    }

}
