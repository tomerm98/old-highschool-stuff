package com.example.tomer.gravitilt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GameModeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);

    }
    public void onClickBtnLevelPractice(View view) {

        startActivity(new Intent(this,LevelSelectActivity.class));
    }
    public void onClickBtnSpeedRun(View view) {
        Intent toGameplayIntent = new Intent(this,GameplayActivity.class);
        //starts the gameplay activity with the speed run extra value
        toGameplayIntent.putExtra(Game.GAME_MODE_EXTRA_NAME,Game.GAME_MODE_SPEED_RUN);
        startActivity(toGameplayIntent);
    }
}