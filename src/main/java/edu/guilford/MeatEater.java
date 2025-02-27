package edu.guilford;

import java.util.ArrayList;
import java.util.Random;

public class MeatEater extends Critter {
    private Random random = new Random();
    private ArrayList<PlantEater> plantEaters;
    private static final double  PROBABILITY_TO_CATCH = .6; 

    public MeatEater(double size, double growthRate, double foodNeed, ArrayList<PlantEater> plantEaters) {
        super(size, growthRate, foodNeed, Parameters.LIFESPAN_MEAT_EATER.getIntValue());
        this.plantEaters = plantEaters;
    }

    /***
     * Overrides simulate day with the meat eater's behavior. The meat eater will eat a random number of plant eaters, and if the meat eater is too old, it will die.
     */
    @Override 
    public void simulateDay() { 
        int numToEat = random.nextInt(1, 3); 
        //use the prob to catch variable in order to determine if the meat eater will catch the plant eater
        for (int i = 0; i < numToEat; i++) {
            if (random.nextDouble() < PROBABILITY_TO_CATCH) {
                PlantEater plantEater = plantEaters.get(random.nextInt(plantEaters.size()));
                eat(plantEater.getSize());
                plantEater.die();
            }

            if(age > lifespan && random.nextDouble() < Parameters.PROBABILITY_OLD_AGE_DEATH_MEAT_EATER.getValue()) {
                die();
            }
            super.simulateDay();
        }

    }

    



    @Override 
    public String toString() {
        return "MeatEater [size=" + size + ", growthRate=" + growthRate + ", alive=" + alive + ", age=" + age + 
               ", foodNeed=" + foodNeed + ", foodEaten=" + foodEaten + " plantEaters=" + plantEaters.size() + "]";
    }
    
    
}
