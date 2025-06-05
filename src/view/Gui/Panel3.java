package src.view.Gui;

import src.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;

public class Panel3 extends JPanel implements ActionListener {

    //Controlador
    private Controller controller;

    private JLabel blueTrainerLabel, bluePokemonLabel, redTrainerLabel, redPokemonLabel;
    private JButton attack1Blue, attack2Blue, attack3Blue, attack1Red, attack2Red, attack3Red;
    private boolean isBlueTurn;
    private JLabel redPokemonHpLabel, bluePokemonHpLabel, bluePokemonImage, redPokemonImage;


    public Panel3() {

        setLayout(new BorderLayout());
        setBackground(new Color(0x8FB5DE));

        //Paneles
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));

        JPanel leftPanel = new JPanel(new BorderLayout());
        JPanel leftLabelsPanel = new JPanel(new GridLayout(2, 1));
        JPanel blueImageAndHpPanel = new JPanel(new BorderLayout());
        JPanel blueButtonsPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        JPanel rightPanel = new JPanel(new BorderLayout());
        JPanel rightLabelsPanel = new JPanel(new GridLayout(2, 1));
        JPanel redImageAndHpPanel = new JPanel(new BorderLayout());
        JPanel redButtonsPanel = new JPanel(new GridLayout(3, 1, 10, 10));


        // Etiquetas
        blueTrainerLabel = new JLabel("", SwingConstants.CENTER);
        blueTrainerLabel.setFont(new Font("Arial", Font.BOLD, 16));

        bluePokemonLabel = new JLabel("", SwingConstants.CENTER);
        bluePokemonLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        bluePokemonHpLabel = new JLabel("HP = " + 0, SwingConstants.CENTER);
        bluePokemonHpLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        redTrainerLabel = new JLabel("", SwingConstants.CENTER);
        redTrainerLabel.setFont(new Font("Arial", Font.BOLD, 16));

        redPokemonLabel = new JLabel("", SwingConstants.CENTER);
        redPokemonLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        redPokemonHpLabel = new JLabel("HP = " + 0, SwingConstants.CENTER);
        redPokemonHpLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        //Etiquetas de imagenes de Pokemones por defecto

        bluePokemonImage = new JLabel(new ImageIcon(new ImageIcon("src/images/pokemonsImages/imagenPokemonDesconocido").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        redPokemonImage = new JLabel(new ImageIcon(new ImageIcon("src/images/pokemonsImages/imagenPokemonDesconocido").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        bluePokemonImage.setHorizontalAlignment(SwingConstants.CENTER);
        redPokemonImage.setHorizontalAlignment(SwingConstants.CENTER);

        //Botones

        attack1Blue = new JButton("Ataque 1");
        attack2Blue = new JButton("Ataque 2");
        attack3Blue = new JButton("Ataque 3");

        attack1Red = new JButton("Ataque 1");
        attack2Red = new JButton("Ataque 2");
        attack3Red = new JButton("Ataque 3");


        //Añadir elementos a los paneles correspondientes
            //Izquierdo
            leftLabelsPanel.add(blueTrainerLabel);
            leftLabelsPanel.add(bluePokemonLabel);

            blueImageAndHpPanel.add(bluePokemonImage, BorderLayout.CENTER);
            blueImageAndHpPanel.add(bluePokemonHpLabel, BorderLayout.SOUTH);

            blueButtonsPanel.add(attack1Blue);
            blueButtonsPanel.add(attack2Blue);
            blueButtonsPanel.add(attack3Blue);

            leftPanel.add(leftLabelsPanel, BorderLayout.NORTH);
            leftPanel.add(blueImageAndHpPanel, BorderLayout.CENTER);
            leftPanel.add(blueButtonsPanel, BorderLayout.SOUTH);

            //Derecho
            rightLabelsPanel.add(redTrainerLabel);
            rightLabelsPanel.add(redPokemonLabel);

            redImageAndHpPanel.add(redPokemonImage, BorderLayout.CENTER);
            redImageAndHpPanel.add(redPokemonHpLabel, BorderLayout.SOUTH);

            redButtonsPanel.add(attack1Red);
            redButtonsPanel.add(attack2Red);
            redButtonsPanel.add(attack3Red);

            rightPanel.add(rightLabelsPanel, BorderLayout.NORTH);
            rightPanel.add(redImageAndHpPanel, BorderLayout.CENTER);
            rightPanel.add(redButtonsPanel, BorderLayout.SOUTH);

        //Panel Principal
        centerPanel.add(leftPanel);
        centerPanel.add(rightPanel);

        add(centerPanel, BorderLayout.CENTER);

        updateButtonStates();

        // Listeners para los clics en botones de ataque
        attack1Blue.addActionListener(this);
        attack2Blue.addActionListener(this);
        attack3Blue.addActionListener(this);
        attack1Red.addActionListener(this);
        attack2Red.addActionListener(this);
        attack3Red.addActionListener(this);
    }

    // Habilita o deshabilita los botones según el turno


    // Maneja los clics en botones de ataque
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isBlueTurn) {
            byte indexAttack = -1;
            if (e.getSource().equals(attack1Blue)) {
                indexAttack = 0;
            } else if (e.getSource().equals(attack2Blue)) {
                indexAttack = 1;
            } else if (e.getSource().equals(attack3Blue)) {
                indexAttack = 2;
            }
            controller.blueMakeDamage(indexAttack);
        } else {
            byte indexAttack = -1;
            if (e.getSource().equals(attack1Red)) {
                indexAttack = 0;
            } else if (e.getSource().equals(attack2Red)) {
                indexAttack = 1;
            } else if (e.getSource().equals(attack3Red)) {
                indexAttack = 2;
            }
            controller.redMakeDamage(indexAttack);
        }

        // Verificar si alguno de los Pokemon fue derrotado
        controller.checkAlivePokemon();

        isBlueTurn = !isBlueTurn;
        updateButtonStates();
    }


    //Metodo para actualizar indices y determinar quien empieza
    public void setPokemonIndexes(boolean turn) {
        isBlueTurn = turn;
        updateButtonStates();
    }

    private void updateButtonStates() {

        attack1Blue.setEnabled(isBlueTurn);
        attack2Blue.setEnabled(isBlueTurn);
        attack3Blue.setEnabled(isBlueTurn);

        attack1Red.setEnabled(!isBlueTurn);
        attack2Red.setEnabled(!isBlueTurn);
        attack3Red.setEnabled(!isBlueTurn);
    }

    public void setNamesAttacks(Queue<String> blueAttacks, Queue<String> redAttacks) {

        attack1Blue.setText(blueAttacks.poll());
        attack2Blue.setText(blueAttacks.poll());
        attack3Blue.setText(blueAttacks.poll());

        attack1Red.setText(redAttacks.poll());
        attack2Red.setText(redAttacks.poll());
        attack3Red.setText(redAttacks.poll());
    }

    //Actualiza HP con los valores actuales
    public void updateHpLabels(short hpBlue, short hpRed) {
        bluePokemonHpLabel.setText("HP = " + hpBlue);
        redPokemonHpLabel.setText("HP = " + hpRed);
    }

    // Setters para actualizar nombres e imágenes
    public void setBluePokemonImage(String path) {
        ImageIcon bluePokemonImage = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        this.bluePokemonImage.setIcon(bluePokemonImage);
    }

    public void setRedPokemonImage(String path) {
        ImageIcon redPokemonImage = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        this.redPokemonImage.setIcon(redPokemonImage);
    }

    public void setBlueTrainerName(String name) {
        blueTrainerLabel.setText("Entrenador azul: " + name);
    }

    public void setRedTrainerName(String name) {
        redTrainerLabel.setText("Entrenador rojo: " + name);
    }

    public void setBluePokemonName(String name) {
        bluePokemonLabel.setText("Pokemon: " + name);
    }

    public void setRedPokemonName(String name) {
        redPokemonLabel.setText("Pokemon: " + name);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void isBlueTurn(boolean isBlueTurn) {
        this.isBlueTurn = isBlueTurn;
    }
}