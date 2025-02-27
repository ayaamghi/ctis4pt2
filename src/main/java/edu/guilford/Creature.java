package edu.guilford;

/***
 * This class is the parent class for all the creatures in the simulation. It has the basic attributes and methods that all creatures will have. 
 * @author Ayaam Ghimire
 */
public abstract class Creature {
    protected double size;
    protected double growthRate;
    protected boolean alive;
    protected int age;
    protected int lifespan; 

    public Creature(double size, double growthRate, int lifespan ) {
        this.size = size;
        this.growthRate = growthRate;
        this.alive = true;
        this.age = 0;
        this.lifespan = lifespan;
    }

    public double getSize() {
        return size;
    }

    public double getGrowthRate() {
        return growthRate;
    }

    public boolean getAlive() {
        return alive;
    }

    public int getAge() {
        return age;
    }

    public void setGrowthRate(double growthRate) {
        this.growthRate = growthRate;
    }

    /***
     * This method changes the size of the creature by the amount passed in. If the size is less than or equal to 0.0001, the creature dies.
     * @param amount the amount to change the size by
     */
    public void changeSize(double amount) {
        size += amount;
        //change it so that if size is within some distance from 0 or 0 it dies
        if (size <= 0.0001) {
            die();
        }

    }


    /***
     * This method simulates a day in the life of the creature. It increments the age of the creature and changes the size of the creature based on the growth rate. 
     */
    public void simulateDay() {
        age++;
        changeSize(growthRate);
        
    }

    /***
     * This method kills the creature by setting the size, growth rate, and alive status to 0. 
     */
    public void die() {
        size = 0;
        growthRate = 0;
        alive = false;
    }
}