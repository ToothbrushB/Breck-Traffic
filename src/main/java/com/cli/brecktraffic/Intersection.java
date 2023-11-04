package com.cli.brecktraffic;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class Intersection extends Pane implements Updatable{
    private final ArrayList<VehicleQueue> roads = new ArrayList<>();
    private double ewDist;
    private double nsDist;
    private int i = 0;

    public static Intersection fromQueues(VehicleQueue... queues) {
        Intersection i = new Intersection();
        if (queues.length != 4)
            throw new IllegalArgumentException("You must pass exactly 4 roads in the order NESW");
        i.roads.addAll(List.of(queues));
        queues[0].setLayoutY(-200);
        queues[1].setLayoutX(200);
        queues[2].setLayoutY(200);
        queues[3].setLayoutX(-200);

        i.getChildren().addAll(queues[0]);
        i.setLayoutX(500);
        i.setLayoutY(500);
        return i;
    }

    private boolean moveVehicle(VehicleQueue take, VehicleQueue give) {
        if (take.peek() != null && roads.contains(take) && roads.contains(give) && give.canAddVehicle(take.peek())) {
            give.addCar(take.poll());
            return true;
        }
        return false;

    }
    @Override
    public void update() {
        roads.forEach(VehicleQueue::update);
        Vehicle v = roads.get(i).peek();
//        if (Timer.getTime() % 20 == 0) {
            if (v != null && roads.contains(v.nextRoad())) {
                moveVehicle(v.getCurrentRoad(), v.nextRoad());
            }
            i = ++i % roads.size();

//        }
    }
}
