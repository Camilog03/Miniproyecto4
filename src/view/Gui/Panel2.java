package src.view.Gui;

import src.controller.Controller;

import java.awt.*;
import java.util.Queue;
import javax.swing.*;

public class Panel2 extends JPanel {

    Controller controller;

    private JLabel blueTrainerLabel;
    private JLabel redTrainerLabel;
    private JRadioButton radioButton1Blue, radioButton2Blue, radioButton3Blue;
    private JRadioButton radioButton1Red, radioButton2Red, radioButton3Red;
    private ButtonGroup leftButtonGroup, rightButtonGroup;


    public Panel2() {

        setLayout(new BorderLayout());

        //Paneles
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        JPanel buttonPanel = new JPanel();
        JPanel leftPanel = new JPanel(new BorderLayout());
        JPanel leftRadioPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        JPanel rightPanel = new JPanel(new BorderLayout());
        JPanel rightRadioPanel = new JPanel(new GridLayout(3, 1, 10, 10));


        //Imagenes
        ImageIcon blueTrainerImageIcon = new ImageIcon(new ImageIcon("src/images/blueTrainer.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        ImageIcon redTrainerImageIcon = new ImageIcon(new ImageIcon("src/images/redTrainer.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));

        //Etiquetas Izquierdas
        blueTrainerLabel = new JLabel("", SwingConstants.CENTER);
        blueTrainerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel blueTrainerImageLabel = new JLabel(blueTrainerImageIcon);
        blueTrainerImageLabel.setHorizontalAlignment(SwingConstants.CENTER);


        //Etiquetas Derechas
        redTrainerLabel = new JLabel("", SwingConstants.CENTER); // Nombre del entrenador 2
        redTrainerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel redTrainerImageLabel = new JLabel(redTrainerImageIcon);
        redTrainerImageLabel.setHorizontalAlignment(SwingConstants.CENTER);


        //Botones de tipo Radio
            //Izquierda
        radioButton1Blue = new JRadioButton();
        radioButton2Blue = new JRadioButton();
        radioButton3Blue = new JRadioButton();

        radioButton1Blue.setActionCommand("Button1Left");
        radioButton2Blue.setActionCommand("Button2Left");
        radioButton3Blue.setActionCommand("Button3Left");

            //Derecha
        radioButton1Red = new JRadioButton();
        radioButton2Red = new JRadioButton();
        radioButton3Red = new JRadioButton();

        radioButton1Red.setActionCommand("Button1Right");
        radioButton2Red.setActionCommand("Button2Right");
        radioButton3Red.setActionCommand("Button3Right");


        //Grupos de botones

        leftButtonGroup = new ButtonGroup();
        leftButtonGroup.add(radioButton1Blue);
        leftButtonGroup.add(radioButton2Blue);
        leftButtonGroup.add(radioButton3Blue);

        rightButtonGroup = new ButtonGroup();
        rightButtonGroup.add(radioButton1Red);
        rightButtonGroup.add(radioButton2Red);
        rightButtonGroup.add(radioButton3Red);


        // Botón "Empezar Batalla"
        JButton startBattleButton = new JButton("Empezar Batalla");

        // Botón "Cambiar a Terminal"
        JButton changeToTerminal = new JButton("Cambiar a Terminal");

        // Acción del botón
        startBattleButton.addActionListener(e -> startBottonAction());
        changeToTerminal.addActionListener(e -> controller.changeView());
        // Añadir elementos en los paneles respectivos

        //Izquierdos
        leftPanel.add(blueTrainerLabel, BorderLayout.NORTH);
        leftPanel.add(blueTrainerImageLabel, BorderLayout.CENTER);

        leftRadioPanel.add(radioButton1Blue);
        leftRadioPanel.add(radioButton2Blue);
        leftRadioPanel.add(radioButton3Blue);
        leftPanel.add(leftRadioPanel, BorderLayout.SOUTH);

        //Derechos
        rightPanel.add(redTrainerLabel, BorderLayout.NORTH);
        rightPanel.add(redTrainerImageLabel, BorderLayout.CENTER);

        rightRadioPanel.add(radioButton1Red);
        rightRadioPanel.add(radioButton2Red);
        rightRadioPanel.add(radioButton3Red);
        rightPanel.add(rightRadioPanel, BorderLayout.SOUTH);

        // Añadir boton al panel de boton

        buttonPanel.add(startBattleButton);
        buttonPanel.add(changeToTerminal);

        // Añadir paneles izquierdo y derecho al centro y agregar

        centerPanel.add(leftPanel);
        centerPanel.add(rightPanel);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setNamesPokemons(Queue<String> namesBlue, Queue<String> namesRed){
        radioButton1Blue.setText(namesBlue.poll());
        radioButton2Blue.setText(namesBlue.poll());
        radioButton3Blue.setText(namesBlue.poll());

        radioButton1Red.setText(namesRed.poll());
        radioButton2Red.setText(namesRed.poll());
        radioButton3Red.setText(namesRed.poll());
    }

    public void updateAlivePokemons(Queue<Boolean> aliveBlue, Queue<Boolean> aliveRed) {

            //limpiar seleccion de los pokemones del entrenador azul
            leftButtonGroup.clearSelection();

            // Desactivar botones del entrenador azul si el Pokémon no está vivo
            radioButton1Blue.setEnabled(aliveBlue.poll());
            radioButton2Blue.setEnabled(aliveBlue.poll());
            radioButton3Blue.setEnabled(aliveBlue.poll());

            //limpiar seleccion de los pokemones del entrenador rojo
            rightButtonGroup.clearSelection();

            // Desactivar botones del entrenador rojo si el Pokémon no está vivo
            radioButton1Red.setEnabled(aliveRed.poll());
            radioButton2Red.setEnabled(aliveRed.poll());
            radioButton3Red.setEnabled(aliveRed.poll());
        }


    private void startBottonAction(){
        byte indexPokemonBlue = -1;
        byte indexPokemonRed = -1;

        if (radioButton1Blue.isSelected()) indexPokemonBlue = 0;
        if (radioButton2Blue.isSelected()) indexPokemonBlue = 1;
        if (radioButton3Blue.isSelected()) indexPokemonBlue = 2;

        if (radioButton1Red.isSelected()) indexPokemonRed = 0;
        if (radioButton2Red.isSelected()) indexPokemonRed = 1;
        if (radioButton3Red.isSelected()) indexPokemonRed = 2;

        if (indexPokemonBlue == -1 || indexPokemonRed == -1) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un Pokémon para ambos entrenadores.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            controller.goToPanel3(indexPokemonBlue,indexPokemonRed);
        }
    }



    // Setters y Getters
    public void setTrainerBlueName(String name) {
        blueTrainerLabel.setText(name);
    }

    public void setTrainerRedName(String name) {
        redTrainerLabel.setText(name);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
