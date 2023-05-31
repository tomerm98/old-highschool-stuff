package com.example.tomer.gravitilt;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Tomer on 23/04/2017.
 */

public class Entity{
    //UNITS ARE IN DP
    private float locationX;
    private float locationY;
    private float velocityX;
    private float velocityY;
    private float accelerationX;
    private float accelerationY;
    private float mass;
    /*if the below boolean is false, the entity will not be accelerated from gravity forces
    and will not lose it's velocity from friction. however, it will still inflict a gravity force
    on other entities.
    */
    private boolean affectedByExternalForcesAndFriction;
    private GeoVector netForce;//the total force applied to this entity
    private Paint paint;



    public Entity()
    {
        locationX = 0;
        locationY = 0;
        velocityX = 0;
        velocityY = 0;
        accelerationX = 0;
        accelerationY = 0;
        mass =1;
        paint = new Paint();
        paint.setColor(Color.BLACK);
        netForce = new GeoVector();
        affectedByExternalForcesAndFriction = true;
    }

    public boolean isAffectedByExternalForcesAndFriction(){return affectedByExternalForcesAndFriction;}

    public void setAffectedByExternalForcesAndFriction(boolean affectedByExternalForcesAndFriction) {
        this.affectedByExternalForcesAndFriction = affectedByExternalForcesAndFriction;
    }



    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public GeoVector getNetForce() {
        return netForce;
    }

    public void setNetForce(GeoVector netForce) {
        this.netForce = netForce;
    }

    public float getLocationX() {
        return locationX;
    }

    public void setLocationX(float locationX) {
        this.locationX = locationX;
    }

    public float getLocationY() {
        return locationY;
    }

    public void setLocationY(float locationY) {
        this.locationY = locationY;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    public float getAccelerationX() {
        return accelerationX;
    }

    public void setAccelerationX(float accelerationX) {
        this.accelerationX = accelerationX;
    }

    public float getAccelerationY() {
        return accelerationY;
    }

    public void setAccelerationY(float accelerationY) {
        this.accelerationY = accelerationY;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }
}