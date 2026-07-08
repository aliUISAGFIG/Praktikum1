package pk1.rv.datenhaltung;

import pk1.rv.fachlogik.*;
import pk1.rv.gui.ErrorFensterDialog;

import java.io.*;
import java.nio.file.AccessDeniedException;
import java.util.*;

public class Risikoverwaltung {

    private List<Risiko> list;

    public Risikoverwaltung() {
        list = new ArrayList<>();
    }

    public List<Risiko> getList() {
        return list;
    }

    public void setList(List<Risiko> list) {
        this.list = list;
    }

    public void aufnehmen(Risiko r) {
        list.add(r);
    }

    public Iterator<Risiko> iterator() {
        return list.iterator();
    }


    public String zeigeRisiken() throws LeereListeException {
        String alleRisken = "";
        if (list.isEmpty()) {
            throw new LeereListeException("Liste ist leer");
        } else {
            Collections.sort(list);
            Iterator<Risiko> risikoIterator = list.iterator();
            while (risikoIterator.hasNext()) {
                Risiko r = risikoIterator.next();
                System.out.println();
                alleRisken += r.toString();
            }
        }
        return alleRisken;
    }

    public Risiko sucheRisikoMitMaxRueckstellung() throws LeereListeException {
        if (list.isEmpty()) {
            throw new LeereListeException("List ist leer");
        }
        Risiko maxRisiko = list.get(0);
        for (int i = 1; i < list.size(); i++) {

            if (list.get(i).ermittleRueckstellung() > maxRisiko.ermittleRueckstellung()) {
                maxRisiko = list.get(i);
            }
        }
        return maxRisiko;
    }


    public void druckeRisikenInDatei(String dateiName) throws PersistenzException {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dateiName))) {

            Iterator<Risiko> iter = iterator();
            while (iter.hasNext()) {
                bufferedWriter.write(iter.next().toString());
            }

        }
        catch (FileNotFoundException e){
            throw new PersistenzException("Die Datei wurde nicht gefunden");
        }
        catch (AccessDeniedException ex){

            throw new PersistenzException("zugriff auf der Datei wurde verweigert");
        }
        catch (IOException e) {
            throw new PersistenzException("Die Daten konnte nicht gespeichert werden ");
        }
    }

    public float berechneSummeRueckstellungen() {
        if (list.isEmpty()){
          new ErrorFensterDialog("Es exsistiert kein Risiko in der Liste").showView();
        }
        float summen = list.stream().
                map(Risiko::ermittleRueckstellung).
                reduce(0f, Float::sum);
        return summen;
    }
}
