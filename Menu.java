//package Pk1Projekt;
//
//import pk1.rv.fachlogik.*;
//
//import javax.swing.JOptionPane;
//import java.io.File;
//import java.io.IOException;
//import java.util.InputMismatchException;
//
//public class Menu {
//    private Risikoverwaltung verwaltung;
//
//    public Menu() {
//
//        verwaltung = new Risikoverwaltung();
//    }
//
//
//    public void ZeigMenu() {
//
//
//        boolean laeuft = false;
//
//        haupmenu:
//        while (!laeuft) {
//            try {
//
//                String eingabe = JOptionPane.showInputDialog("\t\t\tRisikoverwaltung\n\n" + "1. Risiko aufnehmen\n" +
//                        "2. Zeige alle Risiken\n" + "3. Risikoliste in Datei schreiben \n" + "4. Zeige Risiko mit maximaler Ruckstellung\n" + "5. Berechne Summe aller Ruckstellungen\n"
//                        + "6. Speichern\n" + "7. Laden\n" + "8. Beenden\n\n" + "Bitte Menü punkt wählen:");
//                if (eingabe == null){
//                    JOptionPane.showMessageDialog(null, "Programm wurde beendet");
//                    break;
//
//                }
//                int auswahl = Integer.parseInt(eingabe);
//
//                if (auswahl < 1 || auswahl > 8) {
//                    throw new InvalidMenuOptionException("Bitte ein zahl zwischen 1 bis 8");
//                }
//                switch (auswahl) {
//                    case 1:
//
//                        boolean gultig3 = true;
//                        String bezeichnerEingabe = "";
//                        while (gultig3) {
//
//                            try {
//                                String eingabeBezeichnung = JOptionPane.showInputDialog("Geben Sie bitte ihre bezeichung ein :");
//
//                                if (eingabeBezeichnung == null) {
//                                    continue haupmenu;
//                                }
//
//                                bezeichnerEingabe = eingabeBezeichnung;
//                                gultig3 = false;
//
//                            } catch (InputMismatchException e) {
//                                JOptionPane.showMessageDialog(null, "ungültiger eingabe versuchen Sie Nochmal ");
//
//                            }
//                        }
//
//
//                        float EintrittwharcheinligkeitEingabe = 0;
//                        boolean gultig = true;
//
//                        while (gultig) {
//                            try {
//                                String eingabe1 = JOptionPane.showInputDialog("Geben Sie bitte ihre Eintrittwahrcheinligkeit ein : ");
//                                if (eingabe1 == null) {
//                                    continue haupmenu;
//                                }
//                                EintrittwharcheinligkeitEingabe = Float.parseFloat(eingabe1);
//
//                                gultig = false;
//                            } catch (NumberFormatException e) {
//
//                                JOptionPane.showMessageDialog(null, "Geben Sie bitte eine gültige Eintrittwahrcheinligkeit ein ");
//                            }
//
//
//                        }
//
//
//                        float Kosten_im_schadenfallEingabe = 0;
//                        boolean gultige = true;
//                        while (gultige) {
//
//                            try {
//                                String eingabe2 = JOptionPane.showInputDialog("Geben Sie bitte ihre Kosten_im_schadenfallEingabe ein :");
//                                if (eingabe2 == null) {
//                                    continue haupmenu;
//                                }
//                                Kosten_im_schadenfallEingabe = Float.parseFloat(eingabe2);
//                                gultige = false;
//
//                            } catch (NumberFormatException ex) {
//
//                                JOptionPane.showMessageDialog(null, "Geben Sie bitte eine gültige Kosten_im_schadenfallEingabe ein ");
//                            }
//
//
//                        }
//
//
//                        if (EintrittwharcheinligkeitEingabe * Kosten_im_schadenfallEingabe < Risiko.LIMIT) {
//
//                            Risiko akzeptabelrisiko = new AkzeptabelesRisiko(bezeichnerEingabe, EintrittwharcheinligkeitEingabe, Kosten_im_schadenfallEingabe);
//                            verwaltung.aufnehmen(akzeptabelrisiko);
//                            JOptionPane.showMessageDialog(null, "Risiko wurde aufgenommen.");
//
//                        } else {
//
//                            String massnahme = "";
//                            boolean lauft1 = true;
//                            while (lauft1) {
//                                try {
//                                    String massnahmeEingabe = JOptionPane.showInputDialog("Geben Sie Ihre Massnahme ein");
//                                    if (massnahmeEingabe == null) {
//                                        continue haupmenu;
//                                    } else if (massnahmeEingabe.trim().isEmpty()) {
//
//                                        throw new IllegalArgumentException();
//
//                                    }
//                                    massnahme = massnahmeEingabe;
//                                    lauft1 = false;
//
//                                } catch (InputMismatchException e) {
//                                    JOptionPane.showMessageDialog(null, "Bitte geben Sie ein gültiges wert ein");
//
//
//                                } catch (IllegalArgumentException ef) {
//                                    JOptionPane.showMessageDialog(null, "Falscher eingabe versuchen Sie bitte nochmal", "!Error!", JOptionPane.ERROR_MESSAGE);
//
//                                }
//
//
//                            }
//
//
//                            if (Kosten_im_schadenfallEingabe > Risiko.KOSTENLIMIT) {
//
//                                float VersicherungsbeitragEingabe = Float.parseFloat(JOptionPane.showInputDialog("Geben Sie bitte ihre versicherungsbeitrag ein : "));
//
//                                Risiko extremrisiko = new ExtremsRisko(bezeichnerEingabe, EintrittwharcheinligkeitEingabe, Kosten_im_schadenfallEingabe, massnahme, VersicherungsbeitragEingabe);
//                                verwaltung.aufnehmen(extremrisiko);
//                                JOptionPane.showMessageDialog(null, "Risiko wurde aufgenommen.");
//                            } else {
//
//                                Risiko inakzeptabelrisiko = new InakzeptabelesRisiko(bezeichnerEingabe, EintrittwharcheinligkeitEingabe, Kosten_im_schadenfallEingabe, massnahme);
//                                verwaltung.aufnehmen(inakzeptabelrisiko);
//                                JOptionPane.showMessageDialog(null, "Risiko wurde aufgenommen.");
//                            }
//
//
//                        }
//                        break;
//
//                    case 2:
//
//                        verwaltung.zeigeRisiken();
//                        break;
//
//                    case 3:
//                        boolean lauf = true;
//                        String abfrageDatiname1 = "";
//                        while (lauf) {
//                            try {
//                                String abfrageDateiname2 = JOptionPane.showInputDialog("  Geben Sie ihre Dateiname ein  ");
//                                if (abfrageDateiname2 == null) {
//                                    continue haupmenu;
//                                } else if (abfrageDateiname2.trim().isEmpty()) {
//                                    JOptionPane.showMessageDialog(null, "Falscher eingabe versuchen Sie bitte nochmal", "!Error!", JOptionPane.ERROR_MESSAGE);
//                                    int eingabevonDateiname = JOptionPane.showConfirmDialog(null, "Dateiname ist leer! neue Dateiname wählen ?", "Hinweis", JOptionPane.YES_NO_OPTION);
//                                    if (eingabevonDateiname == JOptionPane.YES_OPTION) {
//                                        continue;
//                                    }
//
//                                    if (eingabevonDateiname == JOptionPane.NO_OPTION) {
//                                        continue haupmenu;
//                                    }
//                                }
//                                abfrageDatiname1 = abfrageDateiname2;
//                                verwaltung.druckeRisikenInDatei(new File(abfrageDatiname1));
//                                lauf = false;
//
//
//                            } catch (IOException ec) {
//                                JOptionPane.showMessageDialog(null, "Die eingabe war ungültig vesuschen Sie Bitte nochmal ");
//
//                            } catch (InputMismatchException exe) {
//                                JOptionPane.showMessageDialog(null, "Bitte geben Sie ein gültiges wert ein ");
//
//                            }
//
//
//                        }
//                        break;
//
//                    case 4:
//                        verwaltung.sucheRisikoMitMaxRueckstellung();
//                        break;
//
//                    case 5:
//                        float summe = verwaltung.berechneSummeRueckstellungen();
//                        JOptionPane.showMessageDialog(null, "Summe aller Ruckstellungen : " + summe);
//                        break;
//
//                    case 6:
//                        File file1 = new File("listeRisiko1.ser");
//                        verwaltung.List_in_datei_Speichern(file1);
//                        JOptionPane.showMessageDialog(null, "Risikos wurden erfolgreich in listeRisiko1.ser gespeichert");
//                        break;
//
//                    case 7:
//                        File file2 = new File("listeRisiko1.ser");
//                        verwaltung.ListVonDateiLesen(file2);
//                        JOptionPane.showMessageDialog(null, "erfolgreich in listeRisiko1.ser geladen");
//                        break;
//
//                    case 8:
//                        JOptionPane.showMessageDialog(null, "Programm wurde beendet");
//                        laeuft = true;
//                        break;
//
//                    default:
//                        JOptionPane.showMessageDialog(null, "! Flacher Eingabe !", "ungültig", JOptionPane.ERROR_MESSAGE);
//
//                }
//
//
//            } catch (NumberFormatException e) {
//
//                JOptionPane.showMessageDialog(null, "Fehler bitte ein zahl eingeben ");
//
//            } catch (InvalidMenuOptionException | LeereListeException e) {
//
//
//                JOptionPane.showMessageDialog(null, e.getMessage(), "Fehlermeldung ", JOptionPane.ERROR_MESSAGE);
//            } catch (Exception r) {
//
//                System.out.println("Es ist ein fehler beigetreten versuchen Sie bitte nochmal");
//            }

//        }
//
//    }
//
//}
