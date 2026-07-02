package pk1.rv.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InfoFensterDialog extends Stage {

    private Label nachricht = new Label();
    private GridPane gridPaneText = new GridPane();
    private Button btnOK = new Button("OK");

    private HBox buttonBox = new HBox();
    private VBox root = new VBox();

  public InfoFensterDialog(String text){

      this.setTitle("Meldung");
      this.initModality(Modality.APPLICATION_MODAL);
      this.setResizable(false);
      this.setHeight(165);
      this.setWidth(500);

      Label icon = new Label("ⓘ");
      icon.setStyle("-fx-font-size: 28px; -fx-text-fill: #3B82F6;");

      nachricht = new Label(text);
      nachricht.setStyle("-fx-font-family: 'Segoe UI'; -fx-font-size: 13px; -fx-font-weight: bold;");
      nachricht.setWrapText(true);


      gridPaneText.add(icon , 0 , 0 );
      icon.setMinWidth(30);
      gridPaneText.add(nachricht , 2 , 0);
      gridPaneText.setHgap(15);


      btnOK.setPrefWidth(50);
      btnOK.setOnAction(e -> this.close());


      buttonBox.setAlignment(Pos.CENTER);
      buttonBox.getChildren().add(btnOK);


      root.setPadding(new Insets(10));
      root.getChildren().addAll(gridPaneText , buttonBox);
      root.setSpacing(25);

      Scene scene = new Scene(root , 330, 140);
      this.setScene(scene);
  }

  public void showDialog(){

     this.showAndWait();
  }


}
