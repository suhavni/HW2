package io.muic.ooc.fab;

import java.util.List;
import java.util.Random;

public abstract class Predator extends Animal {
    // The food value of a single rabbit. In effect, this is the
    // number of steps a fox can go before it has to eat again.
    protected static final int RABBIT_FOOD_VALUE = 9;
    // Random generator
    private static final Random RANDOM = new Random();

    // The maximum number of births.
    protected static final int MAX_LITTER_SIZE = 2;

    // The fox's food level, which is increased by eating rabbits.
    protected int foodLevel;

    public Predator(boolean randomAge, Field field, Location location) {
        super(randomAge, field, location);
        foodLevel = RANDOM.nextInt(RABBIT_FOOD_VALUE);
    }

    @Override
    public Location generateNewLocation() {
        Location newLocation = findFood();
        if (newLocation == null) {
            // No food found - try to move to a free location.
            newLocation = field.freeAdjacentLocation(getLocation());
        }
        return newLocation;
    }

    @Override
    public void nextStep(List<Actor> animals) {
        incrementHunger();
        super.nextStep(animals);
    }

    /**
     * Make this fox more hungry. This could result in the fox's death.
     */
    private void incrementHunger() {
        foodLevel--;
        if (foodLevel <= 0) {
            setDead();
        }
    }

    protected boolean eatPrey(Object animal) {
        if (animal instanceof Rabbit) {
            Rabbit rabbit = (Rabbit) animal;
            if (rabbit.isAlive()) {
                rabbit.setDead();
                foodLevel = RABBIT_FOOD_VALUE;
                return true;
            }
        }
        return false;
    }

    private Location findFood() {
        List<Location> adjacent = field.adjacentLocations(location);
        for (Location where : adjacent) {
            Object animal = field.getObjectAt(where);
            if (eatPrey(animal)) { return where; }
        }
        return null;
    }
}
