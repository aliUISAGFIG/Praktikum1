package pk1.rv.fachlogik;

import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


public abstract class Risiko implements Comparable<Risiko>, Serializable {

    private final int id;
    public static final float LIMIT = 10000.0f;
    public static final float KOSTENLIMIT = 1000000.0f;
    private static final long serialVersionUID = 100L;
    private String bezeichnung;
    private static int counter = 0;
    private float eintrittswahrcheiligkeit;
    private float kosten_im_schadenfall;
    private LocalDate erstellungsdatum;

    public Risiko() {
        this.id = counter;
        erstellungsdatum = LocalDate.now();
        counter++;
    }

    public Risiko(String bezeichnung, float eintrittswahrcheiligkeit, float kosten_im_schadenfall) {
        this();
        setBezeichung(bezeichnung);
        this.eintrittswahrcheiligkeit = eintrittswahrcheiligkeit;
        this.kosten_im_schadenfall = kosten_im_schadenfall;
    }

    @Override
    public int compareTo(Risiko o) {
        return Float.compare(this.berechneRisikowert(), o.berechneRisikowert());

    }

    public float berechneRisikowert() {

        return this.getEintrittswahrcheiligkeit() * this.getKosten_im_schadenfall();

    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int count) {
        counter = count;
    }
    public int getMonate(){
       return getErstellungsdatum().getMonthValue();
    }
    public int getJahr(){
        return getErstellungsdatum().getYear();
    }
    public float getEintrittswahrcheiligkeit() {
        return eintrittswahrcheiligkeit;
    }

    public void setEintrittswahrcheiligkeit(float eintrittswahrcheiligkeit) {
        this.eintrittswahrcheiligkeit = eintrittswahrcheiligkeit;
    }

    public float getKosten_im_schadenfall() {
        return kosten_im_schadenfall;
    }

    public void setKosten_im_schadenfall(float kosten_im_schadenfall) {
        this.kosten_im_schadenfall = kosten_im_schadenfall;
    }

    public abstract float ermittleRueckstellung();

    public abstract void druckDaten(OutputStream stream);

    public int getId() {
        return this.id;
    }

    public LocalDate getErstellungsdatum() {

        return erstellungsdatum;
    }

    public static void vergleichmethoden(Risiko o1, Risiko o2) {
        if (o1.equals(o2)) {
            System.out.println("Die Objekte mit Id " + o1.getId() + " und Id " + o2.getId() + " sind (fachlich) gleich\n" +
                    "Die gleichen Objekte haben den Hashcode " + o1.hashCode());
        } else {
            System.out.println("Die Objekte mit Id " + o1.getId() + " und Id " + o2.getId() + " sind nicht gleich\n" +
                    "Die unterschiedliche Objekte haben den Hashcode " + o1.hashCode() + " und " + o2.hashCode());

        }

    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichung(String bezeichnung) {

        this.bezeichnung = bezeichnung;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Risiko other = (Risiko) o;
        return bezeichnung.equals(other.bezeichnung) &&
                eintrittswahrcheiligkeit == eintrittswahrcheiligkeit &&
                kosten_im_schadenfall == kosten_im_schadenfall;
    }

    @Override
    public int hashCode() {

        return Objects.hash(bezeichnung, eintrittswahrcheiligkeit, kosten_im_schadenfall);
    }

}
