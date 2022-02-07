package com.example.assignment1;

public class Score {
    private int _id;
    private String _gameScore;
    private int _gameId; // to identify the game

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

    public void setID(int id){
        this._id = id;
    }

    public int getID(){
        return this._id;
    }

    public void setGameScore(String gameScore){
        this._gameScore = gameScore;
    }

    public String getGameScore(){
        return this._gameScore;
    }

    public void setGameID(int gameId){
        this._gameId = gameId;
    }

    public int getGameID(){
        return this._gameId;
    }
}
