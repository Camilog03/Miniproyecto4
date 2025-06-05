package src.model.pokemons;

import src.model.actions.Attack;

public class GroundPokemon extends Pokemon {

    //Constructor and somes attacks to the type of pokemon
    public GroundPokemon(String name, Type type, short hp, short defenseFisic, short defenseSpecial, short speed, String path) {
        super(name, type, hp, defenseFisic, defenseSpecial, speed, path);

        //Add fisic attacks to attacks of class

        attacksOfClass.add(new Attack("Terremoto", Type.PHYSICAL, (short) 80, false));
        attacksOfClass.add(new Attack("Golpe de Arena", Type.PHYSICAL, (short) 50, false));
        attacksOfClass.add(new Attack("Pisotón Terrestre", Type.PHYSICAL, (short) 55, false));
        attacksOfClass.add(new Attack("Corte Rocoso", Type.PHYSICAL, (short) 60, false));
        attacksOfClass.add(new Attack("Bucle Subterráneo", Type.PHYSICAL, (short) 65, false));
        attacksOfClass.add(new Attack("Puño Tierra", Type.PHYSICAL, (short) 70, false));
        attacksOfClass.add(new Attack("Impacto de Tierra", Type.PHYSICAL, (short) 75, false));
        attacksOfClass.add(new Attack("Desgarro Subterráneo", Type.PHYSICAL, (short) 85, false));
        attacksOfClass.add(new Attack("Tajo Subterráneo", Type.PHYSICAL, (short) 55, false));
        attacksOfClass.add(new Attack("Sacudida Terrestre", Type.PHYSICAL, (short) 60, false));

        //Add specials attacks to attacks of class

        attacksOfClass.add(new Attack("Roca Filosa", Type.GROUND, (short) 70, true));
        attacksOfClass.add(new Attack("Sismo", Type.GROUND, (short) 85, true));
        attacksOfClass.add(new Attack("Terremoto Infernal", Type.GROUND, (short) 95, true));
        attacksOfClass.add(new Attack("Cañón de Arena", Type.GROUND, (short) 80, true));
        attacksOfClass.add(new Attack("Tsunami Subterráneo", Type.GROUND, (short) 90, true));
        attacksOfClass.add(new Attack("Terraforma", Type.GROUND, (short) 75, true));
        attacksOfClass.add(new Attack("Bomba Lodo", Type.GROUND, (short) 80, true));
        attacksOfClass.add(new Attack("Puño Rocoso", Type.GROUND, (short) 70, true));
        attacksOfClass.add(new Attack("Torbellino de Tierra", Type.GROUND, (short) 85, true));
        attacksOfClass.add(new Attack("Rugido de la Tierra", Type.GROUND, (short) 75, true));


    }

    @Override
    public void doAttack(Pokemon oponentPokemon, byte indexAttackSelected) {

        double effectivity;
        switch (oponentPokemon.getType()){
            case ELECTRIC -> effectivity = 2.0;
            case ROCK -> effectivity = 2.0;
            case FIRE -> effectivity = 2.0;
            case PLANT -> effectivity = 5.0;
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