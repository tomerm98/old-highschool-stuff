package com.example.tomer.gravitilt;

/**
 * Created by Tomer on 23/04/2017.
 */

public class Score implements Comparable {
    private float time;
    private String name;

    public Score(String name,float time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    @Override
    public int compareTo(Object another) {
        float difference = this.time - ((Score)another).getTime();
        if (difference > 0) return 1;
        if (difference <0 ) return -1;
        return 0;
    }
}