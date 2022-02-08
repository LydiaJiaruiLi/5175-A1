package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameBScoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

/*
    This class is used to manage the content of score fragment of game B. In this screen, it will
    display the score of game B and a "BACK TO HOME" button that provides the feature of backing to
    home page for the user.
 */
public class GameBScoreFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GameBScoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameBScoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameBScoreFragment newInstance(String param1, String param2) {
        GameBScoreFragment fragment = new GameBScoreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    int userInput = 0; // the user input
    int actualAmount; // the number of actual number of animals displayed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // Receive the data sent by other fragments
        Bundle input = getArguments();
        if (input != null) {
            userInput = input.getInt("userInput");
            actualAmount = input.getInt("actualAmount");
        }
    }

    String score; // a string to display the score on the screen
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_b_score, container, false);

        // Display the score in the fragment
        TextView  gameBScore = view.findViewById(R.id.GameBScore);
        score = userInput + "/" + actualAmount;
        gameBScore.setText(score);

        // Add click listener for the "BACK TO HOME" button
        view.findViewById(R.id.gameBScoreBackButton).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view){
        // Move to the home page if the "BACK TO HOME" button is clicked
        Navigation.findNavController(view).navigate(R.id.action_gameBScoreFragment_to_secondFragment);
    }
}