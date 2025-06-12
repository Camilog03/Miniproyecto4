package src.controller;

import src.model.BattleManager;
import src.model.characters.Trainer;
import src.model.pokemons.Pokemon;
import src.view.View;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

    public void newGame(String trainerBlueName,  String trainerRedName) {
        this.battleManager = new BattleManager(trainerBlueName, trainerRedName);
    }

    public void goToPanel2(){

        view.showPanel2(battleManager.getBlueTrainerName(), battleManager.getRedTrainerName(), battleManager.getNamesBlue(), battleManager.getNamesRed(), battleManager.getAlivesBlue(), battleManager.getAlivesRed());

    }

    public void goToPanel3(String pokemonBlue, String pokemonRed){

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

    public Stack<String> getActionHistory() {
        return battleManager.getActionHistory();
    }

    public void checkAlivePokemon(){
        if (!battleManager.getBluePokemonStatus() || !battleManager.getRedPokemonStatus()) {
            String deadPokemon = battleManager.getBluePokemonStatus() ?  battleManager.getRedPokemonName():battleManager.getBluePokemonName();

            view.showMessage(deadPokemon + " ya no puede continuar...\nAcepta para volver al menú.");
            winner();
            battleManager.updatePokemonsAlives();
            view.clearActionHistory(); // <-- Limpia el historial en la vista
            goToPanel2();
        }
    }

    public void updateHP(){
        view.updateHP(battleManager.getHPBluePokemon(), battleManager.getHPRedPokemon(), battleManager.getHPInitialBluePokemon(), battleManager.getHPInitialRedPokemon());
    }

    public void blueMakeDamage(byte indexAttack){
        view.showMessage(battleManager.bluePokemonAttack(indexAttack));
        updateHP();
        view.updateActionHistory(getActionHistory());
    }

    public void redMakeDamage(byte indexAttack){
        view.showMessage(battleManager.redPokemonAttack(indexAttack));
        updateHP();
        view.updateActionHistory(getActionHistory());
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

    public void uploadGame(String path) {
        try {
            this.battleManager = BattleManager.loadGame(path);
            goToPanel2();
        } catch (Exception e) {
            view.showMessage("Error al cargar partida: " + e.getMessage());
        }
    }

    public void saveGame(String path) {
        try {
            battleManager.saveGame(path);
            view.showMessage("Partida guardada exitosamente.");
        } catch (Exception e) {
            view.showMessage("Error al guardar la partida: " + e.getMessage());
        }
    }
}
