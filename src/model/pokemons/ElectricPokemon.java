package src.model.pokemons;

import src.model.actions.Attack;

public class ElectricPokemon extends Pokemon {


    //Constructor and somes attacks to the type of pokemon
    public ElectricPokemon(String name, Type type, short hp, short defenseFisic, short defenseSpecial, short speed, String path) {
        super(name, type, hp, defenseFisic, defenseSpecial, speed, path);

        //Add physical attacks to attacks of class

        attacksOfClass.add(new Attack("Puño Eléctrico", Type.PHYSICAL, (short) 50, false));
        attacksOfClass.add(new Attack("Rayo Corto", Type.PHYSICAL, (short) 45, false));
        attacksOfClass.add(new Attack("Impacto Voltaje", Type.PHYSICAL, (short) 60, false));
        attacksOfClass.add(new Attack("Tacleo Eléctrico", Type.PHYSICAL, (short) 40, false));
        attacksOfClass.add(new Attack("Golpe Relámpago", Type.PHYSICAL, (short) 55, false));
        attacksOfClass.add(new Attack("Puño Trueno", Type.PHYSICAL, (short) 50, false));
        attacksOfClass.add(new Attack("Voltio", Type.PHYSICAL, (short) 60, false));
        attacksOfClass.add(new Attack("Choque Eléctrico", Type.PHYSICAL, (short) 65, false));
        attacksOfClass.add(new Attack("Estallido Voltaico", Type.PHYSICAL, (short) 70, false));
        attacksOfClass.add(new Attack("Rayo Impacto", Type.PHYSICAL, (short) 75, false));

        //Add specials attacks to attacks of class

        attacksOfClass.add(new Attack("Rayo", Type.ELECTRIC, (short) 80, true));
        attacksOfClass.add(new Attack("Trueno", Type.ELECTRIC, (short) 90, true));
        attacksOfClass.add(new Attack("Relámpago", Type.ELECTRIC, (short) 75, true));
        attacksOfClass.add(new Attack("Chispa Eléctrica", Type.ELECTRIC, (short) 60, true));
        attacksOfClass.add(new Attack("Electrocañón", Type.ELECTRIC, (short) 85, true));
        attacksOfClass.add(new Attack("Carga Eléctrica", Type.ELECTRIC, (short) 70, true));
        attacksOfClass.add(new Attack("Destello", Type.ELECTRIC, (short) 65, true));
        attacksOfClass.add(new Attack("Electrochorro", Type.ELECTRIC, (short) 80, true));
        attacksOfClass.add(new Attack("Tornado Eléctrico", Type.ELECTRIC, (short) 90, true));
        attacksOfClass.add(new Attack("Rayo Solar Eléctrico", Type.ELECTRIC, (short) 75, true));

    }

    @Override
    public void doAttack(Pokemon oponentPokemon, byte indexAttackSelected) {

        double effectivity;

        switch (oponentPokemon.getType()){
            case WATER -> effectivity = 2.0;
            case ROCK -> effectivity = 2.0;
            case FIRE -> effectivity = 0.5;
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
