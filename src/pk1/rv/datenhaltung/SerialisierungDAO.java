package pk1.rv.datenhaltung;

import pk1.rv.fachlogik.Risiko;

import java.io.*;
import java.util.List;

public class SerialisierungDAO implements IDao{


    @Override
    public void speichern(List<Risiko> liste) throws PersistenzException {
        File file = new File("serialisierungfile.ser") ;
        try (FileOutputStream fil = new FileOutputStream(file);
             ObjectOutputStream obj = new ObjectOutputStream(fil))
        {
            obj.writeInt(Risiko.getCounter());
            obj.writeObject(liste);

        } catch (IOException e) {
            throw new PersistenzException(e.getMessage());
        }
    }

    @Override
    public List<Risiko> laden() throws PersistenzException {
        File file = new File("serialisierungfile.ser") ;
        List<Risiko> geladeneRisiken;
        try (FileInputStream fil = new FileInputStream(file);
             ObjectInputStream les = new ObjectInputStream(fil)){

            Risiko.setCounter(les.readInt());
            geladeneRisiken = (List<Risiko>) les.readObject();


        } catch (IOException | ClassNotFoundException e) {
            throw new PersistenzException(e.getMessage());
        }
        return geladeneRisiken;
    }
}
