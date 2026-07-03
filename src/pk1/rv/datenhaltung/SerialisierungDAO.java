package pk1.rv.datenhaltung;

import pk1.rv.fachlogik.Risiko;

import java.io.*;
import java.nio.file.AccessDeniedException;
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

        } catch (FileNotFoundException ee){

            throw new PersistenzException("Die Datei könnte nicht erstellt werden");
        } catch (AccessDeniedException ex){

            throw new PersistenzException("zugriff auf der Datei wurde verweigert");
        } catch (IOException e) {
            throw new PersistenzException("Die Daten konnte nicht gespeichert werden ");
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


        }
        catch (FileNotFoundException e3){
            throw new PersistenzException("Die Datei wurde nicht gefunden");

        }catch (AccessDeniedException ex){

            throw new PersistenzException("zugriff auf der Datei serialisierungfile.ser wurde verweigert");}

        catch (ClassNotFoundException e2){
            throw new PersistenzException("Die gespeicherte Daten sind nicht mit diese programm version kompatibel");
        }
        catch (IOException e) {
            throw new PersistenzException(e.getMessage());
        }
        return geladeneRisiken;
    }
}
