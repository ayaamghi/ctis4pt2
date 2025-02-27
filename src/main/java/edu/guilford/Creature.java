package edu.guilford;

public abstract class Creature {
    protected double size;
    protected double growthRate;
    protected boolean alive;
    protected int age;

    public Creature(double size, double growthRate) {
        this.size = size;
        this.growthRate = growthRate;
        this.alive = true;
        this.age = 0;
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

    public void changeSize(double amount) {
        size += amount;
        if (size < 0) {
            die();
        }

    }

    public void simulateDay() {
        changeSize(growthRate);
        age++;
    }

    public void die() {
        size = 0;
        growthRate = 0;
        alive = false;
    }
}