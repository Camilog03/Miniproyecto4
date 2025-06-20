package src.controller;

import src.model.BattleManager;
import src.model.exceptions.SeleccionInvalidaException;
import src.view.View;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    // Inicia una nueva partida con los nombres de los entrenadores
    public void newGame(String trainerBlueName,  String trainerRedName) throws SeleccionInvalidaException{
        if (trainerBlueName.isEmpty() || trainerRedName.isEmpty()){
            throw new SeleccionInvalidaException("Debes llenar ambos campos!");
        }
        this.battleManager = new BattleManager(trainerBlueName, trainerRedName);
    }

    public void goToPanel2() throws SeleccionInvalidaException{

        view.showPanel2(battleManager.getBlueTrainerName(), battleManager.getRedTrainerName(), battleManager.getNamesBlue(), battleManager.getNamesRed(), battleManager.getAlivesBlue(), battleManager.getAlivesRed());

    }

    // Inicia la batalla con los pokemons seleccionados por los entrenadores
    public void goToPanel3(String pokemonBlue, String pokemonRed) throws SeleccionInvalidaException{ 
        
        if (pokemonBlue.isBlank() ||  pokemonRed.isBlank()){
            throw new SeleccionInvalidaException("Debes seleccionar un pokemon para ambos entrenadores!.");
        }
        battleManager.startBattle(pokemonBlue, pokemonRed);

        boolean turn = battleManager.getTurn();

        Queue<String> blueAttacks = battleManager.getAttacksBlue();
        Queue<String> redAttacks = battleManager.getAttacksRed();


        view.updateHP(battleManager.getHPBluePokemon(), battleManager.getHPRedPokemon(), battleManager.getHPInitialBluePokemon(), battleManager.getHPInitialRedPokemon());

        view.showPanel3("Inicia el entrenador " + (turn?"AZUL":"ROJO") , battleManager.getBlueTrainerName(), battleManager.getRedTrainerName(), pokemonBlue,
                pokemonRed, battleManager.getPathBluePokemon(), battleManager.getPathRedPokemon() ,blueAttacks, redAttacks, turn);
    }

    // Cambia el turno de batalla
    public boolean nextTurn() {
        battleManager.nextTurn();
        return battleManager.getTurn();
    }

    // Obtiene el historial de acciones de la batalla
    public Stack<String> getActionHistory() {
        return battleManager.getActionHistory();
    }

    // Verifica si los pokemons están vivos, si no lo están, muestra un mensaje y vuelve al panel 2
    public void checkAlivePokemon() throws SeleccionInvalidaException{
        if (!battleManager.getBluePokemonStatus() || !battleManager.getRedPokemonStatus()) {
            String deadPokemon = battleManager.getBluePokemonStatus() ?  battleManager.getRedPokemonName():battleManager.getBluePokemonName();

            view.showMessage(deadPokemon + " ya no puede continuar...\nAcepta para volver al menú.");
            winner();
            battleManager.updatePokemonsAlives();
            view.clearActionHistory(); // <-- Limpia el historial en la vista
            goToPanel2();
        }
        
    }
    
    //Actualiza la vida de los pokemons en la vista
    public void updateHP(){
        view.updateHP(battleManager.getHPBluePokemon(), battleManager.getHPRedPokemon(), battleManager.getHPInitialBluePokemon(), battleManager.getHPInitialRedPokemon());
    }

    //Muestra daños de cada pokemon y los carga en el historial de batalla
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

    //Muestra ganador y termina el juego
    public void winner(){
        String result = battleManager.hasWinner();
        if(!result.isBlank()){
            view.showMessage(result);
            System.exit(0);
        }
    }

    // Vuelve al panel 1
    public void goToPanel1(){
        view.showPanel1();
    }

    //Cargar juego
    public void uploadGame(String path) throws IOException, FileNotFoundException, ClassNotFoundException, SeleccionInvalidaException{
       
        this.battleManager = BattleManager.loadGame(path);
        goToPanel2();
        
    }

    //Guardar juego
    public void saveGame(String path) throws IOException, FileNotFoundException{
    
        battleManager.saveGame(path);
        view.showMessage("Partida guardada exitosamente.");
        
    }
}