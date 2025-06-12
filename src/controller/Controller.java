package src.controller;

import src.model.BattleManager;
import src.model.characters.Trainer;
import src.model.exceptions.SeleccionInvalidaException;
import src.model.pokemons.Pokemon;
import src.view.View;
import javax.swing.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Controller {

    private BattleManager battleManager;
    private View view;

    public Controller(View view) {
        this.view = view;
        this.view.setController(this);
    }

    public void start(){
        view.showPanel1();
    }

    public void newGame(String trainerBlueName,  String trainerRedName) throws SeleccionInvalidaException{
        if (trainerBlueName.isEmpty() || trainerRedName.isEmpty()){
            throw new SeleccionInvalidaException("Debes llenar ambos campos!");
        }
        this.battleManager = new BattleManager(trainerBlueName, trainerRedName);
    }

    public void goToPanel2() throws SeleccionInvalidaException{

        view.showPanel2(battleManager.getBlueTrainerName(), battleManager.getRedTrainerName(), battleManager.getNamesBlue(), battleManager.getNamesRed(), battleManager.getAlivesBlue(), battleManager.getAlivesRed());

    }

    public void goToPanel3(String pokemonBlue, String pokemonRed) throws SeleccionInvalidaException{ 
        
        if (pokemonBlue.isBlank() ||  pokemonRed.isBlank()){
            throw new SeleccionInvalidaException("Debes seleccionar un pokemon para ambos entrenadores!.");
        }
        battleManager.startBattle(pokemonBlue, pokemonRed);

        boolean turn = battleManager.getTurn();

        Queue<String> blueAttacks = battleManager.getAttacksBlue();
        Queue<String> redAttacks = battleManager.getAttacksRed();


        view.updateHP(battleManager.getHPBluePokemon(), battleManager.getHPRedPokemon(), battleManager.getHPInitialBluePokemon(), battleManager.getHPInitialRedPokemon());

        view.showPanel3("Inicia el entrandor " + (turn?"AZUL":"ROJO") , battleManager.getBlueTrainerName(), battleManager.getRedTrainerName(), pokemonBlue,
                pokemonRed, battleManager.getPathBluePokemon(), battleManager.getPathRedPokemon() ,blueAttacks, redAttacks, turn);
    }

    public boolean nextTurn() {
        battleManager.nextTurn();
        return battleManager.getTurn();
    }

    public void checkAlivePokemon() throws SeleccionInvalidaException{
        if (!battleManager.getBluePokemonStatus() || !battleManager.getRedPokemonStatus()) {
            String deadPokemon = battleManager.getBluePokemonStatus() ?  battleManager.getRedPokemonName():battleManager.getBluePokemonName();

            view.showMessage(deadPokemon + " ya no puede continuar...\nAcepta para volver al menú.");
            winner();
            battleManager.updatePokemonsAlives();
            goToPanel2();
        }
        
    }

    public void updateHP(){
        view.updateHP(battleManager.getHPBluePokemon(), battleManager.getHPRedPokemon(), battleManager.getHPInitialBluePokemon(), battleManager.getHPInitialRedPokemon());
    }

    public void blueMakeDamage(byte indexAttack){
        view.showMessage(battleManager.bluePokemonAttack(indexAttack));
        updateHP();
    }

    public void redMakeDamage(byte indexAttack){
        view.showMessage(battleManager.redPokemonAttack(indexAttack));
        updateHP();
    }

    public void winner(){
        String result = battleManager.hasWinner();
        if(!result.isBlank()){
            view.showMessage(result);
            System.exit(0);
        }
    }

    public void goToPanel1(){
        view.showPanel1();
    }

    public void uploadGame(String path) throws IOException, FileNotFoundException, ClassNotFoundException, SeleccionInvalidaException{
       
        this.battleManager = BattleManager.loadGame(path);
        goToPanel2();
        
    }

    public void saveGame(String path) throws IOException, FileNotFoundException{
    
        battleManager.saveGame(path);
        view.showMessage("Partida guardada exitosamente.");
        
    }
}