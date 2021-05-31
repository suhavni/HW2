package io.muic.ooc.fab;


import io.muic.ooc.fab.view.SimulatorView;

import java.util.*;
import java.awt.Color;

public class Simulator {

    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 120;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 80;

    // Lists of animals in the field.
    private List<Actor> actors;
    // The current state of the field.
    private final Field FIELD;
    // The current step of the simulation.
    private int step;
    // A graphical view of the simulation.
    private final SimulatorView VIEW;
    // Random generator
    private static final Random RANDOM = new Random();

    /**
     * Construct a simulation field with default size.
     */
    public Simulator() {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }

    /**
     * Create a simulation field with the given size.
     *
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width) {
        if (width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be >= zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }

        actors = new ArrayList<>();
        FIELD = new Field(depth, width);

        // Create a view of the state of each location in the field.
        VIEW = new SimulatorView(depth, width);
        VIEW.setColor(Rabbit.class, Color.ORANGE);
        VIEW.setColor(Fox.class, Color.CYAN);
        VIEW.setColor(Tiger.class, Color.MAGENTA);
        VIEW.setColor(Hunter.class, Color.BLACK);

        // Setup a valid starting point.
        reset();
    }

    /**
     * Run the simulation from its current state for a reasonably long period
     * (4000 steps).
     */
    public void runLongSimulation() {
        simulate(4000);
    }

    /**
     * Run the simulation for the given number of steps. Stop before the given
     * number of steps if it ceases to be viable.
     *
     * @param numSteps The number of steps to run for.
     */
    public void simulate(int numSteps) {
        for (int step = 1; step <= numSteps && VIEW.isViable(FIELD); step++) {
            simulateOneStep();
            // delay(60);   // uncomment this to run more slowly
        }
    }

    /**
     * Run the simulation from its current state for a single step. Iterate over
     * the whole field updating the state of each fox and rabbit.
     */
    public void simulateOneStep() {
        step++;

        // Provide space for newborn rabbits.
        List<Actor> newActors = new ArrayList<>();
        // Let all rabbits act.
        for (Iterator<Actor> it = actors.iterator(); it.hasNext();) {
            Actor actor = it.next();
            actor.nextStep(newActors);
            if (!actor.isAlive()) {
                it.remove();
            }
        }

        // Add the newly born foxes and rabbits to the main lists.
        actors.addAll(newActors);

        VIEW.showStatus(step, FIELD);
    }

    /**
     * Reset the simulation to a starting position.
     */
    public void reset() {
        step = 0;
        actors.clear();
        Location hunterLoc = new Location(RANDOM.nextInt(FIELD.getDepth()), RANDOM.nextInt(FIELD.getWidth()));
        Actor hunter = ActorFactory.createActor(ActorType.HUNTER, false, FIELD, hunterLoc);
        actors.add(hunter);
        populate();

        // Show the starting state in the view.
        VIEW.showStatus(step, FIELD);
    }

    /**
     * Randomly populate the field with foxes and rabbits.
     */
    private void populate() {
        
        FIELD.clear();
        for (int row = 0; row < FIELD.getDepth(); row++) {
            for (int col = 0; col < FIELD.getWidth(); col++) {
                for (ActorType actorType : ActorType.values()) {
                    if (actorType.equals(ActorType.HUNTER)) { continue; }
                    if (RANDOM.nextDouble() <= actorType.getCreationProbability()) {
                        Location location = new Location(row, col);
                        Actor animal = ActorFactory.createActor(actorType, true, FIELD, location);
                        actors.add(animal);
                        break;
                    }
                }
                // else leave the location empty.
            }
        }
    }

    /**
     * Pause for a given time.
     *
     * @param millisec The time to pause for, in milliseconds
     */
    private void delay(int millisec) {
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException ie) {
            // wake up
        }
    }
}
