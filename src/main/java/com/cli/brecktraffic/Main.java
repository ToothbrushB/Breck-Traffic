package com.cli.brecktraffic;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {
    private static final long TICK_TIME = 20; // ms
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane root = new Pane();
        primaryStage.setTitle("Traffic Simulator");
        primaryStage.setScene(new Scene(root, 1000, 1000));
        primaryStage.show();

//        Rectangle car = new Rectangle(50, 50, 100, 100);
//        car.setFill(Color.TRANSPARENT);
//        car.setStroke(Color.BLACK);
//
//        root.getChildren().add(car);

        VehicleQueue q1 = new VehicleQueue(10, 20, Approach.NORTH);
        VehicleQueue q3 = new VehicleQueue(10, 20, Approach.SOUTH);
        VehicleQueue q4 = new VehicleQueue(10, 20, Approach.EAST);
        EntranceVehicleQueue q2 = new EntranceVehicleQueue(10, 20, 2, () -> {
            double rand = Math.random();
            if (rand < 1/3.0)
                return new Vehicle(2, 2, List.of(q1));
            else if (rand < 2/3.0)
                return new Vehicle(2, 2, List.of(q3));
            else
                return new Vehicle(2, 2, List.of(q4));

        }, Approach.WEST);


        Intersection i = Intersection.fromQueues(q1, q2, q3, q4);

        RoadSystem system = new RoadSystem(List.of(i));
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                system.update();
//                com.cli.brecktraffic.Timer.increment();
//            }
//        };
//
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(task, 0, TICK_TIME);
        root.getChildren().add(i);
    }
}