package pk1.rv.gui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.Priority;
import pk1.rv.fachlogik.InakzeptabelesRisiko;
import pk1.rv.fachlogik.Risiko;

public class InakzeptablesRisikoView extends Stage {
    private TextField txtmasnahme;
    private boolean isBestetigt = false;

    public InakzeptablesRisikoView(Risiko risiko, Stage primaryStage) {

        this.initOwner(primaryStage);
        this.initModality(Modality.WINDOW_MODAL);

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 400, 300);
        this.setTitle("Erfassung inakzetables Risiko");
        this.setScene(scene);

        Label labBezeichnung = new Label("Bezeichnung:");
        Label labMasnahme = new Label("Maßnahme:");

        Label txtBezeichnung = new Label("DB Experte verlässt das Projekt");
        txtmasnahme = new TextField();
        if (risiko != null) {
            InakzeptabelesRisiko inakzep = (InakzeptabelesRisiko) risiko;
            txtmasnahme.setText(inakzep.getMassnahme());
        }

        GridPane.setHgrow(txtmasnahme, Priority.ALWAYS);

        Button btnNeu = new Button("Neu");

        btnNeu.setOnAction(e -> {
            if (getTextMasnahmeInakzeptabel().isEmpty()){
                new ErrorFensterDialog("Bitte geben Sie eine Maßnahme ein!").showView();
                return;
            }

            if (risiko != null) {
                ((InakzeptabelesRisiko) risiko).setMassnahme(getTextMasnahmeInakzeptabel());
            }
            isBestetigt = true;
            this.close();
        });


        Button btnAbbrechen = new Button("Abbrechen");
        btnAbbrechen.setOnAction(e -> {
            this.close();
        });

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(1);

        grid.add(labBezeichnung, 0, 0);
        grid.add(txtBezeichnung, 1, 0);

        grid.add(labMasnahme, 0, 1);
        grid.add(txtmasnahme, 1, 1);

        GridPane.setHalignment(labBezeichnung, HPos.RIGHT);
        GridPane.setHalignment(labMasnahme, HPos.RIGHT);

        HBox hb = new HBox(10);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(btnNeu, btnAbbrechen);

        VBox vb = new VBox(20);
        vb.setPadding(new Insets(15));
        vb.getChildren().addAll(grid, hb);

        Scene scene1 = new Scene(vb);
        this.setScene(scene1);
        this.setTitle("Erfassung inakzetables Risiko");


    }

    public void showView() {

        this.showAndWait();
    }

    public String getTextMasnahmeInakzeptabel() {
        return txtmasnahme.getText();
    }

    public boolean isBestetigt() {
        return isBestetigt;
    }
}
