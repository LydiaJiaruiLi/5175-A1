package com.example.assignment1;

import java.util.ArrayList;
import java.util.List;

/*
    This class is used to manage the questions in game A.
 */
public class Question {
    private String _question;
    private List<String> _answers;
    private int _result;

    public Question(){
    }

    public Question(String question, List<String> answers, int result){
        this._question = question;
        this._answers = answers;
        this._result = result;
    }

    // Get the content of the question
    public String getQuestion(){
        return this._question;
    }

    // Get the choices of the question
    public List<String> getAnswers(){
        return this._answers;
    }

    // Get the choice of the question according to the index
    public String getAnswersByInt(int index){
        return this._answers.get(index);
    }

    // Get the index of the question's correct answer
    public int getResult(){
        return this._result;
    }

    // Get the correct question's correct answer
    public String getCorrectAnswer(){
        return this._answers.get(this._result);
    }
}

