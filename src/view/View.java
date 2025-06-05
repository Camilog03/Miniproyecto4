package src.view;

import src.controller.Controller;
import java.util.Queue;

public interface View {

    void showPanel1();  // Ej: ingresar datos
    void showPanel2(String blueTrainerName, String redTrainerName, Queue<String> namesBlue, Queue<String> namesRed, Queue<Boolean> aliveBlue, Queue<Boolean> aliveRed);  // Ej: mostrar entrenadores
    void showPanel3(String menssageStart, String blueTrainerName, String redTrainerName, String bluePokemonName, String redPokemonName,
                    String bluePath, String redPath,Queue<String> blueAttacks, Queue<String> redAttacks, boolean turn);  // Ej: resumen u opciones
    void updateHP(short hpBlue, short hpRed); //metodo unico para la gui
    void showMessage(String msg);
    void setController(Controller controller);
    void disable();
}
