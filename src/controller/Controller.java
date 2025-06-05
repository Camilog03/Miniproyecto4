package src.controller;

import src.model.characters.Trainer;
import src.model.pokemons.Pokemon;
import src.view.Terminal.Terminal;
import src.view.View;
import java.util.LinkedList;
import java.util.Queue;

public class Controller {

    private View view;
    private Trainer trainerBlue;
    private Trainer trainerRed;
    private Queue<String> namesBlue;
    private Queue<String> namesRed;
    private Queue<Boolean> alivesBlue;
    private Queue<Boolean> alivesRed;
    private byte indexPokemonBlue;
    private byte indexPokemonRed;
    private boolean isGui;

    public Controller(View view,  Trainer trainerBlue, Trainer trainerRed, boolean isGui) {
        this.view = view;
        this.trainerBlue = trainerBlue;
        this.trainerRed = trainerRed;
        this.isGui = isGui;

        //Random pokemons
        this.trainerBlue.randomPokemon();
        this.trainerRed.randomPokemon();
        this.view.setController(this);
    }

    public void start(){
        view.showPanel1();
    }

    public void setTrainersNames(String trainerBlueName,  String trainerRedName) {
        trainerBlue.setTrainerName(trainerBlueName);
        trainerRed.setTrainerName(trainerRedName);
        System.out.println(trainerRed.getTrainerName());
        System.out.println(trainerBlue.getTrainerName());
    }

    public void goToPanel2(){

        //Colas de nombres de los pokemones

        namesBlue = new LinkedList<>();
        namesBlue.add(trainerBlue.getSelectPokemonslist().get(0).getName());
        namesBlue.add(trainerBlue.getSelectPokemonslist().get(1).getName());
        namesBlue.add(trainerBlue.getSelectPokemonslist().get(2).getName());

        namesRed = new LinkedList<>();
        namesRed.add(trainerRed.getSelectPokemonslist().get(0).getName());
        namesRed.add(trainerRed.getSelectPokemonslist().get(1).getName());
        namesRed.add(trainerRed.getSelectPokemonslist().get(2).getName());

        //Colas de estado del los pokemones

        alivesBlue = new LinkedList<>();
        alivesBlue.add(trainerBlue.getSelectPokemonslist().get(0).isAlive());
        alivesBlue.add(trainerBlue.getSelectPokemonslist().get(1).isAlive());
        alivesBlue.add(trainerBlue.getSelectPokemonslist().get(2).isAlive());

        alivesRed = new LinkedList<>();
        alivesRed.add(trainerRed.getSelectPokemonslist().get(0).isAlive());
        alivesRed.add(trainerRed.getSelectPokemonslist().get(1).isAlive());
        alivesRed.add(trainerRed.getSelectPokemonslist().get(2).isAlive());

        view.showPanel2(trainerBlue.getTrainerName(), trainerRed.getTrainerName(), namesBlue, namesRed, alivesBlue, alivesRed);

    }

    public void goToPanel3(byte indexBlue, byte indexRed){
        this.indexPokemonBlue =  indexBlue;
        this.indexPokemonRed =  indexRed;

        boolean turn = trainerBlue.getSelectedPokemon(indexBlue).getSpeed() > trainerRed.getSelectedPokemon(indexRed).getSpeed();

        Queue<String> blueAttacks = new LinkedList<>();
        Queue<String> redAttacks = new LinkedList<>();

        blueAttacks.add(trainerBlue.getSelectedPokemon(indexBlue).getAttacksInstance().get(0).getName());
        blueAttacks.add(trainerBlue.getSelectedPokemon(indexBlue).getAttacksInstance().get(1).getName());
        blueAttacks.add(trainerBlue.getSelectedPokemon(indexBlue).getAttacksInstance().get(2).getName());

        redAttacks.add(trainerRed.getSelectedPokemon(indexRed).getAttacksInstance().get(0).getName());
        redAttacks.add(trainerRed.getSelectedPokemon(indexRed).getAttacksInstance().get(1).getName());
        redAttacks.add(trainerRed.getSelectedPokemon(indexRed).getAttacksInstance().get(2).getName());

        view.updateHP(trainerBlue.getSelectedPokemon(indexBlue).getHp(), trainerRed.getSelectedPokemon(indexRed).getHp());

        view.showPanel3("Inicia el entrandor " + (turn?"AZUL":"ROJO") ,trainerBlue.getTrainerName(),trainerRed.getTrainerName(), trainerBlue.getSelectedPokemon(indexBlue).getName(),
                trainerRed.getSelectedPokemon(indexRed).getName(), trainerBlue.getSelectedPokemon(indexBlue).getPath(), trainerRed.getSelectedPokemon(indexRed).getPath() ,blueAttacks, redAttacks, turn);
    }

    public void checkAlivePokemon(){
        if (!trainerBlue.getSelectedPokemon(indexPokemonBlue).isAlive() ||
                !trainerRed.getSelectedPokemon(indexPokemonRed).isAlive()) {
            String deadPokemon = trainerBlue.getSelectedPokemon(indexPokemonBlue).isAlive() ? trainerRed.getSelectedPokemon(indexPokemonRed).getName()
                    : trainerBlue.getSelectedPokemon(indexPokemonBlue).getName();

            view.showMessage(deadPokemon + " ya no puede continuar...\nAcepta para volver al menú.");
            winner();
            goToPanel2();
        }
    }

    public void updateHP(){
        view.updateHP(trainerBlue.getSelectedPokemon(indexPokemonBlue).getHp(), trainerRed.getSelectedPokemon(indexPokemonRed).getHp());
    }

    public void blueMakeDamage(byte indexAttack){
        trainerBlue.getSelectedPokemon(indexPokemonBlue).doAttack(trainerRed.getSelectedPokemon(indexPokemonRed), indexAttack);
        view.showMessage(trainerRed.getSelectedPokemon(indexPokemonRed).getName() + " recibio " + trainerBlue.getSelectedPokemon(indexPokemonBlue).getDamageMadeIt() + " puntos de daño");
        updateHP();
    }

    public void redMakeDamage(byte indexAttack){
        trainerRed.getSelectedPokemon(indexPokemonRed).doAttack(trainerBlue.getSelectedPokemon(indexPokemonBlue), indexAttack);
        view.showMessage(trainerBlue.getSelectedPokemon(indexPokemonBlue).getName() + " recibio " + trainerRed.getSelectedPokemon(indexPokemonRed).getDamageMadeIt() + " puntos de daño");
        updateHP();
    }

    public void winner(){
        byte counterBlue = 0;
        byte counterRed = 0;
        for(Pokemon po:trainerBlue.getSelectPokemonslist()){
            if(!po.isAlive()){
                counterBlue++;
            }
        }
        for(Pokemon po:trainerRed.getSelectPokemonslist()){
            if(!po.isAlive()){
                counterRed++;
            }
        }
        if(counterBlue == 3 || counterRed == 3){
            view.showMessage("Gana el entrenador "+ (counterBlue==3?"Rojo: "+trainerRed.getTrainerName():"Azul: "+trainerBlue.getTrainerName()) +
                    "\nFelicidades!!!!!!"+
                    "\nEl juego ha FINALIZADO...");
            System.exit(0);
        }
    }

    public void goToPanel1(){
        view.showPanel1();
    }

    public void changeView(){
        if(isGui){
            view.disable();
            view = new Terminal(); 
        } else {
            view = new src.view.Gui.Gui();
            view.showPanel1();
        }
        isGui = !isGui;
        view.setController(this);
        goToPanel2();


    }


}
