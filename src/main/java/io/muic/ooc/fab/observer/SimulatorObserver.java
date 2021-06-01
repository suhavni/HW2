package io.muic.ooc.fab.observer;

import io.muic.ooc.fab.Field;
import io.muic.ooc.fab.view.SimulatorView;

public class SimulatorObserver implements Observer {
    SimulatorView view;

    public SimulatorObserver(SimulatorView view) {
        this.view = view;
    }

    @Override
    public void update(int step, Field field) {
        view.showStatus(step, field);
    }
}
