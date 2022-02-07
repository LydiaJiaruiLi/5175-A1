package com.example.assignment1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class GameAScoreFragment extends Fragment {

    TextView finalscore_text;
    int GAscore;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bld = getArguments();
        if (bld != null) {
            GAscore = bld.getInt("GAscore");
        } else {GAscore =0;}

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View gameALayout = inflater.inflate(R.layout.fragment_game_a_score, container, false);
        finalscore_text = gameALayout.findViewById(R.id.Score_text);

        finalscore_text.setText(GAscore);
        return gameALayout;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.return_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_gameAScoreFragment_to_secondFragment);
            }
        });

    }
}
