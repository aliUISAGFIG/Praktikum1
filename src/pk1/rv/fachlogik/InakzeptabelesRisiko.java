package pk1.rv.fachlogik;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Objects;

public class InakzeptabelesRisiko extends Risiko {
    private String massnahme;
    private static final long serialVersionUID = 100L;

    public InakzeptabelesRisiko(String bezeichnung, float eintrittwahrscheilicjkeit, float kosten_im_schadenfall, String massnahme) {
        super(bezeichnung, eintrittwahrscheilicjkeit, kosten_im_schadenfall);
        this.massnahme = massnahme;

    }

    public InakzeptabelesRisiko() {
        super();
    }

    @Override
    public float ermittleRueckstellung() {
        return berechneRisikowert();
    }

    public String getMassnahme() {

        return massnahme;

    }

    public void setMassnahme(String massnahme) {
        this.massnahme = massnahme;
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
                berechneRisikowert() + " Rückstellung " + ermittleRueckstellung() + " Maßnahme  " + getMassnahme();

    }

    @Override
    public boolean equals(Object o) {

        if (!super.equals(o)) return false;
        InakzeptabelesRisiko obj = (InakzeptabelesRisiko) o;
        return massnahme.equals(obj.massnahme);

    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), massnahme);

    }


}
