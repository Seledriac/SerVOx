package game_app.Exceptions;

public class ChasseurException extends Exception {
        public ChasseurException(){
            super("Un chasseur possède 1 arc, un sort, ET RIEN D'AUTRE.");
        }
}
