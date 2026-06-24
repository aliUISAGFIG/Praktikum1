package Pk1Projekt;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;


import java.io.*;
import java.util.*;

public class Risikoverwaltung extends Application {

    private List<Risiko> risiken;


    public Risikoverwaltung() {
        risiken = new ArrayList<>();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = new BorderPane();
        Scene secne = new Scene(root ,600 ,400);

        primaryStage.setTitle("RisikoVerwaltung");
        primaryStage.setScene(secne);
        primaryStage.show();
        MenuBar menubar = new MenuBar();

        //گزینه‌های کشویی رو دونه‌دونه بساز:
        Menu menu = new Menu("Datei");
        Menu menu1 = new Menu("Risiko");
        Menu menu2 = new Menu("Anzeige");
        //für menu
        MenuItem itemladen = new MenuItem("Laden");
        MenuItem itemspeichern = new MenuItem("Speichern");
        MenuItem itemRisiko_in_Datei = new MenuItem("Risiko in Datei");
        MenuItem itemBeenden = new MenuItem("Beenden");
        //für menu1
        MenuItem itemNeuRisiko = new MenuItem("Neues Risiko");
        //für menu2
        MenuItem itemRisiko_mit_max = new MenuItem("Risiko mit maximaler Rückstellung");
        MenuItem itemSumme = new MenuItem("Summe aller Rückstellungen");

        menu.getItems().addAll(itemladen , itemspeichern , itemRisiko_in_Datei , itemBeenden);
        menu1.getItems().addAll(itemNeuRisiko);
        menu2.getItems().addAll(itemRisiko_mit_max , itemSumme);

        menubar.getMenus().addAll(menu , menu1 , menu2); //منوی "Datei" رو بنداز داخل نوار منوی اصلی:

        root.setTop(menubar); //و در نهایت، همون‌طور که گفتیم، نوار منو رو به قسمت بالای صفحه اختصاص بده:

  RisikoerfassungView risikoerfassungView = new RisikoerfassungView(null , primaryStage);
  risikoerfassungView.showView();

  AkzeptablesRisikoView akzeptablesRisikoView = new AkzeptablesRisikoView(null , primaryStage);
  akzeptablesRisikoView.showView();

  InakzeptablesRisikoView inakzeptablesRisikoView = new InakzeptablesRisikoView(null , primaryStage);
  inakzeptablesRisikoView.showView();
    }


    public void aufnehmen(Risiko r) {
        risiken.add(r);
    }

    public void zeigeRisiken () throws LeereListeException {
        if (risiken.isEmpty()) {
            throw new LeereListeException("Liste ist leer");
        } else {
            Collections.sort(risiken);
            Iterator<Risiko> risikoIterator = risiken.iterator();
            while (risikoIterator.hasNext()) {
                Risiko r = risikoIterator.next();
                r.druckDaten(System.out);
            }
        }
    }
    public Risiko sucheRisikoMitMaxRueckstellung () throws LeereListeException {
        if(risiken.isEmpty()){
            throw new LeereListeException("List ist leer");
        }

        Risiko maxRisiko = risiken.get(0);

        for(int i = 1 ; i < risiken.size() ; i++){

            if( risiken.get(i).ermittleRueckstellung() > maxRisiko.ermittleRueckstellung() ){
                maxRisiko = risiken.get(i);
            }

        }
            maxRisiko.druckDaten(System.out);


        return maxRisiko;
    }
    public void List_in_datei_Speichern(File file) throws IOException {
                try (  FileOutputStream fil = new FileOutputStream(file);
                       ObjectOutputStream obj = new ObjectOutputStream(fil)){
                    obj.writeObject(risiken);

                }


    }

    public void ListVonDateiLesen(File file) throws IOException, ClassNotFoundException {
        List<Risiko> geladeneRisiken;
        try (FileInputStream fil = new FileInputStream(file);
             ObjectInputStream les = new ObjectInputStream(fil)){

            geladeneRisiken = (List<Risiko>) les.readObject();
            this.risiken = geladeneRisiken;
        }


    }

    public void druckeRisikenInDatei(File file) throws IOException, LeereListeException {
        if(risiken.isEmpty()){throw new LeereListeException("Liste ist leer");}

       try (FileOutputStream st = new FileOutputStream(file)){
           for(Risiko r : risiken){
               r.druckDaten(st);
       }

        }
    }



    public float berechneSummeRueckstellungen() {
        float summe = 0;
        for (Risiko r : risiken) {
            summe += r.ermittleRueckstellung();

        }
        return summe;

    }

}
