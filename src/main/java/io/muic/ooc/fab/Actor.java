package io.muic.ooc.fab;

import java.util.List;
import java.util.Random;

public abstract class Actor {
    // Whether the actor is alive or not.
    protected boolean alive;

    // The actor's position.
    protected Location location;
    // The field occupied.
    protected Field field;

    protected abstract Location generateNewLocation();

    protected static final Random RANDOM = new Random();

    /**
     * Check whether the actor is alive or not.
     *
     * @return true if the actor is still alive.
     */
    public boolean isAlive() {
        return alive;
    }


    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Return the actor's location.
     *
     * @return The actor's location.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Place the actor at the new location in the given field.
     *
     * @param newLocation The actor's new location.
     */
    protected void setLocation(Location newLocation) {
        if (location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }

    protected abstract void nextStep(List<Actor> newActors);
}
