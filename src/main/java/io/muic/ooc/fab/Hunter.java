package io.muic.ooc.fab;

import java.util.List;

public class Hunter extends Actor {
    public Hunter(Field field, Location location) {
        setAlive(true);
        this.field = field;
        setLocation(location);
    }


    @Override
    protected Location generateNewLocation() {
        Location newLocation = huntAnimal();
        if (newLocation == null) {
            // No animal found - try to move to a free location.
            newLocation = field.freeAdjacentLocation(location);
        }
        return newLocation;
    }

    @Override
    protected void nextStep(List<Actor> newActors) {
        Location newLocation = generateNewLocation();
        setLocation(newLocation);
    }

    private Location huntAnimal() {
        List<Location> adjacent = field.adjacentLocations(location);
        for (Location where : adjacent) {
            Object actor = field.getObjectAt(where);
            if ((actor instanceof Animal)) {
                Animal animal = (Animal) actor;
                animal.setDead();
            }
        }
        return null;
    }
}
