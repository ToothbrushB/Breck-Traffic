package com.cli.brecktraffic;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;
import static com.cli.brecktraffic.Constants.PIXELS_PER_METER;

public class Vehicle extends Rectangle {
    private final double length;
    private final double acceleration;
    private List<VehicleQueue> poiQueues;
    private VehicleQueue currentRoad;

    public Vehicle(double length, double acceleration, List<VehicleQueue> poiQueues) {
        this.length = length;
        this.acceleration = acceleration;
        this.poiQueues = poiQueues;
        setFill(Color.TRANSPARENT);
        setStroke(Color.BLACK);
        setHeight(Constants.CAR_HEIGHT);
        setWidth(length*PIXELS_PER_METER);
    }

    public double getLength() {
        return length;
    }

    public void setCurrentRoad(VehicleQueue currentRoad) {
        this.currentRoad = currentRoad;
        poiQueues.subList(poiQueues.indexOf(currentRoad)+1, poiQueues.size()); // truncate the list to the current position
    }

    public VehicleQueue getCurrentRoad() {
        return currentRoad;
    }

    public VehicleQueue nextRoad() {
        return poiQueues.get(0);
    }
}
