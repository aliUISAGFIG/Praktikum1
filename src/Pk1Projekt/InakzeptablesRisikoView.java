package Pk1Projekt;

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

public class InakzeptablesRisikoView extends Stage {

 public InakzeptablesRisikoView(Risiko risiko , Stage primaryStage){

this.initOwner(primaryStage);
this.initModality(Modality.WINDOW_MODAL);

     Pane pane = new Pane();
     Scene scene = new Scene(pane , 400 , 300);
     this.setTitle("Erfassung inakzetables Risiko");
     this.setScene(scene);

     Label labBezeichnung = new Label("Bezeichnung:");
     Label labMasnahme = new Label("Maßnahme:");

     Label txtBezeichnung = new Label("DB Experte verlässt das Projekt");
     TextField txtmasnahme = new TextField();

     GridPane.setHgrow(txtmasnahme , Priority.ALWAYS);

     Button btnNeu = new Button("Neu");
     Button btnAbbrechen = new Button("Abbrechen");

     GridPane grid = new GridPane();
     grid.setVgap(10);
     grid.setHgap(1);

     grid.add(labBezeichnung ,0,0);
     grid.add(txtBezeichnung ,1,0);

     grid.add(labMasnahme,0 ,1);
     grid.add(txtmasnahme,1 ,1);

     GridPane.setHalignment(labBezeichnung , HPos.RIGHT);
     GridPane.setHalignment(labMasnahme , HPos.RIGHT);

     HBox hb = new HBox(10);
     hb.setAlignment(Pos.CENTER);
     hb.getChildren().addAll(btnNeu , btnAbbrechen);

     VBox vb = new VBox(20);
     vb.setPadding(new Insets(15));
     vb.getChildren().addAll(grid , hb);

     Scene scene1 = new Scene(vb);
     this.setScene(scene1);
     this.setTitle("Erfassung inakzetables Risiko");




 }

    public void showView(){

        this.showAndWait();
    }

}
