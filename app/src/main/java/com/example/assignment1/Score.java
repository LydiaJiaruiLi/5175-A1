package com.example.assignment1;

/*
    This class is used to manage the scores.
 */
public class Score {
    private int _id; // the id of the score
    private String _gameScore;
    private int _gameId; // to identify the game, 1 is represented as game B and 2 is represented as game A

    public Score(){
    }

    public Score(int id, String gameScore, int gameId){
        this._id = id;
        this._gameScore = gameScore;
        this._gameId = gameId;
    }

    public Score(String gameScore, int gameId) {
        this._gameScore = gameScore;
        this._gameId = gameId;
    }

    // This function allows setting id for the score
    public void setID(int id){
        this._id = id;
    }

    // Get the id of the score
    public int getID(){
        return this._id;
    }

    // Set the score
    public void setGameScore(String gameScore){
        this._gameScore = gameScore;
    }

    // Get the score
    public String getGameScore(){
        return this._gameScore;
    }

    // Set the game id
    public void setGameID(int gameId){
        this._gameId = gameId;
    }

    // Get the game id
    public int getGameID(){
        return this._gameId;
    }
}
