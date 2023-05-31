package com.example.tomer.gravitilt;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by Tomer on 23/04/2017.
 */

public class LevelsArchive {
    private float screenWidth;
    private float screenHeight;
    private ArrayList<Level> levels;

    public LevelsArchive(float screenWidth, float screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        levels = new ArrayList<>();
        loadAllLevels();
    }

    private void loadAllLevels() {
        loadLevel_0();
        loadLevel_1();
        loadLevel_2();
        loadLevel_3();
        loadLevel_4();
        loadLevel_5();
        loadLevel_6();


    }

    public Level getLevel(int id) {
        return levels.get(id);
    }


    private void loadLevel_0() {
        Paint bluePaint = new Paint();
        Paint greenPaint = new Paint();
        bluePaint.setColor(Color.BLUE);
        greenPaint.setColor(Color.GREEN);

        float radius = 10;
        Circle mainCircle = new Circle(radius);
        mainCircle.setLocationX(screenWidth / 10);
        mainCircle.setLocationY(screenHeight / 2);
        mainCircle.setPaint(bluePaint);
        mainCircle.setMass(10);

        Circle hole1 = new Circle(35);
        Circle hole2 = new Circle(35);

        hole1.setLocationX(screenWidth / 2);
        hole1.setLocationY(2 * screenHeight / 7);
        hole1.setAffectedByExternalForcesAndFriction(false);
        hole1.setMass(75);
        hole2.setLocationX(screenWidth / 2);
        hole2.setLocationY(5 * screenHeight / 7);
        hole2.setAffectedByExternalForcesAndFriction(false);
        hole2.setMass(75);

        Rectangle endRectangle = new Rectangle(radius + 5, 150);
        endRectangle.setLocationX(screenWidth - (endRectangle.getWidth() * 1));
        endRectangle.setLocationY((screenHeight / 2) - (endRectangle.getHeight() / 2));
        endRectangle.setAffectedByExternalForcesAndFriction(false);
        endRectangle.setPaint(greenPaint);

        ArrayList<Circle> holes = new ArrayList<>();
        ArrayList<Rectangle> blocks = new ArrayList<>();

        holes.add(hole1);
        holes.add(hole2);

        levels.add(new Level(0, mainCircle, holes, blocks, endRectangle));

    }

    private void loadLevel_1() {
        Paint bluePaint = new Paint();
        Paint greenPaint = new Paint();
        bluePaint.setColor(Color.BLUE);
        greenPaint.setColor(Color.GREEN);

        float radius = 10;
        Circle mainCircle = new Circle(radius);
        mainCircle.setLocationX(0);
        mainCircle.setLocationY(screenHeight / 2);
        mainCircle.setPaint(bluePaint);
        mainCircle.setMass(10);

        Circle hole1 = new Circle(20);
        Circle hole2 = new Circle(20);
        Circle hole3 = new Circle(20);

        hole1.setLocationX(screenWidth / 4);
        hole1.setLocationY(screenHeight / 7);
        hole1.setAffectedByExternalForcesAndFriction(false);
        hole1.setMass(40);

        hole2.setLocationX(screenWidth / 4);
        hole2.setLocationY(screenHeight / 2);
        hole2.setAffectedByExternalForcesAndFriction(false);
        hole2.setMass(40);

        hole3.setLocationX(screenWidth / 4);
        hole3.setLocationY(6 * screenHeight / 7);
        hole3.setAffectedByExternalForcesAndFriction(false);
        hole3.setMass(40);

        Circle hole4 = new Circle(35);
        Circle hole5 = new Circle(35);

        hole4.setLocationX(screenWidth / 2);
        hole4.setLocationY(2 * screenHeight / 7);
        hole4.setAffectedByExternalForcesAndFriction(false);
        hole4.setMass(60);

        hole5.setLocationX(screenWidth / 2);
        hole5.setLocationY(5 * screenHeight / 7);
        hole5.setAffectedByExternalForcesAndFriction(false);
        hole5.setMass(60);

        Circle hole6 = new Circle(50);
        hole6.setLocationX(3 * screenWidth / 4);
        hole6.setLocationY(screenHeight / 2);
        hole6.setAffectedByExternalForcesAndFriction(false);
        hole6.setMass(80);

        Rectangle endRectangle = new Rectangle(radius + 5, 150);
        endRectangle.setLocationX(screenWidth - (endRectangle.getWidth() * 1));
        endRectangle.setLocationY((screenHeight / 2) - (endRectangle.getHeight()));
        endRectangle.setAffectedByExternalForcesAndFriction(false);
        endRectangle.setPaint(greenPaint);

        ArrayList<Circle> holes = new ArrayList<>();
        ArrayList<Rectangle> blocks = new ArrayList<>();

        holes.add(hole1);
        holes.add(hole2);
        holes.add(hole3);
        holes.add(hole4);
        holes.add(hole5);
        holes.add(hole6);

        levels.add(new Level(1, mainCircle, holes, blocks, endRectangle));

    }

    private void loadLevel_2() {
        Paint bluePaint = new Paint();
        Paint greenPaint = new Paint();
        bluePaint.setColor(Color.BLUE);
        greenPaint.setColor(Color.GREEN);

        float mainRadius = 10;
        Circle mainCircle = new Circle(mainRadius);
        mainCircle.setLocationX(0);
        mainCircle.setLocationY(0);
        mainCircle.setPaint(bluePaint);
        mainCircle.setMass(10);

        float holeRadius = 30;
        float holeMass = 60;
        float holeVelocityY = 500;
        boolean holeAffected = false;

        Circle hole1 = new Circle(holeRadius);
        Circle hole2 = new Circle(holeRadius);
        Circle hole3 = new Circle(holeRadius);
        Circle hole4 = new Circle(holeRadius);

        hole1.setLocationX(screenWidth / 5);
        hole1.setLocationY(screenHeight - hole1.getRadius());
        hole1.setAffectedByExternalForcesAndFriction(holeAffected);
        hole1.setMass(holeMass);
        hole1.setVelocityY(holeVelocityY);

        hole2.setLocationX(2 * screenWidth / 5);
        hole2.setLocationY(hole2.getRadius());
        hole2.setAffectedByExternalForcesAndFriction(holeAffected);
        hole2.setMass(holeMass);
        hole2.setVelocityY(holeVelocityY);

        hole3.setLocationX(3 * screenWidth / 5);
        hole3.setLocationY(screenHeight - hole3.getRadius());
        hole3.setAffectedByExternalForcesAndFriction(holeAffected);
        hole3.setMass(holeMass);
        hole3.setVelocityY(holeVelocityY);

        hole4.setLocationX(4 * screenWidth / 5);
        hole4.setLocationY(hole4.getRadius());
        hole4.setAffectedByExternalForcesAndFriction(holeAffected);
        hole4.setMass(holeMass);
        hole4.setVelocityY(holeVelocityY);

        Rectangle endRectangle = new Rectangle(70, mainRadius + 5);
        endRectangle.setLocationX(screenWidth - endRectangle.getWidth());
        endRectangle.setLocationY(screenHeight - endRectangle.getHeight());
        endRectangle.setAffectedByExternalForcesAndFriction(false);
        endRectangle.setPaint(greenPaint);
        ArrayList<Circle> holes = new ArrayList<Circle>();
        holes.add(hole1);
        holes.add(hole2);
        holes.add(hole3);
        holes.add(hole4);
        ArrayList<Rectangle> blocks = new ArrayList<>();
        levels.add(new Level(2, mainCircle, holes, blocks, endRectangle));
    }

    private void loadLevel_3() {
        Paint bluePaint = new Paint();
        Paint greenPaint = new Paint();
        Paint redPaint = new Paint();
        redPaint.setColor(Color.RED);
        bluePaint.setColor(Color.BLUE);
        greenPaint.setColor(Color.GREEN);
        float smallBlockHeight = 50;
        float gap = 50;
        float radius = 10;

        Circle mainCircle = new Circle(radius);
        mainCircle.setLocationX(0.5F * screenWidth / 6);
        mainCircle.setLocationY(screenHeight / 2);
        mainCircle.setPaint(bluePaint);
        mainCircle.setMass(10);

        Rectangle big1 = new Rectangle(radius * 2, screenHeight - gap - smallBlockHeight);
        Rectangle big2 = new Rectangle(radius * 2, screenHeight - gap - smallBlockHeight);
        Rectangle big3 = new Rectangle(radius * 2, screenHeight - gap - smallBlockHeight);
        Rectangle big4 = new Rectangle(radius * 2, screenHeight - gap - smallBlockHeight);
        Rectangle big5 = new Rectangle(radius * 2, screenHeight - gap - smallBlockHeight);

        big1.setPaint(redPaint);
        big2.setPaint(redPaint);
        big3.setPaint(redPaint);
        big4.setPaint(redPaint);
        big5.setPaint(redPaint);

        big1.setLocationX(1 * screenWidth / 6);
        big2.setLocationX(2 * screenWidth / 6);
        big3.setLocationX(3 * screenWidth / 6);
        big4.setLocationX(4 * screenWidth / 6);
        big5.setLocationX(5 * screenWidth / 6);

        big1.setLocationY(0);
        big2.setLocationY(screenHeight - big2.getHeight());
        big3.setLocationY(0);
        big4.setLocationY(screenHeight - big4.getHeight());
        big5.setLocationY(0);

        big1.setAffectedByExternalForcesAndFriction(false);
        big2.setAffectedByExternalForcesAndFriction(false);
        big3.setAffectedByExternalForcesAndFriction(false);
        big4.setAffectedByExternalForcesAndFriction(false);
        big5.setAffectedByExternalForcesAndFriction(false);

        Rectangle small1 = new Rectangle(radius * 2, smallBlockHeight);
        Rectangle small2 = new Rectangle(radius * 2, smallBlockHeight);
        Rectangle small3 = new Rectangle(radius * 2, smallBlockHeight);
        Rectangle small4 = new Rectangle(radius * 2, smallBlockHeight);
        Rectangle small5 = new Rectangle(radius * 2, smallBlockHeight);

        small1.setPaint(redPaint);
        small2.setPaint(redPaint);
        small3.setPaint(redPaint);
        small4.setPaint(redPaint);
        small5.setPaint(redPaint);

        small1.setLocationX(1 * screenWidth / 6);
        small2.setLocationX(2 * screenWidth / 6);
        small3.setLocationX(3 * screenWidth / 6);
        small4.setLocationX(4 * screenWidth / 6);
        small5.setLocationX(5 * screenWidth / 6);

        small1.setLocationY(screenHeight - small1.getHeight());
        small2.setLocationY(0);
        small3.setLocationY(screenHeight - small3.getHeight());
        small4.setLocationY(0);
        small5.setLocationY(screenHeight - small5.getHeight());

        small1.setAffectedByExternalForcesAndFriction(false);
        small2.setAffectedByExternalForcesAndFriction(false);
        small3.setAffectedByExternalForcesAndFriction(false);
        small4.setAffectedByExternalForcesAndFriction(false);
        small5.setAffectedByExternalForcesAndFriction(false);

        Rectangle endRectangle = new Rectangle(radius + 5, screenHeight);
        endRectangle.setLocationX(screenWidth - endRectangle.getWidth());
        endRectangle.setLocationY(0);
        endRectangle.setAffectedByExternalForcesAndFriction(false);
        endRectangle.setPaint(greenPaint);

        ArrayList<Circle> holes = new ArrayList<>();
        ArrayList<Rectangle> blocks = new ArrayList<>();
        blocks.add(big1);
        blocks.add(big2);
        blocks.add(big3);
        blocks.add(big4);
        blocks.add(big5);
        blocks.add(small1);
        blocks.add(small2);
        blocks.add(small3);
        blocks.add(small4);
        blocks.add(small5);
        blocks.add(endRectangle);

        levels.add(new Level(3, mainCircle, holes, blocks, endRectangle));

    }

    private void loadLevel_4() {
        Paint bluePaint = new Paint();
        Paint greenPaint = new Paint();
        bluePaint.setColor(Color.BLUE);
        greenPaint.setColor(Color.GREEN);

        float mainRadius = 10;
        Circle mainCircle = new Circle(mainRadius);
        mainCircle.setLocationX(mainRadius);
        mainCircle.setLocationY(mainRadius);
        mainCircle.setPaint(bluePaint);
        mainCircle.setMass(10);

        float bigRadius = 50;
        Circle big = new Circle(bigRadius);
        big.setLocationX(screenWidth / 2);
        big.setLocationY(screenHeight / 2);
        big.setAffectedByExternalForcesAndFriction(false);
        big.setMass(300);

        float smallRadius = 25;
        Circle small1 = new Circle(smallRadius);
        Circle small2 = new Circle(smallRadius);
        Circle small3 = new Circle(smallRadius);
        Circle small4 = new Circle(smallRadius);

        small1.setAffectedByExternalForcesAndFriction(false);
        small2.setAffectedByExternalForcesAndFriction(false);
        small3.setAffectedByExternalForcesAndFriction(false);
        small4.setAffectedByExternalForcesAndFriction(false);

        small1.setLocationX(screenWidth / 2);
        small2.setLocationX(screenWidth - smallRadius);
        small3.setLocationX(screenWidth / 2);
        small4.setLocationX(smallRadius);

        small1.setLocationY(smallRadius);
        small2.setLocationY(screenHeight / 2);
        small3.setLocationY(screenHeight - smallRadius);
        small4.setLocationY(screenHeight / 2);

        float smallVelocityX = 500;
        float time = (screenWidth - 2 * smallRadius) / smallVelocityX;
        float smallVelocityY = (screenHeight - 2 * smallRadius) / time;

        small1.setVelocityX(smallVelocityX);
        small2.setVelocityX(-smallVelocityX);
        small3.setVelocityX(-smallVelocityX);
        small4.setVelocityX(smallVelocityX);

        small1.setVelocityY(smallVelocityY);
        small2.setVelocityY(smallVelocityY);
        small3.setVelocityY(-smallVelocityY);
        small4.setVelocityY(-smallVelocityY);

        Rectangle endRectangle = new Rectangle(mainRadius + 5, bigRadius * 2);
        endRectangle.setLocationX(screenWidth - endRectangle.getWidth());
        endRectangle.setLocationY(screenHeight / 2 - endRectangle.getHeight() / 2);
        endRectangle.setAffectedByExternalForcesAndFriction(false);
        endRectangle.setPaint(greenPaint);

        ArrayList<Circle> holes = new ArrayList<>();
        ArrayList<Rectangle> blocks = new ArrayList<>();

        holes.add(big);
        holes.add(small1);
        holes.add(small2);
        holes.add(small3);
        holes.add(small4);

        levels.add(new Level(4, mainCircle, holes, blocks, endRectangle));
    }

    private void loadLevel_5() {
        Paint bluePaint = new Paint();
        Paint greenPaint = new Paint();
        Paint redPaint = new Paint();
        redPaint.setColor(Color.RED);
        bluePaint.setColor(Color.BLUE);
        greenPaint.setColor(Color.GREEN);

        float mainRadius = 10;
        float bigRadius = 75;
        float bottomGap = 30;
        float rectWidth = 15;

        Circle mainCircle = new Circle(mainRadius);
        mainCircle.setLocationX(mainRadius);
        mainCircle.setLocationY(mainRadius);
        mainCircle.setPaint(bluePaint);
        mainCircle.setMass(10);

        Circle bigHole = new Circle(bigRadius);
        bigHole.setLocationX(screenWidth / 2);
        bigHole.setLocationY(screenHeight / 2);
        bigHole.setMass(150);
        bigHole.setAffectedByExternalForcesAndFriction(false);

        Rectangle holder = new Rectangle(rectWidth, screenHeight / 2 - bigRadius);
        holder.setLocationX(screenWidth / 2 - holder.getWidth() / 2);
        holder.setLocationY(0);
        holder.setAffectedByExternalForcesAndFriction(false);

        Rectangle leftBigSpike = new Rectangle(rectWidth, screenHeight / 2);
        Rectangle rightBigSpike = new Rectangle(rectWidth, screenHeight / 2);

        leftBigSpike.setAffectedByExternalForcesAndFriction(false);
        rightBigSpike.setAffectedByExternalForcesAndFriction(false);
        leftBigSpike.setPaint(redPaint);
        rightBigSpike.setPaint(redPaint);

        leftBigSpike.setLocationX((screenWidth / 2 - bigRadius) / 2 - rectWidth / 2);
        rightBigSpike.setLocationX(screenWidth / 2 + bigRadius + leftBigSpike.getLocationX());
        leftBigSpike.setLocationY(screenHeight - leftBigSpike.getHeight());
        rightBigSpike.setLocationY(screenHeight - rightBigSpike.getHeight());

        Rectangle leftMediumSpike = new Rectangle(rectWidth, screenHeight / 2 - bigRadius / 2);
        Rectangle rightMediumSpike = new Rectangle(rectWidth, screenHeight / 2 - bigRadius / 2);

        leftMediumSpike.setAffectedByExternalForcesAndFriction(false);
        rightMediumSpike.setAffectedByExternalForcesAndFriction(false);
        leftMediumSpike.setPaint(redPaint);
        rightMediumSpike.setPaint(redPaint);

        float leftHoleSide = screenWidth / 2 - bigRadius;
        float sideGap = leftHoleSide - leftBigSpike.getLocationX() - rectWidth;
        float rightHoleSide = screenWidth/2 + bigRadius;

        leftMediumSpike.setLocationX(leftHoleSide - sideGap/2 - rectWidth/2);
        rightMediumSpike.setLocationX(rightHoleSide + sideGap/2 -rectWidth/2);
        leftMediumSpike.setLocationY(screenHeight - leftMediumSpike.getHeight());
        rightMediumSpike.setLocationY(screenHeight - rightMediumSpike.getHeight());

        Rectangle middleSmallSpike = new Rectangle
                (rectWidth, screenHeight / 2 - bigRadius - bottomGap);
        Rectangle leftSmallSpike = new Rectangle
                (rectWidth, screenHeight/2 -bigRadius - bottomGap);
        Rectangle rightSmallSpike = new Rectangle
                (rectWidth, screenHeight/2 -bigRadius - bottomGap);

        middleSmallSpike.setAffectedByExternalForcesAndFriction(false);
        leftSmallSpike.setAffectedByExternalForcesAndFriction(false);
        rightSmallSpike.setAffectedByExternalForcesAndFriction(false);
        middleSmallSpike.setPaint(redPaint);
        rightSmallSpike.setPaint(redPaint);
        leftSmallSpike.setPaint(redPaint);

        float sideGapFromMediumSpike = screenWidth/2 - leftMediumSpike.getLocationX() - rectWidth;
        middleSmallSpike.setLocationX(screenWidth / 2 - rectWidth / 2);
        leftSmallSpike.setLocationX(screenWidth/2 - sideGapFromMediumSpike/2 - rectWidth/2 );
        rightSmallSpike.setLocationX(screenWidth/2 + sideGapFromMediumSpike/2 - rectWidth/2 );

        middleSmallSpike.setLocationY(screenHeight - middleSmallSpike.getHeight());
        leftSmallSpike.setLocationY(screenHeight - leftSmallSpike.getHeight());
        rightSmallSpike.setLocationY(screenHeight - rightSmallSpike.getHeight());


        Rectangle endRectangle = new Rectangle
                (screenWidth / 2 - holder.getWidth() / 2, mainRadius + 5);
        endRectangle.setLocationX(screenWidth - endRectangle.getWidth());
        endRectangle.setLocationY(0);
        endRectangle.setAffectedByExternalForcesAndFriction(false);
        endRectangle.setPaint(greenPaint);

        ArrayList<Circle> holes = new ArrayList<>();
        ArrayList<Rectangle> blocks = new ArrayList<>();

        holes.add(bigHole);
        blocks.add(holder);
        blocks.add(leftSmallSpike);
        blocks.add(middleSmallSpike);
        blocks.add(rightSmallSpike);
        blocks.add(leftMediumSpike);
        blocks.add(rightMediumSpike);
        blocks.add(leftBigSpike);
        blocks.add(rightBigSpike);

        levels.add(new Level(5, mainCircle, holes, blocks, endRectangle));

    }

    private void loadLevel_6() {
        float smallRadius = 25;
        Circle small1 = new Circle(smallRadius);
        Circle small2 = new Circle(smallRadius);
        Circle small3 = new Circle(smallRadius);
        Circle small4 = new Circle(smallRadius);

        small1.setAffectedByExternalForcesAndFriction(false);
        small2.setAffectedByExternalForcesAndFriction(false);
        small3.setAffectedByExternalForcesAndFriction(false);
        small4.setAffectedByExternalForcesAndFriction(false);

        small1.setLocationX(screenWidth / 2);
        small2.setLocationX(screenWidth - smallRadius);
        small3.setLocationX(screenWidth / 2);
        small4.setLocationX(smallRadius);

        small1.setLocationY(smallRadius);
        small2.setLocationY(screenHeight / 2);
        small3.setLocationY(screenHeight - smallRadius);
        small4.setLocationY(screenHeight / 2);

        float smallVelocityX = 500;
        float time = (screenWidth - 2 * smallRadius) / smallVelocityX;
        float smallVelocityY = (screenHeight - 2 * smallRadius) / time;

        small1.setVelocityX(smallVelocityX);
        small2.setVelocityX(-smallVelocityX);
        small3.setVelocityX(-smallVelocityX);
        small4.setVelocityX(smallVelocityX);

        small1.setVelocityY(smallVelocityY);
        small2.setVelocityY(smallVelocityY);
        small3.setVelocityY(-smallVelocityY);
        small4.setVelocityY(-smallVelocityY);

        Level level5 = getLevel(5);
        Circle mainCircle = level5.getMainCircle();
        Rectangle endRectangle = level5.getEndRectangle();
        ArrayList<Circle> holes = new ArrayList<>();
        for (Circle hole : level5.getHoles())
            holes.add(hole);
        holes.add(small1);
        holes.add(small2);
        holes.add(small3);
        holes.add(small4);
        ArrayList<Rectangle> blocks = level5.getBlocks();

        levels.add(new Level(6, mainCircle, holes, blocks, endRectangle));
    }


}