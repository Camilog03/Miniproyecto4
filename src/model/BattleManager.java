package src.model;

import src.model.actions.Attack;
import src.model.characters.Trainer;

import src.model.pokemons.Pokemon;

import java.io.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BattleManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private Trainer trainerBlue;
    private Trainer trainerRed;
    private Pokemon pokemonBlue;
    private Pokemon pokemonRed;
    private LinkedList<Pokemon> turnOrder;
    private Stack<String> actionHistory;

    private Queue<String> namesBlue;

    private Queue<String> namesRed;
    private Queue<Boolean> alivesBlue;
    private Queue<Boolean> alivesRed;
    // Constructor para nueva partida

    public BattleManager(String blueTrainerName, String redTrainerName) {
        this.trainerBlue = new Trainer(blueTrainerName);
        this.trainerRed = new Trainer(redTrainerName);

        this.trainerBlue.randomPokemon();
        this.trainerRed.randomPokemon();

        this.turnOrder = new LinkedList<>();
        this.actionHistory = new Stack<>();

        this.namesBlue = new LinkedList<>();
        this.namesRed = new LinkedList<>();

        this.alivesBlue = new LinkedList<>();
        this.alivesRed = new LinkedList<>();

        for (String name : trainerBlue.getTeam().keySet()) {
            this.namesBlue.add(name);
        }

        for (String name : trainerRed.getTeam().keySet()) {
            this.namesRed.add(name);
        }

        for(String name : trainerBlue.getTeam().keySet()) {
            this.alivesBlue.add(trainerBlue.getTeam().get(name).isAlive());
        }

        for(String name : trainerRed.getTeam().keySet()) {
            this.alivesRed.add(trainerRed.getTeam().get(name).isAlive());
        }
    }

    // Constructor para cargar partida desde datos restaurados

    public BattleManager(Trainer blue, Trainer red, LinkedList<Pokemon> turnOrder, Stack<String> actionHistory) {
        this.trainerBlue = blue;
        this.trainerRed = red;
        this.turnOrder = turnOrder;
        this.actionHistory = actionHistory;

        this.namesBlue = new LinkedList<>();
        this.namesRed = new LinkedList<>();


        for (String name : trainerBlue.getTeam().keySet()) {
            this.namesBlue.add(name);
        }

        for (String name : trainerRed.getTeam().keySet()) {
            this.namesRed.add(name);
        }

        this.alivesBlue = new LinkedList<>();
        this.alivesRed = new LinkedList<>();

        for(String name : trainerBlue.getTeam().keySet()) {
            this.alivesBlue.add(trainerBlue.getTeam().get(name).isAlive());
        }

        for(String name : trainerRed.getTeam().keySet()) {
            this.alivesRed.add(trainerRed.getTeam().get(name).isAlive());
        }
    }

    public void startBattle(String pokemonBlue, String pokemonRed) {
        turnOrder.clear();
        this.pokemonBlue = trainerBlue.getTeam().get(pokemonBlue);
        this.pokemonRed = trainerRed.getTeam().get(pokemonRed);

        turnOrder.add(this.pokemonBlue);
        turnOrder.add(this.pokemonRed);
        turnOrder.sort((a,b) -> b.getSpeed() - a.getSpeed());

        actionHistory.clear(); // <-- Limpia el historial al iniciar batalla
    }

    public void updatePokemonsAlives(){
        for(String name : trainerBlue.getTeam().keySet()) {
            this.alivesBlue.add(trainerBlue.getTeam().get(name).isAlive());
        }

        for(String name : trainerRed.getTeam().keySet()) {
            this.alivesRed.add(trainerRed.getTeam().get(name).isAlive());
        }
    }

    public void nextTurn() {
        turnOrder.addLast(turnOrder.removeFirst());
    }

    public String bluePokemonAttack(byte indexAttack){
        
        pokemonBlue.doAttack(pokemonRed, indexAttack);
        
        String message = pokemonBlue.getName() + " uso " + pokemonBlue.getAttacksInstance().get(indexAttack).getName() + " sobre " + pokemonRed.getName() + " causando " + pokemonBlue.getDamageMadeIt() + " puntos de daño.";
        actionHistory.push(message);
        return message;
    }

    public String redPokemonAttack(byte indexAttack){
        
        pokemonRed.doAttack(pokemonBlue, indexAttack);
        
        String message = pokemonRed.getName() + " uso " + pokemonRed.getAttacksInstance().get(indexAttack).getName() + " sobre " + pokemonBlue.getName() + " causando " + pokemonRed.getDamageMadeIt() + " puntos de daño.";
        actionHistory.push(message);
        return message;
    }

    public String hasWinner(){
        byte countBlue = 0;
        byte countRed = 0;

        for(String name : trainerBlue.getTeam().keySet()) {
            if(!trainerBlue.getTeam().get(name).isAlive()){
                countBlue++;
            }
        }
        for(String name : trainerRed.getTeam().keySet()) {
            if(!trainerRed.getTeam().get(name).isAlive()){
                countRed++;
            }
        }
        if(countBlue == 3 ||  countRed == 3){
            return "Gana el entrenador" + ((countBlue == 3)?(" Rojo - " + trainerRed.getTrainerName()):(" Azul - " + trainerBlue.getTrainerName()));
        }
        return "";
    }

    //Funcion de clase para guardar la batalla
    public void saveGame(String path) throws IOException {
        updatePokemonsAlives();
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))){
            out.writeObject(this);
        }
    }

    //Funcion estatica que sirve para cargar batallas
    public static BattleManager loadGame(String path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
            return (BattleManager) in.readObject();
        }
    }
    public boolean getTurn(){
        return turnOrder.getFirst() == pokemonBlue;
    }

    public Queue<String> getNamesBlue() {
        return new LinkedList<>(namesBlue);
    }

    public void setNamesBlue(Queue<String> namesBlue) {
        this.namesBlue = namesBlue;
    }

    public Queue<String> getNamesRed() {
        return new LinkedList<>(namesRed);
    }

    public void setNamesRed(Queue<String> namesRed) {
        this.namesRed = namesRed;
    }

    public Queue<Boolean> getAlivesBlue() {
        return alivesBlue;
    }

    public void setAlivesBlue(Queue<Boolean> alivesBlue) {
        this.alivesBlue = alivesBlue;
    }

    public Queue<Boolean> getAlivesRed() {
        return alivesRed;
    }

    public void setAlivesRed(Queue<Boolean> alivesRed) {
        this.alivesRed = alivesRed;
    }

    public String getBlueTrainerName(){
        return trainerBlue.getTrainerName();
    }

    public String getRedTrainerName(){
        return trainerRed.getTrainerName();
    }

    public Queue<String> getAttacksBlue() {
        Queue<String> attacks = new LinkedList<>();
        for(Attack attackName : pokemonBlue.getAttacksInstance()){
            attacks.add(attackName.toString());
        }
        return attacks;
    }

    public Queue<String> getAttacksRed() {
        Queue<String> attacks = new LinkedList<>();
        for(Attack attackName :  pokemonRed.getAttacksInstance()){
            attacks.add(attackName.toString());
        }
        return attacks;
    }

    public short getHPBluePokemon() {
        return pokemonBlue.getHp();
    }

    public short getHPRedPokemon() {
        return pokemonRed.getHp();
    }

    public String getPathBluePokemon() {
        return pokemonBlue.getPath();
    }

    public String getPathRedPokemon() {
        return pokemonRed.getPath();
    }

    public boolean getBluePokemonStatus() {
        return  pokemonBlue.isAlive();
    }

    public boolean getRedPokemonStatus() {
        return  pokemonRed.isAlive();
    }

    public String getBluePokemonName() {
        return pokemonBlue.getName();
    }

    public String getRedPokemonName() {
        return pokemonRed.getName();
    }

    public short getHPInitialBluePokemon() {
        return pokemonBlue.getHpInitial();
    }

    public short getHPInitialRedPokemon() {
        return pokemonRed.getHpInitial();
    }

    public Stack<String> getActionHistory() {
        return (Stack<String>) actionHistory.clone();
    }
}
