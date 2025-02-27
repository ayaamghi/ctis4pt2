package edu.guilford;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Ecosystem {
    public static void main(String[] args) {

        Random random = new Random();
        // test 1
        ArrayList<Plant> plants = new ArrayList<Plant>();
        for (int i = 0; i < 2000; i++) {
            plants.add(new Plant(random.nextDouble(900, 1100), 5));
        }

        PlantEater plantEater = new PlantEater(random.nextDouble(900, 1100), 3, 50, plants);

        // test what happens when you chew on a plant
        Plant plant = plants.get(0);
        // chew on the plant
        System.out.println(plant);
        plantEater.chew(plant);
        System.out.println(plant);
        // expect the plant to be smaller-- is smaller

        // test what happens when you simulate a day
        System.out.println(plantEater);
        plantEater.simulateDay();
        System.out.println(plantEater);
        // died because it could only eat 1 plant

        // create a new plant eater and test what happens when it eats every plant
        PlantEater plantEater2 = new PlantEater(random.nextDouble(900, 1100), 3, 50, plants);
        System.out.println(plantEater2);
        for (int i = 0; i < plants.size(); i++) {
            plantEater2.chew(plants.get(i));
        }
        plantEater2.simulateDay();
        System.out.println(plantEater2);
        // survives and grows a little

        // add the meateater to the same rules as above
        // test 2
        ArrayList<PlantEater> plantEaters = new ArrayList<PlantEater>();
        for (int i = 0; i < 300; i++) {
            plantEaters.add(new PlantEater(random.nextDouble(900, 1100), 3, 50, plants));
        }

        ArrayList<PlantEater> plantEatersCopy = new ArrayList<>(plantEaters);
        MeatEater eater = new MeatEater(100.0, 10.0, 10.0, plantEatersCopy);
        System.out.println(eater);
        eater.simulateDay();
        System.out.println(eater); // tuned value of probability to catch to .75 from this

        // create 150 meateaters
        ArrayList<MeatEater> meatEaters = new ArrayList<>();
        for (int i = 0; i < Parameters.NUM_MEAT_EATERS.getIntValue(); i++) {
            meatEaters.add(
                    new MeatEater(Parameters.MEAT_EATER_SIZE.getValue(), Parameters.MEAT_EATER_GROWTH_RATE.getValue(),
                            Parameters.MEAT_EATER_FOOD_NEED.getValue(), plantEaters));
        }
        System.out.println("Simulation results:");
        SimulationResults results = simulationResults(plants, plantEaters, meatEaters);
        System.out.println(results);
        // print out maximum of each variable
        // consistently getting day 1 as maxes-- values are cooked

        System.out.println("Max plant count: " + Collections.max(results.plantCounts) + " on day "
                + (results.plantCounts.indexOf(Collections.max(results.plantCounts)) + 1));
        System.out.println("Max plant eater count: " + Collections.max(results.plantEaterCounts) + " on day "
                + (results.plantEaterCounts.indexOf(Collections.max(results.plantEaterCounts)) + 1));
        System.out.println("Max meat eater count: " + Collections.max(results.meatEaterCounts) + " on day "
                + (results.meatEaterCounts.indexOf(Collections.max(results.meatEaterCounts)) + 1));
        System.out.println("Max plant mass: " + Collections.max(results.plantMasses) + " on day "
                + (results.plantMasses.indexOf(Collections.max(results.plantMasses)) + 1));
        System.out.println("Max plant eater mass: " + Collections.max(results.plantEaterMasses) + " on day "
                + (results.plantEaterMasses.indexOf(Collections.max(results.plantEaterMasses)) + 1));
        System.out.println("Max meat eater mass: " + Collections.max(results.meatEaterMasses) + " on day "
                + (results.meatEaterMasses.indexOf(Collections.max(results.meatEaterMasses)) + 1));

        // copilots code ???
        // System.out.println("Max plant count: " +
        // results.plantCounts.stream().max(Integer::compare).get() + " on day " +
        // (results.plantCounts.indexOf(results.plantCounts.stream().max(Integer::compare).get())
        // + 1));
        // System.out.println("Max plant eater count: " +
        // results.plantEaterCounts.stream().max(Integer::compare).get() + " on day " +
        // (results.plantEaterCounts.indexOf(results.plantEaterCounts.stream().max(Integer::compare).get())
        // + 1));
        // System.out.println("Max meat eater count: " +
        // results.meatEaterCounts.stream().max(Integer::compare).get() + " on day " +
        // (results.meatEaterCounts.indexOf(results.meatEaterCounts.stream().max(Integer::compare).get())
        // + 1));
        // System.out.println("Max plant mass: " +
        // results.plantMasses.stream().max(Double::compare).get() + " on day " +
        // (results.plantMasses.indexOf(results.plantMasses.stream().max(Double::compare).get())
        // + 1));
        // System.out.println("Max plant eater mass: " +
        // results.plantEaterMasses.stream().max(Double::compare).get() + " on day " +
        // (results.plantEaterMasses.indexOf(results.plantEaterMasses.stream().max(Double::compare).get())
        // + 1));
        // System.out.println("Max meat eater mass: " +
        // results.meatEaterMasses.stream().max(Double::compare).get() + " on day " +
        // (results.meatEaterMasses.indexOf(results.meatEaterMasses.stream().max(Double::compare).get())
        // + 1));

        // run the simulation 10 thousand times, and see what the maximum values are for
        // each variable is

        ArrayList<Integer> plantCounts = new ArrayList<>();
        ArrayList<Integer> plantEaterCounts = new ArrayList<>();
        ArrayList<Integer> meatEaterCounts = new ArrayList<>();
        ArrayList<Double> plantMasses = new ArrayList<>();
        ArrayList<Double> plantEaterMasses = new ArrayList<>();
        ArrayList<Double> meatEaterMasses = new ArrayList<>();
        ArrayList<Integer> daysRan = new ArrayList<>();

        System.out.println("Simulate 200 Times");
        for (int i = 0; i < 200; i++) {

            // make new lists each time
            plants = new ArrayList<Plant>();
            for (int j = 0; j < 2000; j++) {
                plants.add(new Plant(random.nextDouble(900, 1100), 5));
            }
            // make plant
            plantEaters = new ArrayList<PlantEater>();
            for (int j = 0; j < 300; j++) {
                plantEaters.add(new PlantEater(random.nextDouble(900, 1100), 3, 50, plants));
            }
            meatEaters = new ArrayList<>();
            for (int j = 0; j < Parameters.NUM_MEAT_EATERS.getIntValue(); j++) {
                meatEaters.add(
                        new MeatEater(Parameters.MEAT_EATER_SIZE.getValue(),
                                Parameters.MEAT_EATER_GROWTH_RATE.getValue(),
                                Parameters.MEAT_EATER_FOOD_NEED.getValue(), plantEaters));
            }

            results = simulationResults(new ArrayList<>(plants), new ArrayList<>(plantEaters),
                    new ArrayList<>(meatEaters));
            plantCounts.add(Collections.max(results.plantCounts));
            plantEaterCounts.add(Collections.max(results.plantEaterCounts));
            meatEaterCounts.add(Collections.max(results.meatEaterCounts));
            plantMasses.add(Collections.max(results.plantMasses));
            plantEaterMasses.add(Collections.max(results.plantEaterMasses));
            meatEaterMasses.add(Collections.max(results.meatEaterMasses));
            daysRan.add(results.plantCounts.size());
        }
        // print each of the amxes out
        System.out.println("Max Days Ran " + Collections.max(daysRan));
        // average days ran
        System.out.println("Average Days Ran " + daysRan.stream().mapToInt(Integer::intValue).average().getAsDouble());
        System.out.println("Max plant count: " + Collections.max(plantCounts));
        System.out.println("Max plant eater count: " + Collections.max(plantEaterCounts));
        System.out.println("Max meat eater count: " + Collections.max(meatEaterCounts));
        System.out.println("Max plant mass: " + Collections.max(plantMasses));
        System.out.println("Max plant eater mass: " + Collections.max(plantEaterMasses));
        System.out.println("Max meat eater mass: " + Collections.max(meatEaterMasses));

    }
    // with the current parameters, everything just kind of dies out

    /***
     * A class used to hold results from a run of a simulation
     */
    private static class SimulationResults {
        private ArrayList<String> dailyResults;
        private ArrayList<Integer> plantCounts;
        private ArrayList<Integer> plantEaterCounts;
        private ArrayList<Integer> meatEaterCounts;
        private ArrayList<Double> plantMasses;
        private ArrayList<Double> plantEaterMasses;
        private ArrayList<Double> meatEaterMasses;
        private int day;

        public SimulationResults() {
            dailyResults = new ArrayList<>();
            plantCounts = new ArrayList<>();
            plantEaterCounts = new ArrayList<>();
            meatEaterCounts = new ArrayList<>();
            plantMasses = new ArrayList<>();
            plantEaterMasses = new ArrayList<>();
            meatEaterMasses = new ArrayList<>();
            day = 0;
        }

        public void addDay(int day, int plantCount, int plantEaterCount, int meatEaterCount, double plantMass,
                double plantEaterMass, double meatEaterMass) {
            plantCounts.add(plantCount);
            plantEaterCounts.add(plantEaterCount);
            meatEaterCounts.add(meatEaterCount);
            plantMasses.add(plantMass);
            plantEaterMasses.add(plantEaterMass);
            meatEaterMasses.add(meatEaterMass);
            day++;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            // .size() is the same for all of them so just using plantCounts works
            for (int i = 0; i < plantCounts.size(); i++) {
                sb.append("Day ").append(i + 1).append(": ");
                sb.append("Plants = ").append(plantCounts.get(i)).append(" (total mass = ").append(plantMasses.get(i))
                        .append("), ");
                sb.append("PlantEaters = ").append(plantEaterCounts.get(i)).append(" (total mass = ")
                        .append(plantEaterMasses.get(i)).append("), ");
                sb.append("MeatEaters = ").append(meatEaterCounts.get(i)).append(" (total mass = ")
                        .append(meatEaterMasses.get(i)).append(")\n");
            }
            return sb.toString();
        }
    }

    /***
     * This method runs a simulation of the ecosystem. It simulates 10,000 days or until all the plant eaters die. It returns a SimulationResults object that contains the results of the simulation.
     * @param plants
     * @param plantEaters
     * @param meatEaters
     * @return
     */
    private static SimulationResults simulationResults(ArrayList<Plant> plants, ArrayList<PlantEater> plantEaters,
            ArrayList<MeatEater> meatEaters) {
        Random random = new Random();
        SimulationResults results = new SimulationResults();
        int day = 0;
        while (day < 10000 && stillAlive(plantEaters) && stillAlive(plants)) { // meateaters die too much so not going
                                                                               // to check for them

            // Simulate a day for every Plant (e.g., they grow).
            for (Plant p : plants) {
                p.simulateDay();
            }

            // Simulate a day for every PlantEater.
            for (PlantEater pe : plantEaters) {
                pe.simulateDay();
            }

            // simulate a day for every MeatEater
            for (MeatEater me : meatEaters) {
                me.simulateDay();
            }

            // Birth process:
            // 5% chance each day to give birth to a new Plant.
            if (random.nextDouble() < 0.05) {
                double newPlantSize = 250 + random.nextDouble() * 100; // Approximately 300 ± 50 grams.
                plants.add(new Plant(newPlantSize, 5));
            }
            // 30% chance each day to give birth to a new PlantEater.
            if (random.nextDouble() < 0.30) {
                double newPEsize = 900 + random.nextDouble() * 200; // Approximately 1000 ± 100 grams.
                plantEaters.add(new PlantEater(newPEsize, 3, 50, plants));
            }

            // 30% chance each day to give birth to a new MeatEater.
            if (random.nextDouble() < 0.30) {
                meatEaters.add(new MeatEater(Parameters.MEAT_EATER_SIZE.getValue(),
                        Parameters.MEAT_EATER_GROWTH_RATE.getValue(), Parameters.MEAT_EATER_FOOD_NEED.getValue(),
                        plantEaters));
            }
            // Remove any dead PlantEater objects.
            // We use a while loop to avoid ConcurrentModificationException.
            // The trick here is that when we remove an element, all subsequent elements
            // shift left,
            // so we do not increment the index in that case, ensuring every element is
            // checked.
            int i = 0;
            while (i < plantEaters.size()) {
                if (!plantEaters.get(i).getAlive()) {
                    plantEaters.remove(i);
                } else {
                    i++;
                }
            }

            // remove dead meateaters
            int j = 0;
            while (j < meatEaters.size()) {
                if (!meatEaters.get(j).getAlive()) {
                    meatEaters.remove(j);
                } else {
                    j++;
                }
            }

            day++;
            // fill out results.addDay() with the correct values
            results.addDay(
                    day,
                    plants.size(),
                    plantEaters.size(),
                    meatEaters.size(),
                    totalMass(plants),
                    totalMass(plantEaters),
                    totalMass(meatEaters));

        }
        return results;
    }

    /***
     * This method returns true if any of the creatures in the list are alive, and false otherwise.
     * @param creatures
     * @return boolean true if any of the creatures in the list are alive, and false otherwise.
     */
    private static boolean stillAlive(ArrayList<? extends Creature> creatures) {
        for (Creature c : creatures) {
            if (c.getAlive()) {
                return true;
            }
        }
        return false;
    }

    /***
     * This method returns the total mass of all the creatures in the list.
     * @param creatures
     * @return double total mass of all the creatures in the list.
     */
    private static double totalMass(ArrayList<? extends Creature> creatures) {
        double totalMass = 0;
        for (Creature c : creatures) {
            totalMass += c.getSize();
        }
        return totalMass;
    }

}