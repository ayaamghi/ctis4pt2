package edu.guilford;



/***
 * This class creates Plants that extend creature 
 *  
 */
public class Plant extends Creature {

    public Plant(double size, double growthRate) {
        super(size, growthRate, Parameters.LIFESPAN_PLANT.getIntValue());
    }

    /***
     * This method simulates the plant being chewed on. It changes the size of the plant by the amount passed in. If the amount is greater than the size of the plant, the plant dies. 
     * @param amount the amount to change the size by
     */
    public void chewedOn(double amount) {
        if (amount > size) {
            amount = size;
        }
        changeSize(-amount);
    }

    /***
     * This method simulates a day in the life of the plant. If the plant is too old, it dies. 
     */
    @Override 
    public void simulateDay() {
        if(age > lifespan && Math.random() < Parameters.PROBABILITY_OLD_AGE_DEATH_PLANT.getValue()) {
            die();
        }
        super.simulateDay();
    }
    @Override
    public String toString() {
        return "Plant [size=" + size + ", growthRate=" + growthRate + ", alive=" + alive + ", age=" + age + "]";
    }
}