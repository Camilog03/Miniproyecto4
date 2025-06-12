package src.view.Gui;

import src.controller.Controller;
import src.model.exceptions.SeleccionInvalidaException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

class BarHpPokemonPanel extends JPanel {
    private int currHp, maxHp;
    private final int width = 150;
    private final int height = 20;

    public BarHpPokemonPanel(int currHp, int maxHp) {
        this.currHp = currHp;
        this.maxHp = maxHp;
        setPreferredSize(new Dimension(width, height + 15));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Fondo barra
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        // Color según porcentaje de vida
        Color currColor;
        double percent = (double) currHp / maxHp;
        if (percent >= 0.8) {
            currColor = Color.GREEN;
        } else if (percent >= 0.5) {
            currColor = Color.YELLOW;
        } else {
            currColor = Color.RED;
        }

        int lifeWidth = (int) ((width * currHp) / (double) maxHp);
        g.setColor(currColor);
        g.fillRect(0, 0, lifeWidth, height);

    }

    public void updateHp(int newHp) {
        Timer t = new Timer(10, e -> {
            if (currHp == newHp) {
                ((Timer) e.getSource()).stop();
                return;
            }
            if (currHp < newHp) currHp++;
            else currHp--;
            repaint();
        });
        t.start();
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
        repaint();
    }
}

public class Panel3 extends JPanel implements ActionListener {

    //Controlador
    private Controller controller;

    private JLabel blueTrainerLabel, bluePokemonLabel, redTrainerLabel, redPokemonLabel;
    private JButton attack1Blue, attack2Blue, attack3Blue, attack1Red, attack2Red, attack3Red;
    private boolean isBlueTurn;
    private JLabel redPokemonHpLabel, bluePokemonHpLabel, bluePokemonImage, redPokemonImage;
    private short hpBlueInitial, hpRedInitial;
    private BarHpPokemonPanel blueHpBar, redHpBar;

    public Panel3() {

        setLayout(new BorderLayout());

        // Fondo degradado
        setOpaque(false);

        //Paneles
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        centerPanel.setOpaque(false);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setOpaque(false);
        JPanel leftLabelsPanel = new JPanel(new GridLayout(2, 1));
        leftLabelsPanel.setOpaque(false);
        JPanel blueButtonsPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        blueButtonsPanel.setOpaque(false);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setOpaque(false);
        JPanel rightLabelsPanel = new JPanel(new GridLayout(2, 1));
        rightLabelsPanel.setOpaque(false);
        JPanel redButtonsPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        redButtonsPanel.setOpaque(false);

        // Bordes y separación
        leftPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0x1976D2), 2, true),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        rightPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0xD32F2F), 2, true),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Etiquetas
        blueTrainerLabel = new JLabel("", SwingConstants.CENTER);
        blueTrainerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        blueTrainerLabel.setForeground(new Color(0x1976D2));

        bluePokemonLabel = new JLabel("", SwingConstants.CENTER);
        bluePokemonLabel.setFont(new Font("Arial", Font.BOLD, 20));
        bluePokemonLabel.setForeground(new Color(0x1976D2));
        bluePokemonLabel.setHorizontalAlignment(SwingConstants.CENTER);

        bluePokemonHpLabel = new JLabel("HP = " + 0, SwingConstants.CENTER);
        bluePokemonHpLabel.setFont(new Font("Arial", Font.BOLD, 18));
        bluePokemonHpLabel.setForeground(new Color(0x1976D2));
        bluePokemonHpLabel.setHorizontalAlignment(SwingConstants.CENTER);

        redTrainerLabel = new JLabel("", SwingConstants.CENTER);
        redTrainerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        redTrainerLabel.setForeground(new Color(0xD32F2F));

        redPokemonLabel = new JLabel("", SwingConstants.CENTER);
        redPokemonLabel.setFont(new Font("Arial", Font.BOLD, 20));
        redPokemonLabel.setForeground(new Color(0xD32F2F));
        redPokemonLabel.setHorizontalAlignment(SwingConstants.CENTER);

        redPokemonHpLabel = new JLabel("HP = " + 0, SwingConstants.CENTER);
        redPokemonHpLabel.setFont(new Font("Arial", Font.BOLD, 18));
        redPokemonHpLabel.setForeground(new Color(0xD32F2F));
        redPokemonHpLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //Etiquetas de imagenes de Pokemones por defecto
        bluePokemonImage = new JLabel(new ImageIcon(new ImageIcon("src/images/pokemonsImages/imagenPokemonDesconocido").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        redPokemonImage = new JLabel(new ImageIcon(new ImageIcon("src/images/pokemonsImages/imagenPokemonDesconocido").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        bluePokemonImage.setHorizontalAlignment(SwingConstants.CENTER);
        redPokemonImage.setHorizontalAlignment(SwingConstants.CENTER);

        //Barra de HP
        blueHpBar = new BarHpPokemonPanel(0, 100);
        redHpBar = new BarHpPokemonPanel(0, 100);

        //Botones
        attack1Blue = new JButton("Ataque 1");
        attack2Blue = new JButton("Ataque 2");
        attack3Blue = new JButton("Ataque 3");

        attack1Red = new JButton("Ataque 1");
        attack2Red = new JButton("Ataque 2");
        attack3Red = new JButton("Ataque 3");

        // Personaliza botones
        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        Color blueButtonColor = new Color(0xE3F2FD);
        Color redButtonColor = new Color(0xFFEBEE);

        attack1Blue.setBackground(blueButtonColor);
        attack2Blue.setBackground(blueButtonColor);
        attack3Blue.setBackground(blueButtonColor);
        attack1Blue.setFont(buttonFont);
        attack2Blue.setFont(buttonFont);
        attack3Blue.setFont(buttonFont);

        attack1Red.setBackground(redButtonColor);
        attack2Red.setBackground(redButtonColor);
        attack3Red.setBackground(redButtonColor);
        attack1Red.setFont(buttonFont);
        attack2Red.setFont(buttonFont);
        attack3Red.setFont(buttonFont);

        // Panel izquierdo (Pokémon azul)
        JPanel bluePokemonPanel = new JPanel();
        bluePokemonPanel.setOpaque(false);
        bluePokemonPanel.setLayout(new BoxLayout(bluePokemonPanel, BoxLayout.Y_AXIS));
        bluePokemonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        bluePokemonLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bluePokemonImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        bluePokemonHpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        blueHpBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        bluePokemonPanel.add(Box.createVerticalStrut(10));
        bluePokemonPanel.add(bluePokemonLabel);
        bluePokemonPanel.add(Box.createVerticalStrut(10));
        bluePokemonPanel.add(bluePokemonImage);
        bluePokemonPanel.add(Box.createVerticalStrut(10));
        bluePokemonPanel.add(bluePokemonHpLabel); // HP debajo del Pokémon
        bluePokemonPanel.add(Box.createVerticalStrut(5));

        // Panel para centrar la barra de vida azul
        JPanel blueHpBarWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        blueHpBarWrapper.setOpaque(false);
        blueHpBarWrapper.add(blueHpBar);

        bluePokemonPanel.add(blueHpBarWrapper);
        bluePokemonPanel.add(Box.createVerticalStrut(10));

        // Panel derecho (Pokémon rojo)
        JPanel redPokemonPanel = new JPanel();
        redPokemonPanel.setOpaque(false);
        redPokemonPanel.setLayout(new BoxLayout(redPokemonPanel, BoxLayout.Y_AXIS));
        redPokemonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        redPokemonLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        redPokemonImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        redPokemonHpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        redHpBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        redPokemonPanel.add(Box.createVerticalStrut(10));
        redPokemonPanel.add(redPokemonLabel);
        redPokemonPanel.add(Box.createVerticalStrut(10));
        redPokemonPanel.add(redPokemonImage);
        redPokemonPanel.add(Box.createVerticalStrut(10));
        redPokemonPanel.add(redPokemonHpLabel); // HP debajo del Pokémon
        redPokemonPanel.add(Box.createVerticalStrut(5));

        // Panel para centrar la barra de vida roja
        JPanel redHpBarWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        redHpBarWrapper.setOpaque(false);
        redHpBarWrapper.add(redHpBar);

        redPokemonPanel.add(redHpBarWrapper);
        redPokemonPanel.add(Box.createVerticalStrut(10));

        // Añadir elementos a los paneles correspondientes
        leftLabelsPanel.add(blueTrainerLabel);
        leftLabelsPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(leftLabelsPanel, BorderLayout.NORTH);
        leftPanel.add(bluePokemonPanel, BorderLayout.CENTER);
        blueButtonsPanel.add(attack1Blue);
        blueButtonsPanel.add(attack2Blue);
        blueButtonsPanel.add(attack3Blue);
        leftPanel.add(blueButtonsPanel, BorderLayout.SOUTH);

        rightLabelsPanel.add(redTrainerLabel);
        rightLabelsPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(rightLabelsPanel, BorderLayout.NORTH);
        rightPanel.add(redPokemonPanel, BorderLayout.CENTER);
        redButtonsPanel.add(attack1Red);
        redButtonsPanel.add(attack2Red);
        redButtonsPanel.add(attack3Red);
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

    // Fondo degradado
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(0, 0, new Color(0xB2DFDB), 0, getHeight(), new Color(0xE1BEE7));
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    // Habilita o deshabilita los botones según el turno
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
    public void updateHpLabels(short hpBlue, short hpRed, short hpInitialBlue, short hpInitialRed) {
        bluePokemonHpLabel.setText("HP = " + hpBlue  + "/" + hpInitialBlue);
        redPokemonHpLabel.setText("HP = " + hpRed + "/" + hpInitialRed);
        blueHpBar.setMaxHp(hpInitialBlue);
        redHpBar.setMaxHp(hpInitialRed);
        blueHpBar.updateHp(hpBlue);
        redHpBar.updateHp(hpRed);
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
        bluePokemonLabel.setText(name);
    }

    public void setRedPokemonName(String name) {
        redPokemonLabel.setText(name);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void isBlueTurn(boolean isBlueTurn) {
        this.isBlueTurn = isBlueTurn;
    }

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
        controller.winner();

        try {
            controller.checkAlivePokemon();
        } catch (SeleccionInvalidaException e1) {
       
            JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }

        isBlueTurn = controller.nextTurn();
        updateButtonStates();
    }

    //Metodo para actualizar indices y determinar quien empieza
    public void setPokemonIndexes(boolean turn) {
        isBlueTurn = turn;
        updateButtonStates();
    }
}