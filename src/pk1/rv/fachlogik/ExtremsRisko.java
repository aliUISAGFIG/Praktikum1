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
        PrintStream str = new PrintStream(stream);
        int monate = getErstellungsdatum().getMonthValue();
        int jahr = getErstellungsdatum().getYear();
        str.printf("Id %d Extremes Risiko %s aus %d/%d;%nVersicherungsbeitrag %.2f Maßnahme %s%n",
                getId(),
                getbezeichnung(),
                monate,
                jahr,
                getVersicherungsbeitrag(),
                getMassnahme());
    }

    @Override
    public String toString() {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        druckDaten(baos);
        return baos.toString();

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
