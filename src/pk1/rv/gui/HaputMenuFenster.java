package pk1.rv.gui;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pk1.rv.datenhaltung.PersistenzException;
import pk1.rv.datenhaltung.Risikoverwaltung;
import pk1.rv.datenhaltung.SerialisierungDAO;
import pk1.rv.fachlogik.*;

import javax.swing.*;
import java.io.IOException;
import java.util.InputMismatchException;

public class HaputMenuFenster extends Stage {

    Risikoverwaltung risikoverwaltung = new Risikoverwaltung();
    SerialisierungDAO serial = new SerialisierungDAO();

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


    public HaputMenuFenster() {

        this.setTitle("HaputFenster");

        menuDatei.getItems().addAll(itemladen, itemspeichern, itemRisikoInDatei, itemBeenden);
        menuRisiko.getItems().addAll(itemNeuRisiko);
        menuAnzeige.getItems().addAll(itemRisikoMitMaximalerRueckstellung, itemSumme);

        menubar.getMenus().addAll(menuDatei, menuRisiko, menuAnzeige); //منوی "Datei" رو بنداز داخل نوار منوی اصلی:

        root.setTop(menubar); //و در نهایت، همون‌طور که گفتیم، نوار منو رو به قسمت بالای صفحه اختصاص بده:

        Scene secne = new Scene(root, 600, 400);
        this.setScene(secne);
        this.show();

       //von File Deserialiesieren
        itemladen.setOnAction(e -> {
            try {
                if (risikoverwaltung.getList() != null) {

                    risikoverwaltung.setList(serial.laden());
                    new InfoFensterDialog("Ladung war erfolgreich.").showDialog();
                }

            } catch (PersistenzException e2) {
                new InfoFensterDialog("Fehler bein laden die Liste kann nicht geladen werden! ").showDialog();
            }
        });
      //in File Serialisieren
        itemspeichern.setOnAction(e -> {
            try {
                if (risikoverwaltung.getList() != null) {

                    serial.speichern(risikoverwaltung.getList());
                }
                new InfoFensterDialog("Die Risiko liste wurde in File Serialisiert").showDialog();
            } catch (PersistenzException e1) {

                new ErrorFensterDialog("Fehler bein speichern die Liste kann nicht gespeichert werden!").showDialog();
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
                        new ErrorFensterDialog("Falscher eingabe versuchen Sie bitte nochmal").showDialog();
                        int eingabevonDateiname = JOptionPane.showConfirmDialog(null, "Dateiname ist leer! neue Dateiname wählen ?", "Hinweis", JOptionPane.YES_NO_OPTION);

                        if (eingabevonDateiname == JOptionPane.YES_OPTION) {
                            continue;
                        }
                        else{
                            break;
                        }
                    }

                    risikoverwaltung.druckeRisikenInDatei(inputDateiname + ".txt");
                    new InfoFensterDialog("es wurde erfolgreich in file " + inputDateiname + " gespeichert").showDialog();
                    break;

                }

                catch (IOException ec) {
                    new ErrorFensterDialog("Die eingabe war ungültig vesuschen Sie Bitte nochmal ").showDialog();

                } catch (InputMismatchException | LeereListeException exe) {
                    new ErrorFensterDialog("Bitte geben Sie ein gültiges wert ein ").showDialog();
                }
            }
        });

        //Neue Risiko erstellen
        itemNeuRisiko.setOnAction(e -> {

            RisikoerfassungView risikoerfasung = new RisikoerfassungView(null, this);
            risikoerfasung.showView();


            if (risikoerfasung.isBestetigt()) {
                String bezeichnung = risikoerfasung.getTxtBezeichnungRisikoerfassung();
                float eintritt = risikoerfasung.getTxtEintrittRisikoerfassung();
                float kosten = risikoerfasung.getTxtKostenRisikoerfassung();

                if (eintritt * kosten < Risiko.LIMIT) {

                    Risiko akzeptabelrisiko = new AkzeptabelesRisiko(bezeichnung, eintritt, kosten);
                    risikoverwaltung.aufnehmen(akzeptabelrisiko);
                    new InfoFensterDialog("Risko wurde aufgenommen.").showDialog();

                } else if (risikoerfasung.getTxtKostenRisikoerfassung() > Risiko.KOSTENLIMIT) {

                    ExtremeRisikoView extremeRisikoView = new ExtremeRisikoView(null, this);
                    extremeRisikoView.showView();

                    String massnahme = extremeRisikoView.getTextMasnahmeExtrem();
                    float versicherung = extremeRisikoView.getTextversicherungExtream();

                    Risiko extremrisiko = new ExtremsRisko(bezeichnung, eintritt, kosten, massnahme, versicherung);
                    risikoverwaltung.aufnehmen(extremrisiko);
                    new InfoFensterDialog("Risko wurde aufgenommen.").showDialog();
                } else {

                    InakzeptablesRisikoView inakzeptablesRisikoView = new InakzeptablesRisikoView(null, this);
                    inakzeptablesRisikoView.showView();
                    String massnahme = inakzeptablesRisikoView.getTextMasnahmeInakzeptabel();
                    Risiko inakzeptabelrisiko = new InakzeptabelesRisiko(bezeichnung, eintritt, kosten, massnahme);
                    risikoverwaltung.aufnehmen(inakzeptabelrisiko);
                    new InfoFensterDialog("Risko wurde aufgenommen.").showDialog();

                }
            }
        });


        itemBeenden.setOnAction(e -> {
            this.close();
        });

        itemRisikoMitMaximalerRueckstellung.setOnAction(e -> {
            try {

                Risiko maxruckstellung = risikoverwaltung.sucheRisikoMitMaxRueckstellung();
                new InfoFensterDialog(maxruckstellung.toString()).showDialog();

            } catch (LeereListeException e4) {
                new ErrorFensterDialog("Es existiert kein Risiko").showDialog();
            }


        });


        itemSumme.setOnAction(e -> {
            float summe = risikoverwaltung.berechneSummeRueckstellungen();
            new InfoFensterDialog(String.valueOf(summe)).showDialog();

        });


    }



}
