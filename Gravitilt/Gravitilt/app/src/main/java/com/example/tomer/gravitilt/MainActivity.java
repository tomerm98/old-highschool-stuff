package com.example.tomer.gravitilt;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    public final static int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handlePhoneCallPermission();

    }

    private void handlePhoneCallPermission() {
        // this method will request permission to handle phone call events
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
        }
    }




    public void onClickBtnPlayEvent(View view) {
        startActivity(new Intent(this,GameModeActivity.class));
    }

    public void onClickBtnLeaderboardEvent(View view) {
        startActivity(new Intent(this,LeaderboardsActivity.class));
    }

    public void onClickBtnSettingsEvent(View view) {
        startActivity(new Intent(this,SettingsActivity.class));
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }
}
