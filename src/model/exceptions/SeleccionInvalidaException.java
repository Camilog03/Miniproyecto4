package src.model.exceptions;

// Excepción personalizada para manejar selecciones inválidas en el juego
public class SeleccionInvalidaException extends Exception{
    
    public SeleccionInvalidaException (String message){
        super(message);
    }

}