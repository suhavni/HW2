package io.muic.ooc.fab;

import java.util.List;

public abstract class Actor {
    // Whether the animal is alive or not.
    protected boolean alive;

    // The fox's position.
    protected Location location;
    // The field occupied.
    protected Field field;

    protected abstract Location generateNewLocation();

    /**
     * Check whether the animal is alive or not.
     *
     * @return true if the animal is still alive.
     */
    public boolean isAlive() {
        return alive;
    }


    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Return the fox's location.
     *
     * @return The fox's location.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Place the rabbit at the new location in the given field.
     *
     * @param newLocation The rabbit's new location.
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
