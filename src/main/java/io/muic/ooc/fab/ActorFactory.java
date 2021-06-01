package io.muic.ooc.fab;

import java.lang.reflect.InvocationTargetException;

public class ActorFactory {
    public static Actor createActor(ActorType actorType, boolean randomAge, Field field, Location location) {
        try {
            return actorType.getActorClass()
                    .getConstructor(boolean.class, Field.class, Location.class)
                    .newInstance(randomAge, field, location);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
