package edu.guilford;

import java.util.ArrayList;
import java.util.Random;

public class PlantEater extends Critter {
    private ArrayList<Plant> plants;
    private Random random = new Random();

    public PlantEater(double size, double growthRate, double foodNeed, ArrayList<Plant> plants) {
        super(size, growthRate, foodNeed);
        this.plants = plants;
    }

    public void chew(Plant plant) {
        double maxChewAmount = Math.min(Math.min(plant.getSize() / 2, stillNeed()), foodNeed * 0.1);
        if (maxChewAmount <= 0) {
            return;
        }
        double chewAmount = random.nextDouble(maxChewAmount);
        plant.chewedOn(chewAmount);
        eat(chewAmount);
    }

    @Override
    public void simulateDay() {
        int numPlantsToChew = (int) (random.nextDouble(.05, .1) * plants.size()); //had to change this otherwise kept crashing to 0 immediatly 
        for (int i = 0; i < numPlantsToChew; i++) {
            Plant plant = plants.get(random.nextInt(plants.size()));
            chew(plant);
        }
        super.simulateDay();
        ; 
    }

    @Override
    public String toString() {
        return "PlantEater [size=" + size + ", growthRate=" + growthRate + ", alive=" + alive + ", age=" + age + 
               ", foodNeed=" + foodNeed + ", foodEaten=" + foodEaten + ", plants=" + plants.size() + "]";
    }
}