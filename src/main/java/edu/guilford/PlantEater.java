package edu.guilford;

import java.util.ArrayList;
import java.util.Random;

/***
 * This class is the child class of Critter. It represents the plant eaters in the simulation. 
 * It has the attributes of the plants that the plant eater can eat. 
 * It has the methods to simulate a day in the life of the plant eater and to chew on the plants. 
 * It also has a toString method to print out the attributes of the plant eater. 
 * @author Ayaam Ghimire
 */
public class PlantEater extends Critter {
    private ArrayList<Plant> plants;
    private Random random = new Random();

    public PlantEater(double size, double growthRate, double foodNeed, ArrayList<Plant> plants) {
        super(size, growthRate, foodNeed, Parameters.LIFESPAN_PLANT_EATER.getIntValue());
        this.plants = plants;
    }

  


    /***
     * This method simulates the plant eater chewing on a plant. It changes the size of the plant by the amount passed in. 
     * @param plant the plant to chew on
     */
    public void chew(Plant plant) {
        double maxChewAmount = Math.min(Math.min(plant.getSize() * Parameters.AMOUNT_OF_PLANT_MAX_CHEWED.getValue(), stillNeed()), foodNeed * Parameters.PERCENTAGE_OF_FOODNEED_TO_CHEW.getValue()); //changed these from the assignment as well 
        if (maxChewAmount <= 0) {
            return;
        }
        double chewAmount = random.nextDouble(Parameters.FLOOR_ON_CHEW_AMOUNT.getValue() * maxChewAmount,  maxChewAmount); //added a floor 
        plant.chewedOn(chewAmount);
        eat(chewAmount);
    }

    /***
     * This method simulates a day in the life of the plant eater. It determines how many plants the plant eater will chew on and then calls the chew method. 
     */
    @Override
    public void simulateDay() {
        if (age > lifespan && random.nextDouble() < Parameters.PROBABILITY_OLD_AGE_DEATH_PLANT_EATER.getValue()) {
            die();
        }
        int numPlantsToChew = (int) (random.nextDouble(Parameters.LOWER_BOUND_NUM_PLANTS_TO_CHEW.getValue(), Parameters.UPPER_BOUND_NUM_PLANTS_TO_CHEW.getValue()) * plants.size()); //had to change nextDouble range  otherwise kept crashing to 0 immediatly 
        for (int i = 0; i < numPlantsToChew; i++) {
            Plant plant = plants.get(random.nextInt(plants.size()));
            chew(plant);
        }
        
        super.simulateDay();
    }

    @Override
    public String toString() {
        return "PlantEater [size=" + size + ", growthRate=" + growthRate + ", alive=" + alive + ", age=" + age + 
               ", foodNeed=" + foodNeed + ", foodEaten=" + foodEaten + ", plants=" + plants.size() + "]";
    }
}