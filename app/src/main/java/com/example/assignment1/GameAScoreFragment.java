package com.example.assignment1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameAScoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameAScoreFragment extends Fragment {
    int gameAScore = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View gameALayout = inflater.inflate(R.layout.fragment_game_a_score, container, false);

        Bundle input = getArguments();
        if (input != null) {
            gameAScore = input.getInt("gameAScore");
        }

        return gameALayout;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView score = view.findViewById(R.id.Score_text);

        score.setText(gameAScore + "/" + 5);

        view.findViewById(R.id.return_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Navigation.findNavController(view).navigate(R.id.action_gameAFragmentScore_to_secondFragment);
            }
        });

    }
}
