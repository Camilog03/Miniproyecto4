package src.model.pokemons;

import src.model.actions.Attack;

public class RockPokemon extends Pokemon {

    //Constructor and somes attacks to the type of pokemon
    public RockPokemon(String name, Type type, short hp, short defenseFisic, short defenseSpecial, short speed, String path) {
        super(name, type, hp, defenseFisic, defenseSpecial, speed, path);

        //Add fisic attacks to attacks of class

        attacksOfClass.add(new Attack("Roca Filosa", Type.PHYSICAL, (short) 50, false));
        attacksOfClass.add(new Attack("Puño Rocoso", Type.PHYSICAL, (short) 55, false));
        attacksOfClass.add(new Attack("Impacto de Roca", Type.PHYSICAL, (short) 60, false));
        attacksOfClass.add(new Attack("Tajo de Piedra", Type.PHYSICAL, (short) 65, false));
        attacksOfClass.add(new Attack("Piedra Lanza", Type.PHYSICAL, (short) 60, false));
        attacksOfClass.add(new Attack("Golpe Roca", Type.PHYSICAL, (short) 45, false));
        attacksOfClass.add(new Attack("Desgarro Rocoso", Type.PHYSICAL, (short) 70, false));
        attacksOfClass.add(new Attack("Embate Rocoso", Type.PHYSICAL, (short) 75, false));
        attacksOfClass.add(new Attack("Puño de Piedra", Type.PHYSICAL, (short) 65, false));
        attacksOfClass.add(new Attack("Ataque de Roca", Type.PHYSICAL, (short) 80, false));

        //Add specials attacks to attacks of class

        attacksOfClass.add(new Attack("Bomba de Roca", Type.ROCK, (short) 80, true));
        attacksOfClass.add(new Attack("Lluvia de Meteoritos", Type.ROCK, (short) 95, true));
        attacksOfClass.add(new Attack("Roca Luminosa", Type.ROCK, (short) 75, true));
        attacksOfClass.add(new Attack("Colisión Meteórica", Type.ROCK, (short) 90, true));
        attacksOfClass.add(new Attack("Explosión Rocosa", Type.ROCK, (short) 100, true));
        attacksOfClass.add(new Attack("Avalancha de Piedras", Type.ROCK, (short) 80, true));
        attacksOfClass.add(new Attack("Roca Llameante", Type.ROCK, (short) 70, true));
        attacksOfClass.add(new Attack("Puño Terrón", Type.ROCK, (short) 85, true));
        attacksOfClass.add(new Attack("Tormenta de Rocas", Type.ROCK, (short) 85, true));
        attacksOfClass.add(new Attack("Roca Celestial", Type.ROCK, (short) 90, true));


    }

    @Override
    public void doAttack(Pokemon oponentPokemon, byte indexAttackSelected) {

        double effectivity;
        switch (oponentPokemon.getType()){
            case FLYING -> effectivity = 2.0;
            case FIRE -> effectivity = 2.0;
            case GROUND -> effectivity = 0.5;
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
