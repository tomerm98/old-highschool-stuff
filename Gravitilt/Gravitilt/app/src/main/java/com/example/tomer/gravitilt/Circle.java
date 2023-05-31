package com.example.tomer.gravitilt;

/**
 * Created by Tomer on 23/04/2017.
 */

public class Circle extends Entity {
    private float radius;
    public Circle(float radius) {
        super();
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}