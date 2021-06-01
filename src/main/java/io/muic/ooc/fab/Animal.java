package io.muic.ooc.fab;

import java.util.List;

public abstract class Animal extends Actor {
    // Individual characteristics (instance fields).
    // The animal's age.
    protected int age;

    public Animal(boolean randomAge, Field field, Location location) {
        age = 0;
        setAlive(true);
        this.field = field;
        setLocation(location);
        if (randomAge) {
            age = RANDOM.nextInt(getMaxAge());
        }
    }

    public abstract int getMaxAge();

    /**
     * Increase the age. This could result in the animal's death.
     */
    protected void incrementAge() {
        age++;
        if (age > getMaxAge()) {
            setDead();
        }
    }


    /**
     * Indicate that the animal is no longer alive. It is removed from the field.
     */
    protected void setDead() {
        setAlive(false);
        if (location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    protected void nextStep(List<Actor> animals) {
        incrementAge();
        if (isAlive()) {
            giveBirth(animals);
            Location newLocation = generateNewLocation();
            // See if it was possible to move.
            if (newLocation != null) {
                setLocation(newLocation);
            } else {
                // Overcrowding.
                setDead();
            }
        }
    }

    /**
     * Generate a number representing the number of births, if it can breed.
     *
     * @return The number of births (may be zero).
     */
    protected int breed() {
        int births = 0;
        if (canBreed() && RANDOM.nextDouble() <= getBreedingProbability()) {
            births = RANDOM.nextInt(getMaxLitterSize()) + 1;
        }
        return births;
    }

    protected abstract double getBreedingProbability();

    protected abstract int getMaxLitterSize();

    protected abstract int getFoodValue();

    /**
     * An animal can breed if it has reached the breeding age.
     *
     * @return true if the animal can breed, false otherwise.
     */
    private boolean canBreed() {
        return age >= getBreedingAge();
    }

    protected abstract int getBreedingAge();

    protected abstract Animal createYoung(Field field, Location location);

    /**
     * Check whether or not this animal is to give birth at this step. New
     * births will be made into free adjacent locations.
     *
     * @param newAnimals A list to return newly born animals.
     */
    protected void giveBirth(List<Actor> newAnimals) {
        // New animals are born into adjacent locations.
        // Get a list of adjacent free locations.
        List<Location> free = field.getFreeAdjacentLocations(location);
        int births = breed();
        for (int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Animal young = createYoung(field, loc);
            newAnimals.add(young);
        }
    }


}
