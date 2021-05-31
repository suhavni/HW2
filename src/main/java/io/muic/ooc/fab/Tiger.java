package io.muic.ooc.fab;

public class Tiger extends Predator {
    // Characteristics shared by all foxes (class variables).

    // The age at which a fox can start to breed.
    private static final int BREEDING_AGE = 25;
    // The age to which a fox can live.
    private static final int MAX_AGE = 333;
    // The likelihood of a fox breeding.
    private static final double BREEDING_PROBABILITY = 0.012;

    // The food value of a single fox. In effect, this is the
    // number of steps a tiger can go before it has to eat again.
    protected static final int FOX_FOOD_VALUE = 14;

    /**
     * Create a fox. A fox can be created as a new born (age zero and not
     * hungry) or with a random age and food level.
     *
     * @param randomAge If true, the fox will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Tiger(boolean randomAge, Field field, Location location) {
        super(randomAge, field, location);
    }

    @Override
    protected boolean eatPrey(Object animal) {
        if (super.eatPrey(animal)) { return true; }
        if (animal instanceof Fox) {
            Fox fox = (Fox) animal;
            if (fox.isAlive()) {
                fox.setDead();
                foodLevel = FOX_FOOD_VALUE;
                return true;
            }
        }
        return false;
    }


    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected double getBreedingProbability() {
        return BREEDING_PROBABILITY;
    }

    @Override
    protected int getMaxLitterSize() {
        return MAX_LITTER_SIZE;
    }

    @Override
    protected int getBreedingAge() {
        return BREEDING_AGE;
    }

    @Override
    protected Animal createYoung(Field field, Location location) {
        return new Tiger(false, field, location);
    }
}
