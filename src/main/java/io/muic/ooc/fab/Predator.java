package io.muic.ooc.fab;

import java.util.List;

public abstract class Predator extends Animal {

    // The maximum number of births.
    protected static final int MAX_LITTER_SIZE = 2;

    // The predator's food level, which is increased by eating preys.
    protected int foodLevel;

    public Predator(boolean randomAge, Field field, Location location) {
        super(randomAge, field, location);
        foodLevel = RANDOM.nextInt(9);
    }

    @Override
    public Location generateNewLocation() {
        Location newLocation = findFood();
        if (newLocation == null) {
            // No food found - try to move to a free location.
            newLocation = field.freeAdjacentLocation(location);
        }
        return newLocation;
    }

    @Override
    public void nextStep(List<Actor> animals) {
        incrementHunger();
        super.nextStep(animals);
    }

    /**
     * Make this predator more hungry. This could result in the predator's death.
     */
    private void incrementHunger() {
        foodLevel--;
        if (foodLevel <= 0) {
            setDead();
        }
    }

    protected int getFoodValue(Object animal) {
        if (animal instanceof Rabbit) {
            return ((Rabbit) animal).getFoodValue();
        }
        return 0;
    }

    private boolean eatPrey(Object animal) {
        int foodValue = getFoodValue(animal);
        if (foodValue != 0) {
            Animal prey = (Animal) animal;
            if (prey.isAlive()) {
                prey.setDead();
                foodLevel = foodValue;
                return true;
            }
        }
        return false;
    }

    private Location findFood() {
        List<Location> adjacent = field.adjacentLocations(location);
        for (Location where : adjacent) {
            Object animal = field.getObjectAt(where);
            if (eatPrey(animal)) {
                return where;
            }
        }
        return null;
    }
}
