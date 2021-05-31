package io.muic.ooc.fab;

public enum ActorType {
    FOX(0.08),
    RABBIT(0.02),
    TIGER(0.015),
    HUNTER(0);

    private double creationProbability;

    ActorType(double creationProbability) { this.creationProbability = creationProbability; }
    public double getCreationProbability() { return creationProbability; }
}
