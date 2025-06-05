package src.view.Terminal;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import src.controller.Controller;
import src.view.View;

public class Terminal implements View {
    private Scanner scanner = new Scanner(System.in);
    private Controller controller;
    private short hpBlue, hpRed;

    @Override
    public void showPanel1() {
        String trainerBlue, trainerRed;
        //Intro
        System.out.println("¡Bienvenido al mundo Pokémon, entrenador!");
        System.out.println("¡Prepárate para una aventura llena de batallas épicas! \nPor favor, ingresa los nombres de los entrenadores que se enfrentarán.");


        //Creating trainers
        System.out.print("Nombre del Entrenador 1: ");
        trainerBlue = scanner.nextLine();
        System.out.print("Nombre del Entrenador 2: ");
        trainerRed = scanner.nextLine();


        controller.setTrainersNames(trainerBlue, trainerRed);
        controller.goToPanel2();
    }

    @Override
    public void showPanel2(String blueTrainerName, String redTrainerName, Queue<String> namesBlue,
            Queue<String> namesRed, Queue<Boolean> aliveBlue, Queue<Boolean> aliveRed) {

        System.out.println("Deseas cambiar a vista Gui? (S/N)");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("S")) {
            controller.changeView();
            return;
        }

        byte indexBlue, indexRed;

        System.out.println("------ Pokemones Entrenador Azul " + blueTrainerName + "-------");
        byte counterBlue = 1,  counterRed = 1;
        while(!namesBlue.isEmpty()){
            System.out.println(counterBlue + ". "+ namesBlue.poll() + " - "+ (aliveBlue.poll() ? "Vivo":"Derrotado"));
            counterBlue++;
        }

        System.out.print("Ingrese su pokemon:");
        indexBlue = scanner.nextByte();
        scanner.nextLine();

        System.out.println("------ Pokemones Entrenador Rojo " + redTrainerName + "-------");
        while(!namesRed.isEmpty()){
            System.out.println(counterRed + ". " + namesRed.poll() + " - "+ (aliveRed.poll() ? "Vivo":"Derrotado"));
            counterRed++;
        }
        System.out.print("Ingrese su pokemon:");
        indexRed = scanner.nextByte();
        scanner.nextLine();
        indexBlue--;
        indexRed--;
        

        controller.goToPanel3(indexBlue, indexRed);

    }

    @Override
    public void showPanel3(String menssageStart, String blueTrainerName, String redTrainerName, String bluePokemonName, String redPokemonName, String bluePath, String redPath, Queue<String> blueAttacks, Queue<String> redAttacks, boolean turn) {
        
        showMessage(menssageStart);

        System.out.println("\n\n------ ¡Comienza la Batalla! ------");
        while (hpBlue > 0 && hpRed > 0) {
            ArrayList<String> blueAttacksCopy = new ArrayList<>(blueAttacks);
            ArrayList<String> redAttacksCopy = new ArrayList<>(redAttacks);

            byte indexAttack = -1;

            String attacker = (turn ? bluePokemonName:redPokemonName);
            String defender = (!turn ? bluePokemonName:redPokemonName);

            System.out.println("------ Ataques ------");
            byte counter = 1;
            if (turn) {
                for(String attack :  blueAttacksCopy){
                    System.out.println(counter + ". " + attack);
                    counter++;
                }
            }else{
                for(String attack :  redAttacksCopy){
                    System.out.println(counter + ". " + attack);
                    counter++;
                }
            }
            System.out.print("Selecciona tu ataque: ");
            indexAttack = scanner.nextByte() ;
            scanner.nextLine();
            indexAttack--;

            if (turn) {
                controller.blueMakeDamage(indexAttack);
            }else{
                controller.redMakeDamage(indexAttack);
            }
            System.out.println(defender + " - " +(turn ? hpBlue:hpRed) + "HP");
            //Change the attacker and defender
            turn = !turn;
           
        }
        controller.checkAlivePokemon();
    }

    @Override
    public void updateHP(short hpBlue, short hpRed) {
        this.hpBlue = hpBlue;
        this.hpRed = hpRed;
    }

    @Override
    public void showMessage(String msg) {
        System.out.println(msg);
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void disable() {}
}