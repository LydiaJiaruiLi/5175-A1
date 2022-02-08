package com.example.assignment1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

/*
    This class is used to manage the second fragment, which has four buttons. If the first button,
    "GAME A" button was clicked, it will start a new game A. The second button, "GAME B" button is
    used to jump to game B. If the user hits The "SHOW SCORE" button, it will move to a screen of
    showing the last scores of game A and game B. And the "BACK TO WELCOME" button is used to go back
    to the welcome page.
 */
public class SecondFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        // Add click listener to each button by searching id which was set in fragment_second.xml.
        view.findViewById(R.id.GameAButton).setOnClickListener(this);
        view.findViewById(R.id.GameBButton).setOnClickListener(this);
        view.findViewById(R.id.ScoreButton).setOnClickListener(this);
        view.findViewById(R.id.BackButton).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view){
        // Move to corresponding fragment when a button is clicked using Navigation.
        switch (view.getId()){
            case R.id.GameAButton:
                // Jump to game A's fragment if the "GAME A" button is clicked.
                Navigation.findNavController(view).navigate(R.id.action_secondFragment_to_gameAFragment);
                break;
            case R.id.GameBButton:
                // Jump to game B's fragment if the "GAME B" button is clicked.
                Navigation.findNavController(view).navigate(R.id.action_secondFragment_to_gameBFragment);
                break;
            case R.id.ScoreButton:
                // Jump to score fragment if the "SHOW SCORE" button is clicked.
                Navigation.findNavController(view).navigate(R.id.action_secondFragment_to_scoreFragment);
                break;
            case R.id.BackButton:
                // Jump to welcome page if the "BACK TO WELCOME" button is clicked.
                Navigation.findNavController(view).navigate(R.id.action_secondFragment_to_firstFragment);
        }

    }

}