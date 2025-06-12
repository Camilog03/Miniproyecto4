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
import java.util.Enumeration;
import java.util.Queue;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Panel2 extends JPanel {

    Controller controller;

    private JLabel blueTrainerLabel;
    private JLabel redTrainerLabel;
    private JRadioButton radioButton1Blue, radioButton2Blue, radioButton3Blue;
    private JRadioButton radioButton1Red, radioButton2Red, radioButton3Red;
    private ButtonGroup leftButtonGroup, rightButtonGroup;

    public Panel2() {

        setLayout(new BorderLayout());
        setOpaque(false);

        //Paneles
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        centerPanel.setOpaque(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setOpaque(false);
        JPanel leftRadioPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        leftRadioPanel.setOpaque(false);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setOpaque(false);
        JPanel rightRadioPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        rightRadioPanel.setOpaque(false);

        //Imagenes
        ImageIcon blueTrainerImageIcon = new ImageIcon(new ImageIcon("src/images/blueTrainer.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        ImageIcon redTrainerImageIcon = new ImageIcon(new ImageIcon("src/images/redTrainer.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));

        //Etiquetas Izquierdas
        blueTrainerLabel = new JLabel("", SwingConstants.CENTER);
        blueTrainerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        blueTrainerLabel.setForeground(new Color(0x1976D2));
        JLabel blueTrainerImageLabel = new JLabel(blueTrainerImageIcon);
        blueTrainerImageLabel.setHorizontalAlignment(SwingConstants.CENTER);


        //Etiquetas Derechas
        redTrainerLabel = new JLabel("", SwingConstants.CENTER);
        redTrainerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        redTrainerLabel.setForeground(new Color(0xD32F2F));
        JLabel redTrainerImageLabel = new JLabel(redTrainerImageIcon);
        redTrainerImageLabel.setHorizontalAlignment(SwingConstants.CENTER);


        //Botones de tipo Radio
        Font radioFont = new Font("Arial", Font.BOLD, 15);

        //Izquierda
        radioButton1Blue = new JRadioButton();
        radioButton2Blue = new JRadioButton();
        radioButton3Blue = new JRadioButton();

        radioButton1Blue.setActionCommand("Button1Left");
        radioButton2Blue.setActionCommand("Button2Left");
        radioButton3Blue.setActionCommand("Button3Left");

        radioButton1Blue.setFont(radioFont);
        radioButton2Blue.setFont(radioFont);
        radioButton3Blue.setFont(radioFont);

        radioButton1Blue.setBackground(new Color(0xE3F2FD));
        radioButton2Blue.setBackground(new Color(0xE3F2FD));
        radioButton3Blue.setBackground(new Color(0xE3F2FD));

        //Derecha
        radioButton1Red = new JRadioButton();
        radioButton2Red = new JRadioButton();
        radioButton3Red = new JRadioButton();

        radioButton1Red.setActionCommand("Button1Right");
        radioButton2Red.setActionCommand("Button2Right");
        radioButton3Red.setActionCommand("Button3Right");

        radioButton1Red.setFont(radioFont);
        radioButton2Red.setFont(radioFont);
        radioButton3Red.setFont(radioFont);

        radioButton1Red.setBackground(new Color(0xFFEBEE));
        radioButton2Red.setBackground(new Color(0xFFEBEE));
        radioButton3Red.setBackground(new Color(0xFFEBEE));

        //Grupos de botones
        leftButtonGroup = new ButtonGroup();
        leftButtonGroup.add(radioButton1Blue);
        leftButtonGroup.add(radioButton2Blue);
        leftButtonGroup.add(radioButton3Blue);

        rightButtonGroup = new ButtonGroup();
        rightButtonGroup.add(radioButton1Red);
        rightButtonGroup.add(radioButton2Red);
        rightButtonGroup.add(radioButton3Red);

        // Bordes y separación
        leftPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0x1976D2), 2, true),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        rightPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0xD32F2F), 2, true),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Botón "Empezar Batalla"
        JButton startBattleButton = new JButton("Empezar Batalla");
        startBattleButton.setBackground(new Color(0x1976D2));
        startBattleButton.setForeground(Color.WHITE);
        startBattleButton.setFont(new Font("Arial", Font.BOLD, 14));
        startBattleButton.setFocusPainted(false);

        // Botón "Guardar Partida"
        JButton saveGame = new JButton("Guardar Partida");
        saveGame.setBackground(new Color(0xD32F2F));
        saveGame.setForeground(Color.WHITE);
        saveGame.setFont(new Font("Arial", Font.BOLD, 14));
        saveGame.setFocusPainted(false);

        // Acción del botón
        startBattleButton.addActionListener(e -> start());
        saveGame.addActionListener(e -> saveGame());

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
        buttonPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        buttonPanel.add(startBattleButton);
        buttonPanel.add(saveGame);

        // Añadir paneles izquierdo y derecho al centro y agregar
        centerPanel.add(leftPanel);
        centerPanel.add(rightPanel);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
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

    private void start(){
        String pokemonBlue = "";
        String pokemonRed = "";

        for (Enumeration<AbstractButton> e = leftButtonGroup.getElements(); e.hasMoreElements();){
            AbstractButton button = e.nextElement();
            if (button.isSelected()){
                pokemonBlue = button.getText();
            }
        }

        for (Enumeration<AbstractButton> e = rightButtonGroup.getElements(); e.hasMoreElements();){
            AbstractButton button = e.nextElement();
            if (button.isSelected()){
                pokemonRed = button.getText();
            }
        }

        try {

            controller.goToPanel3(pokemonBlue, pokemonRed);
            
        } catch (SeleccionInvalidaException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        
    }

    private void saveGame() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar partida");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos de partida (*.dat)", "dat"));
        int resultado = fileChooser.showSaveDialog(this); // 'this' si Panel2 es un JPanel
        if (resultado == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            if (!path.toLowerCase().endsWith(".dat")) {
                path += ".dat"; // Asegúrate de que la extensión sea .dat
            }
            try {
                controller.saveGame(path);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al guardar la partida: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
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