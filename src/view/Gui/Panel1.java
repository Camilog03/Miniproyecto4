package src.view.Gui;

import src.controller.Controller;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class Panel1 extends JPanel {

    // Controlador
    private Controller controller;

    private JTextField trainerName1, trainerName2;
    private JButton startButton, loadButton;

    public Panel1() {

        setLayout(new BorderLayout());

        // Paneles
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        JPanel leftPanel = new JPanel(new BorderLayout());
        JPanel rightPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();

        // Imágenes
        ImageIcon titleImage = new ImageIcon(new ImageIcon("src/images/battleTitle.png").getImage().getScaledInstance(350, 200, Image.SCALE_SMOOTH));
        ImageIcon blueTrainerImage = new ImageIcon(new ImageIcon("src/images/blueTrainer.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        ImageIcon redTrainerImage = new ImageIcon(new ImageIcon("src/images/redTrainer.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));

        // Etiquetas de las imágenes
        JLabel titleLabelImage = new JLabel(titleImage);
        JLabel trainer1Image = new JLabel(blueTrainerImage);
        JLabel trainer2Image = new JLabel(redTrainerImage);

        // Posición de las etiquetas de las imágenes
        titleLabelImage.setHorizontalAlignment(SwingConstants.CENTER);
        trainer1Image.setHorizontalAlignment(SwingConstants.CENTER);
        trainer2Image.setHorizontalAlignment(SwingConstants.CENTER);

        // Etiqueta del color de cada entrenador
        JLabel trainerLabel1 = new JLabel("Entrenador Azul:");
        JLabel trainerLabel2 = new JLabel("Entrenador Rojo:");

        // Posición de los labels texto de cada entrenador
        trainerLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        trainerLabel2.setHorizontalAlignment(SwingConstants.CENTER);

        // Campos de textos
        trainerName1 = new JTextField(5);
        trainerName2 = new JTextField(5);

        // Botones de inicio y carga
        startButton = new JButton("Nueva Partida");
        loadButton = new JButton("Cargar partida");

        // Añadir los elementos a los paneles correspondientes
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
        buttonPanel.add(loadButton);

        add(titleLabelImage, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        startButton.addActionListener(e -> checkTextFields());
        loadButton.addActionListener(e -> uploadGame());
    }

    // Comprobación de los campos llenos
    private void checkTextFields() {
        if (trainerName1.getText().isEmpty() || trainerName2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes llenar ambos campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "¡Ambos campos están llenos! Iniciando juego...", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            controller.newGame(trainerName1.getText(), trainerName2.getText());
            controller.goToPanel2();
        }
    }

    // Metodo para cargar partida con JFileChooser
    private void uploadGame() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona un archivo de partida");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos de partida (*.dat)", "dat"));
        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            try {
                controller.uploadGame(path);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al cargar la partida: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}