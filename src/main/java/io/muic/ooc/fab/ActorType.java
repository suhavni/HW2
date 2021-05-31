package io.muic.ooc.fab;

public enum ActorType {
    FOX(0.08),
    RABBIT(0.02),
    TIGER(0.015),
    HUNTER(0);

    private final double CREATION_PROBABILITY;

    ActorType(double creationProbability) { this.CREATION_PROBABILITY = creationProbability; }
    public double getCreationProbability() { return CREATION_PROBABILITY; }
}
