package edu.guilford;

public abstract class Critter extends Creature {
    protected double foodNeed;
    protected double foodEaten;

    public Critter(double size, double growthRate, double foodNeed) {
        super(size, growthRate);
        this.foodNeed = foodNeed;
        this.foodEaten = 0;
    }

    public double getFoodNeed() {
        return foodNeed;
    }

    public void setFoodNeed(double foodNeed) {
        this.foodNeed = foodNeed;
    }

    public void eat(double amount) {
        foodEaten += amount;
    }

    public double stillNeed() {
        return foodNeed - foodEaten;
    }

    @Override
    public void simulateDay() {
        if (foodEaten < foodNeed) {
            die();
        } else {
            foodEaten = 0;
            super.simulateDay();
        }
    }
}