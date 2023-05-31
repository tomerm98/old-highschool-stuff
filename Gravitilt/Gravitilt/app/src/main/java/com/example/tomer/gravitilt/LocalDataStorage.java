package com.example.tomer.gravitilt;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.MessageFormat;
import java.util.ArrayList;

/**
 * Created by Tomer on 23/04/2017.
 */

public class LocalDataStorage implements DataStorage {
    private final String DATABASE_NAME = "ScoresDataBase";
    private final String TABLE_SCORES = "ScoresTable";
    private final String COLUMN_NAMES = "Names";
    private final String COLUMN_TIMES = "Times";
    private final int COLUMN_NAMES_INDEX = 0;
    private final int COLUMN_TIMES_INDEX = 1;
    private SQLiteDatabase database;

    public LocalDataStorage(Context context)
    {
        database = context.openOrCreateDatabase(DATABASE_NAME,Context.MODE_PRIVATE,null);
        String tableQuery =
                MessageFormat.format("CREATE TABLE IF NOT EXISTS {0} ({1} VARCHAR(255), {2} REAL);",
                        TABLE_SCORES,COLUMN_NAMES,COLUMN_TIMES);
        database.execSQL(tableQuery);
    }


    @Override
    public void saveScore(Score score) {
        String name = score.getName();
        String time = String.valueOf(score.getTime());

        String insertQuery =
                MessageFormat.format("INSERT INTO {0} VALUES(''{1}'', {2});",
                        TABLE_SCORES,name,time);
        database.execSQL(insertQuery);

    }

    @Override
    public ArrayList<Score> getAllScores() {

        ArrayList<Score> scores = new ArrayList<>();
        String tempName;
        float tempTime;
        String selectQuery = "SELECT * FROM " + TABLE_SCORES + ";";
        Cursor resultSet = database.rawQuery(selectQuery,null);
        for (int i =0; i< resultSet.getCount(); i++)
        {
            resultSet.moveToPosition(i);
            tempName = resultSet.getString(COLUMN_NAMES_INDEX);
            tempTime = resultSet.getFloat(COLUMN_TIMES_INDEX);
            scores.add(new Score(tempName,tempTime));
        }
        return scores;
    }

    @Override
    public void deleteAllScores() {
        String deleteQuery = "DELETE FROM " + TABLE_SCORES +";";
        database.execSQL(deleteQuery);

    }
}
