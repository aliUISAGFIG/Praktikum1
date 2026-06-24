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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RisikoerfassungView extends Stage {

    public RisikoerfassungView(Risiko risiko, Stage primaryStage) {

        // تنظیمات مدال بودن پنجره
        this.initOwner(primaryStage);
        this.initModality(Modality.WINDOW_MODAL);

        // ۱. ساخت لیبل‌ها
        Label labBezeichnung = new Label("Bezeichnung:");
        Label labEintrittswahrscheinlichkeit = new Label("Eintrittswahrscheinlichkeit:");
        Label labKosten = new Label("Kosten im Schadenfall:");

        // ۲. ساخت فیلدهای متنی
        TextField txtBezeichnung = new TextField();
        TextField txtEintritt = new TextField();
        TextField txtKosten = new TextField();

        // ۳. تنظیم کش آمدن افقی فیلدها با بزرگ شدن پنجره
        GridPane.setHgrow(txtBezeichnung, Priority.ALWAYS);
        GridPane.setHgrow(txtEintritt, Priority.ALWAYS);
        GridPane.setHgrow(txtKosten, Priority.ALWAYS);

        // ۴. ساخت دکمه‌ها
        Button btnNeu = new Button("Neu");
        Button btnAbbrechen = new Button("Abbrechen");

        // ۵. تنظیمات Layout فرم (GridPane)
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(5); // فاصله‌ی افقی بین لیبل و فیلد رو روی 5 گذاشتم تا کمتر بشه

        // اضافه کردن اِلِمان‌ها به گرید
        grid.add(labBezeichnung, 0, 0);
        grid.add(txtBezeichnung, 1, 0);

        grid.add(labEintrittswahrscheinlichkeit, 0, 1);
        grid.add(txtEintritt, 1, 1);

        grid.add(labKosten, 0, 2);
        grid.add(txtKosten, 1, 2);

        // ۶. راست‌چین کردن لیبل‌ها برای از بین بردن فاصله‌ی خالی
        GridPane.setHalignment(labBezeichnung, HPos.RIGHT);
        GridPane.setHalignment(labEintrittswahrscheinlichkeit, HPos.RIGHT);
        GridPane.setHalignment(labKosten, HPos.RIGHT);

        // ۷. تنظیمات دکمه‌ها (HBox)
        HBox hb = new HBox(10);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(btnNeu, btnAbbrechen);

        // ۸. چیدن نهایی همه‌چیز زیر هم (VBox)
        VBox vb = new VBox(20);
        vb.setPadding(new Insets(15));
        vb.getChildren().addAll(grid, hb);

        // ۹. ساخت صحنه و اعمال روی پنجره
        Scene scene = new Scene(vb);
        this.setScene(scene);
        this.setTitle("Risikoerfassung");
    }

    public void showView() {
        this.showAndWait();
    }
}



