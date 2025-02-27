package edu.guilford;

/***
 * This class is the parent class for critters, defined as the plant eaters and meat eaters. 
 * @author Ayaam Ghimire
 */
public abstract class Critter extends Creature {
    protected double foodNeed;
    protected double foodEaten;

    public Critter(double size, double growthRate, double foodNeed, int lifespan) {
        super(size, growthRate, lifespan);
        this.foodNeed = foodNeed;
        this.foodEaten = 0;
        
    }

    public double getFoodNeed() {
        return foodNeed;
    }

    public void setFoodNeed(double foodNeed) {
        this.foodNeed = foodNeed;
    }

    /***
     * This method simulates the critter eating food. It increments the amount of food eaten by the amount passed in. 
     * @param amount the amount of food eaten
     */
    public void eat(double amount) {
        foodEaten += amount;
    }

    public double stillNeed() {
        return foodNeed - foodEaten;
    }

    /***
     * This method simulates a day in the life of the critter. If the critter has not eaten enough food, it dies. Otherwise, it simulates a day in the life of the creature. 
     */
    @Override
    public void simulateDay() {
        if (foodEaten < foodNeed) {
            die();
        } else {
            foodEaten = 0;
            super.simulateDay();
        }
    }

    /***
     * This method changes the size of the critter by the amount passed in. It then updates the food need of the critter. 
     * @param amount the amount to change the size by
     */
    @Override
    public void changeSize(double amount) {
            super.changeSize(amount);
            foodNeed = size * 0.1; //call super changeSize method and then update foodNeed
}
}