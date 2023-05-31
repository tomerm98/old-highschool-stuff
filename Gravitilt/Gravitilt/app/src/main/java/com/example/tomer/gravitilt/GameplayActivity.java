package com.example.tomer.gravitilt;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;

public class GameplayActivity extends Activity {
    /*
    *  ----- WORKFLOW: -----
    * in onCreate we initiate variables and sensors and create a new screenView.
    * in onFocusChanged the screenView is fully loaded and we call the launchCurrentGameMode method.
    * this method will call the proper method for the current game mode: startLevelPractice() or startSpeedRun().
    * these methods will load the desired level using loadLevel method and then call startCurrentLevel method.
    * after every call for the screenView's onDraw method (meaning that a frame passed), he will call the
    * onFrameTick method of this activity. The method will check if the user finished the level or
    * was swallowed by an obstacle using the isMainCircleReachedEnd and isMainCircleSwallowed methods,
    * and will then restart the level or finish the level using the finishLevel and restartCurrentLevel methods.
    * The finishLevel method will check the current game mode and will call the finishPracticeLevel method or the
    * finishSpeedRunLevel methods accordingly. These methods will either start the next level using
    * the startNextLevel method or exit the activity.
    *
    * */




    public static ScreenView screenView;
    private static Context context;
    private static Level currentLevel;
    private static final int ROTATION_X_INDEX = 1;
    private static final int ROTATION_Y_INDEX = 0;
    private static final int FIRST_LEVEL_ID = 1;
    private static final int LAST_LEVEL_ID = 5;
    private static final int CHALLENGE_LEVEL_ID = 6;
    private static long speedRunStartTime;
    private static Activity thisActivity;//for methods that need context
    private static String currentGameMode;
    private static Intent receivedIntent;
    private static LevelsArchive levelsArchive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisActivity = this;
        context = this;
        receivedIntent = getIntent();
        currentGameMode = receivedIntent.getExtras().getString(Game.GAME_MODE_EXTRA_NAME);
        screenView = new ScreenView(this);
        setContentView(screenView);
        //prevents screen from sleeping
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        initializeSensor();

        //the game will actually launch on Window Focus Changed (when the screenView is ready)
    }

    private void launchCurrentGameMode() {
        switch (currentGameMode) {
            case Game.GAME_MODE_LEVEL_PRACTICE:
                startLevelPractice();
                break;
            case Game.GAME_MODE_SPEED_RUN:
                startSpeedRun();
                break;
        }
    }

    private void startSpeedRun() {
        speedRunStartTime = System.currentTimeMillis();
        loadLevel(FIRST_LEVEL_ID);
        startCurrentLevel();

    }

    private void startLevelPractice() {
        int id = receivedIntent.getExtras().getInt(Game.LEVEL_ID_EXTRA_NAME);
        loadLevel(id);
        startCurrentLevel();

    }

    public static void onFrameTick() {
        //this method is called after the screenView draw
        if (isMainCircleReachedEnd())
            finishLevel();
        else if (isMainCircleSwallowed())
            restartCurrentLevel();

    }

    private static void finishLevel() {
        screenView.stop();
        switch (currentGameMode) {
            case Game.GAME_MODE_LEVEL_PRACTICE:
                finishPracticeLevel();
                break;
            case Game.GAME_MODE_SPEED_RUN:
                finishSpeedRunLevel();
                break;
        }


    }

    private static void finishSpeedRunLevel() {
        if (currentLevel.getId() == LAST_LEVEL_ID) {
            Intent toFinishedRunIntent = new Intent(context, FinishedRunActivity.class);
            float score = (float) (System.currentTimeMillis() - speedRunStartTime) / 1000;
            toFinishedRunIntent.putExtra(Game.SPEED_RUN_SCORE_EXTRA_NAME, score);
            thisActivity.startActivity(toFinishedRunIntent);
        } else startNextLevel();
    }

    private static void startNextLevel() {
        Game.clear();
        loadLevel(currentLevel.getId() + 1);
        startCurrentLevel();

    }

    private static void finishPracticeLevel() {
        if (currentLevel.getId() == LAST_LEVEL_ID ||
                currentLevel.getId() == CHALLENGE_LEVEL_ID)
            thisActivity.finish();
        else startNextLevel();
    }

    private static boolean isMainCircleReachedEnd() {

        return Game.isEntitySwallowed(currentLevel.getMainCircle(), currentLevel.getEndRectangle());
    }


    private static void restartCurrentLevel() {
        //resets the main circle
        currentLevel.getMainCircle().setLocationX(currentLevel.getSpawnX());
        currentLevel.getMainCircle().setLocationY(currentLevel.getSpawnY());
        currentLevel.getMainCircle().setVelocityX(0);
        currentLevel.getMainCircle().setVelocityY(0);
        currentLevel.getMainCircle().setAccelerationX(0);
        currentLevel.getMainCircle().setAccelerationY(0);
        currentLevel.getMainCircle().setNetForce(new GeoVector());
    }

    private static void startCurrentLevel() {
        Game.clear();
        Game.add(currentLevel.getMainCircle());
        Game.add(currentLevel.getEndRectangle());
        for (Circle hole : currentLevel.getHoles())
            Game.add(hole);
        for (Rectangle block : currentLevel.getBlocks())
            Game.add(block);

        screenView.run();
    }

    private static boolean isMainCircleSwallowed() {
        for (Circle hole : currentLevel.getHoles())
            if (Game.isEntitySwallowed(currentLevel.getMainCircle(), hole))
                return true;
        for (Rectangle block : currentLevel.getBlocks())
            if (Game.isEntitySwallowed(currentLevel.getMainCircle(), block))
                return true;
        return false;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        //when this method is called, screenView is fully initialized
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            float screenWidth = Game.pxToDp(screenView.getWidth(), this);
            float screenHeight = Game.pxToDp(screenView.getHeight(), this);
            levelsArchive = new LevelsArchive(screenWidth, screenHeight);
            launchCurrentGameMode();
        }
    }

    private static void loadLevel(int id) {
        currentLevel = levelsArchive.getLevel(id);
    }


    private static void initializeSensor() {

        SensorManager sensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SensorEventListener sel = initializeSEL();
        sensorManager.registerListener(sel, sensor, SensorManager.SENSOR_DELAY_GAME);

    }

    private static SensorEventListener initializeSEL() {
        SensorEventListener sel = new SensorEventListener() {
            //this method is called whenever the device is rotated
            @Override
            public void onSensorChanged(SensorEvent event) {
                Game.calcGeneralGravity(event.values[ROTATION_X_INDEX], event.values[ROTATION_Y_INDEX]);

            }


            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }

        };
        return sel;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        screenView.stop();
        Game.clear();
    }

    @Override
    protected void onStop() {
        super.onStop();
        screenView.stop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        screenView.run();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}