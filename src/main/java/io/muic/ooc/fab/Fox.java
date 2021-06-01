package io.muic.ooc.fab;

public class Fox extends Predator {
    // Characteristics shared by all foxes (class variables).

    // The food value of a single fox. In effect, this is the
    // number of steps a tiger can go before it has to eat again.
    protected static final int FOOD_VALUE = 14;
    // The age at which a fox can start to breed.
    private static final int BREEDING_AGE = 15;
    // The age to which a fox can live.
    private static final int MAX_AGE = 150;
    // The likelihood of a fox breeding.
    private static final double BREEDING_PROBABILITY = 0.08;

    /**
     * Create a fox. A fox can be created as a new born (age zero and not
     * hungry) or with a random age and food level.
     *
     * @param randomAge If true, the fox will have random age and hunger level.
     * @param field     The field currently occupied.
     * @param location  The location within the field.
     */
    public Fox(boolean randomAge, Field field, Location location) {
        super(randomAge, field, location);
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
        return new Fox(false, field, location);
    }

    @Override
    protected int getFoodValue() {
        return FOOD_VALUE;
    }
}
