package pk1.rv.fachlogik;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Objects;

public class ExtremsRisko extends InakzeptabelesRisiko {
    private static final long serialVersionUID = 100L;
    private float versicherungsbeitrag;

    public ExtremsRisko(String bezeichung, float eintrittwahrscheilicjkeit, float kosten_im_schadenfall, String massnahme, float versicherungsbeitrag) {
        super(bezeichung, eintrittwahrscheilicjkeit, kosten_im_schadenfall, massnahme);
        this.versicherungsbeitrag = versicherungsbeitrag;
    }

    public ExtremsRisko() {
        super();
    }

    public float getVersicherungsbeitrag() {
        return versicherungsbeitrag;
    }

    public void setVersicherungsbeitrag(float setversicherung) {
        versicherungsbeitrag = setversicherung;
    }

    @Override
    public float ermittleRueckstellung() {
        return versicherungsbeitrag;
    }

    @Override
    public void druckDaten(OutputStream stream) {
        PrintStream ps = new PrintStream(stream);
        ps.printf(toString());
    }

    @Override
    public String toString() {
        return super.toString() + "Versischerung " + getVersicherungsbeitrag();
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        ExtremsRisko obj = (ExtremsRisko) o;

        return obj.versicherungsbeitrag == this.versicherungsbeitrag;


    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), versicherungsbeitrag);

    }

}
