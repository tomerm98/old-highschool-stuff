package com.example.tomer.gravitilt;

import java.util.ArrayList;

/**
 * Created by Tomer on 23/04/2017.
 */

public class Level{

    private int id;
    private Circle mainCircle; //controlled by the user
    private ArrayList<Circle> holes; //obstacles
    private ArrayList<Rectangle> blocks; //obstacles
    private Rectangle endRectangle; //the goal is to get the mainCircle to this Rectangle
    //the spawn values are the initial location of the mainCircle
    private float spawnX;
    private float spawnY;

    public Level(int id, Circle mainCircle, ArrayList<Circle> holes, ArrayList<Rectangle> blocks, Rectangle endRectangle) {
        this.id = id;
        this.mainCircle = mainCircle;
        this.holes = holes;
        this.blocks = blocks;
        this.endRectangle = endRectangle;
        this.spawnX = mainCircle.getLocationX();
        this.spawnY = mainCircle.getLocationY();
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Circle getMainCircle() {
        return mainCircle;
    }

    public void setMainCircle(Circle mainCircle) {
        this.mainCircle = mainCircle;
    }

    public ArrayList<Circle> getHoles() {
        return holes;
    }

    public void setHoles(ArrayList<Circle> holes) {
        this.holes = holes;
    }

    public ArrayList<Rectangle> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<Rectangle> blocks) {
        this.blocks = blocks;
    }

    public Rectangle getEndRectangle() {
        return endRectangle;
    }

    public void setEndRectangle(Rectangle endRectangle) {
        this.endRectangle = endRectangle;
    }

    public float getSpawnX() {
        return spawnX;
    }

    public void setSpawnX(float spawnX) {
        this.spawnX = spawnX;
    }

    public float getSpawnY() {
        return spawnY;
    }

    public void setSpawnY(float spawnY) {
        this.spawnY = spawnY;
    }


}