package src.view.Gui;

import src.controller.Controller;
import src.model.exceptions.SeleccionInvalidaException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;

import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Panel1 extends JPanel {

    // Controlador
    private Controller controller;

    private JTextField trainerName1, trainerName2;
    private JButton startButton, loadButton;

    public Panel1() {

        setLayout(new BorderLayout());

        // Fondo degradado
        setOpaque(false);

        // Paneles
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 40, 0));
        centerPanel.setOpaque(false);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setOpaque(false);
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setOpaque(false);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

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

        // Posición y estilo de los labels texto de cada entrenador
        trainerLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        trainerLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        trainerLabel1.setFont(new Font("Arial", Font.BOLD, 16));
        trainerLabel2.setFont(new Font("Arial", Font.BOLD, 16));
        trainerLabel1.setForeground(new Color(0x1976D2));
        trainerLabel2.setForeground(new Color(0xD32F2F));

        // Campos de textos personalizados
        trainerName1 = new JTextField(8);
        trainerName2 = new JTextField(8);
        trainerName1.setFont(new Font("Arial", Font.BOLD, 16));
        trainerName1.setBackground(new Color(0xE3F2FD));
        trainerName1.setBorder(BorderFactory.createLineBorder(new Color(0x1976D2), 2, true));
        trainerName2.setFont(new Font("Arial", Font.BOLD, 16));
        trainerName2.setBackground(new Color(0xFFEBEE));
        trainerName2.setBorder(BorderFactory.createLineBorder(new Color(0xD32F2F), 2, true));

        // Botones de inicio y carga personalizados
        startButton = new JButton("Nueva Partida");
        loadButton = new JButton("Cargar partida");
        startButton.setBackground(new Color(0x1976D2));
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Arial", Font.BOLD, 14));
        startButton.setFocusPainted(false);
        loadButton.setBackground(new Color(0xD32F2F));
        loadButton.setForeground(Color.WHITE);
        loadButton.setFont(new Font("Arial", Font.BOLD, 14));
        loadButton.setFocusPainted(false);

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

    // Fondo degradado
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(0, 0, new Color(0xB2DFDB), 0, getHeight(), new Color(0xE1BEE7));
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    // Comprobación de los campos llenos
    private void checkTextFields() {
        try {
            controller.newGame(trainerName1.getText(), trainerName2.getText());
            JOptionPane.showMessageDialog(this, "¡Ambos campos están llenos! Iniciando juego...", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            controller.goToPanel2();
        } catch (SeleccionInvalidaException e) {
           JOptionPane.showMessageDialog(this, e.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
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
            } catch (IOException|ClassNotFoundException|SeleccionInvalidaException e) {
                JOptionPane.showMessageDialog(this, "Error al cargar la partida: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}