package com.example.tomer.gravitilt;

import java.util.ArrayList;

/**
 * Created by Tomer on 23/04/2017.
 */

public interface DataStorage {
    //an interface for storing a list of scores somewhere
    void saveScore(Score score);
    ArrayList<Score> getAllScores();
    void deleteAllScores();
}