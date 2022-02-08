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
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

/*
    This class is used to manage the game A's fragment, which displays random 5 questions in 5 screen
    with different background colors. In this fragment, the user is allowed to select a answer for
    each question. And it will move to next question if "NEXT" button is clicked. After displayed 5
    questions, it will move to a new page.
 */
public class GameAFragment extends Fragment {
    TextView showQuestionTextView;
    RadioButton showselect1;
    RadioButton showselect2;
    RadioButton showselect3;
    RadioButton showselect4;
    RadioGroup radgroup;
    int count = 0;
    ArrayList numlist = new ArrayList();

    private ArrayList<String> colorlst = new ArrayList(Arrays.asList("#A11111", "#B2A11111", "#95FF5722", "#C4FF5722", "#FFFF5722"));

    // Get the map of questions from class QuestionStorage
    QuestionStorage questionStorage = new QuestionStorage();
    Map<Integer, Question> qList = questionStorage.getQuestionList();

    private void loadquestion(View view) {
        if (count >= 5) {
            return;
        }

        Random rand = new Random();
        int randInt = 0;
        boolean[] bool = new boolean[6];

        do {
            randInt = rand.nextInt(6);
        } while (bool[randInt]);
        bool[randInt] = true;
        numlist.add(randInt);

        // random select quesiton
        String question_text = String.format("%s. %s", (count + 1), qList.get(numlist.get(count)).getQuestion());
        showQuestionTextView.setText(question_text);

        // Get the corresponding choices of the generated question
        List<String> answers = qList.get(numlist.get(count)).getAnswers();

        String select1 = answers.get(0);
        showselect1.setText(select1);
        String select2 = answers.get(1);
        showselect2.setText(select2);
        String select3 = answers.get(2);
        showselect3.setText(select3);
        String select4 = answers.get(3);
        showselect4.setText(select4);

        // set backgroud color
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

    String input = ""; // the index of user selected
    String questionId = ""; // the ids of generated questions
    public void onViewCreated(@NonNull View view1, Bundle savedInstanceState) {
        super.onViewCreated(view1, savedInstanceState);
        view1.findViewById(R.id.Nextq_button).setVisibility(View.VISIBLE);
        view1.findViewById(R.id.gotoscorebutton).setVisibility(View.GONE);

        view1.findViewById(R.id.Nextq_button).setOnClickListener(new View.OnClickListener() {
                 public void onClick(View view) {
                     // Get the index of the user clicked
                     int radioBtns = radgroup.getCheckedRadioButtonId();
                     View radButton = radgroup.findViewById(radioBtns);
                     int idx = radgroup.indexOfChild(radButton);
                     input += idx + " ";

                     if (count <= 4) {
                         count++;
                         loadquestion(view1);
                         radgroup.clearCheck();
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
                int radioBtns = radgroup.getCheckedRadioButtonId();
                View radButton = radgroup.findViewById(radioBtns);
                int idx = radgroup.indexOfChild(radButton);
                input += idx;

                for (int i = 0; i < numlist.size(); i++){
                    questionId += numlist.get(i) + " ";
                }

                // send score to score fragment of game A
                Bundle args = new Bundle();
                args.putString("gameAInput", input);
                args.putString("gameAQuestionId", questionId);

                Navigation.findNavController(view).navigate(R.id.action_gameAFragment_to_gameAScoreFragment, args);
            }
        });
    }
}