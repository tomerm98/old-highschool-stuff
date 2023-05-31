package com.example.tomer.gravitilt;

import android.graphics.PointF;

import static java.lang.Math.PI;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 * Created by Tomer on 23/04/2017.
 */

public class GeoVector{
    /*
    * This class represents a two-dimensional geometric vector and is suited to be used
    * in the context of physics calculations. The vector is represented by a it's length and it's
    * angle below the right side of the horizon. The angle is in Radians and should always be positive.
    * For example, a vector pointing up will have an angle value of 1.5 PI
    * */
    private float length;
    private float angle; //BELOW right horizon, RAD

    public GeoVector(float length,float angle) {

        this.length = length;
        this.angle = angle;
    }
    public  GeoVector()
    {
        length = 0;
        angle = 0;
    }
    public float getXcomponent()
    {
        return length * (float)cos(angle);
    }
    public float getYcomponent()
    {
        return length * (float)sin(angle);
    }

    public static GeoVector add(GeoVector v1, GeoVector v2)
    {
        float newXcomp = v1.getXcomponent() + v2.getXcomponent();
        float newYcomp = v1.getYcomponent() + v2.getYcomponent();
        return generateFromComponents(newXcomp,newYcomp);
    }

    public static float getAngleFromTwoPoints(PointF p1, PointF p2)
    {
        float xComp = p2.x - p1.x;
        float yComp = p2.y - p1.y;
        return getAngleFromTwoComponents(xComp,yComp);
    }
    public static float getAngleFromTwoComponents(float x, float y)
    {
        float angle = (float)atan2(y,x);
        if (angle < 0)
            angle += 2 *(float)PI;
        return angle;
    }



    public static float getDistanceFromTwoPoints(PointF p1, PointF p2) {
        return (float)sqrt(pow(p1.x - p2.x, 2) + pow(p1.y - p2.y, 2));
    }


    public static GeoVector generateFromComponents(float x, float y)
    {
        GeoVector vector = new GeoVector();
        vector.setLength((float)(sqrt(x*x + y*y)));
        vector.setAngle(getAngleFromTwoComponents(x,y));
        return vector;
    }


    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}