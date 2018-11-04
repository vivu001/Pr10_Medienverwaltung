package GUI;

import Fachlogik.Bild;
import Fachlogik.Medienverwaltung;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class BildErfassungView extends ModalStage {
    Medienverwaltung mvw = new Medienverwaltung();
    private Bild bild;

    public BildErfassungView(Bild bild, Stage stage) {
        super(stage);
        this.bild = bild;
    }

    public void showView() {

        Label titel = new Label("Titel: ");
        Label ort = new Label("Ort: ");
        Label aufnahmeJahr = new Label("Aufnahmejahr: ");

        TextField tf_titel = new TextField();
        TextField tf_ort = new TextField();
        TextField tf_jahr = new TextField();

        Button neu = new Button("Neu");
        neu.setOnAction(event -> mvw.aufnehmen(bild));
        Button abbrechen = new Button("Abbrechen");
        abbrechen.setOnAction(e -> close());

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(neu, abbrechen);
        hbox.setAlignment(Pos.CENTER);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setVgap(5);
        grid.setHgap(5);

        GridPane.setConstraints(titel, 0, 0);
        GridPane.setHalignment(titel, HPos.RIGHT);

        GridPane.setConstraints(ort, 0, 1);
        GridPane.setHalignment(ort, HPos.RIGHT);

        GridPane.setConstraints(aufnahmeJahr, 0, 2);
        GridPane.setHalignment(aufnahmeJahr, HPos.RIGHT);

        GridPane.setConstraints(tf_titel, 1, 0);
        GridPane.setConstraints(tf_ort, 1, 1);
        GridPane.setConstraints(tf_jahr, 1, 2);
        GridPane.setHgrow(tf_jahr, Priority.ALWAYS);

        GridPane.setConstraints(hbox, 1, 3);

        grid.getChildren().addAll(titel, ort, aufnahmeJahr, tf_titel, tf_ort, tf_jahr, hbox);

        Scene scene = new Scene(grid, 320, 150);

        setScene(scene);
        setTitle("Bilderfassung");
        setMaxHeight(250);
        setMinHeight(190);
        setMinWidth(300);
        show();
    }

}
