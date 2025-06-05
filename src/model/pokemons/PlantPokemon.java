package src.model.pokemons;

import src.model.actions.Attack;

public class PlantPokemon extends Pokemon {

    //Constructor and somes attacks to the type of pokemon
    public PlantPokemon(String name, Type type, short hp, short defenseFisic, short defenseSpecial, short speed, String path) {
        super(name, type, hp, defenseFisic, defenseSpecial, speed, path);

        //Add fisic attacks to attacks of class

        attacksOfClass.add(new Attack("Latigazo", Type.PHYSICAL, (short) 35, false));
        attacksOfClass.add(new Attack("Hoja Afilada", Type.PHYSICAL, (short) 45, false));
        attacksOfClass.add(new Attack("Látigo Cepa", Type.PHYSICAL, (short) 30, false));
        attacksOfClass.add(new Attack("Fitoimpulso", Type.PHYSICAL, (short) 45, false));
        attacksOfClass.add(new Attack("Hoja Aguda", Type.PHYSICAL, (short) 50, false));
        attacksOfClass.add(new Attack("Embate Verde", Type.PHYSICAL, (short) 40, false));
        attacksOfClass.add(new Attack("Puya Espinosa", Type.PHYSICAL, (short) 35, false));
        attacksOfClass.add(new Attack("Tacleo Floral", Type.PHYSICAL, (short) 25, false));
        attacksOfClass.add(new Attack("Corte Hoja", Type.PHYSICAL, (short) 40, false));
        attacksOfClass.add(new Attack("Ira Botánica", Type.PHYSICAL, (short) 55, false));

        //Add specials attacks to attacks of class

        attacksOfClass.add(new Attack("Rayo Solar", Type.PLANT, (short) 65, true));
        attacksOfClass.add(new Attack("Gigadrenado", Type.PLANT, (short) 55, true));
        attacksOfClass.add(new Attack("Energibola", Type.PLANT, (short) 50, true));
        attacksOfClass.add(new Attack("Hoja Mágica", Type.PLANT, (short) 40, true));
        attacksOfClass.add(new Attack("Danza Pétalo", Type.PLANT, (short) 60, true));
        attacksOfClass.add(new Attack("Follaje", Type.PLANT, (short) 25, true));
        attacksOfClass.add(new Attack("Tempestad Floral", Type.PLANT, (short) 60, true));
        attacksOfClass.add(new Attack("Explosión Verde", Type.PLANT, (short) 70, true));
        attacksOfClass.add(new Attack("Esfera Espora", Type.PLANT, (short) 50, true));
        attacksOfClass.add(new Attack("Tormenta de Hojas", Type.PLANT, (short) 60, true));



    }

    @Override
    public void doAttack(Pokemon oponentPokemon, byte indexAttackSelected) {

        double effectivity;
        switch (oponentPokemon.getType()){
            case WATER -> effectivity = 2.0;
            case GROUND -> effectivity = 2.0;
            case ROCK -> effectivity = 2.0;
            case FIRE -> effectivity = 0.5;
            case PLANT -> effectivity = 0.5;
            case FLYING -> effectivity = 0.5;
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
