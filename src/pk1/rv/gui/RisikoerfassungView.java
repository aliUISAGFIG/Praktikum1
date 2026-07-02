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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pk1.rv.fachlogik.Risiko;

public class RisikoerfassungView extends Stage {
    TextField txtBezeichnung;
    TextField txtEintritt;
    TextField txtKosten;
    private boolean isBestetigt = false;

    public RisikoerfassungView(Risiko risiko, Stage primaryStage) {

        // تنظیمات مدال بودن پنجره
        this.initOwner(primaryStage);
        this.initModality(Modality.WINDOW_MODAL);

        // ۱. ساخت لیبل‌ها
        Label labBezeichnung = new Label("Bezeichnung:");
        Label labEintrittswahrscheinlichkeit = new Label("Eintrittswahrscheinlichkeit:");
        Label labKosten = new Label("Kosten im Schadenfall:");

        // ۲. ساخت فیلدهای متنی
        txtBezeichnung = new TextField();
        txtEintritt = new TextField();
        txtKosten = new TextField();
        if (risiko != null) {
            txtBezeichnung.setText(risiko.getbezeichnung());
            txtEintritt.setText(Float.toString(risiko.getEintrittswahrcheiligkeit()));
            txtKosten.setText(Float.toString(risiko.getKosten_im_schadenfall()));
        }
        // ۳. تنظیم کش آمدن افقی فیلدها با بزرگ شدن پنجره
        GridPane.setHgrow(txtBezeichnung, Priority.ALWAYS);
        GridPane.setHgrow(txtEintritt, Priority.ALWAYS);
        GridPane.setHgrow(txtKosten, Priority.ALWAYS);

        // ۴. ساخت دکمه‌ها
        Button btnNeu = new Button("Neu");

        btnNeu.setOnAction(e -> {
      String SaubereBezeichnung = getTxtBezeichnungRisikoerfassung().trim();
      String SaubereEintritt = txtEintritt.getText().trim();
      String SaubereKosten = txtKosten.getText().trim();

        if (SaubereBezeichnung.isEmpty() || SaubereEintritt.isEmpty() || SaubereKosten.isEmpty()){
            new ErrorFensterDialog("Bitte füllen Sie alle Felder aus!").showDialog();
            return;
        }
        try {
          float eintritt = getTxtEintrittRisikoerfassung() ;
          float kosten = getTxtKostenRisikoerfassung();
            if (risiko != null) {

                risiko.setBezeichung(getTxtBezeichnungRisikoerfassung());
                risiko.setEintrittswahrcheiligkeit(eintritt);
                risiko.setKosten_im_schadenfall(kosten);
            }

            isBestetigt = true;
            this.close();

        }catch (NumberFormatException ex){

            new ErrorFensterDialog("Bitte geben Sie gültige Zahlenwert ein!").showDialog();

        }





        });


        Button btnAbbrechen = new Button("Abbrechen");
        btnAbbrechen.setOnAction(e -> {
            this.close();
        });

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

    public String getTxtBezeichnungRisikoerfassung() {
        return txtBezeichnung.getText();
    }

    public float getTxtKostenRisikoerfassung() {
        return Float.parseFloat(txtKosten.getText());
    }

    public float getTxtEintrittRisikoerfassung() {
        return Float.parseFloat(txtEintritt.getText());
    }

    public boolean isBestetigt() {
        return isBestetigt;
    }
}



