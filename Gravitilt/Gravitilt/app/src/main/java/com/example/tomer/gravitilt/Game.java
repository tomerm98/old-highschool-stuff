package com.example.tomer.gravitilt;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;

import java.util.ArrayList;

/**
 * This is the global class for the game, Everything is static.
 * The class contains constants, useful methods and most importantly the state of the game,
 * which is a list of all the entities that are currently in the game. This class also has all
 * the necessary physics methods for calculating the properties of the entities after a frame passed.
 *
 *
 */

public class Game {
    private static ArrayList<Entity> entities = new ArrayList<Entity>();

    public static enum Amount {
        Low(150),
        Medium(300),
        High(450);


        private final float value;

        Amount(final float newValue) {
            value = newValue;
        }

        public float getValue() {
            return value;
        }
    }

    public static final float G = 1000; //the gravitational constant for newton's formula
    public static final float TERMINAL_VELOCITY = 1000; //maximum achievable velocity
    public static final float UNIVERSAL_FRICTION_MULTIPLIER = 0.7F; // speed decay per second
    public static  float timeFactor = 1; // for slow/fast motion
    public static  Amount generalGravityFactor = Amount.Medium; //basically the tilt sensitivity
    public static GeoVector generalGravityField = new GeoVector(); //caused by device rotation
    public static final String GAME_MODE_LEVEL_PRACTICE = "level_practice";
    public static final String GAME_MODE_SPEED_RUN = "speed_run";
    public static final String GAME_MODE_EXTRA_NAME = "gamemode_extra";
    public static final String LEVEL_ID_EXTRA_NAME = "level_id";
    public static final String SPEED_RUN_SCORE_EXTRA_NAME = "score";


    public static void calcEntitiesAttributes(float elapsedSeconds) {
        //calculates the new properties of the entities after a frame passed
        elapsedSeconds *= timeFactor;
        for (Entity entity : entities) {
            if (entity.isAffectedByExternalForcesAndFriction()) {
                calcNetForce(entity);
            }
            calcAcceleration(entity);
            calcVelocityAndLocation(entity, elapsedSeconds);
        }
    }

    private static void calcNetForce(Entity entity)
    {
        /*this methods calculates the total force that is applied to this entity
        from all other entities.*/
        GeoVector newNetForce = new GeoVector();

        //calculate the force applied from the general gravity field
        newNetForce.setAngle(generalGravityField.getAngle());
        //formula: force = field * mass
        newNetForce.setLength(generalGravityField.getLength() * entity.getMass());

        //calculating the gravity force from all other entities:
        float m1 = entity.getMass();
        float m2; //other entity mass
        float r; //distance between the two entities
        PointF p1 = new PointF(entity.getLocationX(),entity.getLocationY());;
        PointF p2; // other entity location
        //the force applied from the other entity
        GeoVector tempForce = new GeoVector();

        for (Entity other : entities)
        {
            p2 = new PointF(other.getLocationX(),other.getLocationY());
            r = GeoVector.getDistanceFromTwoPoints(p1,p2);
            if (r == 0)
                continue;
            m2 = other.getMass();
            tempForce.setAngle(GeoVector.getAngleFromTwoPoints(p1,p2));
            //newton's gravitational formula:
            tempForce.setLength(G*m1*m2 / r);
            //add the new force to the total force vector
            newNetForce = GeoVector.add(newNetForce,tempForce);
        }
        entity.setNetForce(newNetForce);
    }
    private static void calcAcceleration(Entity entity)
    {
        //formula: F = m * a
        float forceX = entity.getNetForce().getXcomponent();
        float forceY = entity.getNetForce().getYcomponent();
        entity.setAccelerationX(forceX / entity.getMass());
        entity.setAccelerationY(forceY / entity.getMass());
    }
    private static void calcVelocityAndLocation(Entity entity,float elapsedSeconds) {
        float x0, v0, a, dt = elapsedSeconds; //physics notation
        //newtonian mechanics formula for location with constant acceleration
        x0 = entity.getLocationX();
        v0 = entity.getVelocityX();
        a = entity.getAccelerationX();
        entity.setLocationX(x0 + v0 * dt + (a * dt * dt) / 2F);
        entity.setVelocityX(v0 + a * dt);

        x0 = entity.getLocationY();
        v0 = entity.getVelocityY();
        a = entity.getAccelerationY();
        entity.setLocationY(x0 + v0 * dt + (a * dt * dt) / 2F);
        entity.setVelocityY(v0 + a * dt);

        if (entity.isAffectedByExternalForcesAndFriction())
            reduceVelocityDueToFriction(entity, elapsedSeconds);
        fixTerminalVelocityDeviation(entity);
    }
    private static void reduceVelocityDueToFriction(Entity entity, float elapsedSeconds)
    {
        // mathematical decay formula
        float velocityMultiplier = (float)Math.pow(UNIVERSAL_FRICTION_MULTIPLIER,elapsedSeconds);
        float newVelX = entity.getVelocityX() * velocityMultiplier;
        float newVelY = entity.getVelocityY() * velocityMultiplier;
        entity.setVelocityX(newVelX);
        entity.setVelocityY(newVelY);
    }

    private static void fixTerminalVelocityDeviation(Entity entity)
    {
        if (entity.getVelocityX() > TERMINAL_VELOCITY)
            entity.setVelocityX(TERMINAL_VELOCITY);
        else if (entity.getVelocityX() < -TERMINAL_VELOCITY)
            entity.setVelocityX(-TERMINAL_VELOCITY);

        if (entity.getVelocityY() > TERMINAL_VELOCITY)
            entity.setVelocityY(TERMINAL_VELOCITY);
        else if (entity.getVelocityY() < -TERMINAL_VELOCITY)
            entity.setVelocityY(-TERMINAL_VELOCITY);
    }

    public static void fixOffBorderEntities(float borderWidth, float borderHeight)
    {
        //prevents the entity from going off the screen
        for (Entity entity : entities) {
            if (entity instanceof Circle)
                fixOffBorderCircle((Circle) entity, borderWidth, borderHeight);
            else if (entity instanceof  Rectangle)
                fixOffBorderRectangle((Rectangle)entity, borderWidth,borderHeight);
        }
    }

    private static void fixOffBorderRectangle(Rectangle rectangle, float borderWidth, float borderHeight) {
        float velX = rectangle.getVelocityX();
        float velY = rectangle.getVelocityY();
        float locX = rectangle.getLocationX();
        float locY = rectangle.getLocationY();
        float width = rectangle.getWidth();
        float height = rectangle.getHeight();
        if ((locX <= 0 && velX <0) ||
                (locX +width >= borderWidth && velX >0)) {
            rectangle.setVelocityX(-velX);
        }
        if ((locY <= 0 && velY <0) ||
                (locY + height >= borderHeight && velY >0)) {
            rectangle.setVelocityY(-velY);
        }
    }

    private static void fixOffBorderCircle(Circle circle, float borderWidth, float borderHeight) {
        float velX = circle.getVelocityX();
        float velY = circle.getVelocityY();
        float locX = circle.getLocationX();
        float locY = circle.getLocationY();
        float rad = circle.getRadius();
        if ((locX - rad <= 0 && velX < 0) ||
                (locX + rad >= borderWidth && velX > 0)) {
            circle.setVelocityX(-velX);
        }
        if ((locY - rad <= 0 && velY <0)||
                (locY + rad >= borderHeight && velY >0))
            circle.setVelocityY(-velY);


    }

    public static void calcGeneralGravity(float rotationX, float rotationY)
    {
        //this method should be called whenever the device rotates
        float gravityX = rotationX * generalGravityFactor.getValue();
        float gravityY = rotationY * generalGravityFactor.getValue();
        generalGravityField = GeoVector.generateFromComponents(gravityX,gravityY);


    }

    public static boolean isEntitySwallowed(Entity entity, Entity other)
    {
        //returns true if the first entity is inside the other entity
        if (entity instanceof Circle && other instanceof Circle)
            return isCircleSwallowedByCircle((Circle)entity, (Circle)other);
        if (entity instanceof Circle && other instanceof Rectangle)
            return isCircleSwallowedByRectangle((Circle)entity, (Rectangle)other);
        if (entity instanceof Rectangle && other instanceof Circle)
            return isRectangleSwallowedByCircle((Rectangle)entity, (Circle)other);
        if (entity instanceof Rectangle && other instanceof Rectangle)
            return isRectangleSwallowedByRectangle((Rectangle)entity, (Rectangle)other);

        return false;
    }

    private static boolean isRectangleSwallowedByRectangle(Rectangle rectangle, Rectangle other) {
        float centerX = (rectangle.getLocationX() + rectangle.getWidth()) /2;
        float centerY = (rectangle.getLocationY() + rectangle.getHeight()) /2;
        float otherLeft = other.getLocationX();
        float otherRight = other.getLocationX() + other.getWidth();
        float otherTop = other.getLocationY();
        float otherBottom = other.getLocationY() + other.getHeight();
        return centerX >= otherLeft && centerX <= otherRight &&
                centerY >= otherTop && centerY <= otherBottom;

    }

    private static boolean isRectangleSwallowedByCircle(Rectangle rectangle, Circle other) {
        float centerX = (rectangle.getLocationX() + rectangle.getWidth()) /2;
        float centerY = (rectangle.getLocationY() + rectangle.getHeight()) /2;
        PointF center = new PointF(centerX,centerY);
        PointF otherCenter = new PointF(other.getLocationX(), other.getLocationY());
        float distance = GeoVector.getDistanceFromTwoPoints(center,otherCenter);
        return distance <= other.getRadius();
    }

    private static boolean isCircleSwallowedByRectangle(Circle circle, Rectangle other) {
        float radius = circle.getRadius();
        float centerX = circle.getLocationX();
        float centerY = circle.getLocationY();
        float otherLeft = other.getLocationX();
        float otherRight = other.getLocationX() + other.getWidth();
        float otherTop = other.getLocationY();
        float otherBottom = other.getLocationY() + other.getHeight();
        boolean rightHit = centerX + radius >= otherLeft && centerX+radius <= otherRight
                && centerY >= otherTop && centerY <=otherBottom;
        boolean leftHit = centerX - radius >= otherLeft && centerX-radius <= otherRight
                && centerY >= otherTop && centerY <=otherBottom;
        boolean topHit = centerY + radius >= otherTop && centerY+radius <= otherBottom
                && centerX >= otherLeft && centerX <=otherRight;
        boolean bottomHit = centerY - radius >= otherTop && centerY-radius <= otherBottom
                && centerX >= otherLeft && centerX <=otherRight;

        return rightHit || leftHit || topHit || bottomHit;
    }

    private static boolean isCircleSwallowedByCircle(Circle circle, Circle other)
    {
        PointF circleLoc = new PointF(circle.getLocationX(),circle.getLocationY());
        PointF otherLoc = new PointF(other.getLocationX(),other.getLocationY());
        float distance = GeoVector.getDistanceFromTwoPoints(circleLoc,otherLoc);
        return distance <= other.getRadius();

    }




    public static void add(Entity entity) {
        entities.add(entity);
    }

    public static void clear() {
        entities.clear();
    }

    public static ArrayList<Entity> getAllEntities() {
        return entities;
    }

    public static Entity getEntityAt(int index) {
        return entities.get(index);
    }


    public static float pxToDp(float px, Context context) {
        //converts a pixel value to dp value according to the device DPI
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return  Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
    public static float dpToPx(float dp,Context context) {
        //converts a dp value to pixel value according to the device DPI
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

}