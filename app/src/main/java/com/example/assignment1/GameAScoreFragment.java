package com.example.assignment1;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.sql.ResultSet;
import java.util.Map;

/*
    This class is used to manage the content of game A's score fragment. In this fragment, it shows
    the score of game A and each choice the user selected for each question. If the user selected a
    wrong choice, the choice will be highlighted. And a "BACK TO HOME" button is used to jump to the
    home page if clicked.
 */
public class GameAScoreFragment extends Fragment {
    String gameAInput;
    String gameAQuestionId;
    QuestionStorage questionStorage = new QuestionStorage();
    Map<Integer, Question> qList = questionStorage.getQuestionList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View gameALayout = inflater.inflate(R.layout.fragment_game_a_score, container, false);

        Bundle input = getArguments();
        if (input != null) {
            gameAInput = input.getString("gameAInput");
            gameAQuestionId = input.getString("gameAQuestionId");
        }

        return gameALayout;
    }

    int finalScore = 0;
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] inputStr = gameAInput.split(" ");
        String[] questionIdStr = gameAQuestionId.split(" ");

        // Calculate the game A's score
        for (int i = 0; i < 5; i++) {
            if (Integer.parseInt(String.valueOf(inputStr[i])) == qList.get(Integer.parseInt(String.valueOf(questionIdStr[i]))).getResult()){
                finalScore++;
            }
        }

        // Display the content of the first question and the choice user selected
        TextView question1Text = view.findViewById(R.id.question1Title);
        question1Text.setText("Question 1. " + qList.get(Integer.parseInt(String.valueOf(questionIdStr[0]))).getQuestion());
        TextView question1Result = view.findViewById(R.id.question1Result);
        question1Result.setText("Your Choice: " + qList.get(Integer.parseInt(String.valueOf(questionIdStr[0]))).getAnswersByInt(Integer.parseInt(String.valueOf(inputStr[0]))));
        checkTheAnswer(question1Result, Integer.parseInt(String.valueOf(inputStr[0])), qList.get(Integer.parseInt(String.valueOf(questionIdStr[0]))).getResult());

        // Display the content of the second question and the choice user selected
        TextView question2Text = view.findViewById(R.id.question2Title);
        question2Text.setText("Question 2. " + qList.get(Integer.parseInt(String.valueOf(questionIdStr[1]))).getQuestion());
        TextView question2Result = view.findViewById(R.id.question2Result);
        question2Result.setText("Your Choice: " + qList.get(Integer.parseInt(String.valueOf(questionIdStr[1]))).getAnswersByInt(Integer.parseInt(String.valueOf(inputStr[1]))));
        checkTheAnswer(question2Result, Integer.parseInt(String.valueOf(inputStr[1])), qList.get(Integer.parseInt(String.valueOf(questionIdStr[1]))).getResult());

        // Display the content of the third question and the choice user selected
        TextView question3Text = view.findViewById(R.id.question3Title);
        question3Text.setText("Question 3. " + qList.get(Integer.parseInt(String.valueOf(questionIdStr[2]))).getQuestion());
        TextView question3Result = view.findViewById(R.id.question3Result);
        question3Result.setText("Your Choice: " + qList.get(Integer.parseInt(String.valueOf(questionIdStr[2]))).getAnswersByInt(Integer.parseInt(String.valueOf(inputStr[2]))));
        checkTheAnswer(question3Result, Integer.parseInt(String.valueOf(inputStr[2])), qList.get(Integer.parseInt(String.valueOf(questionIdStr[2]))).getResult());

        // Display the content of the fourth question and the choice user selected
        TextView question4Text = view.findViewById(R.id.question4Title);
        question4Text.setText("Question 4. " + qList.get(Integer.parseInt(String.valueOf(questionIdStr[3]))).getQuestion());
        TextView question4Result = view.findViewById(R.id.question4Result);
        question4Result.setText("Your Choice: " + qList.get(Integer.parseInt(String.valueOf(questionIdStr[3]))).getAnswersByInt(Integer.parseInt(String.valueOf(inputStr[3]))));
        checkTheAnswer(question4Result, Integer.parseInt(String.valueOf(inputStr[3])), qList.get(Integer.parseInt(String.valueOf(questionIdStr[3]))).getResult());

        // Display the content of the fifth question and the choice user selected
        TextView question5Text = view.findViewById(R.id.question5Title);
        question5Text.setText("Question 5. " + qList.get(Integer.parseInt(String.valueOf(questionIdStr[4]))).getQuestion());
        TextView question5Result = view.findViewById(R.id.question5Result);
        question5Result.setText("Your Choice: " + qList.get(Integer.parseInt(String.valueOf(questionIdStr[4]))).getAnswersByInt(Integer.parseInt(String.valueOf(inputStr[4]))));
        checkTheAnswer(question5Result, Integer.parseInt(String.valueOf(inputStr[4])), qList.get(Integer.parseInt(String.valueOf(questionIdStr[4]))).getResult());

        // Send the game A's score to database we created
        ScoreDBHandler dbHandler = new ScoreDBHandler(getContext(), null, null, 1);
        Score score = new Score(finalScore + "/" + 5, 2);
        // Overwrite the game A's score
        if (dbHandler.isExisted(2)){
            dbHandler.deleteScore(2);
        }
        dbHandler.addScore(score);

        // Display the score in the screen
        TextView scoreText = view.findViewById(R.id.Score_text);
        scoreText.setText(finalScore + "/" + 5);

        // Add click listener for "BACK TO HOME" button
        view.findViewById(R.id.return_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Move to home page if the button clicked
                Navigation.findNavController(view).navigate(R.id.action_gameAScoreFragment_to_secondFragment);
            }
        });
    }

    // This function is used to highlight the incorrect answer
    public void checkTheAnswer(TextView textview, int userAnswer, int result){
        if (userAnswer != result){
            textview.setBackgroundColor(Color.parseColor("#95FF5722"));
        }
    }

}
