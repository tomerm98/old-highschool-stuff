package com.example.tomer.gravitilt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FinishedRunActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_run);

        final TextView tvTime = (TextView) findViewById(R.id.tvTime);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        final LocalDataStorage lds = new LocalDataStorage(this);

        Intent receivedIntent = getIntent();
        //gets the time value from the intent and displays it
        final float time = receivedIntent.getExtras().getFloat(Game.SPEED_RUN_SCORE_EXTRA_NAME);
        tvTime.setText(String.valueOf(time));



        //this will make make the button clickable only if the name string is not empty
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (etName.getText().length() > 0)
                    btnSubmit.setEnabled(true);
                else btnSubmit.setEnabled(false);
            }
        });

        //saves a new score and returns to mainActivity
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                Score score = new Score(name, time);
                lds.saveScore(score);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }

    //returns to mainActivity instead of gameplayActivity when the users clicks the back button
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}