package src.model.pokemons;

import src.model.actions.Attack;

public class FlyingPokemon extends Pokemon {

    //Constructor and somes attacks to the type of pokemon
    public FlyingPokemon(String name, Type type, short hp, short defenseFisic, short defenseSpecial, short speed, String path) {
        super(name, type, hp, defenseFisic, defenseSpecial, speed, path);

        //Add fisic attacks to attacks of class

        attacksOfClass.add(new Attack("Puño Volador", Type.PHYSICAL, (short) 50, false));
        attacksOfClass.add(new Attack("Bajo Vuelo", Type.PHYSICAL, (short) 55, false));
        attacksOfClass.add(new Attack("Golpe Áereo", Type.PHYSICAL, (short) 60, false));
        attacksOfClass.add(new Attack("Garras Aéreas", Type.PHYSICAL, (short) 45, false));
        attacksOfClass.add(new Attack("Impacto de Alas", Type.PHYSICAL, (short) 50, false));
        attacksOfClass.add(new Attack("Vuelo Cortante", Type.PHYSICAL, (short) 60, false));
        attacksOfClass.add(new Attack("Ataque de Pluma", Type.PHYSICAL, (short) 50, false));
        attacksOfClass.add(new Attack("Alas Asesinas", Type.PHYSICAL, (short) 70, false));
        attacksOfClass.add(new Attack("Giro Aéreo", Type.PHYSICAL, (short) 55, false));
        attacksOfClass.add(new Attack("Puño Aéreo", Type.PHYSICAL, (short) 65, false));

        //Add specials attacks to attacks of class

        attacksOfClass.add(new Attack("Tormenta de Viento", Type.FLYING, (short) 75, true));
        attacksOfClass.add(new Attack("Rayo Aéreo", Type.FLYING, (short) 85, true));
        attacksOfClass.add(new Attack("Ala de Fuego", Type.FLYING, (short) 70, true));
        attacksOfClass.add(new Attack("Vuelo Veloz", Type.FLYING, (short) 80, true));
        attacksOfClass.add(new Attack("Viento Cortante", Type.FLYING, (short) 60, true));
        attacksOfClass.add(new Attack("Ciclón", Type.FLYING, (short) 85, true));
        attacksOfClass.add(new Attack("Torbellino de Plumas", Type.FLYING, (short) 75, true));
        attacksOfClass.add(new Attack("Lluvia de Aves", Type.FLYING, (short) 90, true));
        attacksOfClass.add(new Attack("Viento Huracanado", Type.FLYING, (short) 80, true));
        attacksOfClass.add(new Attack("Explosión Aérea", Type.FLYING, (short) 95, true));

    }

    @Override
    public void doAttack(Pokemon oponentPokemon, byte indexAttackSelected) {

        double effectivity;
        switch (oponentPokemon.getType()){
            case ELECTRIC -> effectivity = 0.5;
            case ROCK -> effectivity = 0.5;
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
