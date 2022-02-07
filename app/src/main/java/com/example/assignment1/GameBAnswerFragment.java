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
public class GameBAnswerFragment extends Fragment implements View.OnClickListener{
    EditText userInput;

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

    int actualAmount;
    private FragmentManager fm;
    private FragmentTransaction ft;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

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

        view.findViewById(R.id.gameBScoreButton).setOnClickListener(this);
        userInput = (EditText) view.findViewById(R.id.userInput);

        return view;


    }

    @Override
    public void onClick(View view){
        Bundle args = new Bundle();
        if (userInput.getText() != null) {
            args.putInt("userInput", Integer.parseInt(userInput.getText().toString()));
            args.putInt("actualAmount", actualAmount);
        }else{
            args.putInt("userInput", 0);
            args.putInt("actualAmount", actualAmount);
        }

////        newFrg.setArguments(input); //data being send to SecondFragment
//        Intent intent = new Intent(getActivity(), ScoreTrans.class);
//        intent.putExtra("actualAmount", actualAmount);
//        intent.putExtra("userInput", Integer.parseInt(userInput.getText().toString()));
//        startActivity(intent);

        Score score = new Score();
        ScoreDBHandler dbHandler = new ScoreDBHandler(this.getContext(), null, null, 1);
        if (userInput.getText() != null){
            score = new Score(Integer.parseInt(userInput.getText().toString()) + "/" + actualAmount, 1);
        }else{
            score = new Score(0 + "/" + actualAmount, 1);
        }

        if (dbHandler.isExisted(1)){
            dbHandler.deleteScore(1);
            dbHandler.addScore(score);
        }


        Navigation.findNavController(view).navigate(R.id.action_gameBAnswerFragment_to_gameBScoreFragment, args);
    }

}