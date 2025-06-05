package src.model.pokemons;

import src.model.actions.Attack;

public class FirePokemon extends Pokemon {

    //Constructor and somes attacks to the type of pokemon
    public FirePokemon(String name, Type type, short hp, short defenseFisic, short defenseSpecial, short speed, String path) {
        super(name, type, hp, defenseFisic, defenseSpecial, speed, path);

        //Add fisic attacks to attacks of class

        attacksOfClass.add(new Attack("Lanzallamas", Type.PHYSICAL, (short) 60, false));
        attacksOfClass.add(new Attack("Golpe Ígneo", Type.PHYSICAL, (short) 45, false));
        attacksOfClass.add(new Attack("Placaje de Fuego", Type.PHYSICAL, (short) 40, false));
        attacksOfClass.add(new Attack("Tacleo Llamas", Type.PHYSICAL, (short) 35, false));
        attacksOfClass.add(new Attack("Puño Fuego", Type.PHYSICAL, (short) 50, false));
        attacksOfClass.add(new Attack("Bola de Fuego", Type.PHYSICAL, (short) 55, false));
        attacksOfClass.add(new Attack("Impacto Llama", Type.PHYSICAL, (short) 65, false));
        attacksOfClass.add(new Attack("Rugido Infernal", Type.PHYSICAL, (short) 40, false));
        attacksOfClass.add(new Attack("Ataque Calor", Type.PHYSICAL, (short) 50, false));
        attacksOfClass.add(new Attack("Garras Ardientes", Type.PHYSICAL, (short) 45, false));

        //Add specials attacks to attacks of class

        attacksOfClass.add(new Attack("Rayo Solar", Type.FIRE, (short) 75, true));
        attacksOfClass.add(new Attack("Llamarada", Type.FIRE, (short) 80, true));
        attacksOfClass.add(new Attack("Fuego Fatuo", Type.FIRE, (short) 65, true));
        attacksOfClass.add(new Attack("Lluvia de Llamas", Type.FIRE, (short) 70, true));
        attacksOfClass.add(new Attack("Luz Solar", Type.FIRE, (short) 60, true));
        attacksOfClass.add(new Attack("Explosión Solar", Type.FIRE, (short) 85, true));
        attacksOfClass.add(new Attack("Furia Ígnea", Type.FIRE, (short) 55, true));
        attacksOfClass.add(new Attack("Fuego Sagrado", Type.FIRE, (short) 90, true));
        attacksOfClass.add(new Attack("Torrente de Fuego", Type.FIRE, (short) 65, true));
        attacksOfClass.add(new Attack("Tempestad de Llamas", Type.FIRE, (short) 75, true));


    }

    @Override
    public void doAttack(Pokemon oponentPokemon, byte indexAttackSelected) {

        double effectivity;
        switch (oponentPokemon.getType()){
            case WATER -> effectivity = 0.5;
            case ROCK -> effectivity = 0.5;
            case FIRE -> effectivity = 0.5;
            case PLANT -> effectivity = 2.0;
            default -> effectivity = 1.0;
        }

        Attack selectedAttack = attacksInstance.get(indexAttackSelected);

        double damage;

        if (selectedAttack.isSpecial()) {
            damage = ((selectedAttack.getAttackPower()) / (double) oponentPokemon.getDefenseSpecial()) + (Math.random() * 15) + 1;
        } else {
            damage = ((selectedAttack.getAttackPower()) / (double) oponentPokemon.getDefenseFisic()) + (Math.random() * 10) + 1;
        }
        damageMadeIt = (short)Math.round(damage*effectivity);
        oponentPokemon.receiveDamage(damageMadeIt);
    }
}
