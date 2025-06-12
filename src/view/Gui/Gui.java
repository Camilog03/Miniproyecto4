package src.view.Gui;
import src.controller.Controller;
import src.view.View;

import java.awt.CardLayout;
import java.awt.Container;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Gui extends JFrame implements View {

    private Container container;
    private CardLayout cardLayout;

    private Panel1 panel1;
    private Panel2 panel2;
    private Panel3 panel3;

    // Constructor de la GUI
    public Gui(){
        setTitle("BATALLA POKEMON");
        setSize(900,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        container = getContentPane();
        container.setLayout(cardLayout);

        panel1 = new Panel1();
        panel2 = new Panel2();
        panel3 = new Panel3();

        container.add(panel1, "panel 1");
        container.add(panel2, "panel 2");
        container.add(panel3, "panel 3");


    }

    // Método para mostrar el panel 1 (ingreso de datos)
    @Override
    public void showPanel1() {
        setVisible(true);
        setLocationRelativeTo(null);
    }

    // Método para mostrar el panel 2 (selección de pokemones)
    @Override
    public void showPanel2(String blueTrainerName, String redTrainerName, Queue<String> namesBlue, Queue<String> namesRed, Queue<Boolean> aliveBlue, Queue<Boolean> aliveRed) {

        //Poner nombres
        panel2.setTrainerBlueName(blueTrainerName);
        panel2.setTrainerRedName(redTrainerName);

        //Actualizar datos de los pokemones
        panel2.setNamesPokemons(namesBlue, namesRed);
        panel2.updateAlivePokemons(aliveBlue, aliveRed);

        cardLayout.show(container, "panel 2");

    }

    // Método para mostrar el panel 3 (batalla)
    @Override
    public void showPanel3(String menssageStart, String blueTrainerName, String redTrainerName, String bluePokemonName, String redPokemonName,
                           String bluePath, String redPath,Queue<String> blueAttacks, Queue<String> redAttacks, boolean turn) {
        showMessage(menssageStart);

        panel3.setBlueTrainerName(blueTrainerName);
        panel3.setRedTrainerName(redTrainerName);

        panel3.setBluePokemonName(bluePokemonName);
        panel3.setRedPokemonName(redPokemonName);

        panel3.setBluePokemonImage(bluePath);
        panel3.setRedPokemonImage(redPath);

        panel3.setNamesAttacks(blueAttacks, redAttacks);

        panel3.setPokemonIndexes(turn);

        cardLayout.show(container, "panel 3");

    }

    // Método para actualizar los puntos de vida de los pokemones
    @Override
    public void updateHP(short hpBlue, short hpRed, short hpInitialBlue, short hpInitialRed) {
        panel3.updateHpLabels(hpBlue, hpRed, hpInitialBlue, hpInitialRed);
    }

    // Método para mostrar un mensaje en la GUI
    @Override
    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "INFORMACION" ,JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para establecer el controlador
    @Override
    public void setController(Controller controller) {
        panel1.setController(controller);
        panel2.setController(controller);
        panel3.setController(controller);
    }

    // Método para deshabilitar la GUI al finalizar el juego
    @Override
    public void disable() {
        dispose();
    }

    // Método para actualizar el historial de acciones
    @Override
    public void updateActionHistory(Stack<String> actionHistory) {
        panel3.setActionHistory(actionHistory);
    }

    // Método para limpiar el historial de acciones
    @Override
    public void clearActionHistory() {
        panel3.clearActionHistory();
    }
}
