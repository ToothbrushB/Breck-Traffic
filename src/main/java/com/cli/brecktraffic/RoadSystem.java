package com.cli.brecktraffic;

import java.util.List;

public class RoadSystem implements Updatable{
    private List<Intersection> intersections;

    public RoadSystem(List<Intersection> intersections) {
        this.intersections = intersections;
    }

    @Override
    public void update() {
        intersections.forEach(Intersection::update);
    }
}
