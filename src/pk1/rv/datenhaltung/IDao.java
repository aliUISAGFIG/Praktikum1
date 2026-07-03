package pk1.rv.datenhaltung;

import pk1.rv.fachlogik.Risiko;
import java.util.List;

public interface IDao {

    void speichern(List<Risiko> liste) throws PersistenzException;

    List<Risiko> laden() throws PersistenzException;

}
