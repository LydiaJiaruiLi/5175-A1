package com.example.assignment1;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

/*
    This class is used to manage the SQLite database.
 */
public class ScoreDBHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "scoreDB.db";
    private static final String TABLE_SCORES = "scores";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SCORE = "gameScore";
    private static final String COLUMN_GAMEID = "gameId";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SCORES_TABLE = "CREATE TABLE " +
                TABLE_SCORES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_SCORE
                + " TEXT," + COLUMN_GAMEID + " INTEGER" + ")";
        db.execSQL(CREATE_SCORES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int
            oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);
        onCreate(db);
    }

    // Clear the databse
    public void clearDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        String clearDBQuery = "DELETE FROM "+TABLE_SCORES;
        db.execSQL(clearDBQuery);
    }

    public ScoreDBHandler(Context context,
                       String name,
                       SQLiteDatabase.CursorFactory factory,
                       int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    // Add the score to database
    public void addScore(Score score){
        ContentValues values = new ContentValues();
        values.put(COLUMN_SCORE, score.getGameScore());
        values.put(COLUMN_GAMEID, score.getGameID());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_SCORES, null, values);
        db.close();
    }

    // Delete the score from database by using game id
    public boolean deleteScore(int gameId){
        String query = "DELETE FROM " + TABLE_SCORES + " WHERE " + COLUMN_GAMEID + " = " + gameId;

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            cursor.close();
            return true;
        }
        db.close();
        return false;
    }

    // Find the score by using game id
    public Score findScoreByGameId(int gameId) {
        String query = "SELECT * FROM " + TABLE_SCORES + " WHERE " + COLUMN_GAMEID + " = " + gameId;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Score score = new Score();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            score.setID(Integer.parseInt(cursor.getString(0)));
            score.setGameScore(cursor.getString(1));
            score.setGameID(Integer.parseInt(cursor.getString(2)));
            cursor.close();
        } else {
            score = null;
        }
        db.close();
        return score;
    }

    // Check if the game's score existed by providing the game id
    public boolean isExisted(int gameId){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_SCORES + " WHERE " + COLUMN_GAMEID + " = " + gameId;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        db.close();
        return true;
    }
}
