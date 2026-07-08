package pk1.rv.fachlogik;

import java.io.*;

public class AkzeptabelesRisiko extends Risiko {
    private static final long serialVersionUID = 100L;

    public AkzeptabelesRisiko(String bezeichnung, float eintrittwahrscheilicjkeit, float kosten_im_schadenfall) {
        super(bezeichnung, eintrittwahrscheilicjkeit, kosten_im_schadenfall);
    }

    public AkzeptabelesRisiko() {
        super();
    }

    @Override
    public float ermittleRueckstellung() {
        return 0;

    }


    @Override
    public void druckDaten(OutputStream stream) {

        PrintStream ps = new PrintStream(stream);
        ps.printf(toString());
    }

    @Override
    public String toString() {
        return "Id " + getId() + " Akzeptables Risiko " + getBezeichnung() +
                " aus " + getMonate() + "/" + getJahr() + " Risikowert " +
                berechneRisikowert() + " Rückstellung " + ermittleRueckstellung();

    }


}
