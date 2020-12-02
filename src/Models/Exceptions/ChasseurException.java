package Models.Exceptions;

public class ChasseurException extends Exception {
        public ChasseurException(){
            super("Un chasseur ne doit posséder que des arcs et des sorts.");
        }
}
