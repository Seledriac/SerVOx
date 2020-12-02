package Models.Exceptions;

public class MageException extends Exception{
    public MageException(){
        super("Un mage ne doit posséder que des sorts.");
    }
}
