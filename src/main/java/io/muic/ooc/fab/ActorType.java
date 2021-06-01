package io.muic.ooc.fab;

public enum ActorType {
    TIGER(0.012, Tiger.class),
    FOX(0.08, Fox.class),
    RABBIT(0.12, Rabbit.class),
    HUNTER(0.001, Hunter.class);

    private final double creationProbability;
    private final Class<? extends Actor> actorClass;

    ActorType(double creationProbability, Class<? extends Actor> actorClass) {

        this.creationProbability = creationProbability;
        this.actorClass = actorClass;
    }

    public double getCreationProbability() {
        return creationProbability;
    }

    public Class<? extends Actor> getActorClass() { return actorClass; }
}
