package src;

import src.controller.Controller;
import src.view.Gui.Gui;

public class App {
    public static void main(String[] args) {

        Controller controller = new Controller(new Gui());
        controller.start();

    }
}


