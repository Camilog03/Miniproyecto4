package src.model.actions;

import src.model.pokemons.Type;

public class Attack {
    private final String name;
    private final Type typeOfDamage;
    private final short attackPower;
    private final boolean special;

    //Constructor
    public Attack(String name, Type typeOfDamage, short attackPower, boolean special) {
        this.name = name;
        this.typeOfDamage = typeOfDamage;
        this.attackPower = attackPower;
        this.special = special;
    }

    //Setters and Getters

    public boolean isSpecial() {
        return special;
    }

    public String getName() {
        return name;
    }

    public Type getTypeOfDamage() {
        return typeOfDamage;
    }

    public short getAttackPower() {
        return attackPower;
    }

    //toString method to print the object on the console
    @Override
    public String toString() {return name + " - DAM: " + attackPower + " - " + (special ? " - " + typeOfDamage : "BASICO");}
}



