package edu.guilford;

import java.util.ArrayList;
import java.util.Random;

public class Ecosystem {
    public static void main(String[] args) {


        Random random = new Random();
        //test 1
        ArrayList<Plant> plants = new ArrayList<Plant>();
        for (int i = 0; i < 2000; i++) {
            plants.add(new Plant(random.nextDouble(900, 1100), 5)); 
        }

        PlantEater plantEater = new PlantEater(random.nextDouble(900, 1100), 3, 50, plants);
        
        //test what happens when you chew on a plant
        Plant plant = plants.get(0);
        //chew on the plant
        System.out.println(plant); 
        plantEater.chew(plant);
        System.out.println(plant);
        //expect the plant to be smaller-- is smaller

        //test what happens when you simulate a day
        System.out.println(plantEater);
        plantEater.simulateDay();
        System.out.println(plantEater);
        //died because it could only eat 1 plant 

        //create a new plant eater and test what happens when it eats every plant
        PlantEater plantEater2 = new PlantEater(random.nextDouble(900, 1100), 3, 50, plants);
        System.out.println(plantEater2); 
        for (int i = 0; i < plants.size(); i++) {
            plantEater2.chew(plants.get(i));
        }
        plantEater2.simulateDay();
        System.out.println(plantEater2);
        //survives and grows a little 

        //test 2 
        ArrayList<PlantEater> plantEaters = new ArrayList<PlantEater>();
        for(int i =0; i < 300; i++) {
            plantEaters.add(new PlantEater(random.nextDouble(900, 1100), 3, 50, plants));
        }


        // int step = 0;
        // while (step < 1000 && plantEaters.size() > 0) {
        //     // Simulate a day for EACH Plant (Plants grow regardless)
        //     for (Plant p : plants) {
        //         p.simulateDay();
        //     }
        //     // Simulate a day for EACH PlantEater
        //     for (PlantEater pe : plantEaters) {
        //         pe.simulateDay();
        //     }
            
        //     // Birth process:
        //     // 5% chance to give birth to a new Plant each day.
        //     if (random.nextDouble() < 0.05) {
        //         double newPlantSize = 250 + random.nextDouble() * 100; // 300 ± 50 grams.
        //         plants.add(new Plant(newPlantSize, 5));
        //     }
        //     // 30% chance to give birth to a new PlantEater each day.
        //     if (random.nextDouble() < 0.30) {
        //         double newPEsize = 900 + random.nextDouble() * 200; // 1000 ± 100 grams.
        //         plantEaters.add(new PlantEater(newPEsize, 3, 50, plants));
        //     }
            
        //     // Remove any dead PlantEater objects.
        //     // We use a while loop to avoid ConcurrentModificationException.
        //     int i = 0;
        //     while (i < plantEaters.size()) {
        //         // If the PlantEater at index i is not alive, remove it.
        //         if (!plantEaters.get(i).getAlive()) {
        //             plantEaters.remove(i);
        //             // Note: We do NOT increment 'i' here because the list elements shift left.
        //         } else {
        //             i++;  // Only increment if no removal occurred.
        //         }
        //     }
            
        //     // Calculate total number and mass for Plants.
        //     int numPlants = plants.size();
        //     double totalPlantMass = 0;
        //     for (Plant p : plants) {
        //         totalPlantMass += p.getSize();
        //     }
            
        //     // Calculate total number and mass for PlantEaters.
        //     int numPlantEaters = plantEaters.size();
        //     double totalPlantEaterMass = 0;
        //     for (PlantEater pe : plantEaters) {
        //         totalPlantEaterMass += pe.getSize();
        //     }
            
        //     System.out.println("Day " + step + ": " +
        //         "Plants = " + numPlants + " (total mass=" + totalPlantMass + "), " +
        //         "PlantEaters = " + numPlantEaters + " (total mass=" + totalPlantEaterMass + ")");
            
        //     step++;
        // }

        // Simulation loop: Run for 1000 days or until there are no more PlantEaters.
        int day = 0;
        while (day < 500 && stillAlive(plantEaters) && stillAlive(plants)) {
            // Simulate a day for every Plant (e.g., they grow).
            for (Plant p : plants) {
                p.simulateDay();
            }

            // Simulate a day for every PlantEater.
            for (PlantEater pe : plantEaters) {
                pe.simulateDay();
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

            // Remove any dead PlantEater objects.
            // We use a while loop to avoid ConcurrentModificationException.
            // The trick here is that when we remove an element, all subsequent elements shift left,
            // so we do not increment the index in that case, ensuring every element is checked.
            int i = 0;
            while (i < plantEaters.size()) {
                if (!plantEaters.get(i).getAlive()) {
                    plantEaters.remove(i);
                } else {
                    i++;
                }
            }
            
            
            // Print the current status for the day.
            System.out.println("Day " + day + ": " +
                "Plants = " + plants.size() + " (total mass = " + totalMass(plants) + "), " +
                "PlantEaters = " + plantEaters.size() + " (total mass = " + totalMass(plantEaters) + ")");
            
            day++;
        }
    }

    

    private static boolean stillAlive(ArrayList<? extends Creature> creatures) {
        for (Creature c : creatures) {
            if (c.getAlive()) {
                return true;
            }
        }
        return false;
    }
    private static double totalMass(ArrayList<? extends Creature> creatures) {
        double totalMass = 0;
        for (Creature c : creatures) {
            totalMass += c.getSize();
        }
        return totalMass;
    }

}