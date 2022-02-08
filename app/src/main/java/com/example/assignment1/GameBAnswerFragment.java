package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameBAnswerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

/*
    This class is used to manage the content of fragment which asks the user how many animals has
    has been seen in the last screen. In this fragment, it displays a message "How many animals did
    you see?", and a underline that provides a input when it is clicked. And if click the "OK"
    button on the screen, it will move to next fragment. Entering nothing is provided in this case,
    if the user did this, the score will be 0.
 */
public class GameBAnswerFragment extends Fragment implements View.OnClickListener{
    EditText userInput; // the user input

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GameBAnswerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameBAnswerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameBAnswerFragment newInstance(String param1, String param2) {
        GameBAnswerFragment fragment = new GameBAnswerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    int actualAmount; // the number of animals has been shown in the last screen
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // Receive the data sent (the number of actual images shown) from other fragments
        Bundle actual = getArguments();
        if (actual != null) {
            actualAmount = actual.getInt("actualAmount");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_b_answer, container, false);

        // Add a listener on the "OK" button
        view.findViewById(R.id.gameBScoreButton).setOnClickListener(this);
        // Get the content of user entered
        userInput = (EditText) view.findViewById(R.id.userInput);

        return view;
    }

    @Override
    public void onClick(View view){
        // Send the user input and the actual number of animals shown to the next fragment that shows the score of game B
        Bundle args = new Bundle();
        if (userInput.getText() != null) {
            args.putInt("userInput", Integer.parseInt(userInput.getText().toString()));
            args.putInt("actualAmount", actualAmount);
        }else{
            // The user input is set to 0 if nothing input provided
            args.putInt("userInput", 0);
            args.putInt("actualAmount", actualAmount);
        }

        // Send the score to database
        Score score = new Score();
        ScoreDBHandler dbHandler = new ScoreDBHandler(this.getContext(), null, null, 1);
        if (userInput.getText() != null){
            score = new Score(Integer.parseInt(userInput.getText().toString()) + "/" + actualAmount, 1);
        }else{
            score = new Score(0 + "/" + actualAmount, 1);
        }

        // Overwrite the game B's score
        if (dbHandler.isExisted(1)){
            dbHandler.deleteScore(1);
        }
        dbHandler.addScore(score);

        // Move to next fragment
        Navigation.findNavController(view).navigate(R.id.action_gameBAnswerFragment_to_gameBScoreFragment, args);
    }
}