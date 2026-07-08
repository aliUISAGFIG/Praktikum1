package pk1.rv.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pk1.rv.fachlogik.PersistenzException;
import pk1.rv.datenhaltung.Risikoverwaltung;
import pk1.rv.datenhaltung.SerialisierungDAO;
import pk1.rv.fachlogik.*;

import java.util.List;


public class RisikoController {
  private Risikoverwaltung risikoverwaltung;
  private SerialisierungDAO serialisierungDAO;
  private ObservableList<Risiko> observableList;


  public RisikoController(){
   risikoverwaltung = new Risikoverwaltung();
   serialisierungDAO = new SerialisierungDAO();
   observableList = FXCollections.observableArrayList();
  }
    public ObservableList<Risiko> getObservableList() {
        return observableList;
    }


  public void speichernRisikoList()throws PersistenzException{

    serialisierungDAO.speichern(risikoverwaltung.getList());
  }
  public void ladenRisikoList()throws PersistenzException{

      risikoverwaltung.setList(serialisierungDAO.laden());
  }

  public void risikoInDateiSchreiben(String Dateiname)throws PersistenzException{
      risikoverwaltung.druckeRisikenInDatei(Dateiname);

  }
public Risikotyp ermittleRisikoTyp(float eintritt , float kosten){

  float risikowert = eintritt * kosten;

  if (risikowert <= Risiko.LIMIT){
     return Risikotyp.AKZEPTABEL;
  }
    else if (kosten > Risiko.KOSTENLIMIT) {
      return Risikotyp.EXTREM;
    }
    else{
        return Risikotyp.INAKZEPTABEL;
    }
}
//nimmt für den beiden Risike ein Rissiko auf damit ich in unteree methoden ein code paar mal nicht wiederhole
    private void risikenAufnehmen(Risiko rr){
        risikoverwaltung.aufnehmen(rr);
        observableList.add(rr);
    }

public void erstelleAkzetabelRisiko(String bezeichnung , float eintritt , float kosten){

    Risiko akzeptabelrisiko = new AkzeptabelesRisiko(bezeichnung, eintritt, kosten);
    risikenAufnehmen(akzeptabelrisiko);
}

public void erstelleExtremlRisiko(String bezeichnung, float eintritt, float kosten, String massnahme, float versicherung){

    Risiko extremrisiko = new ExtremsRisko(bezeichnung, eintritt, kosten, massnahme, versicherung);
    risikenAufnehmen(extremrisiko);
}

public void erstelleInakzetabelRisiko(String bezeichnung, float eintritt, float kosten, String massnahme2){
    Risiko inakzeptabelrisiko = new InakzeptabelesRisiko(bezeichnung, eintritt, kosten, massnahme2);
    risikenAufnehmen(inakzeptabelrisiko);
}

public Risiko sucheRisikoMitMaximaleRuckstellung() throws LeereListeException{
    return risikoverwaltung.sucheRisikoMitMaxRueckstellung();
}

public float berechneDieSummeRuckstellungen(){

    return risikoverwaltung.berechneSummeRueckstellungen();
}

public List<Risiko> getRiskoList(){
      return risikoverwaltung.getList();
}

}
