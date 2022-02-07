package com.example.assignment1;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameAFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class GameAFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    TextView showQuestionTextView;
    RadioButton showselect1;
    RadioButton showselect2;
    RadioButton showselect3;
    RadioButton showselect4;
    RadioGroup radgroup;
    int finalScore = 0;
    int count = 0;
    ArrayList numlist = new ArrayList();

    //    generate question set
    private ArrayList<String> Q1 = new ArrayList(Arrays.asList("What is moon?", "A Satellite", "A Star", "A Planet", "A Meteor", 1));
    private ArrayList<String> Q2 = new ArrayList(Arrays.asList("What is tulip?", "A type of food", "An animal", "A flower", "A Star", 3));
    private ArrayList<String> Q3 = new ArrayList(Arrays.asList("45+65=?", "115", "100", "120", "110", 4));
    private ArrayList<String> Q4 = new ArrayList(Arrays.asList("Where do swans live?", "In dessert", "In mountains", "In lakes", "In forest", 3));
    private ArrayList<String> Q5 = new ArrayList(Arrays.asList("Where is China?", "Europe", "Asia", "Africa", "North America", 2));
    private ArrayList<String> Q6 = new ArrayList(Arrays.asList("45*5=?", "225", "215", "235", "245", 1));
    private Map<Integer, List> qList = Map.of(0, Q1, 1, Q2, 2, Q3, 3, Q4, 4, Q5,5,Q6);
    private ArrayList<String> colorlst = new ArrayList(Arrays.asList("#A11111", "#B2A11111", "#95FF5722", "#C4FF5722", "#FFFF5722"));

    private void loadquestion(View view) {
        if (count >= 5) {
            return;
        }

        Random rand = new Random();
        int randInt = 0;
        boolean[] bool = new boolean[6];
        for (int i = 0; i < 5; i++) {
            do {
                randInt = rand.nextInt(6);
            } while (bool[randInt]);
            bool[randInt] = true;
            numlist.add(randInt);
        }
        //        random select quesiton
        String question_text = String.format("%s. %s", (count + 1), qList.get(numlist.get(count)).get(0));
        showQuestionTextView.setText(question_text);

        String select1 = qList.get(numlist.get(count)).get(1).toString();
        showselect1.setText(select1);
        String select2 = qList.get(numlist.get(count)).get(2).toString();
        showselect2.setText(select2);
        String select3 = qList.get(numlist.get(count)).get(3).toString();
        showselect3.setText(select3);
        String select4 = qList.get(numlist.get(count)).get(4).toString();
        showselect4.setText(select4);
        //      set backgroud color
        String color = colorlst.get(count);
        view.setBackgroundColor(Color.parseColor(color));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View first_questionLayout = inflater.inflate(R.layout.fragment_game_a, container, false);

        showQuestionTextView = first_questionLayout.findViewById(R.id.Question_text);
        showselect1 = first_questionLayout.findViewById(R.id.Select1);
        showselect2 = first_questionLayout.findViewById(R.id.Select2);
        showselect3 = first_questionLayout.findViewById(R.id.Select3);
        showselect4 = first_questionLayout.findViewById(R.id.Select4);
        loadquestion(first_questionLayout);
        radgroup = first_questionLayout.findViewById(R.id.main_radiogroup);

        return first_questionLayout;

    }

    public void onViewCreated(@NonNull View view1, Bundle savedInstanceState) {
        super.onViewCreated(view1, savedInstanceState);
        view1.findViewById(R.id.Nextq_button).setVisibility(View.VISIBLE);
        view1.findViewById(R.id.gotoscorebutton).setVisibility(View.GONE);

        view1.findViewById(R.id.Nextq_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int radioBtns = radgroup.getCheckedRadioButtonId();
                if (radioBtns == Integer.parseInt(String.valueOf(qList.get(numlist.get(count)).get(5)))) {
                    finalScore++;
                }
                if (count <= 4) {
                    count++;
                    loadquestion(view1);

                }
                if (count == 4)
                    {

                    view1.findViewById(R.id.Nextq_button).setVisibility(View.GONE);
                    view1.findViewById(R.id.gotoscorebutton).setVisibility(View.VISIBLE);

                }
            }
        }
        );
        view1.findViewById(R.id.gotoscorebutton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                System.out.println(finalScore);
                    // send score to score fragment of game A
                Bundle args = new Bundle();
                args.putInt("gameAScore", finalScore);

                Score score = new Score();
                ScoreDBHandler dbHandler = new ScoreDBHandler(getContext(), null, null, 1);
                score = new Score(finalScore + "/" + 5, 2);

                if (dbHandler.isExisted(2)){
                    dbHandler.deleteScore(2);
                    }
                dbHandler.addScore(score);

                Navigation.findNavController(view).navigate(R.id.action_gameAFragment_to_gameAScoreFragment, args);

            }

        });


    }
}