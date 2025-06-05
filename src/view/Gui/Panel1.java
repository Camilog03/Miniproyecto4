package src.view.Gui;

import src.controller.Controller;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class Panel1 extends JPanel {

    //Controlador
    private Controller controller;

    private JTextField trainerName1, trainerName2;
    private JButton startButton;


    public Panel1() {

        setLayout(new BorderLayout());


        //paneles
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        JPanel leftPanel = new JPanel(new BorderLayout());
        JPanel rightPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();

        //Imagenes
        ImageIcon titleImage = new ImageIcon(new ImageIcon("src/images/battleTitle.png").getImage().getScaledInstance(350, 200, Image.SCALE_SMOOTH));
        ImageIcon blueTrainerImage = new ImageIcon(new ImageIcon("src/images/blueTrainer.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        ImageIcon redTrainerImage = new ImageIcon(new ImageIcon("src/images/redTrainer.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));


        //Etiquetas de las imagenes
        JLabel titleLabelImage = new JLabel(titleImage);
        JLabel trainer1Image = new JLabel(blueTrainerImage);
        JLabel trainer2Image = new JLabel(redTrainerImage);


        //Posicion de las etiquetas de las imagenes
        titleLabelImage.setHorizontalAlignment(SwingConstants.CENTER);
        trainer1Image.setHorizontalAlignment(SwingConstants.CENTER);
        trainer2Image.setHorizontalAlignment(SwingConstants.CENTER);


        //Etiqueta del color de cada entrenador
        JLabel trainerLabel1 = new JLabel("Entrenador Azul:");
        JLabel trainerLabel2 = new JLabel("Entrenador Rojo:");


        //Posicion de los labels texto de cada entrenador
        trainerLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        trainerLabel2.setHorizontalAlignment(SwingConstants.CENTER);


        //Campos de textos
        trainerName1 = new JTextField(5);
        trainerName2 = new JTextField(5);

        //Boton de inicio
        startButton = new JButton("Start Game");

        //Añadir los elementos a los paneles correspondientes

        leftPanel.add(trainer1Image, BorderLayout.NORTH);
        leftPanel.add(trainerLabel1, BorderLayout.CENTER);
        leftPanel.add(trainerName1, BorderLayout.SOUTH);

        rightPanel.add(trainer2Image, BorderLayout.NORTH);
        rightPanel.add(trainerLabel2, BorderLayout.CENTER);
        rightPanel.add(trainerName2, BorderLayout.SOUTH);

        centerPanel.add(leftPanel);
        centerPanel.add(rightPanel);


        buttonPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        buttonPanel.add(startButton);

        add(titleLabelImage, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        startButton.addActionListener(e -> checkTextFields());
    }
    //Comprobación de los campos llenos
    private void checkTextFields() {
        if (trainerName1.getText().isEmpty() || trainerName2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes llenar ambos campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "¡Ambos campos están llenos! Iniciando juego...", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            controller.setTrainersNames(trainerName1.getText(), trainerName2.getText());
            controller.goToPanel2();
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
