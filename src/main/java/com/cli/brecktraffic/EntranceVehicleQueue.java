package com.cli.brecktraffic;

import java.util.function.Supplier;

public class EntranceVehicleQueue extends VehicleQueue {
    private Supplier<Vehicle> carSupplier;
    private long rate;

    public EntranceVehicleQueue(double capacity, double length, long rate, Supplier<Vehicle> carSupplier, Approach approach) {
        super(capacity, length, approach);
        this.rate = rate;
        this.carSupplier = carSupplier;
    }

    public void newCar() {
        addCar(carSupplier.get());
    }

    @Override
    public void update() {
        super.update();
        if (Timer.getTime() % rate == 0) {
            newCar();
        }
    }
}
