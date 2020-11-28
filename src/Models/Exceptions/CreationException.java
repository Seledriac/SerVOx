package Models.Exceptions;

public class CreationException extends Exception {

    public CreationException(){
        super("Valeur(s) de création mauvaise(s)");
    }
}
