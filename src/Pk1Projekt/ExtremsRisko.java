package Pk1Projekt;

import javax.swing.*;
import java.util.Objects;

public class ExtremsRisko extends InakzeptabelesRisiko {

    private float versicherungsbeitrag;

    public ExtremsRisko(String bezeichung, float eintrittwahrscheilicjkeit, float kosten_im_schadenfall, String massnahme, float versicherungsbeitrag) {
        super(bezeichung, eintrittwahrscheilicjkeit, kosten_im_schadenfall, massnahme);
        this.versicherungsbeitrag = versicherungsbeitrag;
    }


    public float getVersicherungsbeitrag() {
        return versicherungsbeitrag;
    }

    @Override
    public float ermittleRueckstellung() {
        return versicherungsbeitrag;
    }

    @Override
    public void druckDaten() {
        int monate = getErstellungsdatum().getMonthValue();
        int jahr = getErstellungsdatum().getYear();
        JOptionPane.showMessageDialog(null , "Id " + getId() + " Extremes Risiko " + getbezeichnung() + " aus " + monate + "/" + jahr + ";\n" +
                "Versicherungbeitrag " + getVersicherungsbeitrag() + "Maßnahme " + getMassnahme());


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
