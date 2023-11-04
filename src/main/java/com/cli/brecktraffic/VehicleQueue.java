package com.cli.brecktraffic;

import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.LinkedList;
import java.util.Queue;

public class VehicleQueue extends Pane implements Updatable {
    private double capacity;
    private double length;
    private Approach approach;
    private Rectangle road = new Rectangle();
    private Queue<Vehicle> vehicles = new LinkedList<>() {
    };

    public VehicleQueue(double capacity, double length, Approach approach) {
        this.capacity = capacity;
        this.length = length;
        this.approach = approach;

        Label label = new Label(approach.toString() + " Approach");
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.CENTER);
        if (approach == Approach.NORTH || approach == Approach.SOUTH) {
            road.setWidth(Constants.QUEUE_WIDTH);
            road.setHeight(Constants.PIXELS_PER_METER * length);
            label.setLayoutX(Constants.QUEUE_WIDTH/2.0);
            label.setLayoutY(Constants.PIXELS_PER_METER * length/2.0);

        } else {
            road.setHeight(Constants.QUEUE_WIDTH);
            road.setWidth(Constants.PIXELS_PER_METER * length);
            label.setLayoutY(Constants.QUEUE_WIDTH/2.0);
            label.setLayoutX(Constants.PIXELS_PER_METER * length);

        }
        road.setFill(Color.TRANSPARENT);
        road.setStroke(Color.RED);
        getChildren().addAll(road, label);
    }

    protected void addCar(Vehicle v) {
        v.setCurrentRoad(this);
        vehicles.add(v);

        if (approach == Approach.NORTH || approach == Approach.SOUTH) { // flip the width and height for N/S approach
            double oldWidth = v.getWidth();
            v.setWidth(v.getHeight());
            v.setHeight(oldWidth);
        }
        getChildren().add(v);
    }

    public Vehicle poll() {
        return vehicles.poll();
    }
    public Vehicle peek() {
        return vehicles.peek();
    }

    public boolean canAddVehicle(Vehicle toCheck) {
        double sum = toCheck.getLength();
        for (Vehicle v :
                vehicles) {
            sum += v.getLength();
        }
        return sum < capacity;
    }
    @Override
    public void update() {

    }
}
