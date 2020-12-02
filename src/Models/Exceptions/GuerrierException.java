package Models.Exceptions;

public class GuerrierException extends Exception{
    public GuerrierException(){
        super("Un guerrier ne doit posséder que des épées, des boucliers, et des sorts.");
    }
}
