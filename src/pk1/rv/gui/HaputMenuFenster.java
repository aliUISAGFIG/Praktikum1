package pk1.rv.gui;


import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pk1.rv.controller.RisikoController;
import pk1.rv.fachlogik.PersistenzException;
import pk1.rv.fachlogik.*;

import javax.swing.*;

public class HaputMenuFenster extends Stage {

   private RisikoController controller = new RisikoController();

    MenuItem itemladen = new MenuItem("Laden");
    MenuItem itemspeichern = new MenuItem("Speichern");
    MenuItem itemNeuRisiko = new MenuItem("Neues Risiko");
    MenuItem itemRisikoInDatei = new MenuItem("Risiko in Datei");
    MenuItem itemRisikoMitMaximalerRueckstellung = new MenuItem("Risiko mit maximaler Rückstellung");
    MenuItem itemBeenden = new MenuItem("Beenden");
    MenuItem itemSumme = new MenuItem("Summe aller Rückstellungen");

    Menu menuDatei = new Menu("Datei");
    Menu menuRisiko = new Menu("Risiko");
    Menu menuAnzeige = new Menu("Anzeige");

    MenuBar menubar = new MenuBar();
    BorderPane root = new BorderPane();
    ListView<Risiko> listView = new ListView<>();


    public HaputMenuFenster() {

        this.setTitle("HaputFenster");
        this.setWidth(1000);
        this.setHeight(400);
        listView.setItems(controller.getObservableList());
        menuDatei.getItems().addAll(itemladen, itemspeichern, itemRisikoInDatei, itemBeenden);
        menuRisiko.getItems().addAll(itemNeuRisiko);
        menuAnzeige.getItems().addAll(itemRisikoMitMaximalerRueckstellung, itemSumme);

        menubar.getMenus().addAll(menuDatei, menuRisiko, menuAnzeige); //منوی "Datei" رو بنداز داخل نوار منوی اصلی:

        root.setTop(menubar); //و در نهایت، همون‌طور که گفتیم، نوار منو رو به قسمت بالای صفحه اختصاص بده:
        root.setCenter(listView);
        Scene secne = new Scene(root);
        this.setScene(secne);
        this.show();

       //von File Deserialiesieren
        itemladen.setOnAction(e -> {
            try {
              controller.ladenRisikoList();
              new InfoFensterDialog("Ladung war erfolgreich.").showView();
                }
            catch (PersistenzException e2) {
                new ErrorFensterDialog(e2.getMessage()).showView();
            }
        });

      //in File Serialisieren
        itemspeichern.setOnAction(e -> {
            try {
                controller.speichernRisikoList();
                new InfoFensterDialog("Die Risiko liste wurde in File Serialisiert").showView();
            }catch (PersistenzException e1) {
                //در متودspeichern من اکسپشن خودم رو در چند اکیسپشن مختلف صدا میکنم و اینجا فقط e.getmassage صدا میزنم
                new ErrorFensterDialog(e1.getMessage()).showView();
            }

        });

        //سوال پرسیده میشه که شئ های داخل لیست تو چه فایلی نوشته بشه
        itemRisikoInDatei.setOnAction(e -> {

            String inputDateiname = "";
            while (true) {
                try {
                    inputDateiname= JOptionPane.showInputDialog("  Geben Sie ihre Dateiname ein  ");
                    if (inputDateiname == null) {
                        break;
                    }
                    else if (inputDateiname.trim().isEmpty()) {
                        new ErrorFensterDialog("Falscher eingabe versuchen Sie bitte nochmal").showView();
                        int eingabevonDateiname = JOptionPane.showConfirmDialog(null, "Dateiname ist leer! neue Dateiname wählen ?", "Hinweis", JOptionPane.YES_NO_OPTION);

                        if (eingabevonDateiname == JOptionPane.YES_OPTION) {
                            continue;
                        }
                        else{
                            break;
                        }
                    }
                    controller.risikoInDateiSchreiben(inputDateiname + ".txt");
                    new InfoFensterDialog("es wurde erfolgreich in file " + inputDateiname + " gespeichert").showView();
                    break;
                }
//در متودdruckeRisikenInDatei من اکسپشن خودم رو در چند اکیپشن مختلف صدا میکنم و اینجا فقط e.getmassage صدا میزنم
                catch (PersistenzException ec) {
                    new ErrorFensterDialog(ec.getMessage()).showView();
                }
            }
        });
        //Neue Risiko erstellen
        //Neue Risiko erstellen
        itemNeuRisiko.setOnAction(e -> {
            RisikoerfassungView risikoerfasung = new RisikoerfassungView(null, this);
            risikoerfasung.showView();

            if (risikoerfasung.isBestetigt()) {
                String bezeichnung = risikoerfasung.getTxtBezeichnungRisikoerfassung();
                float eintritt = risikoerfasung.getTxtEintrittRisikoerfassung();
                float kosten = risikoerfasung.getTxtKostenRisikoerfassung();

                Risikotyp typ = controller.ermittleRisikoTyp(eintritt, kosten);

                switch (typ) {
                    case AKZEPTABEL:

                        controller.erstelleAkzetabelRisiko(bezeichnung, eintritt, kosten);
                        new InfoFensterDialog("Risko wurde aufgenommen.").showView();
                        break;

                    case EXTREM:
                        ExtremeRisikoView extremeRisikoView = new ExtremeRisikoView(null, this);
                        extremeRisikoView.showView();
                        String massnahme = extremeRisikoView.getTextMasnahmeExtrem();
                        float versicherung = extremeRisikoView.getTextversicherungExtream();
                        controller.erstelleExtremlRisiko(bezeichnung, eintritt, kosten, massnahme, versicherung);
                        new InfoFensterDialog("Risko wurde aufgenommen.").showView();
                        break;

                    case INAKZEPTABEL:
                        InakzeptablesRisikoView inakzeptablesRisikoView = new InakzeptablesRisikoView(null, this);
                        inakzeptablesRisikoView.showView();
                        String massnahme2 = inakzeptablesRisikoView.getTextMasnahmeInakzeptabel();

                        controller.erstelleInakzetabelRisiko(bezeichnung, eintritt, kosten, massnahme2);
                        new InfoFensterDialog("Risko wurde aufgenommen.").showView();
                        break;
                }
            }

        });

        itemBeenden.setOnAction(e5 -> {
            this.close();
        });

        itemRisikoMitMaximalerRueckstellung.setOnAction(e2 -> {
            try {

             Risiko maxruckstellung = controller.sucheRisikoMitMaximaleRuckstellung();
                new InfoFensterDialog(maxruckstellung.toString()).showView();

            } catch (LeereListeException e4) {
                new ErrorFensterDialog("Es existiert kein Risiko").showView();
            }
        });

        itemSumme.setOnAction(e7 -> {
            float summe = controller.berechneDieSummeRuckstellungen();
            new InfoFensterDialog(String.valueOf(summe)).showView();

        });


    }



}
