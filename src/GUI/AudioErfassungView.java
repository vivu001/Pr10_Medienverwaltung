package GUI;

import Fachlogik.Audio;
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

public class AudioErfassungView extends ModalStage {
    Medienverwaltung mvw = new Medienverwaltung();
    Audio audio;

    public AudioErfassungView(Audio audio, Stage stage) {
        super(stage);
        this.audio = audio;
    }

    public void showView() {
        Label titel = new Label("Titel: ");
        Label interpret = new Label("Intepret: ");
        Label aufnahmeJahr = new Label("Aufnahmejahr: ");
        Label dauer = new Label("Spieldauer: ");

        TextField tf_titel = new TextField();
        TextField tf_interpret = new TextField();
        TextField tf_jahr = new TextField();
        TextField tf_dauer = new TextField();

        Button neu = new Button("Neu");
        neu.setOnAction(event -> mvw.aufnehmen(audio));
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

        GridPane.setConstraints(interpret, 0, 1);
        GridPane.setHalignment(interpret, HPos.RIGHT);

        GridPane.setConstraints(aufnahmeJahr, 0, 2);
        GridPane.setHalignment(aufnahmeJahr, HPos.RIGHT);

        GridPane.setConstraints(dauer, 0, 3);
        GridPane.setHalignment(dauer, HPos.RIGHT);

        GridPane.setConstraints(tf_titel, 1, 0);
        GridPane.setConstraints(tf_interpret, 1, 1);
        GridPane.setConstraints(tf_jahr, 1, 2);
        GridPane.setConstraints(tf_dauer, 1, 3);
        GridPane.setHgrow(tf_dauer, Priority.ALWAYS);

        GridPane.setConstraints(hbox, 1, 4);

        grid.getChildren().addAll(titel, interpret, aufnahmeJahr, dauer, tf_titel, tf_interpret, tf_jahr, tf_dauer, hbox);

        Scene scene = new Scene(grid, 320, 200);

        setScene(scene);
        setTitle("Audiofassung");
        setMaxHeight(250);
        setMinHeight(220);
        setMinWidth(300);
        show();
    }

}
