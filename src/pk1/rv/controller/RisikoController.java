package pk1.rv.controller;

import pk1.rv.datenhaltung.PersistenzException;
import pk1.rv.datenhaltung.Risikoverwaltung;
import pk1.rv.datenhaltung.SerialisierungDAO;
import pk1.rv.fachlogik.*;


public class RisikoController {
  private Risikoverwaltung risikoverwaltung;
  private SerialisierungDAO serialisierungDAO;

  public RisikoController(){

   risikoverwaltung = new Risikoverwaltung();
   serialisierungDAO = new SerialisierungDAO();
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

  if (risikowert < Risiko.LIMIT){
     return Risikotyp.AKZEPTABEL;
  }
    if (kosten > Risiko.KOSTENLIMIT) {
      return Risikotyp.EXTREM;
    }
    else{
        return Risikotyp.INAKZEPTABEL;
    }


}
public void erstelleAkzetabelRisiko(String bezeichnung , float eintritt , float kosten){

    Risiko akzeptabelrisiko = new AkzeptabelesRisiko(bezeichnung, eintritt, kosten);
    risikoverwaltung.aufnehmen(akzeptabelrisiko);
}

public void erstelleExtremlRisiko(String bezeichnung, float eintritt, float kosten, String massnahme, float versicherung){

    Risiko extremrisiko = new ExtremsRisko(bezeichnung, eintritt, kosten, massnahme, versicherung);
    risikoverwaltung.aufnehmen(extremrisiko);
}

public void erstelleInakzetabelRisiko(String bezeichnung, float eintritt, float kosten, String massnahme2){
    Risiko inakzeptabelrisiko = new InakzeptabelesRisiko(bezeichnung, eintritt, kosten, massnahme2);
    risikoverwaltung.aufnehmen(inakzeptabelrisiko);
}

public Risiko sucheRisikoMitMaximaleRuckstellung() throws LeereListeException{
    return risikoverwaltung.sucheRisikoMitMaxRueckstellung();
}

public float berechneDieSummeRuckstellungen(){

    return risikoverwaltung.berechneSummeRueckstellungen();
}
}
