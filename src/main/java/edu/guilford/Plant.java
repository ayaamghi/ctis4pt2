package edu.guilford;

public class Plant extends Creature {

    public Plant(double size, double growthRate) {
        super(size, growthRate);
    }

    public void chewedOn(double amount) {
        if (amount > size) {
            amount = size;
        }
        changeSize(-amount);
    }

    @Override
    public String toString() {
        return "Plant [size=" + size + ", growthRate=" + growthRate + ", alive=" + alive + ", age=" + age + "]";
    }
}