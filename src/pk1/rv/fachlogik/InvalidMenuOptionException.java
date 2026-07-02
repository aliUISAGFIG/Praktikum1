package pk1.rv.fachlogik;

public class InvalidMenuOptionException extends Exception {
    public InvalidMenuOptionException(){
        System.err.println("Bitte ein zahl zwischen 1 bis 5");
    }

    public InvalidMenuOptionException(String message) {
        super(message);
    }
}
