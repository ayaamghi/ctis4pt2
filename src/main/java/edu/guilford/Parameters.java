package edu.guilford;


/***
 * This enum class is used to store the parameters that will be used in the simulation.
 * The parameters are stored as doubles or ints, and can be accessed using the getValue() or getIntValue() methods.
 */
public enum Parameters {

    //create a list of the parameters that will be used in the simulation

    LOWER_BOUND_NUM_PLANTS_TO_CHEW(.05), 
    UPPER_BOUND_NUM_PLANTS_TO_CHEW(.1),
    AMOUNT_OF_PLANT_MAX_CHEWED(.425), //making this value too high is worse then lower-- even though plants never are in trouble of dying out? 
    /***
     * Says percentage, is a decimal 
     */
    PERCENTAGE_OF_FOODNEED_TO_CHEW(0.1),
    /***
     * Is a percentage of the max chew amount
     */
    FLOOR_ON_CHEW_AMOUNT(0.2),
    MEAT_EATER_SIZE(.1), 
    MEAT_EATER_GROWTH_RATE(.2),
    MEAT_EATER_FOOD_NEED(0.2),

    PROBABILITY_OLD_AGE_DEATH_PLANT_EATER(.1),
    PROBABILITY_OLD_AGE_DEATH_MEAT_EATER(.1),
    PROBABILITY_OLD_AGE_DEATH_PLANT(.1),
    PROBABILITY_OLD_AGE_DEATH_CREATURE(.1),


    LIFESPAN_PLANT(350), 
    LIFESPAN_PLANT_EATER(300),
    LIFESPAN_MEAT_EATER(500),    
    NUM_MEAT_EATERS(200);

    private final double value;

    Parameters(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    Parameters(int value) {
        this.value = value;
    }

    public int getIntValue() {
        return (int) value;
    }
}
