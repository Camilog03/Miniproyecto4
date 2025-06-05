package src.model.pokemons;

import java.util.ArrayList;
import java.util.Random;

import src.model.actions.Attack;

public abstract class Pokemon implements Comparable<Pokemon> {

    //Attributes
    protected short damageMadeIt;
    private final String path;
    private final String name;
    private final Type type;
    protected static final byte MAXATTACKS = 4;
    private short hp;
    private short defenseFisic;
    private short defenseSpecial;
    private short speed;
    private boolean alive = true; //For default, it will always start as true
    protected ArrayList<Attack> attacksOfClass = new ArrayList<Attack>();


    protected ArrayList<Attack> attacksInstance = new ArrayList<Attack>(MAXATTACKS);


    @Override //Order in which any type of list will order the pokemons (it will order in ascending for their hp)

    public int compareTo(Pokemon other) {
        return Double.compare(this.hp, other.hp);

    }


    //Constructor
    public Pokemon(String name, Type type, short hp, short defenseFisic, short defenseSpecial, short speed, String path) {
        this.name = name;
        this.type = type;
        this.hp = hp;
        this.defenseFisic = defenseFisic;
        this.defenseSpecial = defenseSpecial;
        this.speed = speed;
        this.path = path;
    }

    //Setters and Getters

    public String getName() {
        return name;
    }
    public Type getType() {
        return type;
    }

    public short getHp() {
        return hp;
    }

    public void setHp(short hp) {
        this.hp = hp;
    }

    public short getDefenseFisic() {
        return defenseFisic;
    }

    public void setDefenseFisic(short defenseFisic) {
        this.defenseFisic = defenseFisic;
    }

    public short getDefenseSpecial() {
        return defenseSpecial;
    }

    public void setDefenseSpecial(short defenseSpecial) {
        this.defenseSpecial = defenseSpecial;
    }

    public short getSpeed() {
        return speed;
    }

    public void setSpeed(short speed) {
        this.speed = speed;
    }

    public static byte getMaxAttacks() {return MAXATTACKS;}

    public boolean isAlive() {return alive;}

    public String getPath() {
        return path;
    }

    public short getDamageMadeIt() {
        return damageMadeIt;}

    //Other methods

    //Attack method that need to be defined on each type of pokemon
    public abstract void doAttack(Pokemon oponentPokemon, byte indexAttackSelected);

    //Receive damage method
    public void receiveDamage(short damage){
        this.hp = (short) Math.max(0, this.hp - damage);
        if(this.hp <= 0){
            this.alive = false;
        }
    }

    //Random Attacks generator
    public void selectAttacksRandom(){
        Random random = new Random();
        int indexAttack;


        do{
            indexAttack = random.nextInt(attacksOfClass.size());
        }while(attacksInstance.contains(attacksOfClass.get(indexAttack)));

        attacksInstance.add(attacksOfClass.get(indexAttack));
    }

    public ArrayList<Attack> getAttacksInstance() {
        return attacksInstance;
    }

    //toString method to print the object on the console
    public String toString() {
        return name + "\tTipo:" + type + "\tHP:" + hp + "\tDEF FISICA:"  + "\tEstado: " + (alive ? "Vivo" : "Muerto");
    }
}
