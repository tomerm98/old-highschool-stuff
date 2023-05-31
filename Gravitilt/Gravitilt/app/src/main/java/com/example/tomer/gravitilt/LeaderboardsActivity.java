package com.example.tomer.gravitilt;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class LeaderboardsActivity extends Activity {
    final int SCORES_COUNT = 5;
    TextView[] tvNames = new TextView[SCORES_COUNT];
    TextView[] tvTimes = new TextView[SCORES_COUNT];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);
        initializeTextView();
        resetTextViewsText();
        GetAllScoresTask task = new GetAllScoresTask();
        task.execute();

    }
    private class GetAllScoresTask extends AsyncTask<Object, Object, ArrayList<Score>> {

        @Override
        protected ArrayList<Score> doInBackground(Object... objects) {
            ArrayList<Score> scores = loadScores();
            Collections.sort(scores);
            return scores;
        }

        @Override
        protected void onPostExecute(ArrayList<Score> scores) {
            super.onPostExecute(scores);
            TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
            tvTitle.setText("Top 5 Players");
            setUpTextViewsWithScores(scores);
        }
    }

    private void setUpTextViewsWithScores(ArrayList<Score> scores) {
        int size = scores.size();
        for (int i =0; i<SCORES_COUNT; i++)
        {
            if (size > i)
            {
                String name = scores.get(i).getName();
                String time = String.valueOf(scores.get(i).getTime());
                tvNames[i].setText(name);
                tvTimes[i].setText(time);
            }
        }
    }

    private ArrayList<Score> loadScores() {
        LocalDataStorage lds = new LocalDataStorage(this);
        return lds.getAllScores();
    }


    private void initializeTextView() {
        int resID;
        String tempId;
        for (int i =0; i <tvNames.length; i++)
        {
            tempId = "tvName" + String.valueOf(i+1);
            resID = getResources().getIdentifier(tempId, "id", getPackageName());
            tvNames[i] = (TextView) findViewById(resID);
        }
        for (int i =0; i <tvTimes.length; i++)
        {
            tempId = "tvTime" + String.valueOf(i+1);
            resID = getResources().getIdentifier(tempId, "id", getPackageName());
            tvTimes[i] = (TextView) findViewById(resID);
        }


    }
    private void resetTextViewsText() {
        for (int i =0; i <tvNames.length; i++)
            tvNames[i].setText("");

        for (int i =0; i <tvTimes.length; i++)
            tvTimes[i].setText("");

    }


}