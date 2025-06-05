package src;

import src.controller.Controller;
import src.model.characters.Trainer;
import src.view.Gui.Gui;
import src.view.Terminal.Terminal;

public class App {
    public static void main(String[] args) {
        Trainer trainerBlue = new Trainer("");
        Trainer trainerRed = new Trainer("");


        Controller controller = new Controller(new Terminal(), trainerBlue, trainerRed, false);
        //Controller controller = new Controller(new Gui(), trainerBlue, trainerRed, true);
        controller.start();

    }
}


