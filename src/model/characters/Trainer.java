package src.model.characters;

import java.util.*;

import src.model.pokemons.*;


public class Trainer {

    private String trainerName;
    private static final byte MAX_POKEMONS = 3;
    private ArrayList<Pokemon> selectPokemonslist = new ArrayList<Pokemon>();


    public Trainer(String trainerName) {
        this.trainerName = trainerName;
    }
    //Getters and setters
    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {this.trainerName = trainerName;}

    public ArrayList<Pokemon> getSelectPokemonslist() {
        return selectPokemonslist;
    }

    public Pokemon getSelectedPokemon(byte index) {
        return selectPokemonslist.get(index);
    }

    public static byte getMaxPokemon() {
        return MAX_POKEMONS;
    }

    //Random pokemons

    public void randomPokemon(){
        ArrayList<Pokemon> randomPokemons = new ArrayList<>();

        // 🔥 FIRE
        randomPokemons.add(new FirePokemon("Charmander", Type.FIRE, (short) 39, (short) 43, (short) 50, (short) 65, "src/images/pokemonsImages/fire/charmander.png"));
        randomPokemons.add(new FirePokemon("Vulpix", Type.FIRE, (short) 38, (short) 41, (short) 50, (short) 65, "src/images/pokemonsImages/fire/vulpix.png"));
        randomPokemons.add(new FirePokemon("Growlithe", Type.FIRE, (short) 55, (short) 50, (short) 45, (short) 60, "src/images/pokemonsImages/fire/growlithe.png"));

        // 💧 WATER
        randomPokemons.add(new WaterPokemon("Squirtle", Type.WATER, (short) 44, (short) 48, (short) 50, (short) 43, "src/images/pokemonsImages/water/squirtle.png"));
        randomPokemons.add(new WaterPokemon("Psyduck", Type.WATER, (short) 50, (short) 48, (short) 45, (short) 42, "src/images/pokemonsImages/water/psyduck.png"));
        randomPokemons.add(new WaterPokemon("Totodile", Type.WATER, (short) 50, (short) 60, (short) 50, (short) 43, "src/images/pokemonsImages/water/totodile.png"));

        // ⚡ ELECTRIC
        randomPokemons.add(new ElectricPokemon("Pikachu", Type.ELECTRIC, (short) 35, (short) 30, (short) 40, (short) 90, "src/images/pokemonsImages/electric/pikachu.png"));
        randomPokemons.add(new ElectricPokemon("Mareep", Type.ELECTRIC, (short) 55, (short) 40, (short) 50, (short) 35, "src/images/pokemonsImages/electric/mareep.png"));
        randomPokemons.add(new ElectricPokemon("Magnemite", Type.ELECTRIC, (short) 25, (short) 35, (short) 70, (short) 45, "src/images/pokemonsImages/electric/magnemite.png"));

        // 🌿 PLANT
        randomPokemons.add(new PlantPokemon("Bulbasaur", Type.PLANT, (short) 45, (short) 49, (short) 65, (short) 45, "src/images/pokemonsImages/plant/bulbasaur.png"));
        randomPokemons.add(new PlantPokemon("Bellsprout", Type.PLANT, (short) 50, (short) 35, (short) 45, (short) 60, "src/images/pokemonsImages/plant/bellsprout.png"));
        randomPokemons.add(new PlantPokemon("Treecko", Type.PLANT, (short) 40, (short) 35, (short) 50, (short) 70, "src/images/pokemonsImages/plant/treecko.png"));

        // 🪨 ROCK
        randomPokemons.add(new RockPokemon("Geodude", Type.ROCK, (short) 40, (short) 100, (short) 40, (short) 20, "src/images/pokemonsImages/rock/geodude.png"));
        randomPokemons.add(new RockPokemon("Onix", Type.ROCK, (short) 35, (short) 160, (short) 45, (short) 35, "src/images/pokemonsImages/rock/onix.png"));
        randomPokemons.add(new RockPokemon("Nosepass", Type.ROCK, (short) 30, (short) 135, (short) 50, (short) 30, "src/images/pokemonsImages/rock/nosepass.png"));

        // 🌍 GROUND
        randomPokemons.add(new GroundPokemon("Sandshrew", Type.GROUND, (short) 50, (short) 85, (short) 40, (short) 60, "src/images/pokemonsImages/ground/sandshreww.png"));
        randomPokemons.add(new GroundPokemon("Diglett", Type.GROUND, (short) 10, (short) 25, (short) 50, (short) 100, "src/images/pokemonsImages/ground/diglett.png"));
        randomPokemons.add(new GroundPokemon("Phanpy", Type.GROUND, (short) 90, (short) 60, (short) 45, (short) 40, "src/images/pokemonsImages/ground/phanpy.png"));

        // 🦅 FLYING
        randomPokemons.add(new FlyingPokemon("Pidgey", Type.FLYING, (short) 40, (short) 35, (short) 40, (short) 56, "src/images/pokemonsImages/flying/pidgey.png"));
        randomPokemons.add(new FlyingPokemon("Spearow", Type.FLYING, (short) 40, (short) 30, (short) 50, (short) 70, "src/images/pokemonsImages/flying/spearrow.png"));
        randomPokemons.add(new FlyingPokemon("Taillow", Type.FLYING, (short) 40, (short) 30, (short) 55, (short) 85, "src/images/pokemonsImages/flying/taillow.png"));

        selectPokemonslist.clear();

        Random rand = new Random();

        Set<Pokemon> setNotDuplicates = new HashSet<>();

        while (setNotDuplicates.size() < MAX_POKEMONS) {
            setNotDuplicates.add(randomPokemons.get(rand.nextInt(randomPokemons.size())));
        }

        selectPokemonslist.addAll(setNotDuplicates);

        for (Pokemon pokemon : selectPokemonslist) {
            for (int i = 0; i < Pokemon.getMaxAttacks(); i++){
                pokemon.selectAttacksRandom();
            }
        }

    }

}