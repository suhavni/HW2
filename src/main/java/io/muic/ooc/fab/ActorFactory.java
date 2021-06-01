package io.muic.ooc.fab;

public class ActorFactory {
    public static Actor createActor(ActorType actorType, boolean randomAge, Field field, Location location) {
        switch (actorType) {
            case FOX:
                return new Fox(randomAge, field, location);
            case RABBIT:
                return new Rabbit(randomAge, field, location);
            case TIGER:
                return new Tiger(randomAge, field, location);
            case HUNTER:
                return new Hunter(field, location);
            default:
                throw new IllegalArgumentException("INVALID ACTOR TYPE");
        }
    }
}
