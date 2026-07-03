package pk1.rv.gui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.control.Separator;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ErrorFensterDialog extends Stage {
    Label nachricht = new Label();

    Label icon = new Label("❌");
    GridPane textPane = new GridPane();
    Button btnOK = new Button("OK");

    Separator separator = new Separator();
    HBox buttonBox = new HBox(btnOK);
    VBox root = new VBox(18);

    public ErrorFensterDialog(String text) {

        setTitle("Fehler");
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);


        icon.setStyle("""
                -fx-font-size: 32px;
                -fx-text-fill: #d32f2f;
                """);

        nachricht = new Label(text);
        nachricht.setWrapText(true);
        nachricht.setStyle("""
                -fx-font-family: "Segoe UI";
                -fx-font-size: 13px;
                -fx-font-weight: bold;
                """);


        textPane.setHgap(18);
        textPane.setAlignment(Pos.CENTER_LEFT);
        textPane.add(icon,0,0);
        textPane.add(nachricht,1,0);

        GridPane.setHalignment(icon, HPos.CENTER);




        btnOK.setPrefWidth(85);
        btnOK.setDefaultButton(true);
        btnOK.setOnAction(e -> this.close());


        buttonBox.setAlignment(Pos.CENTER_RIGHT);


        root.setPadding(new Insets(18,18,15,18));
        root.getChildren().addAll(
                textPane,
                separator,
                buttonBox
        );

        Scene scene = new Scene(root, 470, 155);
        setScene(scene);
    }

    public void showView() {
        showAndWait();
    }
}
