package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

/*
    This class is used to manage the content in score page. In this fragment, it will shows the last
    scores both of game A and game B. Due to the use of SQLite, it will show the last score even if
    the user exits this app.
 */
public class ScoreFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ScoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScoreFragment newInstance(String param1, String param2) {
        ScoreFragment fragment = new ScoreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    String gameBScore; // A string to show the score of game B.
    String gameAScore; // A string to show the score of game A.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // Read the content from database we created.
        ScoreDBHandler dbHandler = new ScoreDBHandler(this.getContext(), null, null, 1);

        // Get the score of game B
        if (dbHandler.isExisted(1)) {
            gameBScore = dbHandler.findScoreByGameId(1).getGameScore();
        }else{
            gameBScore = "0/0";
        }

        // Get the score of game A
        if (dbHandler.isExisted(2)) {
            gameAScore = dbHandler.findScoreByGameId(2).getGameScore();
        }else{
            gameAScore = "0/0";
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_score, container, false);

        // Get the text view of showing the scores of B and game A by using the ids set in fragment_score.xml
        TextView BScoreTextView = view.findViewById(R.id.scoreB);
        TextView AScoreTextView = view.findViewById(R.id.scoreA);

        // Show the scores of game A and game B
        BScoreTextView.setText(gameBScore);
        AScoreTextView.setText(gameAScore);

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
    }
}